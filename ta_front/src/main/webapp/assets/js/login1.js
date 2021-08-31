var userdata = "null"; //로그인 정보 담기는 객체
var nickBoolean; //닉네임 중복여부 저장 변수
$(function () {
	
	//시작시 로그인 여부 확인
	//console.log("userdata :",userdata);
	//console.log("DOM생성");
  var url = "http://localhost:9999/ta_back/member/login";
  $.ajax({
    url: url,
    method: "post",
    data: {socialNo:userdata},
    success: function (responseData) {
      userdata = responseData.member;
   //   console.log("최초로그인확인",userdata);
      console.log(responseData);
      if (responseData.logined == "logined") {
        //console.log(responseData.logined);
        $("div.signup").hide();
        $("#memberinfo").show();
        $("#login_thumb_img").attr("src", responseData.member.member_thumb);
        $("#member_nickname").html(
          responseData.member.member_nickName + " 님 반갑습니다."
        );
        $("#social").hide();
        $("#myBtn").hide();
      }
    },
    xhrFields:{
	 withCredentials:true
    },
  });
  



/*	
  // Get the modal
  var modal = document.getElementById("myModal");

  // Get the button that opens the modal
  var btnmodal = document.getElementById("myBtn");

  // Get the <span> element that closes the modal
  var span11 = document.getElementsByClassName("close")[0];

  // When the user clicks on the button, open the modal
  btnmodal.onclick = function () {
    modal.style.display = "block";
  };

  // When the user clicks on <span> (x), close the modal
  span11.onclick = function () {
    modal.style.display = "none";
  };

  // When the user clicks anywhere outside of the modal, close it
  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  };*/


}); //dom 끝 괄호

//로그인 js시작
function init() {
  gapi.load("auth2", function () {
    //$('div.signup').hide();
    gapi.auth2.init();
    options = new gapi.auth2.SigninOptionsBuilder();
    options.setPrompt("select_account");
    // 추가는 Oauth 승인 권한 추가 후 띄어쓰기 기준으로 추가
    options.setScope(
      "email profile openid https://www.googleapis.com/auth/user.birthday.read"
    );
    // 인스턴스의 함수 호출 - element에 로그인 기능 추가
    // GgCustomLogin은 li태그안에 있는 ID, 위에 설정한 options와 아래 성공,실패시 실행하는 함수들
    gapi.auth2
      .getAuthInstance()
      .attachClickHandler("GgCustomLogin", options, onSignIn, onSignInFailure);
  });
}

function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  var gSocial = profile.US;
  var gEmail = profile.Ht;
  var gThumb = profile.wJ;
  console.log("구글정보=>>",profile);
//  console.log(profile.mS);

  var url = "http://localhost:9999/ta_back/member/login";
  //서버로 AJAX 요청, 응답
  $.ajax({
    url: url,
    method: "post",
    data: {
      social_type: "구글",
      socialNo: gSocial,
      email: gEmail,
      thumb: gThumb,
    },
    success: function (responseData) {
     //console.log("구글응답 : ",responseData);
      //console.log(responseData.usercheck);
      if (responseData.usercheck == "non_member") {
        $("#section").load("logininfo.html", function () {
          // console.log(responseData.usercheck);
          $('#modalClose').click();
          $("#myinfodiv").hide();
          $("div.signup").show();
          $("#email").val(gEmail);
          $("#social_type").val("구글");
          $("#social_no").val(gSocial);
          $("#thumb").val(gThumb);
          $("#thumb_img").attr("src", gThumb);
          $("#close").trigger("click");

          $("#nickname").on("keyup", function (e) {
            //console.log("닉체크" + e);
            var data = $("#nickname").val();
            var chkhtml = $("span.signchkNick");
            var url = "http://localhost:9999/ta_back/member/nickname";
            $.ajax({
              url: url,
              method: "post",
              data: { nickName: data },
              success: function (reseponse) {
              //  console.log(reseponse);
                nickBoolean = reseponse.chkNick;
                if (reseponse.chkNick == null) {
                  chkhtml.text("사용 가능한 닉네임");
                  chkhtml.css("color", "blue");
                } else if (reseponse.chkNick == false) {
                  chkhtml.text("사용 불가능 닉네임");
                  chkhtml.css("color", "red");
                }
              },
                  xhrFields:{
	 withCredentials:true
    },
            });
          });
        });
      } else if (responseData.usercheck == "member") {
        userdata = responseData.member;
        logined(responseData);
      }
    },
        xhrFields:{
	 withCredentials:true
    },
    error: function (xhr) {
      alert(xhr.status);
    },
  });
} //구글로그인 끝

function onSignInFailure(t) {
  //console.log("->>",t);
}

