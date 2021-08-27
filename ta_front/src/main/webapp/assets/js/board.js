$(function () {
  $.ajax({
    url: "http://localhost:9999/board/list",
    method: "post",
    success: function (responseObj) {
      //게시글 목록
      var table = $("table.table");
      var $tr10bj = $("tr.data1");

      $(responseObj).each(function (i, e) {
        var $copyObj = $tr10bj.clone();
        /* var lines = e.lines; //게시글 정보
			var lineSize = lines.length; */
        //게시글 번호 , 타입 , 제목, 작성자, 날짜, 조회수

        $copyObj.find("td.board_no").html(e.board_no);
        $copyObj.find("td.board_type").html(e.board_type);
        $copyObj.find("td.board_title").html(e.board_title);
        $copyObj.find("td.board_title").attr("class", e.board_no);

        $copyObj.find("td.board_mem").html(e.board_mem);
        $copyObj.find("td.board_date").html(e.board_date);
        $copyObj.find("td.board_views").html(e.board_views);

        $copyObj.show();
        table.append($copyObj);
      });
    },
  });

  /*//게시글 상세페이지로 이동
	$('table.table').on('click', 'td', function(){
		var board_no = $(this).attr('class');
		console.log(board_no);
		var htmlurl = './boardinfo.html';
		var backurl = '../ta_back/boardinfo';
		$.ajax({
			url: backurl,
			method: 'post',
			data:{board_no: board_no},
			success:function(responseObj){
				
			console.log(responseObj);
			console.log(responseObj.boardDetail);
			var data = responseObj.boardDetail;
			

	          var htmlurl = './boardinfo.html';
	          $('section').load(htmlurl, function () {
	        	//json
	  			var board_no = data.board_no;
	  			var board_type = data.board_type;
	  			var board_title = data.board_title;
	  			var board_contents = data.board_contents;
	  			var board_mem = data.board_mem;
	  			var board_date = data.board_date;
	  			var board_views = data.board_views;
	  			console.log(board_no);
	  			
	  			$('td.board_no').html(board_no);
	  			$('td.board_type').html(board_type);
	  			$('td.board_title').html(board_title);
	  			$('td.board_contents').html(board_contents);
	  			$('td.board_mem').html(board_mem);
	  			$('td.board_date').html(board_date);
	  			$('td.board_views').html(board_views); 
	  			$('div.boardinfo').show();
		
				$('span.board_no').html(board_no);
	  			$('span.board_type').html(board_type);
	  			$('span.board_title').html(board_title);
	  			$('span.board_contents').html(board_contents);
	  			$('span.board_mem').html(board_mem);
	  			$('span.board_date').html(board_date);
	  			$('span.board_views').html(board_views); 
	  			$('div.boardinfo').show();
	  			
	          });
			}
		});
	});
});*/
  $("table.table").on("click", "td", function () {
    var board_no = $(this).attr("class");
    console.log(board_no);
    var htmlurl = "./boardinfo.html";
    var backurl = "../ta_back/boarcomlist";
    $.ajax({
      url: backurl,
      method: "post",
      data: { board_no: board_no },
      success: function (responseObj) {
        var htmlurl = "./boardinfo.html";
      },
    });
  });
  //게시글 댓글
  $("table.table").on("click", "td", function () {
    var board_no = $(this).attr("class");
    console.log(board_no);
    var htmlurl = "./boardinfo.html";
    var backurl = "../ta_back/boardviews";
    $.ajax({
      url: backurl,
      method: "post",
      data: { board_no: board_no },
      success: function (responseObj) {
        console.log(responseObj);

        var table = $("table [name=comment]");
        var $tr10bj = $("tr.data2");
        $("section").load(htmlurl, function () {
          $(responseObj).each(function (i, e) {
            var $copyObj = $tr10bj.clone();

            //댓글 번호, 댓글 내용 , 작성자, 날짜

            $copyObj.find("td.com_no").html(e.com_no);
            $copyObj.find("td.com_contents").html(e.com_no);
            $copyObj.find("td.com_mem").html(e.com_no);
            $copyObj.find("td.com_date").html(e.com_no);

            $copyObj.show();
            table.append($copyObj);
          });
        });
      },
    });
  });

  //게시글 작성 페이지로 이동후 게시글 작성
  $("a[name=boardwrite]").on("click", "a", function () {
    /*var board_mem = $(this).attr('class');*/
    var htmlurl = "./boardwrite.html";
    var backurl = "../ta_back/boardwrite";
    $.ajax({
      url: backurl,
      method: "post",
      success: function (responseObj) {
        var htrmlurl = "./boardwrite.html";
        $("section").load(htmlurl, function () {
          $("input.btn1").on("click", function () {
            console.log($("form.write input[name=type]").val());
            $.ajax({
              url: "../ta_back/boardwrite",
              method: "post",
              data: {
                board_type: $("form.write input[name=type]").val(),
                board_title: $("form.write input[name=title]").val(),
                board_contents: $("form.write textarea[name=contents]").val(),
                board_mem: $("form.write input[name=member]").val(),
              },
              success: function (responseObj) {
                console.log(responseObj);
              },
              error: function (xhr) {
                alert(xhr.status);
              },
            });
          });
        });
      },
    });
  });
});
