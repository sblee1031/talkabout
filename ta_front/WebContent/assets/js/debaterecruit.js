    var totalData;    // 총 데이터 수
    var dataPerPage = 5;    // 한 페이지에 나타낼 데이터 수
    var pageCount = 5;        // 한 화면에 나타낼 페이지 수
    
    function paging(totalData, dataPerPage, pageCount, currentPage){
        
        //console.log("currentPage : " + currentPage);
        
        var totalPage = Math.ceil(totalData/dataPerPage);    // 총 페이지 수
		//console.log('토탈페이지 :'+totalPage);
        var pageGroup = Math.ceil(currentPage/pageCount);    // 페이지 그룹
        
       // console.log("pageGroup : " + pageGroup);
        
        var last = pageGroup * pageCount;    // 화면에 보여질 마지막 페이지 번호
        if(last > totalPage)
            last = totalPage;
        var first = last - (pageCount-1);    // 화면에 보여질 첫번째 페이지 번호
		if(first<1)
		first = 1;
        var next = last+1;
        var prev = first-1;
        
        //console.log("last : " + last);
       // console.log("first : " + first);
        //console.log("next : " + next);
      //  console.log("prev : " + prev);
 
        var $pingingView = $("#paging");
        
        var html = "";
        
        if(prev > 0)
            html += "<a href=# id='prev'><</a> ";
        
        for(var i=first; i <= last; i++){
            html += "<a href='#' id=" + i + ">" + i + "</a> ";
        }
        
        if(last < totalPage)
            html += "<a href=# id='next'>></a>";
        
        $("#paging").html(html);    // 페이지 목록 생성
        $("#paging a").css("color", "black");
        $("#paging a#" + currentPage).css({"text-decoration":"none", 
                                           "color":"red", 
                                           "font-weight":"bold"});    // 현재 페이지 표시
                                           
        $("#paging a").click(function(){
            
            var $item = $(this);
            var $id = $item.attr("id");
            var selectedPage = $item.text();
            
            if($id == "next")    selectedPage = next;
            if($id == "prev")    selectedPage = prev;
            
            paging(totalData, dataPerPage, pageCount, selectedPage);

			$('#debateWrite').hide();
			//$('#debateContents').empty();
			$('#debateList').show();
			
		  var url = "../ta_back/debrecruit";
		  var method = "listall";
		  $.ajax({
		    url: url,
		    method: "get",
		    data: { method: method,
			page: selectedPage,
			pagesize: dataPerPage,},
		    success: function (resposeData) {
		      console.log(resposeData);
		      var lists = resposeData.debatelist;
		      var mlists = resposeData.memberinfo;
		      var listsize = lists.length;
		     // var divtext = "";
		      var trContent = $("#tablehead").clone();
		      var trDebates = "";
		      $(lists).each(function (list_i, list) {
		        trDebates += '<tr class="' + list.debate_no + '">';
		        trDebates += '<td class="debate_no">' + list.debate_no + "</td>";
		        trDebates +=
		          '<td class="' +
		          list.debate_no +
		          '" id="debate_no"><a class="atitle" href="#">' +
		          list.debate_topic +
		          "</a></td>";
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
		      		      $('#debateList').html(trContent);
		 				$('#debateList').append(trDebates);
		    },
		  });

        });// $("#paging a") 끝.
                                           
    }


var userdata; //로그인 정보 담기는 객체
var page;
$(function () {
  var url = "../ta_back/login";
  $.ajax({
    url: url,
    method: "post",
    data: {},
    success: function (responseData) {
      $("#debateWrite").hide();
      $("#divDebateView").hide();
	$('#btnDiscussor1').hide();
	$('#btnDiscussor2').hide();
	$('#btnCacleDiscussor1').hide();
	$('#btnCacleDiscussor2').hide();
	$('#btnDeleteDebate').hide();
	$('#btnModify').hide();
	$('#btnModifyDebate').hide();
	
	
      userdata = responseData;
		console.log(userdata);
      //console.log(responseData.usercheck);
      if (responseData.logined == "logined") {//로그인 여부 확인
        //console.log(responseData.logined);
        $("#write").show();
		
      } else {//로그인 안된 상태
        //console.log("회원정보없음" + responseData);
        $("#write").hide();
      }
    },
  });

  $(document).on("click", 'a[href="#"]', function (e) {
    e.preventDefault();
  });

  var url = "../ta_back/debrecruit";
  var method = "listall";
  $.ajax({
    url: url,
    method: "get",
    data: { method: method,
			page: 1,
			pagesize: dataPerPage,
			},
    success: function (resposeData) {
	totalData = resposeData.row;
	paging(totalData, dataPerPage, pageCount, 1);
    
  console.log(resposeData);
      var lists = resposeData.debatelist;
      var mlists = resposeData.memberinfo;
      var listsize = lists.length;
     // var divtext = "";
      var trContent = $("#debateList");
      var trDebates = "";
      $(lists).each(function (list_i, list) {
        trDebates += '<tr class="' + list.debate_no + '">';
        trDebates += '<td class="debate_no">' + list.debate_no + "</td>";
        trDebates +=
          '<td class="' +
          list.debate_no +
          '" id="debate_no"><a class="atitle" href="#">' +
          list.debate_topic +
          "</a></td>";
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
    $("#view").remove(); //기존 상세페이지가 열려 있으면 지우기
	$('#btnDiscussor1').hide();
	$('#btnDiscussor2').hide();
	$('#btnCacleDiscussor1').hide();
	$('#btnCacleDiscussor2').hide();
    //게시글 클릭시 토론게시글 보기
    //console.log(this);
    var deb_no = $(this).attr("class");
    var url = "../ta_back/debrecruit";
    var method = "debatedetail";
    var trContent = $("#debateList");
    $.ajax({
      url: url,
      method: "post",
      data: {
        method: method,
        deb_no: deb_no,
      },
      success: function (resposeData) {
        console.log('res'+ resposeData.detail[0]);
        var debate = resposeData.debate;
        var detail = resposeData.detail;
		var dismem = resposeData.memberinfo;
        var addTrObj; //상세페이지 될 객체
		$('#btnDiscussor1').hide();
		console.log(detail);
        $("#spanDebate_no").html(debate.debate_no);
        $("#spanDebate_topic").html(debate.debate_topic);
        $("#spanDebate_date").html(debate.debate_date);
        $("#spanDebate_time").html(debate.debate_time);
        $("#spanDebate_discuss1").html(detail[0].discuss);
        $("#spanDebate_discuss2").html(detail[1].discuss);
		var writer = debate.debate_writer;
		
		if(dismem[0].member_no==0){
			discussor1=0;
		}else{
       var discussor1 = dismem[0].member_nickName;
		}		if(dismem[1].member_no==0){
			discussor2=0;
		}else{
		 var discussor2 = dismem[1].member_nickName;
		}
		if(userdata.logined=="logined" && writer==userdata.member.member_no){
			$('#btnDeleteDebate').show();
			$('#btnModifyDebate').show();
		}else{
			$('#btnDeleteDebate').hide();
		}
        if (discussor1 == 0) {
          discussor1 = "미정";
			if(userdata.logined=="logined" && discussor2 != userdata.member.member_no ){//토론자0 일떄 로그인 한경우
				$('#btnDiscussor1').show();
			}
        }else if(userdata.logined=="logined" &&discussor1 == userdata.member.member_no){
					console.log('토론자같음 : '+userdata.member.member_no);
					$('#btnCacleDiscussor1').show();
					//$('#btnCacleDiscussor2').show();
					$('#btnDiscussor1').hide();
					$('#btnDiscussor2').hide();
				}
        if (discussor2 == 0) {
          discussor2 = "미정";
			if(userdata.logined=="logined" && discussor1 != userdata.member.member_no ){//토론자0 일떄 로그인 한경우
				$('#btnDiscussor2').show();
			}
        }else if(userdata.logined=="logined" && discussor2 == userdata.member.member_no){
					console.log('토론자같음 : '+userdata.member.member_no);
					//$('#btnCacleDiscussor1').show();
					$('#btnCacleDiscussor2').show();
					$('#btnDiscussor1').hide();
					$('#btnDiscussor2').hide();
				}
        $("#spanDebate_discussor1").html(discussor1);
        $("#spanDebate_discussor2").html(discussor2);
        //window.location.href = "../ta_front/debate.html";
        addTrObj = $("#divDebateView").clone();
        addTrObj.show();
        //$('#divDebateView').show();
        $("tr." + deb_no).after('<tr id="view"></tr>');
        $("#view").append(addTrObj);
        //trContent.append(addTrObj);
      },
    });
  });
  var date = getCurrentDate();
//console.log('date' + date);
  $("#inputDebate_date").val(date);
  $("#inputDebate_date").attr("min", date);

}); //DOM 로드 끝

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
function debateWrite(){
	$('#debateList').hide();
	$("#debateWrite").show();
	$('#btnModify').hide();
	$('#paging').hide();
}

function saveDebate() {
	//$('#paging').show();
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
	else{
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
}
function statusChange(deb_no){
	var url = "../ta_back/debatesave";
  var method = "status";
				$.ajax({
		    url: url,
		    method: "post",
		    data: {
		      method: method,
		      debate_no: deb_no,
		    },
		    success: function (resposeData) {
				console.log('대기중으로 변경 완료');
		      window.location.href = "../ta_front/debrecruit.html";
		    },
		  });
}
function statusCancle(deb_no){
	var url = "../ta_back/debatesave";
  var method = "statusCancle";
				$.ajax({
		    url: url,
		    method: "post",
		    data: {
		      method: method,
		      debate_no: deb_no,
		    },
		    success: function (resposeData) {
				console.log('모집중으로 변경 완료');
		      window.location.href = "../ta_front/debrecruit.html";
		    },
		  });
}
function btnDiscuss1() {
  console.log("1번버튼");
  var url = "../ta_back/debatesave";
  var method = "btnDiscuss1";

      if (userdata.logined == "logined") {//로그인 여부 확인
		       // console.log('login됨');
		var deb_no = $('#spanDebate_no').text();
		var discuss1 = $('#spanDebate_discuss1').text();
		var discuss2 = $('#spanDebate_discuss2').text();
		var discussor1 = $('#spanDebate_discussor1').text();
		var discussor2 = $('#spanDebate_discussor2').text();
		var topic = $('#spanDebate_topic').text();
			$.ajax({
		    url: url,
		    method: "post",
		    data: {
		      method: method,
		      debate_no: deb_no,
		      discuss1: discuss1,
		      debate_topic: topic,
		    },
		    success: function (resposeData) {
				if(discussor1!='미정' && discussor2!= '미정'){
					alert(discussor1+' / '+discussor1);
					statusChange(deb_no);
				}else{
			      //alert("1번 토론자 등록완료");
			      window.location.href = "../ta_front/debrecruit.html";
				}
		    },
		  });
      } else {//로그인 안된 상태
        //console.log("회원정보없음" + responseData);
      }

}
function btnDiscuss2() {
    console.log("2번버튼");
  var url = "../ta_back/debatesave";
  var method = "btnDiscuss2";

      if (userdata.logined == "logined") {//로그인 여부 확인
		       // console.log('login됨');
		var deb_no = $('#spanDebate_no').text();
		var discuss1 = $('#spanDebate_discuss1').text();
		var discuss2 = $('#spanDebate_discuss2').text();
		var topic = $('#spanDebate_topic').text();
			$.ajax({
		    url: url,
		    method: "post",
		    data: {
		      method: method,
		      debate_no: deb_no,
		      discuss2: discuss2,
		      debate_topic: topic,
		    },
		    success: function (resposeData) {
			if(discussor1!='미정' && discussor2!= '미정'){
					alert(discussor1+' / '+discussor1);
					statusChange(deb_no);
				}else{
		      //alert("1번 토론자 등록완료");
		      window.location.href = "../ta_front/debrecruit.html";
				}
		    },
		  });
      } else {//로그인 안된 상태
        //console.log("회원정보없음" + responseData);
      }
}

function btnCancleDiscuss1() {
  var url = "../ta_back/debatesave";
  var method = "btnCancleDiscuss1";
      if (userdata.logined == "logined") {//로그인 여부 확인
		       // console.log('login됨');
		var deb_no = $('#spanDebate_no').text();
		var discuss1 = $('#spanDebate_discuss1').text();
		var discuss2 = $('#spanDebate_discuss2').text();
		var topic = $('#spanDebate_topic').text();
			$.ajax({
		    url: url,
		    method: "post",
		    data: {
		      method: method,
		      debate_no: deb_no,
		      discuss1: discuss1,
		      debate_topic: topic,
		    },
		    success: function (resposeData) {
					statusCancle(deb_no);
		      //alert("1번 토론자 등록완료");
		      window.location.href = "../ta_front/debrecruit.html";
		    },
		  });
      } else {//로그인 안된 상태
        console.log("회원정보없음" + responseData);
      }
}

function btnCancleDiscuss2() {
  var url = "../ta_back/debatesave";
  var method = "btnCancleDiscuss2";

      if (userdata.logined == "logined") {//로그인 여부 확인
		       // console.log('login됨');
		var deb_no = $('#spanDebate_no').text();
		var discuss1 = $('#spanDebate_discuss1').text();
		var discuss2 = $('#spanDebate_discuss2').text();
		var topic = $('#spanDebate_topic').text();
			$.ajax({
		    url: url,
		    method: "post",
		    data: {
		      method: method,
		      debate_no: deb_no,
		      discuss2: discuss2,
		      debate_topic: topic,
		    },
		    success: function (resposeData) {
					statusCancle(deb_no);
		      //alert("1번 토론자 등록완료");
		      window.location.href = "../ta_front/debrecruit.html";
		    },
		  });
      } else {//로그인 안된 상태
        //console.log("회원정보없음" + responseData);
      }
}
function btnDeleteDebate(){
	var url = "../ta_back/debatesave";
  	var method = "debateDelete";
      if (userdata.logined == "logined") {//로그인 여부 확인
		       // console.log('login됨');
		var deb_no = $('#spanDebate_no').text();
			$.ajax({
		    url: url,
		    method: "post",
		    data: {
		      method: method,
		      debate_no: deb_no,
		    },
		    success: function (resposeData) {
					//statusCancle(deb_no);
		      alert("주제 삭제 완료");
		      window.location.href = "../ta_front/debrecruit.html";
		    },
		  });
      } else {//로그인 안된 상태
        //console.log("회원정보없음" + responseData);
      }
}
function debateSearch(){
	if($('#searchInput').val()==''){
		alert('검색어를 입력해주세요.');
	}else{
	$('#debateWrite').hide();
	$('#debateList').show();
	$('#paging').hide();
	var url = "../ta_back/debrecruit";
  	var method = "debatesearch";
	var column =$('#column').val();
	var keyword =$('#searchInput').val();
		   // console.log(column+keyword);
		var deb_no = $('#spanDebate_no').text();
			$.ajax({
		    url: url,
		    method: "post",
		    data: {
		      method: method,
			  page: 1,
		      column: column,
		      keyword: keyword,
		    },
		    success: function (resposeData) {
			//console.log(resposeData);
			//$("#debateList").empty();
		      var lists = resposeData.rs;
			 var mlists = resposeData.memberinfo;
			if(lists==0){
				alert('검색결과가 없습니다.');
			}else{
		      var listsize = lists.length;
		      var trContent = $("#tablehead").clone();
		      var trDebates = "";
		      $(lists).each(function (list_i, list) {
		        trDebates += '<tr class="' + list.debate_no + '">';
		        trDebates += '<td class="debate_no">' + list.debate_no + "</td>";
		        trDebates +=
		          '<td class="' +
		          list.debate_no +
		          '" id="debate_no"><a class="atitle" href="#">' +
		          list.debate_topic +
		          "</a></td>";
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
				
		      $('#debateList').html(trContent);
 				$('#debateList').append(trDebates);
				//trContent.show();
				}
		    },
		  });
	}
}

function btnModifyDebate(){
	$('#debateList').hide();
	$('#debateWrite').show();
	$('#btnSaveDebate').hide();
	$('#btnModify').show();
	
	var deb_no = $('#spanDebate_no').text();
	var discuss1 = $('#spanDebate_discuss1').text();
	var discuss2 = $('#spanDebate_discuss2').text();
	var topic = $('#spanDebate_topic').text();
    var date = $("#spanDebate_date").text();
		date =date.replace(" ","T");//date타입 날자 변환
    var time = $("#spanDebate_time").text();
	
  $("#inputDebate_no").val(deb_no);
  $("#inputDebate_topic").val(topic);
  $("#inputDiscuss1").val(discuss1);
  $("#inputDiscuss2").val(discuss2);
  $("#inputDebate_date").val(date);
  $("#selectDebate_time").val(time);

}
function btnModify(){
	var deb_no =  $("#inputDebate_no").val();
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
	else{
	  var url = "../ta_back/debatesave";
	  var method = "debateModify";
	  $.ajax({
	    url: url,
	    method: "post",
	    data: {
	      method: method,
			debate_no : deb_no,
	      debate_topic: (topic+' '),
	      discuss1: discuss1,
	      discuss2: discuss2,
	      debate_date: indate,
	      debate_time: time,
	    },
	    success: function (resposeData) {
			alert('수정완료');
	      window.location.href = "../ta_front/debrecruit.html";
	    },
	  });
	}
}
function goList(){
	$('#debateWrite').hide();
	//$('#debateContents').empty();
	$('#debateList').show();
	$('#paging').show();
	
  var url = "../ta_back/debrecruit";
  var method = "listall";
  $.ajax({
    url: url,
    method: "get",
    data: { method: method,
			page: 1 ,
			pagesize: dataPerPage},
    success: function (resposeData) {
      console.log(resposeData);
      var lists = resposeData.debatelist;
      var mlists = resposeData.memberinfo;
      var listsize = lists.length;
     // var divtext = "";
      var trContent = $("#tablehead").clone();
      var trDebates = "";
      $(lists).each(function (list_i, list) {
        trDebates += '<tr class="' + list.debate_no + '">';
        trDebates += '<td class="debate_no">' + list.debate_no + "</td>";
        trDebates +=
          '<td class="' +
          list.debate_no +
          '" id="debate_no"><a class="atitle" href="#">' +
          list.debate_topic +
          "</a></td>";
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
      		      $('#debateList').html(trContent);
 				$('#debateList').append(trDebates);
    },
  });
	
}
