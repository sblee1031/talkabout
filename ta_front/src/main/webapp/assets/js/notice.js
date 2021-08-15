var userdata; //로그인 정보 담기는 객체
var title;
var content;
$(function () {
	$('#noticeWrite').hide();
	$('#noticeDetail').show();
	$('#noticeList').show();
	
	
		//로그인 정보 불로오는 경로
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
//			var wrap = $('#');
			var html = '';
			  var lists = responseData.noticelist;
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
				/*$('#notice_type').html(list.notice_type);
				$('#notice_title').html(list.notice_title);
				$('#notice_writer').html(list.notice_admin);
				$('#notice_date').html(list.notice_date);
				$('#notice_views').html(list.notice_views);*/
				
				
				wrap.append(copyObj);
				});
			},
			
			});
			
				$(document).on('click','#notice_title',function(e){
					e.preventDefault();
					console.log('클릭');
					var clicknotice_no = $(this).attr('class');
					//console.log(clicknotice_no);
					var url = "../ta_back/notice";
					var method = 'noticedetail';
					  $.ajax({
					    url: url,
					    method: "post",
					    data: {method : method,
								notice_no : clicknotice_no,
						},
					    success: function (responseData) {
							var data = responseData.notice;
							var addTrObj;
							console.log(data.notice_no);
							title=data.notice_title;
							content=data.notice_contents;
							$("#divNotice_title").html(data.notice_title);
							$("#ddNotice_no").html(data.notice_no);
							$("#divNotice_contents").html(data.notice_contents);
							$("#ddNotice_admin").html(data.notice_admin);
							$("#ddNotice_date").html(data.notice_date);
							$("#ddNotice_views").html(data.notice_views);
							addTrObj = $("#divnoticeDetail").clone();
        					addTrObj.show();
							$('#noticeWrite').hide();
							$('#noticeDetail').show();
							$('#noticeList').hide();
							$('#noticeUpdate').hide();
        					//$("tr." + _no).after('<tr id="view"></tr>');
        					//$("#view").append(addTrObj);
							
						},
					});	
					
			});

});//DOM트리 끝


function btnSaveNotice() {
	var notice_no = $("#ddNotice_no").text();
	var title = $("#inputNotice_title").val();
	//var type = $("#inputNotice_type").val();
	var type = $("#search option:selected").text();
	var contents = $("#inputNotice_contents").val();
	var indate = $("#inputNotice_date").val();
	if (typeof title == "undefined" || title == null || title == "") {
		alert("put notice title");
	} else if (
		typeof type == "undefined" ||
		type == null ||
		type == ""
	) {
		alert("put notice type");
	} else if (
		typeof contents == "undefined" ||
		contents == null ||
		contents == ""
	) {
		alert("put notice contents");
	}
	else{
	var url = "../ta_back/noticesave";
	var method = "noticesave";
	$.ajax({
		url: url,
		method: "post",
		data: {
		method: method,
		notice_no : notice_no,
		notice_title: title,
		notice_type: type,
		notice_contents: contents,
		//debate_date: indate,
		//notice_date: indate
		},
		success: function (responseData) {
		window.location.href = "../ta_front/notice.html";
		},
	});
	}
}
// 상세 - 수정
function btnNoticeUpdate() {
		console.log(title);
	console.log(content);
	$('#noticeWrite').hide();
	$('#noticeDetail').show();
	$('#noticeList').hide();
	$('#noticeUpdate').show();
	$('#inputNotice_title_update').val(title);
	$('#update_contents').val(content);
	
	
}

function btnUpdateNotice(){ 		
	
	var notice_no = $("#ddNotice_no").text();
	console.log("여기");
	console.log(notice_no);
	var title = $("#inputNotice_title_update").val();
	console.log(title);
	//var type = $("#inputNotice_type").val();
	var type = $("#search_3 option:selected").text();
	console.log(type);
	var contents = $("#update_contents").val();
	console.log("여기는 컨텐츠");
	console.log(contents);
	if (typeof title == "undefined" || title == null || title == "") {
		alert("put notice title");
	} else if (
		typeof type == "undefined" ||
		type == null ||
		type == ""
	) {
		alert("put notice type");
	} else if (
		typeof contents == "undefined" ||
		contents == null ||
		contents == ""
	) {
		alert("put notice contents");
	}
	else{
	var url = "../ta_back/noticesave";
	var method = "noticeModify";
	$.ajax({
		url: url,
		method: "post",
		data: {
			
		method: method,
		notice_no : notice_no,
		notice_title: title,
		notice_type: type,
		notice_contents: contents,
		//debate_date: indate,
		//notice_date: indate
		},
		success: function (responseData) {
			window.location.href = "../ta_front/notice.html";
		},
	});
	}
}

function btnDeleteNotice(){
	var url = "../ta_back/noticesave";
  	var method = "noticeDelete";
      if (userdata.logined == "logined") {//로그인 여부 확인
		       // console.log('login됨');
		var noti_no = $('#ddNotice_no').text();
			$.ajax({
		    url: url,
		    method: "post",
		    data: {
		      method: method,
		      notice_no: noti_no,
		    },
		    success: function (responseData) {
					//statusCancle(deb_no);
		      alert("게시글 삭제 완료");
		      window.location.href = "../ta_front/notice.html";
		    },
		  });
      } else {//로그인 안된 상태
        //console.log("회원정보없음" + responseData);
      }
}		