/* function renderButton() {
     gapi.signin2.render("my-signin2", {
       scope: "profile email",
       width: 220,
       height: 50,
       longtitle: false,
       theme: "dark",
     });
   }*/
//처음 실행하는 함수
function changeNick() {
	console.log("정보변경");
  var data = userdata;
  var url = "http://localhost:9999/ta_back/member/nickupdate";
  var inputNick = $("#mynickname").val();
  if (data.member_nickName == inputNick) {
    alert("변경 사항이 없습니다.");
  } else if (data.member_nickName != inputNick && nickBoolean == null) {
   
    $.ajax({
      url: url,
      method: "PUT",
      data: {change_nickName: inputNick },
      success: function (reseponse) {
		 alert("변경완료! 다시 로그인 해주세요");
        //alert("변경완료",reseponse);
        window.location.href = "../ta_front/index.html";
      },
          xhrFields:{
	 withCredentials:true
    },
    });
  }
  //window.location.href = "../ta_front/index.html";
}

function logined(responseData) {
  console.log("logined : " ,responseData);
  $("div.signup").hide();
  $("#memberinfo").show();
  $("#login_thumb_img").attr("src", responseData.member.member_thumb);
  $("#member_nickname").html(
    responseData.member.member_nickName + " 님 반갑습니다."
  );
  $("#social").hide();
  $("#myBtn").hide();
  //location.href = "./login.html";
  $("#close").trigger("click");
	window.location.href = "http://localhost:8888/ta_front/index.html";
}

function logout() {
  //$("#myinfodiv").hide();
  console.log("로그아웃");
  var data={data:"1"};
  var url = "http://localhost:9999/ta_back/member/logout";
  $.ajax({
    url: url,
    method: "post",
    data: data,
    //dataType: "json",
    success: function (data) {
	console.log("-->",data);
	userdata="null";
      $("div.signup").hide();
      $("#memberinfo").hide();
      $("#social").show();
      $("#myBtn").show();
      window.location.href = "http://localhost:8888/ta_front/index.html";
    },
      xhrFields:{
	withCredentials:true
},
  });
}

function myinfo() {
  $("#section").load("logininfo.html", function () {
    //$("#myinfodiv").hide();
    //닉네임 중복 함수

    var url = "http://localhost:9999/ta_back/member/login";
    var data = userdata;
    $.ajax({
      url: url,
      method: "post",
      data: { socialNo: data.member_social_no },
      success: function (resposeData) {
        //console.log(resposeData);
        $("div.signup").hide();
        // $("#memberinfo").hide();
        // $("#social").show();

        $("#myemail").text(data.member_email);
        $("#mynickname").val(data.member_nickName);
        $("#mysocial_type").text(data.member_social_type);
        $("#mybirthday").val(data.member_birth);
        $("#mygender").text(data.member_gender);
        //$("#mysocial_no").val(data.member);
        //$("#thumb").val(profile.DJ);
        $("#mythumb_img").attr("src", data.member_thumb);
        $("#myinfodiv").css("display", "block");
      },
          xhrFields:{
	 withCredentials:true
    },
    });

    $("#mynickname").on("keyup", function (e) {
      console.log("닉체크" + e);
      var data = $("#mynickname").val();
      var chkhtml = $("span.chkNick");
      var url = "http://localhost:9999/ta_back/member/nickname";
      $.ajax({
        url: url,
        method: "post",
        data: { nickName: data },
        success: function (reseponse) {
          console.log(reseponse);
          nickBoolean = reseponse.chkNick;
                if (reseponse.chkNick == null) {
                  chkhtml.text("사용 가능한 닉네임");
                  chkhtml.css("color", "blue");
                } else if (reseponse.chkNick == false) {
                  chkhtml.text("사용 불가능 닉네임");
                  chkhtml.css("color", "red");
                }
        },
            xhrFields:{
	 withCredentials:true
    },
      });
    });
  }); //섹션로드 끝
}

function leave() {
  var data = userdata;
  var url = "http://localhost:9999/ta_back/member/leavemember";
  if (confirm("정말 탈퇴 하시겠습니까?") == true) {
    $.ajax({
      url: url,
      method: "post",
      data: { chkLeave: true, leaveNo: data.member_no },
      success: function (reseponse) {
		alert('탈퇴완료 : 이용해주셔서 감사합니다.')
        window.location.href = "../ta_front/index.html";
      },
          xhrFields:{
	 withCredentials:true
    },
    });
  }
}

//======카카오톡 로그인
function unlinkApp() {
  Kakao.API.request({
    url: "/v1/user/unlink",
    success: function (res) {
      alert("success: " + JSON.stringify(res));
    },
    fail: function (err) {
      alert("fail: " + JSON.stringify(err));
    },
  });
}
Kakao.init("cef4a19442da922d3333aab48432a47a");
//console.log("카카오 클라이언트 연결 : " + Kakao.isInitialized());

