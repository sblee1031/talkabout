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
  
  var url = "../ta_back/admin";
  $.ajax({
    url: url,
    method: "post",
    data: {},
    success: function (responseData) {
    	console.log(responseData);
    	var data = responseData;
    	if(data.status =='fail'){
    		
    	}else if(data.adminInfo !=null){
    		//alert(data.adminInfo+ ' 관리자님 환영합니다.');
    		$("#close").trigger("click");
    		$('#myModal').hide();
    		$('#myBtn').hide();
    		$('#adminInfo').show();
    		$('#adminNo').text(data.adminInfo+' 관리자 ');
    	}
    }
  });
  
  
  $('#btnLogin').on('click', function(){
	  var url = "../ta_back/admin";
	  var adId =$('#adminid').val();
	  var adPwd =$('#adminpw').val();
	  $.ajax({
	    url: url,
	    method: "post",
	    data: {id:adId,
	    	pwd:adPwd},
	    success: function (responseData) {
	    	console.log(responseData);
	    	var data = responseData;
	    	if(data.status =='fail'){
	    		alert('로그인 정보가 일치하지 않습니다.');
	    	}else if(data.adminInfo !=null){
	    		alert(data.adminInfo+ ' 관리자님 환영합니다.');
	    		$("#close").trigger("click");
	    		$('#myModal').hide();
	    		$('#myBtn').hide();
	    		$('#adminInfo').show();
	    		$('#adminNo').text(data.adminInfo+' 관리자 ');
	    	}
	    }
	  });
  });
  $('#adminList').change(function(){
	  var list = $('#adminList option:selected').text();
	  $('#boardTitle').html(list+ ' 내용 ');
	  if(list=='공지사항'){
		  noticeList();
	  }else if(list=='자유게시판'){
		  boardList();
	  }else if(list=='토론모집'){
		  deb_recruite();
	  }else if(list=='토론배틀'){
		  deb_battle();
	  }else if(list=='토론결과'){
		  deb_result();
	  }
  });
 
  
});//DOM 끝
function deb_result(){
	var method = "result";
	  var data = { method: method };
	  var url = "../ta_back/debatecommentselect";
	  $.ajax({
	    url: url,
	    method: "post",
	    data: data,
	    //dataType: "json",
	    success: function (resposeData) {
	    	  console.log(resposeData);
	    	  
	  		var contents = $('#debcontents').clone();
	  		var wrap = $('div.board_list');
	  		var head = $('#deb_head').clone();
	  		  head.show();
	  		  head.find('div.notice_views').text('토론시간');
	  		  wrap.html(head);
	        $(resposeData.debatelist).each(function (i, e) {
	      	  //console.log(i);
	      	  var copyObj = contents.clone();
	      	  
	      	  	copyObj.find('#deb_no').text(e.debate_no);
	  			copyObj.find('#deb_date').text(e.debate_date);
	  			copyObj.find('#deb_title').text(e.debate_topic);
	  			copyObj.find('#deb_title').attr('class',e.debate_topic);
	  			copyObj.find('#deb_writer').text(resposeData.memberinfo[i].member_nickName);
	  			copyObj.find('#deb_status').text(e.debate_status);
	  			copyObj.find('#deb_time').text(e.debate_time);
	  			copyObj.show();
	  			
	  			wrap.append(copyObj);
	        });
	    },
	  });
}
function deb_battle(){
	$.ajax({
		url : '../ta_back/debatebattle',
		method : 'get',
		success:function(responseObj){
			console.log(responseObj);
			var contents = $('#debcontents').clone();
			var wrap = $('div.board_list');
			var head = $('#deb_head').clone();
			  head.show();
			  head.find('div.notice_views').text('시청자');
			  wrap.html(head);
	      $(responseObj.debateinfo).each(function (i, e) {
	    	  //console.log(i);
	    	  var copyObj = contents.clone();
	    	  var deb_list = responseObj.debateinfo;
			  var cnt_list = responseObj.voteinfo;
	    	  	copyObj.find('#deb_no').text(e.debate_no);
				copyObj.find('#deb_date').text(e.debate_date);
				copyObj.find('#deb_title').text(e.debate_topic);
				copyObj.find('#deb_title').attr('class',e.debate_topic);
				copyObj.find('#deb_writer').text(e.debate_writer);
				copyObj.find('#deb_status').text(e.debate_status);
				copyObj.find('#deb_time').text(cnt_list[i]);
				copyObj.show();
				
				wrap.append(copyObj);
	      });
		},
	});
}

