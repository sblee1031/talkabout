var userdata; //로그인 정보 담기는 객체
var com_deb; //토론번호 담는 객체
var com_no; //댓글번호 담는 객체
var deb_no;
$(function() {
	$.ajax({
		url: url,
		method: "post",
		data: {},
		success: function(responseData) {
			userdata = responseData;
			//console.log(userdata);
			//console.log(responseData.usercheck);
			if (responseData.logined == "logined") {
				//로그인 여부 확인
				//console.log(responseData.logined);
				//$("#write").show();
				$("#debcom_writer").val(userdata.member.member_nickName);
				$("#insert_btn").show();
			} else {
				//로그인 안된 상태
				//console.log("회원정보없음" + responseData);
				// $("#write").hide();
			}
		},
		xhrFields: {
			withCredentials: true
		},
	});

	//해당댓글번호로 조회
	console.log('토론결과조회'); //,boardlist완료
	var method = "result";
	var data = { method: method };
	var url = "http://localhost:9999/ta_back/resultlist";
	$.ajax({
		url: url,
		method: "get",
		//data: data,
		//dataType: "json",
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
				trDebates += '<tr class="' + list.debate_no +' '+'table-light" >';
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
			/*var pagelist = '<tr><td><nav aria-label="Page navigation example">' +
				'<ul class="pagination">' +
				'<li class="page-item_prev"><a class="page-link" href="#">Previous</a></li>';

			var startpage = resposeData.pageMaker.startPage;
			var endpage = resposeData.pageMaker.endPage;
			for (i = startpage; i <= endpage; i++) {
				pagelist += '<li class="page-item"><a class="page-link" href="#">' + i + '</a></li>';
			}

			pagelist += '<li class="page-item_next"><a class="page-link" href="#">Next</a></li>' +
				'</ul>' +
				'</nav></td></tr>';
			if (resposeData.pageMaker.prev == true) {
				$(document).ready('li.page-item_prev').show();
				console.log("성공");
			} else {
				$(document).ready(
					function() {
						$('li.page-item_prev').hide();
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
						$('li.page-item_next').hide();
					}
				)
				$('li.page-item_next').hide();
				console.log("실패");
			}
			$('div.paginglist').append(pagelist);
			*/
			trContent.append(trDebates);
			console.log(resposeData);

		},
		xhrFields: {
			withCredentials: true
		},
	});

	$("#debateList").on("click", "#debate_no", function() {
		$("#view").remove(); //기존 상세페이지가 열려 있으면 지우기
		$("tr.list").remove(); //기존 상세페이지가 열려 있으면 지우기

		deb_no = $(this).attr("class");
		var url = "http://localhost:9999/ta_back/resultlist/" + deb_no;
		var trContent = $("#debateList");
		reload();

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
				},
			});
		});
		$(document).on("click", ".com_delete", function() { //댓글 삭제
			alert("삭제버튼 클릭");
			var com_no = $(this).val()
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
	});
}); //Dom끝

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
			if (responseData.logininfo == null) {
				alert("로그인을 해주세용")
			} else {
				//$("div.debateboardlistbox").empty();
				$('#section').empty();
				//$("div.debateboardlistbox").load("http://localhost:8888/ta_front/resultdetail.html");

				console.log(responseData);
				//console.log("test"+responseData.dabateone.debate_writer.member_nickName);

				var debate_no = responseData.dabateone.debate_no;
				var debate_img = responseData.dabateone.debate_mem.member_thumb;
				var debate_writer = responseData.dabateone.debate_mem.member_nickName;
				var debate_topic = responseData.dabateone.debate_topic;
				var debate_date_startdate = responseData.dabateone.debate_startDate;
				var debate_date_enddate = responseData.dabateone.debate_endDate;
				//야ㅑ
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
					'<h3 style="font-size: 50px; margin-top: 40px;">' + debate_topic + '</h3>' +
					'</div>' +
					'</div>' +
					'<div style="margin-top: 25px;">' +
					'<ul class="ul_debinfo">' +
					'<span>토론등록일 : </span>' +
					'<li class="debinfo">6월 13일</li>' +
					'<span>토론시작일 : </span>' +
					'<li class="debinfo">' + debate_date_startdate + '</li>' +
					'<span>토론종료일 : </span>' +
					'<li class="debinfo">' + debate_date_enddate + '</li>' +
					'<span>추천수 : </span>' +
					'<li class="debinfo">' + like_count + "개" + '</li>' +
					'<span>토론시간 : </span>' +
					'<li class="debinfo">180분</li>' +
					'</ul>' +
					'</div>' +
					'</div>' +

					'<br>' +
					'<div class="discuss">' +
					'<div style="display: flex; height: 150px;">' +
					'<div style="flex: 1;">' +
					'<p class="discuss1" style="font-size: 40px; font-weight: 700;">' + debate_discuss + '</p>' +
					'</div>' +
					'<div class="vs">' +
					'<p class="vs" style="margin-top: 3px;">vs</p>' +
					'</div>' +
					'<div style="flex: 1;">' +
					'<p class="discuss2" style="font-size: 40px; font-weight: 700;">' + debate_discuss2 + '</p>' +
					'</div>' +
					'</div>' +
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
					'<p class="speech_bubble" >' + detaillist[1].evi_one + '</p>' +
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
					'<p class="speech_bubble">' + detaillist[1].evi_two + '</p>' +
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
					'<p class="speech_bubble">' + detaillist[1].evi_three + '</p>' +
					'</div>' +
					'</div>';

				detailboard += '<div class="ratingbox">' +
					'<span class="heading">User Rating</span>' +

					'<hr style="border:3px solid #f1f1f1">' +

					'<div class="row">' +
					'<div class="side">' +
					'<div>'+debate_discuss+'</div>' +
					'</div>' +
					'<div class="middle">' +
					'<div class="bar-container">' +
					'<div class="bar-5"></div>' +
					'</div>' +
					'</div>' +
					' <div class="side right">' +
					'<div>150</div>' +
					'</div>' +
					'<div class="side">' +
					'<div>'+debate_discuss2+'</div>' +
					'</div>' +
					'<div class="middle">' +
					'<div class="bar-container">' +
					'<div class="bar-4"></div>' +
					'</div>' +
					'</div>' +
					'<div class="side right">' +
					'<div>63</div>' +
					'</div>' +
					' <div class="side">' +
					'<div>중립</div>' +
					' </div>' +
					'<div class="middle">' +
					'<div class="bar-container">' +
					'<div class="bar-3"></div>' +
					'</div>' +
					'</div>' +
					'<div class="side right">' +
					'<div>15</div>' +
					' </div>' +
					'</div>' +
					'</div>';

				detailboard += '<div style=" margin: 17px; border-radius: 23px;background-color: beige;">';

				var commentlist = responseData.commentlist;
				console.log(commentlist);
				var login_nickname = responseData.logininfo.member_nickName;
				var dabate_nickname = responseData.dabateone.debate_mem.member_nickName
				$(commentlist).each(function(i, e) {



					detailboard += '<div class="comment_box" style="margin-bottom: 10px; width=1255px;">' +
						'<div id="comment_box">' +
						'<img id="comment_profile_img" src="' + commentlist[i].com_mem.member_thumb + '" alt="댓글프로필사진">' +
						'<span id="comment_user_id">' + commentlist[i].com_mem.member_nickName + '</span>' +
						'<div id="comment">' +
						'<span id="comment_maintext">' + commentlist[i].com_contents + '</span>' +
						'<span id="comment_date">' + commentlist[i].com_date + '</span>' +
						'</div>';

					var com_usernickname = commentlist[i].com_mem.member_nickName;

					if (login_nickname == com_usernickname) {
						detailboard += ' <button class="com_delete" value="' + commentlist[i].com_no + '" style="width:53px; height: 50px; background-color: antiquewhite; border-radius: 15px;">삭제</button>' +
							'</div>';
					} else {
						detailboard += ' <button class="com_delete" value="' + commentlist[i].com_no + '" style="width:53px; display:none; height: 50px; background-color: antiquewhite; border-radius: 15px;">삭제</button>' +
							'</div>';
					}

				});
				detailboard += '</div>';
				detailboard += '<div style="margin-top:50px; margin-bottom: 15px;">' +
					'<span style="height: 100px;font-weight: 700;">' + login_nickname + '</span> <input class="comment_content" type="text" placeholder="댓글을 입력하세요 " style="width: 816px; height: 100px; margin-bottom:20px; border-radius: 20px; overflow: hidden;">' +
					'<button class="com_insert" type="button" style="width: 100px;  height: 100px; background-color: antiquewhite; border-radius: 15px;">입력</button>' +
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
			}
		}
	});
}
