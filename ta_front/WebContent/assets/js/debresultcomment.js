var userdata; //로그인 정보 담기는 객체
$(function () {
	$('#btnComment').hide();
	$('#tableinsert').hide();
	$('#divDebateView').hide();
	$('#tablecommentList').hide();
	$('#insert_btn').hide();
	$('#trinsert').attr('readonly',true);
  var url = "../ta_back/login";
  $.ajax({
    url: url,
    method: "post",
    data: {},
    success: function (responseData) {
	
      userdata = responseData;
		console.log(userdata);
      //console.log(responseData.usercheck);
      if (responseData.logined == "logined") {//로그인 여부 확인
        //console.log(responseData.logined);
        //$("#write").show();
		$('#debcom_writer').val(userdata.member.member_nickName);
		$('#insert_btn').show();
      } else {//로그인 안된 상태
        //console.log("회원정보없음" + responseData);
       // $("#write").hide();
      }
    },
  });

	
	
			//해당댓글번호로 조회
    	//console.log('토론결괄조회');
		var method = 'result';
    	var data = {method: method};
    	 var url = "../ta_back/debatecommentselect";
         $.ajax({
           url: url,
           method: "post",
           data: data,
           //dataType: "json",
           success: function (resposeData) {
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
    $("tr.list").remove(); //기존 상세페이지가 열려 있으면 지우기
	$('#commentList').empty();
	$('#tableinsert').show();
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
       console.log(resposeData);
        var debate = resposeData.debate;
        var detail = resposeData.detail;
		var dismem = resposeData.memberinfo;
        var addTrObj; //상세페이지 될 객체
		$('#btnDiscussor1').hide();
		//console.log(detail);
        $("#spanDebate_no").html(debate.debate_no);
        $("#spanDebate_topic").html(debate.debate_topic);
        $("#spanDebate_date").html(debate.debate_date);
        $("#spanDebate_time").html(debate.debate_time);
        $("#spanDebate_discuss1").html(detail[0].discuss);
        $("#spanDebate_discuss2").html(detail[1].discuss);
		var writer = debate.debate_writer;

        var discussor1 = dismem[0].member_nickName;
        var discussor2 = dismem[1].member_nickName;
		if(userdata.logined=="logined" && writer==userdata.member.member_no){
			$('#btnDeleteDebate').show();
			$('#btnModifyDebate').show();
		}else{
			$('#btnDeleteDebate').hide();
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

		$('#tablecommentList').show();
		 var method = "comment";
	 var url = "../ta_back/debatecommentselect";
	    console.log('글번호 : ' + deb_no)
		$.ajax({
	      url: url,
	      method: "post",
	      data: {
	        method: method,
	        com_deb: deb_no,
	      },
	      success: function (resposeData) {
			console.log(resposeData);
			
			var commentlist = $('#tablecommentList');
			var comlist = $('#commentList');
	      var lists = resposeData.comment;
	      var mlists = resposeData.memberinfo;
if(lists=="" || lists==null){
	comlist.html('댓글이 존재 하지 않습니다.');
	}else{
	      var listsize = lists.length;
	     // var divtext = "";
	      var trDebates = "";
	      $(lists).each(function (list_i, list) {
			trDebates += '<tr class="list">';
        trDebates += '<td class="com_no">' + list.com_no + "</td>";
        trDebates += '<td class="com_no">' + list.com_contents + "</td>";
        trDebates +=
          '<td class="debate_writer">' +
          mlists[list_i].member_nickName +
          "</td>";
        trDebates +=
          '<td class="date" id="debate_no">' +
          list.com_date +
          "</td>";
        trDebates += "</tr>";
			
			});
			commentlist.append(trDebates);
			}
			},
			
			});
  });
	
	
	
	
	
	
		//해당댓글번호로 조회
    	console.log('시작1');
    	var com_deb='1';
		var method = 'comment';
    	var data = {method: method, com_deb : com_deb,};
    	 var url = "../ta_back/debatecommentselect";
         $.ajax({
           url: url,
           method: "post",
           data: data,
           //dataType: "json",
           success: function (responsedata) {
				console.log(responsedata.comment);
				var data = responsedata.comment;
				var comment = $('#trcomments');
				var table = $('#tablecomment');
				
				 $(data).each(function(i, e){
					 var cloneobj = comment.clone();
					// console.log(i);
					 cloneobj.html(e.com_contents);
					 table.append(cloneobj);
					 
				 });
           },
         });

});//Dom끝