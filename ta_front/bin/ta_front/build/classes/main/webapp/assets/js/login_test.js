var userdata = "null"; //로그인 정보 담기는 객체
var nickBoolean; //닉네임 중복여부 저장 변수
$(function () {
	
	//시작시 로그인 여부 확인
	console.log("userdata :",userdata);
	console.log("DOM생성");
  var url = "http://localhost:9999/ta_back/member/login";
  $.ajax({
    url: url,
    method: "post",
    data: {socialNo:"1775421132"},
    success: function (responseData) {
      userdata = responseData.member;
      console.log("최초로그인확인",userdata);
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
  };


}); //dom 끝 괄호




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
	window.location.href = "../ta_front/index.html";
}

function logout() {
  //$("#myinfodiv").hide();
  console.log("로그아웃");
  var logininfo = localStorage.getItem("logininfo");
  var data={data:"1",logininfo:logininfo};
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
      //window.location.href = "../ta_front/index.html";
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


