$(document).ready(function() {
	
	
	$(document).on("change","input[name='updateAnswerComplete']",function(){
		const updateVal = $('input[name=updateAnswerComplete]:checked').val();
		const no = $('input[name=no]').val();
		$.ajax({
			
			type : "get",
			url : ctx + "/admin/cs/qna/updateAnswer.do",
			data :  {
				"no": no,
				"answerComplete" : updateVal
			},
			dataType: 'json',
			success : function(data) {
				if(data.result ==1){
					alert('성공적으로 상태가 업데이트 되었습니다.');
					const answerStatus = $('#answerStatus');
					answerStatus.removeClass();
					answerStatus.addClass('answer' + updateVal);
					answerStatus.text(updateVal == 0 ? "미확인" : "검토중" );
				}					
			},
			error : (request, status, error) => { // 순서 체크해보기 
	            console.log("상태코드: " + request.status);
	            console.log("메세지: " + request.responseText);
	            console.log("에러설명: " + error);
	        }
		});
	});
	
	
	
	$(document).on("click",".answerBtn",function(e){
		
		e.preventDefault();
		const kind = $(this).data('kind');
		const answer = $('textarea[name=answer]').val();
		const no = $('input[name=no]').val();
		const writer = $('input[name=writer]').val();
		$.ajax({
			
			type : "post",
			url : ctx + "/admin/cs/qna/updateAnswer.do",
			data :  {
				"no": no,
				"answer" : answer,
				"kind" : kind,
				"writer" : writer
			},
			dataType: 'json',
			success : function(data) {
				if(data.result ==1){
					alert('성공적으로 답변이 업데이트 되었습니다.');
					if(kind=='insert'){
						const answerBtn = $('#insertAnswer');
						answerBtn.attr('id', 'updateAnswer');
						answerBtn.text('답변수정');
						const answerStatus = $('#answerStatus');
						answerStatus.removeClass();
						answerStatus.addClass('answer2');
						answerStatus.text('답변완료');
						$('#updateStaus').remove();
					}
				}					
			},
			error : (request, status, error) => { // 순서 체크해보기 
	            console.log("상태코드: " + request.status);
	            console.log("메세지: " + request.responseText);
	            console.log("에러설명: " + error);
	        }
		});
		
	});
});