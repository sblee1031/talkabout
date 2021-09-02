$(function(){
	$.ajax({
		url : '../ta_back/debatebattle',
		method : 'get',
		success:function(responseObj){
			console.log(responseObj);
			var deb_list = responseObj.debateinfo;
			var cnt_list = responseObj.voteinfo;
			
			//게시글 목록
			var table = $('#deb_battleList');
			// var $trcontent0bj = $('tr.tablecontents');
			
			var trContents = "";
			$(deb_list).each(function (i, e) {
				// trContents += '<tr class="tablecontents">';
				trContents += '<tr id = "' + e.debate_no + '" class="tablecontents">';
				trContents += '<td class="debate_no">' + e.debate_no + '</td>';
				trContents += '<td class="debate_status">' + e.debate_status + '</td>';
				// trContents += '<td class="' + e.debate_no + '" id = "debate_status">' + e.debate_status + '</td>';
				trContents += '<td class="' + e.debate_no + '" id = "to_detail"><a class="atitle" href="#">' + e.debate_topic + '</a></td>';
				trContents += '<td class="debate_writer">' + e.debate_writer + '</td>';
				trContents += '<td class="debate_date">' + e.debate_date + '</td>';
				trContents += '<td class="audience_cnt">' + cnt_list[i] + '</td>';
				trContents += '</tr>';
            });
			table.append(trContents);
		},
	});
	$('#deb_battleList').on('click', '#to_detail', function(){
		var deb_no = $(this).attr('class');
		var selector = 'tr#' + deb_no + ' > td.debate_status';
		var status = $(selector).text();
		
		if (status == "종료") {
			alert("종료된 토론입니다");
		} else {
			$.ajax({
            url: '../ta_back/debatebattledetail',
            method: "get",
            data: {
              deb_no: deb_no
            },
            success: function (responseObj) {
				var status = responseObj.status;
				
			    	var vote_data = responseObj.detail_vote;
	            	var deb_data = responseObj.detail_debate;
	            	var detail_Adata = responseObj.detail_discussorA;
	            	var detail_Bdata = responseObj.detail_discussorB;
	            	var mem_Adata = responseObj.detail_memberA;
	            	var mem_Bdata = responseObj.detail_memberB;
	            	var login_member = responseObj.login_member;
					// var detail_deb = responseObj.deb_no;
	            	
	            	var htmlurl = './debbattle_detail.html';
	            	$('section').load(htmlurl, function () {
	            		// vote_data
	            		var vote_A = vote_data[0];
	            		var vote_Center = vote_data[1];
	            		var vote_B = vote_data[2];
	    	        	
	            		// deb_data
						var detail_deb = deb_data.debate_no;
	    	  			var deb_Topic = deb_data.debate_topic;
	    	  			var deb_Time = deb_data.debate_time;
	    	  			
	    	  			// detail_Adata
	    	  			var detail_A_Discuss = detail_Adata.discuss;
	    	  			var detail_A_EviOne = detail_Adata.evi_one;
	    	  			var detail_A_EviTwo = detail_Adata.evi_two;
	    	  			var detail_A_EviThree = detail_Adata.evi_three;
	    	  			
	    	  			// detail_Bdata
	    	  			var detail_B_Discuss = detail_Bdata.discuss;
	    	  			var detail_B_EviOne = detail_Bdata.evi_one;
	    	  			var detail_B_EviTwo = detail_Bdata.evi_two;
	    	  			var detail_B_EviThree = detail_Bdata.evi_three;
	    	  			
	    	  			// mem_Adata
	    	  			var mem_A_No = mem_Adata.member_no;
	    	  			var mem_A_Nickname = mem_Adata.member_nickName;
	    	  			var mem_A_Thumb = mem_Adata.member_thumb;
	    	  			
	    	  			// mem_Bdata
	    	  			var mem_B_No = mem_Bdata.member_no;
	    	  			var mem_B_Nickname = mem_Bdata.member_nickName;
	    	  			var mem_B_Thumb = mem_Bdata.member_thumb;
	    	  			
	    	  			var login_mem_no = login_member.member_no;
	    	  			
						/*
						$('input.evidence_A1').attr("value", "없음");    	  			
						$('input.evidence_A2').attr("value", "없음");
						$('input.evidence_A3').attr("value", "없음");
						$('input.evidence_B1').attr("value", "없음");
						$('input.evidence_B2').attr("value", "없음");
						$('input.evidence_B3').attr("value", "없음");
	    	  			*/

						$('input.evidence_A1').attr("value", detail_A_EviOne);    	  			
						$('input.evidence_A2').attr("value", detail_A_EviTwo);
						$('input.evidence_A3').attr("value", detail_A_EviThree);
						$('input.evidence_B1').attr("value", detail_B_EviOne);
						$('input.evidence_B2').attr("value", detail_B_EviTwo);
						$('input.evidence_B3').attr("value", detail_B_EviThree);
						
						/*
						$('td.evidence_A1').html(detail_A_EviOne);
						$('td.evidence_A2').html(detail_A_EviTwo);
	    	  			$('td.evidence_A3').html(detail_A_EviThree);
	    	  			$('td.evidence_B1').html(detail_B_EviOne);
	    	  			$('td.evidence_B2').html(detail_B_EviTwo);
	    	  			$('td.evidence_B3').html(detail_B_EviThree);
						*/	    	  			

	    	  			$('div.battle_topic').html(deb_Topic);
	    	  			$('div.battle-timer').html(deb_Time+"분");
	    	  			// $('div.battle-timer').attr("id", deb_Time);
	    	  			$('div.battle_vote').html(detail_A_Discuss + " : " + vote_A + " 표 / 중립" + " : " + vote_Center + " 표 / " + detail_B_Discuss + " : " + vote_B + " 표");
	    	  			
	    	  			$('img.profile_A').attr("src", mem_A_Thumb);
	    	  			$('div.nickname_A').html(mem_A_Nickname);
	    	  			$('div.nickname_A').attr("id", mem_A_No);
	    	  			$('img.profile_B').attr("src", mem_B_Thumb);
	    	  			$('div.nickname_B').html(mem_B_Nickname);
	    	  			$('div.nickname_B').attr("id", mem_B_No);
	    	  			$('input#user').attr("value", login_mem_no);
						$('div.battle_topic').attr("id", detail_deb);
	    	          });
			    
            },
          });
		}
	});
});
