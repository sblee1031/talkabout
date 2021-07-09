var userdata = ""; //로그인 정보 담기는 객체
$(function () {
  var url = "../ta_back/login";
  $.ajax({
    url: url,
    method: "post",
    data: {},
    success: function (responseData) {
      
      userdata = responseData;
      console.log(userdata);
      //console.log(responseData.usercheck);
      if (responseData.logined == "logined") {
		console.log(userdata.member);
        //console.log(responseData.logined);
        $("#write").show();
      } else {
        //console.log("회원정보없음" + responseData);
        $("#write").hide();
      }
    },
  });

  var url = "../ta_back/debrecruit";
  var method = "listall";
  $.ajax({
    url: url,
    method: "get",
    data: { method: method },
    success: function (resposeData) {
      console.log(resposeData);
      var lists = resposeData.debatelist;
      var mlists = resposeData.memberinfo;
      var listsize = lists.length;
      var divtext = "";
      var trContent = $("#debateList");
      var trDebates = "";
      $(lists).each(function (list_i, list) {
        trDebates += '<tr class="' + list.debate_no + '">';
        trDebates += '<td class="debate_no">' + list.debate_no + "</td>";
        trDebates +=
          '<td class="' +
          list.debate_no +
          '" id="debate_no">' +
          list.debate_topic +
          "</td>";
        trDebates +=
          '<td class="debate_writer">' +
          mlists[list_i].member_nickName +
          "</td>";
        trDebates += '<td class="debate_date">' + list.debate_date + "</td>";
        trDebates += '<td class="debate_time">' + list.debate_time + "</td>";
        trDebates +=
          '<td class="debate_status">' + list.debate_status + "</td>";
        trDebates += "</tr>";
      });
      trContent.append(trDebates);
    },
  });
  $("#debateList").on("click", "#debate_no", function () {
    //게시글 클릭시 토론게시글 보기
    //console.log(this);
    var deb_no = $(this).attr("class");
    var url = "../ta_back/debrecruit";
    var method = "debatedetail";
    $.ajax({
      url: url,
      method: "post",
      data: {
        method: method,
        deb_no: deb_no,
      },
      success: function (resposeData) {
        console.log(resposeData);
        var debate = resposeData.debate;
        var detail = resposeData.detail;
        $("#spanDebate_topic").html(debate.debate_topic);
        $("#spanDebate_date").html(debate.debate_date);
        $("#spanDebate_time").html(debate.debate_time);
        $("#spanDebate_discuss1").html(detail[0].discuss);
        $("#spanDebate_discuss2").html(detail[1].discuss);
        var discussor1 = detail[0].discussor;
        var discussor2 = detail[1].discussor;
        if (discussor1 == 0) {
          discussor1 = "미정";
        }
        if (discussor2 == 0) {
          discussor2 = "미정";
        }
        $("#spanDebate_discussor1").html(discussor1);
        $("#spanDebate_discussor2").html(discussor2);
        //window.location.href = "../ta_front/debate.html";
      },
    });
  });

  function getCurrentDate() {
    //현재시간 구하는 함수
    var date = new Date();
    var year = date.getFullYear().toString();
    var month = date.getMonth() + 1;
    month = month < 10 ? "0" + month.toString() : month.toString();
    var day = date.getDate();
    day = day < 10 ? "0" + day.toString() : day.toString();
    var hour = date.getHours();
    hour = hour < 10 ? "0" + hour.toString() : hour.toString();
    if (Number.parseInt(hour) + 1 == 24) {
      hour = "00";
    }
    var minites = date.getMinutes();
    minites = minites < 10 ? "0" + minites.toString() : minites.toString();
    var t;

    return year + "-" + month + "-" + day + "T" + hour + ":" + minites; // 현재시간보다 1시간 추가 ,최소 시작시간은 한시간 뒤부터 가능.
  }
  var date = getCurrentDate();
  $("#inputDebate_date").val(date);
  $("#inputDebate_date").attr("min", date);
}); //DOM 로드 끝

function saveDebate() {
  var topic = $("#inputDebate_topic").val();
  var discuss1 = $("#inputDiscuss1").val();
  var discuss2 = $("#inputDiscuss2").val();
  var indate = $("#inputDebate_date").val();
  var time = $("#selectDebate_time").val();
  if (typeof topic == "undefined" || topic == null || topic == "") {
    alert("토론주제를 입력해주세요.");
  } else if (
    typeof discuss1 == "undefined" ||
    discuss1 == null ||
    discuss1 == ""
  ) {
    alert("주장1을 입력해주세요.");
  } else if (
    typeof discuss2 == "undefined" ||
    discuss2 == null ||
    discuss2 == ""
  ) {
    alert("주장2를 입력해주세요.");
  }

  var url = "../ta_back/debatesave";
  var method = "debatesave";
  $.ajax({
    url: url,
    method: "post",
    data: {
      method: method,
      debate_topic: topic,
      discuss1: discuss1,
      discuss2: discuss2,
      debate_date: indate,
      debate_time: time,
    },
    success: function (resposeData) {
      window.location.href = "../ta_front/debrecruit.html";
    },
  });
}

function btnDiscuss1() {
  console.log("1번버튼");
  var url = "../ta_back/debatesave";
  var method = "btnDiscuss1";
  $.ajax({
    url: url,
    method: "post",
    data: {
      method: method,
      discuss1: discuss1,
      debate_topic: topic,
    },
    success: function (resposeData) {
      alert("1번 토론자 등록완료");
      window.location.href = "../ta_front/debate.html";
    },
  });
}
function btnDiscuss2() {
  console.log("2번버튼");
  var url = "../ta_back/debatesave";
  var method = "btnDiscuss2";
  $.ajax({
    url: url,
    method: "post",
    data: {
      method: method,
      discuss2: discuss2,
      debate_topic: topic,
    },
    success: function (resposeData) {
      alert("2번 토론자 등록완료");
      window.location.href = "../ta_front/debate.html";
    },
  });
}