$("div.signup").hide();
//$("#myinfodiv").hide();

$("body").on("click", "button.sign", function (e) {
	 e.preventDefault();
	jQuery.fn.serializeObject = function() { 
      var obj = null; 
      try { 
          if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) { 
              var arr = this.serializeArray(); 
              if(arr){ obj = {}; 
              jQuery.each(arr, function() { 
                  obj[this.name] = this.value; }); 
              } 
          } 
      }catch(e) { 
          alert(e.message); 
      }finally {} 
      return obj; 
    }
const serializedValues2 = $('#signupfrom').serializeObject()

	
  //폼 태그 전송 ajax
  //var $data = $("#signupfrom").serializeObject();
  console.log(serializedValues2);
 
  var url = "http://localhost:9999/ta_back/member/signup";
  var mem = serializedValues2;
  //console.log(data);
  var nickname = $("#nickname");
  var birthday = $("#birthday");
  //console.log(nickname.val());
  if (
    typeof nickname == "undefined" ||
    nickname == null ||
    nickname.val() == ""
  ) {
    alert("닉네임을 입력해주세요.");
    //console.log("빈값있음");
  } else if (
    typeof birthday == "undefined" ||
    birthday == null ||
    birthday.val() == ""
  ) {
    alert("생년,월 을 입력해주세요.");
  } else{
	   $.ajax({
      url: url,
      method: "post",
	 "headers": {
    "Accept": "application/json, text/plain, */*",
    "Content-Type": "application/json;charset=utf-8"
  },
      dataType:'json',
      data: JSON.stringify(mem),
      success: function (responseData) {
        userdata = responseData;
        console.log("회원가입 응답",responseData);
/*        if(responseData.status ==1){
		alert('가입성공! ');
		window.location.href = "../ta_front/index.html";
}else{
	alert('가입 실패');
	window.location.href = "../ta_front/index.html";
}*/
       if (responseData.usercheck == "non_member") {
          alert("잘못된 접근입니다.");
        } else if (responseData.usercheck == "member") {
          logined(responseData);
        }
      },
          xhrFields:{
	 withCredentials:true
    },
      error: function (xhr) {
        alert(xhr.status);
      },
    });
	
}
///
 return false;
});

Kakao.Auth.createLoginButton({
  container: "#kakao-login-btn",
  success: function (authObj) {
    Kakao.API.request({
      url: "/v2/user/me",
      success: function (result) {
       console.log(result);
        //console.log("result : " + JSON.stringify(result));

        var url = "http://localhost:9999/ta_back/member/login";
        //서버로 AJAX 요청, 응답
        $.ajax({
          url: url,
          method: "post",
          data: {
            social_type: "카카오",
            socialNo: result.id,
            email: result.kakao_account.email,
            thumb: result.kakao_account.profile.profile_image_url,
          } /*id=id1&pwd=p1*/,
          success: function (data) {
            if (data.usercheck == "non_member") {
              $("#section").load("logininfo.html", function () {
				$('#modalClose').click();
                $("#myinfodiv").hide();
                $("div.signup").show();
                $("#email").val(result.kakao_account.email);
                $("#social_type").val("카카오");
                $("#social_no").val(result.id);
                $("#thumb").val(result.properties.profile_image);
                $("#thumb_img").attr(
                  "src",
                  result.properties.profile_image
                );
                $("#close").trigger("click");

                $("#nickname").on("keyup", function (e) {
//                  console.log("닉체크" + e);
                  var data = $("#nickname").val();
                  var chkhtml = $("span.signchkNick");
                  var url = "http://localhost:9999/ta_back/member/nickname";
                  $.ajax({
                    url: url,
                    method: "post",
                    data: { nickName: data },
                    success: function (reseponse) {
                      //console.log(reseponse);
                      nickBoolean = reseponse.chkNick;
                      if (reseponse.chkNick == null) {
                        chkhtml.text("사용 가능한 닉네임");
                        chkhtml.css("color", "blue");
                      } else if (reseponse.chkNick == false) {
                        chkhtml.text("사용 불가능 닉네임");
                        chkhtml.css("color", "red");
                      }
                    },
                  });
                });
              });
            } else if (data.usercheck == "member") {
              userdata = data;
              logined(data);
            }
          },
              xhrFields:{
	 withCredentials:true
    },
          error: function (xhr) {
            alert(xhr.status);
          },
        });
      },
      fail: function (error) {
        alert(
          "login success, but failed to request user information: " +
            JSON.stringify(error)
        );
      },
    });
  },
  fail: function (err) {
    alert("failed to login: " + JSON.stringify(err));
  },
});
