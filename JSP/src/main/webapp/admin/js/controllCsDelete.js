	let success =  new URL(location.href).searchParams.get('success');
	
	if(success ==200){
		alert('성공적으로 삭제되었습니다.');
	}

$(document).ready(function() {



	
	//항목  체크박스  
	$('input[name=all]').change(function(){
		const isChecked = $(this).is(':checked');
		
		if(isChecked){
			// 전체선택
			$('input[name=chk]').prop('checked', true);
		}else{
			// 전체해제
			$('input[name=chk]').prop('checked', false);
		}
	});

	$('.noticeDelete').click(function(){
		
	});	
	
	
})