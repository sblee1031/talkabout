	 $(function(){
		 $('input.btn1').on('click',function(){
			 console.log( $('form.write input[name=type]').val());
			 $.ajax({
					url:'../ta_back/boardwrite',
					method:'post',
					data:{
						board_type : $('form.write input[name=type]').val(),
						board_title: $('form.write input[name=title]').val(),
						board_contents:$('form.write textarea[name=contents]').val(),
						board_mem: $('form.write input[name=member]').val(),
					},
				success: function (responseObj) {
					console.log(responseObj);
				},
				error: function(xhr){
					alert(xhr.status);
				},
					
				}); 
		 });
		 
	 }); 