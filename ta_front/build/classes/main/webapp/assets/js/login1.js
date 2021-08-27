var userdata = ""; //로그인 정보 담기는 객체
var nickBoolean; //닉네임 중복여부 저장 변수
$(function () {
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

  //시작시 로그인 여부 확인

  var url = "../ta_back/login";
  $.ajax({
    url: url,
    method: "post",
    data: {},
    success: function (responseData) {
      userdata = responseData;
      //console.log(responseData.member);
      //console.log(responseData.usercheck);
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
  });
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
  var gSocial = profile.mS;
  var gEmail = profile.Et;
  var gThumb = profile.hJ;
//  console.log(profile);
//  console.log(profile.mS);

  var url = "../ta_back/login";
  //서버로 AJAX 요청, 응답
  $.ajax({
    url: url,
    method: "post",
    data: {
      social_type: "구글",
      social_no: gSocial,
      email: gEmail,
      thumb: gThumb,
    },
    success: function (responseData) {
      //console.log(responseData);
      //console.log(responseData.usercheck);
      if (responseData.usercheck == "non_member") {
        $("#section").load("logininfo.html", function () {
          // console.log(responseData.usercheck);
          $("#myinfodiv").hide();
          $("div.signup").show();
          $("#email").val(gEmail);
          $("#social_type").val("구글");
          $("#social_no").val(gSocial);
          $("#thumb").val(gThumb);
          $("#thumb_img").attr("src", gThumb);
          $("#close").trigger("click");

          $("#nickname").on("keyup", function (e) {
            console.log("닉체크" + e);
            var data = $("#nickname").val();
            var chkhtml = $("span.signchkNick");
            var url = "../ta_back/nickname";
            $.ajax({
              url: url,
              method: "post",
              data: { nickName: data },
              success: function (reseponse) {
                //console.log(reseponse);
                nickBoolean = reseponse.chkNick;
                if (reseponse.chkNick == false) {
                  chkhtml.text("사용 가능한 닉네임");
                  chkhtml.css("color", "blue");
                } else if (reseponse.chkNick == true) {
                  chkhtml.text("사용 불가능 닉네임");
                  chkhtml.css("color", "red");
                }
              },
            });
          });
        });
      } else if (responseData.usercheck == "member") {
        userdata = responseData;
        logined(responseData);
      }
    },
    error: function (xhr) {
      alert(xhr.status);
    },
  });
} //구글로그인 끝

function onSignInFailure(t) {
  console.log(t);
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
  var data = userdata.member;
  var url = "../ta_back/nickname";
  var inputNick = $("#mynickname").val();
  if (data.member_nickName == inputNick) {
    alert("변경 사항이 없습니다.");
  } else if (data.member_nickName != inputNick && nickBoolean == false) {
    alert("변경완료! 다시 로그인 해주세요");
    $.ajax({
      url: url,
      method: "post",
      data: { nickUpdate: true, changeNick: inputNick },
      success: function (reseponse) {
        //alert("변경완료");
        //window.location.href = "../ta_front/index.html";
      },
    });
  }
  window.location.href = "../ta_front/index.html";
}

function logined(responseData) {
  console.log("logined : " + responseData);
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
	location.reload();
}

function logout() {
  //$("#myinfodiv").hide();
  var data;
  var url = "../ta_back/logout";
  $.ajax({
    url: url,
    method: "post",
    data: data,
    //dataType: "json",
    success: function (data) {
      $("div.signup").hide();
      $("#memberinfo").hide();
      $("#social").show();
      $("#myBtn").show();
      window.location.href = "../ta_front/index.html";
    },
  });
}

function myinfo() {
  $("#section").load("logininfo.html", function () {
    //$("#myinfodiv").hide();
    //닉네임 중복 함수

    var url = "../ta_back/login";
    var data = userdata.member;
    $.ajax({
      url: url,
      method: "post",
      data: { social_no: data.member_social_no },
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
    });

    $("#mynickname").on("keyup", function (e) {
      console.log("닉체크" + e);
      var data = $("#mynickname").val();
      var chkhtml = $("span.chkNick");
      var url = "../ta_back/nickname";
      $.ajax({
        url: url,
        method: "post",
        data: { nickName: data },
        success: function (reseponse) {
          //console.log(reseponse);
          nickBoolean = reseponse.chkNick;
          if (reseponse.chkNick == false) {
            chkhtml.text("사용 가능한 닉네임");
            chkhtml.css("color", "blue");
          } else if (reseponse.chkNick == true) {
            chkhtml.text("사용 불가능 닉네임");
            chkhtml.css("color", "red");
          }
        },
      });
    });
  }); //섹션로드 끝
}

function leave() {
  var data = userdata.member;
  var url = "../ta_back/leavemember";
  if (confirm("정말 탈퇴 하시겠습니까?") == true) {
    $.ajax({
      url: url,
      method: "post",
      data: { chkLeave: true, leaveNo: data.member_no },
      success: function (reseponse) {
        window.location.href = "../ta_front/index.html";
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

$("body").on("click", "button.sign", function () {
  //폼 태그 전송 ajax
  var $data = $("#signupfrom").serialize();
  var url = "../ta_back/signup";
  var data = $data;
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
  } else
    $.ajax({
      url: url,
      method: "post",
      data: data,
      //dataType: "json",
      success: function (responseData) {
        userdata = responseData;
        //console.log(responseData);
        if (responseData.usercheck == "non_member") {
          alert("잘못된 접근입니다.");
        } else if (responseData.usercheck == "member") {
          logined(responseData);
        }
      },
      error: function (xhr) {
        alert(xhr.status);
      },
    });
  return false;
});

Kakao.Auth.createLoginButton({
  container: "#kakao-login-btn",
  success: function (authObj) {
    Kakao.API.request({
      url: "/v2/user/me",
      success: function (result) {
        //console.log("result : " + JSON.stringify(result));

        var url = "../ta_back/login";
        //서버로 AJAX 요청, 응답
        $.ajax({
          url: url,
          method: "post",
          data: {
            social_type: "카카오",
            social_no: result.id,
            email: result.kakao_account.email,
            thumb: result.kakao_account.profile.profile_image_url,
          } /*id=id1&pwd=p1*/,
          success: function (data) {
            if (data.usercheck == "non_member") {
              $("#section").load("logininfo.html", function () {
                $("#myinfodiv").hide();
                $("div.signup").show();
                $("#email").val(result.kakao_account.email);
                $("#social_type").val("카카오");
                $("#social_no").val(result.id);
                $("#thumb").val(result.kakao_account.profile.profile_image_url);
                $("#thumb_img").attr(
                  "src",
                  result.kakao_account.profile.profile_image_url
                );
                $("#close").trigger("click");

                $("#nickname").on("keyup", function (e) {
                  console.log("닉체크" + e);
                  var data = $("#nickname").val();
                  var chkhtml = $("span.signchkNick");
                  var url = "../ta_back/nickname";
                  $.ajax({
                    url: url,
                    method: "post",
                    data: { nickName: data },
                    success: function (reseponse) {
                      //console.log(reseponse);
                      nickBoolean = reseponse.chkNick;
                      if (reseponse.chkNick == false) {
                        chkhtml.text("사용 가능한 닉네임");
                        chkhtml.css("color", "blue");
                      } else if (reseponse.chkNick == true) {
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
