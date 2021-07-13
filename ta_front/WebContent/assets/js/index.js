$(function(){
	//$('#noticontents').hide();
		var url = "../ta_back/notice";
	var method = 'listall';
		  $.ajax({
		    url: url,
		    method: "get",
		    data: {method:method},
		    success: function (responseData) {
			var contents = $('#noticontents').clone();
			var wrap = $('#noticeTable');
			var html = '';
			  var lists = responseData.noticelist;
     		 $(lists).each(function (list_i, list) {
				//console.log(list_i);
				if(list_i<5){
				var copyObj = contents.clone();
				copyObj.find('#notice_no').text(list.notice_no);
				copyObj.find('#notice_type').text(list.notice_type);
				copyObj.find('#notice_title').text(list.notice_title);
				copyObj.find('#notice_title').attr('class',list.notice_no);
				copyObj.find('#notice_writer').text(list.notice_admin);
				copyObj.find('#notice_date').text(list.notice_date);
				copyObj.find('#notice_views').text(list.notice_views);
				copyObj.show();
				
				wrap.append(copyObj);
				}
				});
			},
			
			});
	
		  var url = "../ta_back/debrecruit";
		  var method = "listall";
		  $.ajax({
		    url: url,
		    method: "get",
		    data: { method: method,
			page: 1,
			pagesize: 5,},
		    success: function (resposeData) {
		      console.log(resposeData);
		      var lists = resposeData.debatelist;
		      var mlists = resposeData.memberinfo;
		      var listsize = lists.length;
		     // var divtext = "";
		      var contents = $("#debatecontents").clone();
				var wrap = $('#debateTable');
		      $(lists).each(function (list_i, list) {
				if(list_i<5){
				var copyObj = contents.clone();
				copyObj.find('#debate_no').text(list.debate_no);
				copyObj.find('#debate_topic').text(list.debate_topic);
				copyObj.find('#member_nickName').text(mlists[list_i].member_nickName);
				copyObj.find('#debate_status').text(list.debate_status);
				copyObj.show();
				
				wrap.append(copyObj);
			}
		      });
		    },
		  });
	
	
		var url = '../ta_back/boardlist';
		$.ajax({
		url: url,
		method: 'post',
		data: {},
		success:function(boarddata){
		//console.log(boarddata.boardinfo);
		//console.log(boarddata);
		 var lists1 = boarddata.boardinfo;
	//	console.log('리스트1'+lists1);
	      var mlists1 = boarddata.nicklist;
		//게시글 목록
		  var contents = $("#boardcontents").clone();
			var wrap = $('#boardTable');
			//console.log(boarddata.boardinfo.length);
			$(lists1).each(function (i, e) {
				//console.log('1');
				if(i<5){
				var copyObj = contents.clone();
				copyObj.find('#board_no').text(e.board_no);
				copyObj.find('#board_title').text(e.board_title);
				copyObj.find('#board_mem').text(mlists1[i]);
				copyObj.find('#board_views').text(e.board_views);
				
				copyObj.show();
				wrap.append(copyObj);
				}
			 });
		},
	});
	
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
		      var lists2 = resposeData.debatelist;
	      var mlists2 = resposeData.memberinfo;
	      var listsize = lists2.length;
	     // var divtext = "";
	      var contents = $("#debresultcontents").clone();
				var wrap = $('#debresultTable');
	      $(lists2).each(function (list_i, list4) {
				if(list_i<5){
				var copyObj = contents.clone();
				copyObj.find('#result_no').text(list4.debate_no);
				copyObj.find('#result_title').text(list4.debate_topic);
				copyObj.find('#result_time').text(list4.debate_time);
				copyObj.find('#result_end').text(list4.debate_endDate);
				copyObj.show();
				
				wrap.append(copyObj);
			}
	     			 });
	           },
	         });
	
	
	});