var userdata; //로그인 정보 담기는 객체
var com_deb; //토론번호 담는 객체
var com_no; //댓글번호 담는 객체
var deb_no;
var currentpage = 1;
var endpage;
var login_nickname;
$(function() {
	$.ajax({
		url: url,
		method: "post",
		data: {},
		success: function(responseData) {
			userdata = responseData;

			if (responseData.logined == "logined") {

				$("#debcom_writer").val(userdata.member.member_nickName);
				$("#insert_btn").show();
			} else {
				//로그인 안된 상태
			}
		},
		xhrFields: {
			withCredentials: true
		},
	});


	//,boardlist완료
	var url = "http://localhost:9999/ta_back/resultlist";
	$.ajax({
		url: url,
		method: "get",
		success: function(resposeData) {
			//console.log(resposeData.logininfo.member_nickName);
			//var login_Nickname =resposeData.logininfo.member_nickName;
			var lists = resposeData.debatelist;
			/* var mlists = resposeData.memberinfo;
			 var listsize = lists.length;*/
			// var divtext = "";
			var trContent = $("#debateList");
			console.log(lists);

			var trDebates = "";
			$(lists).each(function(list_i, list) {
				trDebates += '<tr class="' + list.debate_no + ' ' + 'table-light" >';
				trDebates += '<td class="debate_no">' + list.debate_no + "</td>";
				trDebates +=
					'<td class="' +
					list.debate_no +
					'" id="debate_no"><a class="atitle" href="#" style="    text-decoration: none; color: black;font-weight: 600;">' +
					list.debate_topic +
					"</a></td>";
				trDebates +=
					'<td class="debate_writer">' +
					list.debate_writer.member_nickName +
					"</td>";
				trDebates += '<td class="debate_time">' + list.debate_time + "</td>";
				trDebates +=
					'<td class="debate_status">' + list.debate_status + "</td>";
				trDebates += "</tr>";
			});
			var pagelist = '<nav aria-label="Page navigation example">' +
				'<ul class="pagination">' +
				'<li class="page-item_prev"><a class="page-link href="#">Previous</a></li>';

			var startpage = resposeData.pageMaker.startPage;
			currentpage = startpage;
			endpage = resposeData.pageMaker.endPage;
			for (i = startpage; i <= endpage; i++) {
				pagelist += '<li class="page-item"><a class="page-link page' + i + '" href="#">' + i + '</a></li>';
			}

			pagelist += '<li class="page-item_next"><a class="page-link" href="#">Next</a></li>' +
				'</ul>' +
				'</nav>';
			if (resposeData.pageMaker.prev == true) {
				$(document).ready('li.page-item_prev').show();
				console.log("성공");
			} else {
				$(document).ready(
					function() {
						$('li.page-item_prev').show();//이부분 고친뒤에 hide로 수정
					}
				)

				console.log(" 실패");
			}
			if (resposeData.pageMaker.next == true) {
				$('li.page-item_next').show();
				console.log("성공");
			} else {
				$(document).ready(
					function() {
						$('li.page-item_next').show();
					}
				)
				$('li.page-item_next').show();//이부분 고친뒤에 hide로 수정
				console.log("실패");
			}
			$('div.paginglist').append(pagelist);

			trContent.append(trDebates);
			console.log(resposeData);
		},
		xhrFields: {
			withCredentials: true
		},
	});
	$("#debateList").on("click", "#debate_no", function() { //게시글상세
		deb_no = $(this).attr("class");
		var url = "http://localhost:9999/ta_back/resultlist/" + deb_no;
		var trContent = $("#debateList");
		reload();
	});
		$(document).on("click", ".com_insert", function() { //댓글 입력
			var url = "http://localhost:9999/ta_back/resultlist/resultreply/" + deb_no;
			var content = $(".comment_content").val();
			$.ajax({
				url: url,
				method: "post",
				data: {
					com_contents: content,
				},
				xhrFields: { withCredentials: true },
				success: function(resposeData) {
					if (resposeData.status == 1) {
						alert("댓글작성완료")
						reload();
					} else {
						alert("댓글작성실패")
					}
				},error : function(error) {
					alert("로그인안되서 오류가 났으니 로그인해주시기 바랍니다");
}
	
			});
		});

		$(document).on("click", ".com_delete", function() { //댓글 삭제
			var com_no = $(this).val();
			var url = "http://localhost:9999/ta_back/resultlist/resultreply/" + com_no;
			$.ajax({
				url: url,
				method: "delete",
				success: function(resposeData) {
					if (resposeData.status == 1) {
						alert("댓글삭제완료")
						reload();
					} else {
						alert("댓글삭제실패")
					}
				}
			});
		});
//here
	$('.searchbutton').click(function() { //단어검색기능
		var word = $('.form-control.searchinput').val();
		console.log(word);
		var url = "http://localhost:9999/ta_back/resultlist/search/" + word;
		$.ajax({
			url: url,
			method: "get",
			success: function(resposeData) {
				console.log(resposeData);
				var lists = resposeData.debatelistbyword;
				var trContent = $("#debateList");
				$('.table-light').empty();
				var trDebates = "";
				$(lists).each(function(list_i, list) {
					trDebates += '<tr class="' + list.debate_no + ' ' + 'table-light" >';
					trDebates += '<td class="debate_no">' + list.debate_no + "</td>";
					trDebates +=
						'<td class="' +
						list.debate_no +
						'" id="debate_no"><a class="atitle" href="#" style="text-decoration: none; color: black;font-weight: 600;">' +
						list.debate_topic +
						"</a></td>";
					trDebates +=
						'<td class="debate_writer">' +
						list.debate_writer.member_nickName +
						"</td>";
					trDebates += '<td class="debate_time">' + list.debate_time + "</td>";
					trDebates +=
						'<td class="debate_status">' + list.debate_status + "</td>";
					trDebates += "</tr>";
				});

				trContent.append(trDebates);
			},
			xhrFields: {
				withCredentials: true
			},
		});
	});
	$(document).on("click", ".page-link", function() { //페이징처리 버튼 이동구현
		var pagenum = $(this).text();
		console.log(pagenum);

		if (pagenum == "Previous") {
			currentpage = ((currentpage * 1) - 1);
			if (currentpage == 0) {
				currentpage = 1;
				
			}
		} else if (pagenum == "Next") {
			currentpage = ((currentpage * 1) + 1);
			if (currentpage > endpage) {
				currentpage = endpage;
				
			}
		} else {
			currentpage = pagenum;
			$('.page-item_prev').show();
		}

		var url = "http://localhost:9999/ta_back/resultlist/list/" + currentpage;
		$.ajax({
			url: url,
			method: "get",
			success: function(resposeData) {
				console.log(resposeData);
				var lists = resposeData.debatelist;
				var trContent = $("#debateList");
				$('.table-light').empty();
				var trDebates = "";
				$(lists).each(function(list_i, list) {
					trDebates += '<tr class="' + list.debate_no + ' ' + 'table-light" >';
					trDebates += '<td class="debate_no">' + list.debate_no + "</td>";
					trDebates +=
						'<td class="' +
						list.debate_no +
						'" id="debate_no"><a class="atitle" href="#" style="text-decoration: none; color: black;font-weight: 600;">' +
						list.debate_topic +
						"</a></td>";
					trDebates +=
						'<td class="debate_writer">' +
						list.debate_writer.member_nickName +
						"</td>";
					trDebates += '<td class="debate_time">' + list.debate_time + "</td>";
					trDebates +=
						'<td class="debate_status">' + list.debate_status + "</td>";
					trDebates += "</tr>";
				});

				trContent.append(trDebates);
			},
			xhrFields: {
				withCredentials: true
			},
		});
	});
	return false;
});