function deb_recruite(){
	  var url = "../ta_back/debrecruit";
	  var method = "listall";
	  $.ajax({
	    url: url,
	    method: "get",
	    data: { method: method,
				page: 1,
				pagesize: 1000,
				},
	    success: function (resposeData) {
		//totalData = resposeData.row;
		//paging(totalData, dataPerPage, pageCount, 1);
	  console.log(resposeData);
	  
		var contents = $('#debcontents').clone();
		var wrap = $('div.board_list');
		var head = $('#deb_head').clone();
		  head.show();
		  head.find('div.notice_views').text('토론시간');
		  wrap.html(head);
      $(resposeData.debatelist).each(function (i, e) {
    	  //console.log(i);
    	  var copyObj = contents.clone();
    	  
    	  	copyObj.find('#deb_no').text(e.debate_no);
			copyObj.find('#deb_date').text(e.debate_date);
			copyObj.find('#deb_title').text(e.debate_topic);
			copyObj.find('#deb_title').attr('class',e.debate_topic);
			copyObj.find('#deb_writer').text(resposeData.memberinfo[i].member_nickName);
			copyObj.find('#deb_status').text(e.debate_status);
			copyObj.find('#deb_time').text(e.debate_time);
			copyObj.show();
			
			wrap.append(copyObj);
	  
	      });
	    },
	  });
}

function boardList(){
	  $.ajax({
		    url: "../ta_back/boardlist",
		    method: "post",
		    success: function (responseObj) {
		    	console.log(responseObj);
		      //게시글 목록
//		      var table = $("table.table");
//		      var $tr10bj = $("tr.data1");
				var contents = $('#noticontents').clone();
				var wrap = $('div.board_list');
				var head = $('#head').clone();
				  head.show();
				  wrap.html(head);
		      $(responseObj.boardinfo).each(function (i, e) {
		    	  //console.log(i);
		    	  var copyObj = contents.clone();
		    	  
		    	  copyObj.find('#notice_no').text(e.board_no);
					copyObj.find('#notice_type').text(e.board_type);
					copyObj.find('#notice_title').text(e.board_title);
					copyObj.find('#notice_title').attr('class',e.board_title);
					copyObj.find('#notice_writer').text(responseObj.nicklist[i]);
					copyObj.find('#notice_date').text(e.board_date);
					copyObj.find('#notice_views').text(e.board_views);
					copyObj.show();
					
					wrap.append(copyObj);
		    	  
		      });
		    },
		  });
}

function noticeList(){
	var url = "../ta_back/notice";
	var method = 'listall';
		  $.ajax({
		    url: url,
		    method: "get",
		    data: {method:method},
		    success: function (responseData) {
			console.log(responseData);
			var contents = $('#noticontents').clone();
			var wrap = $('div.board_list');
			var head = $('#head').clone();
			  var lists = responseData.noticelist;
			  head.show();
			  wrap.html(head);
     		 $(lists).each(function (list_i, list) {
				//console.log(list_i);
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
				});
			},
			
			});
}

$(document).on('click', '#btnNoticeEdit',function(){
	var thisObj = $(this);
	//console.log(thisObj.parent().find('#notice_no').text());
	var notice_no = thisObj.parent().find('#notice_no').text();
	$('#noticeWrite').show();
	
	var url = "../ta_back/notice";
	var method = 'noticedetail';
	  $.ajax({
	    url: url,
	    method: "post",
	    data: {method : method,
				notice_no : notice_no,
		},
	    success: function (responseData) {
			var data = responseData.notice;
			var addTrObj;
			console.log(data.notice_no);
			title=data.notice_title;
			content=data.notice_contents;
			$("#inputNotice_title").val(data.notice_title);
			$("#ddNotice_no").html(data.notice_no);
			$("#inputNotice_contents").html(data.notice_contents);
			$("#ddNotice_admin").html(data.notice_admin);
			$("#ddNotice_date").html(data.notice_date);
			$("#ddNotice_views").html(data.notice_views);
			addTrObj = $("#divnoticeDetail").clone();
			addTrObj.show();
			//$('#noticeWrite').hide();
			//$('#noticeDetail').show();
			$('#noticeList').hide();
			//$('#noticeUpdate').hide();
			//$("tr." + _no).after('<tr id="view"></tr>');
			//$("#view").append(addTrObj);
			
		},
	});	
	
});

function logout(){
	 var url = "../ta_back/admin";
	 var data = 'logout';
	  $.ajax({
	    url: url,
	    method: "post",
	    data: {method:data},
	    success: function (responseData) {
	    	window.location.href = "admin.html";
	    }
	  });
}