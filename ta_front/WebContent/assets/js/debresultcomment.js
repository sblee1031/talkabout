var userdata; //로그인 정보 담기는 객체
var com_deb; //토론번호 담는 객체
var com_no; //댓글번호 담는 객체
$(function () {
	$('#btnComment').hide();
	$('#tableinsert').hide();
	$('#divDebateView').hide();
	$('#tablecommentList').hide();
	$('#copytablecommentList').hide();
	$('#insert_btn').hide();
	$('#btnupdate').hide();
	$('#imglike').hide();
	$('#imgdislike').hide();
	
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
	//$('#commentList').empty();
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
		com_deb = debate.debate_no; //클릭된 토론 번호 저장
		console.log(com_deb);
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

									 //좋아 갯수 확인
								var url = "../ta_back/deblike";
								var like = 'likecnt';
							//	console.log('좋아요 요청');
								$.ajax({
									url: "../ta_back/deblike",
									method: "post",
									data: {
										method : like,
										deb_no : com_deb,
									},
									success: function (data) {
									//	console.log("좋아요");
										console.log(data);
										$('#plike').html(data.likecnt);
										if(userdata.logined=="logined"){
											
													var url = "../ta_back/deblike";
													var like = 'likechk';
													//	console.log('좋아요 요청');
													$.ajax({
														url: url,
														method: "post",
														data: {
															method : like,
															deb_no : com_deb,
														},
														success: function (data) {
															console.log(data);
															if(data.likechk==0){
																$('#imglike').show();
															}else{
																$('#imgdislike').show();
															}
														},
													});

										}
									},
								});

      },
    });
	


		//$('#tablecommentList').show();
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
			$('#divcomment').empty();
			var commentlist = $('#tablecommentList').clone();
			var commenthead = $('#commenthead').clone();
	      var lists = resposeData.comment;
	      var mlists = resposeData.memberinfo;
		if(lists=="" || lists==null || typeof lists == "undefined" ){
		 $('#divcomment').html('댓글이 존재 하지 않습니다.');
		}else{
	      var listsize = lists.length;
	     // var divtext = "";
	      var trDebates = "";
	      $(lists).each(function (list_i, list) {
			//console.log(list_i);
			var copycomlist = $('#commentList').clone();
			copycomlist.find('#deb_no').html(list.com_no);
			copycomlist.find('#deb_content').html(list.com_contents);
			copycomlist.find('#deb_writter').html(mlists[list_i].member_nickName);
			copycomlist.find('#deb_wrritedate').text(list.com_date );
			copycomlist.find('#deb_wrritedate').attr('class',list.com_no);
			
			copycomlist.show();
			commentlist.append(copycomlist);
			
				if(userdata.logined=="logined" && mlists[list_i].member_no==userdata.member.member_no){
					copycomlist.find('#btnedit').css('display','block');
					copycomlist.find('#btndelete').css('display','block');
				}
			
			});
			commentlist.show();
			$('#divcomment').html(commentlist);
			$('#divcomment').append(commentlist);
			
				
			}
			if(userdata.logined=="logined"){
				console.log(userdata.member);
				$('#trinsert').attr('readonly',false);
			}
			
			
			},
			
			});
			
 
 });//게시글 클릭 이벤트 끝
	

 	$(document).on('click','#imglike', function(e){
			//e.preventDefault();
				var url = "../ta_back/deblike";
				var like = 'like';
					console.log('좋아요 요청');
				$.ajax({
					url: url,
					method: "post",
					data: {
						method : like,
						deb_no : com_deb,
					},
					success: function (data) {
						console.log(data);
						console.log('좋아요성공');
						window.location.href = "../ta_front/debresult.html";
					},
				});
		});
			$(document).on('click','#imgdislike' ,function(){
				var url = "../ta_back/deblike";
				var like = 'dislike';
					console.log('좋아요 요청');
				$.ajax({
					url: url,
					method: "post",
					data: {
						method : like,
						deb_no : com_deb,
					},
					success: function (data) {
						console.log(data);
						console.log('좋아요취소');
						window.location.href = "../ta_front/debresult.html";
					},
				});
		});

		$('#insert_btn').on('click',function(){
			
		//	console.log('인서트 버튼');
			var comment = $('#trinsert').val();
			if(comment==''){
				alert('댓글을 입력해주세요.');
			}else{
			var method = "insertcomment";
			var url = "../ta_back/debatecommentinsert";
		  //  console.log('글번호 : ' + com_deb);
			$.ajax({
		      url: url,
		      method: "post",
		      data: {
				com_deb : com_deb,
		        com_contents: comment,
		     	 },
		      success: function (resposeData) {
				//console.log('댓글작성완료:');
				$('#trinsert').val('');
				$('td.'+com_deb).trigger('click');
					
				},
			});
			}
		});
	
	$(document).on('click', '#btnedit',function(){
			var $a = $(this).prev();
			com_no=$a.attr("class");
			console.log($a);
			//console.log($a.attr("class"));
			var method = "selectone";
			var url = "../ta_back/debatecommentselect";
		 // console.log('글번호 : ' + com_deb);
			$.ajax({
		      url: url,
		      method: "post",
		      data: {
				method : method,
				com_deb : $a.attr("class"),
		     	 },
		      success: function (resposeData) {
				//console.log(resposeData);
				var onecomment = resposeData.oneComment;
				$('#insert_btn').hide();
				$('#btnupdate').show();
				$('#trinsert').val(onecomment.com_contents);
			},
		
		});
	});
	$(document).on('click', '#btnupdate',function(){
			console.log('클릭');
			//console.log($a.attr("class"));
			var method = "comupdate";
			var url = "../ta_back/debatecommentupdate";
			var content = $('#trinsert').val();
			console.log('수정내용 : '+ content);
			$.ajax({
		      url: url,
		      method: "post",
		      data: {
				method : method,
				com_no : com_no,
				com_contents : content,
		     	 },
		      success: function (resposeData) {
				//console.log(resposeData);
				console.log('com_deb'+ com_deb);
				$('#insert_btn').show();
				$('#btnupdate').hide();
				$('td.'+com_deb).trigger('click');
				$('#trinsert').val('');
			},
		});
	});
		$(document).on('click', '#btndelete',function(){
			var $a = $(this).prevAll('#deb_wrritedate');
			com_no=$a.attr("class");
			console.log(com_no);
			//console.log(com_no1);
			var url = "../ta_back/debatecommentdelete";
			$.ajax({
		      url: url,
		      method: "post",
		      data: {
				com_no : com_no,
		     	 },
		      success: function (resposeData) {
				//console.log(resposeData);
				window.location.href = "../ta_front/debresult.html";
			},
		});
	});
	

});//Dom끝