function reload() {
	var url = "http://localhost:9999/ta_back/resultlist/" + deb_no;
	//var method = "debatedetail";
	//	var trContent = $("#debateList");
	//alert(url);
	$.ajax({
		url: url,
		method: "get",
		xhrFields: { withCredentials: true },
		success: function(responseData) {
			console.log("다음");
				console.log(responseData);
			if (responseData.logininfo == null || responseData.logininfo != null) {
				//$("div.debateboardlistbox").empty();
				$('#section').empty();
				//$("div.debateboardlistbox").load("http://localhost:8888/ta_front/resultdetail.html");
				
				//console.log("test"+responseData.dabateone.debate_writer.member_nickName);
				var debate_no = responseData.dabateone.debate_no;
				var debate_img = responseData.dabateone.debate_mem.member_thumb;
				var debate_writer = responseData.dabateone.debate_mem.member_nickName;
				var debate_topic = responseData.dabateone.debate_topic;
				var debate_date_startdate = responseData.dabateone.debate_startDate;
				var debate_date_enddate = responseData.dabateone.debate_endDate;
				var debate_discussor = responseData.dabateone.detail_list[0].debate_nickname;
				var debate_discussor2 = responseData.dabateone.detail_list[1].debate_nickname;
				var debate_discussorthambnail = responseData.dabateone.detail_list[0].debate_thambnail;
				var debate_discussorthambnai2 = responseData.dabateone.detail_list[1].debate_thambnail

				var debate_discuss = responseData.dabateone.detail_list[0].discuss;
				var debate_discuss2 = responseData.dabateone.detail_list[1].discuss;

				var debate_evi1_1 = responseData.dabateone.evi_one;
				var debate_evi1_2 = responseData.dabateone.evi_two;
				var debate_evi1_3 = responseData.dabateone.evi_three;
				var debate_evi2_1 = responseData.dabateone.evi_one;
				var debate_evi2_2 = responseData.dabateone.evi_two;
				var debate_evi2_3 = responseData.dabateone.evi_three;

				var like_count = responseData.dabateone.like_count;
				var detaillist = responseData.dabateone.detail_list;
				var discuss1_vote = responseData.vote_left;
				var discuss2_vote = responseData.vote_right;
				var discuss_neutrality = responseData.vote_middle;
				var discuss_content = responseData.dabateone.debate_content;
				console.log("추천수" + like_count);
				/*$(detaillist).each(function (i, e) {
					alert(detaillist[i].discuss);
				});*/

				var detailboard = '<article>' +
					'<div class="resultdetailbox">' +
					'<div class="debateboarderbox">' +
					'<div class="boardheader">' +
					'<div class="profilebox">' +
					'<img class="discussormem" src="' + debate_img + '" alt="t제안자사진"> <span class="debmem_name">등록자 :' + debate_writer + '</span>' +
					'</div>' +
					'<div class="result_discuss">' +
					'<h3 style="font-size: 50px; margin-top: 40px; margin-right: 98px;">' + debate_topic + '</h3>' +
					'</div>' +
					'</div>' +
					'<div style="margin-top: 25px;">' +
					'<ul class="ul_debinfo">' +
					'<span>토론등록일 : </span>' +
					'<li class="debinfo"> 6월 13일</li>' +
					'<span>토론시작일 : </span>' +
					'<li class="debinfo">' + debate_date_startdate + '</li>' +
					'<span>토론종료일 : </span>' +
					'<li class="debinfo"> ' + debate_date_enddate + '</li>' +
					'<span>추천수 : </span>' +
					'<li class="debinfo"> ' + like_count + "개" + '</li>' +
					'<span>토론시간 : </span>' +
					'<li class="debinfo"> 180분</li>' +
					'</ul>' +
					'</div>' +
					'</div>' +
					//토론내용content
					'<br>' +
					'<div class="card border-primary mb-3" style="max-width: 100%;margin: 3%;text-align: initial;">' +
					'<div class="card-header">Header</div>' + 
					'<div class="card-body">' +
					'<h4 class="card-title">Success card title</h4>' + 
					discuss_content +
					'</div>'+
					'</div>';
				/*	'<!-- 여기까지 토픽 -->*/
				var detaillist = responseData.dabateone.detail_list;

				detailboard += '<br>' +
					'<div class="debateboarderContent">' +
					'<div class="main_profilebox">' +
					'<img class="discussor_img" src="' + debate_discussorthambnail + '" alt="토론자1사진"> <span class="discussor_name">' + debate_discussor + '</span>' +
					'</div>' +
					'<div class="evi_content">' +
					'<p class="speech_bubble">' + detaillist[0].evi_one + '</p>' +
					'</div>' +
					'</div>' +
					'<div class="debateboarderContent_opponent">' +
					'<div class="main_profilebox">' +
					'<img class="discussor_img_opponent" src="' + debate_discussorthambnai2 + '" alt="토론자2사진"> <span class="discussor_name_opponent">' + debate_discussor2 + ' </span>' +
					'</div>' +
					'<div class="evi_content_opponent">' +
					'<p class="speech_bubble_opponent" >' + detaillist[1].evi_one + '</p>' +
					'</div>' +
					'</div>' +
					'<br>' +
					'<div class="debateboarderContent">' +
					'<div class="main_profilebox">' +
					'<img class="discussor_img" src="' + debate_discussorthambnail + '" alt="토론자1사진"> <span class="discussor_name">' + debate_discussor + '</span>' +
					'</div>' +
					'<div class="evi_content">' +
					'<p class="speech_bubble">' + detaillist[0].evi_two + '</p>' +
					'</div>' +
					'</div>' +
					'<div class="debateboarderContent_opponent">' +
					'<div class="main_profilebox">' +
					'<img class="discussor_img_opponent" src="' + debate_discussorthambnai2 + '" alt="토론자2사진"> <span class="discussor_name_opponent">' + debate_discussor2 + ' </span>' +
					'</div>' +
					'<div class="evi_content_opponent">' +
					'<p class="speech_bubble_opponent">' + detaillist[1].evi_two + '</p>' +
					'</div>' +
					'</div>' +
					'<br>' +
					'<div class="debateboarderContent">' +
					'<div class="main_profilebox">' +
					'<img class="discussor_img" src="' + debate_discussorthambnail + '" alt="토론자1사진"> <span class="discussor_name">' + debate_discussor + '</span>' +
					'</div>' +
					'<div class="evi_content">' +
					'<p class="speech_bubble">' + detaillist[0].evi_three + '</p>' +
					'</div>' +
					'</div>' +
					'<div class="debateboarderContent_opponent">' +
					'<div class="main_profilebox">' +
					'<img class="discussor_img_opponent" src="' + debate_discussorthambnai2 + '" alt="토론자2사진"> <span class="discussor_name_opponent">' + debate_discussor2 + ' </span>' +
					'</div>' +
					'<div class="evi_content_opponent">' +
					'<p class="speech_bubble_opponent">' + detaillist[1].evi_three + '</p>' +
					'</div>' +
					'</div>';

				//투표결과
				var total = discuss1_vote + discuss2_vote + discuss_neutrality;
				if (total != 0) {
					var discuss1_voterate = discuss1_vote / total * 100;
					var discuss2_voterate = discuss2_vote / total * 100;
					var neutrality_voterate = discuss_neutrality / total * 100;
				} else {
					var discuss1_voterate = 0;
					var discuss2_voterate = 0;
					var neutrality_voterate = 0;
				}

				detailboard += '<div class="ratingbox">' +
					'<span class="heading">Vote status</span>' +

					'<hr style="border:3px solid #f1f1f1">' +

					'<div class="row">' +
					'<div class="side">' +
					'<div>' + debate_discuss + '</div>' +
					'</div>' +
					'<div class="middle">' +
					'<div class="bar-container">' +
					'<div class="bar-5" style="width :' + discuss1_voterate + '%;"></div>' +
					'</div>' +
					'</div>' +
					' <div class="side right">' +
					'<div>' + discuss1_vote + '</div>' +
					'</div>' +
					'<div class="side">' +
					'<div>' + debate_discuss2 + '</div>' +
					'</div>' +
					'<div class="middle">' +
					'<div class="bar-container">' +
					'<div class="bar-4" style="width :' + discuss2_voterate + '%;"></div>' +
					'</div>' +
					'</div>' +
					'<div class="side right">' +
					'<div>' + discuss2_vote + '</div>' +
					'</div>' +
					' <div class="side">' +
					'<div>중립</div>' +
					' </div>' +
					'<div class="middle">' +
					'<div class="bar-container">' +
					'<div class="bar-3" style="width :' + neutrality_voterate + '%;"></div>' +
					'</div>' +
					'</div>' +
					'<div class="side right">' +
					'<div>' + discuss_neutrality + '</div>' +
					' </div>' +
					'<div  style="text-align: right; padding-right: 57px;">total : ' + total + '</div>' +
					'</div>' +
					'</div>';

				detailboard += '<div style=" margin: 17px; border-radius: 23px;background-color: #DEF7DE;">';

				var commentlist = responseData.commentlist;
				console.log(commentlist);
				/*var login_nickname = responseData.logininfo.member_nickName;*/
				if(responseData.logininfo == null){
					login_nickname = "guest"
				}else{
					login_nickname =responseData.logininfo.member_nickName;
				}
				var dabate_nickname = responseData.dabateone.debate_mem.member_nickName
				var email = responseData.logininfo.member_email
				console.log(email);
				$(commentlist).each(function(i, e) {
					detailboard += '<div class="comment_box">'+
						'<div id="comment_box">'+
						'<img id="comment_profile_img" src="' + commentlist[i].com_mem.member_thumb + '" alt="댓글프로필사진">' +
						'<span id="comment_user_id">' + commentlist[i].com_mem.member_nickName + '</span>' +
						'<div id="comment">' +
						'<span id="comment_maintext">' + commentlist[i].com_contents + '</span>' +
						'<span id="comment_date">' + commentlist[i].com_date + '</span>' +
						'</div>';

					var com_usernickname = commentlist[i].com_mem.member_nickName;

					if (login_nickname == com_usernickname) {
						detailboard += ' <button class="btn btn-primary com_delete" value="' + commentlist[i].com_no + '" style="width:60px; height: 50px;  border-radius: 15px;">삭제</button>' +
							'</div>';
					} else {
						detailboard += ' <button class="btn btn-primary com_delete" value="' + commentlist[i].com_no + '" style="width:60px; display:none; height: 50px; border-radius: 15px;">삭제</button>' +
							'</div>';
					}

				});
				detailboard += '</div>';
				detailboard += '<div style="margin-top:50px; margin-bottom: 15px;">' +
					'<span style="height: 100px;font-weight: 700;">' + login_nickname + '</span> <input class="form-control comment_content" type="text" placeholder="댓글을 입력하세요 " aria-describedby="button-addon2" style="display : inline-block; width : 85%; margin-bottom : 10px">' +
					'<button class="btn btn-primary com_insert" type="button" id="button-addon2" ">입력</button>' +
					'</div>' +
					'</div>';

				detailboard += '</div>'
				detailboard += '</article>';
				$('#section').append(detailboard);
				console.log(responseData);

				//var debate_nickname = responseData.dabateone.debate_mem.member_nickName;
				if (login_nickname == dabate_nickname) {
					$('button.com_delete').show();
				}

			} else {
				alert("로그인을 해주세yo")
			}
		}
	});
	return false;
}
