/**
 * 회원가입 유효성 검사
 */
// 폼 데이터 검증결과 상태변수
let isUidOk   = false;
let isPassOk  = false;
let isNameOk  = false;
let isNickOk  = false;
let isEmailOk = false;
let isHpOk    = false;

// 데이터 검증에 사용하는 정규표현식
let reUid   = /^[a-z]+[a-z0-9]{4,19}$/g;
let rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,12}$/; // 최소 8자 이상 최대 12자 
let reName  = /^[가-힣]{2,10}$/ 
let reNick  = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
let reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
let reHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

// 유효성 검증(Validation)
$(function(){
	
	// 아이디 검사
	$('input[name=km_uid]').keydown(function(){
		$('.resultId').text('');
		isUidOk = false;
	});
	
	// 비밀번호 검사 / 회원가입할 때 외에 비밀번호 변경할 때도 쓰인다
	$('input[name=km_pass2]').focusout(function(){
		
		const pass1 = $('input[name=km_pass1]').val();
		const pass2 = $('input[name=km_pass2]').val();
		
		if(pass1 == pass2){
			
			if(pass1.match(rePass)){
				$('.resultPass').css('color', 'green').text('사용할 수 있는 비밀번호 입니다.');
				isPassOk = true;
			}else{
				$('.resultPass').css('color', 'red').text('비밀번호는 숫자, 영문, 특수문자 조합 8자리 이상이어야 합니다.');
				isPassOk = false;
			}
		}else{
			$('.resultPass').css('color', 'red').text('비밀번호가 일치하지 않습니다.');
			isPassOk = false;
		}
	});
	
	// 이름 검사
	$('input[name=km_name]').focusout(function(){
		
		const name = $(this).val();
		
		if(name.match(reName)){
			$('.resultName').text('');
			isNameOk = true;
		}else{
			$('.resultName').css('color', 'red').text('유효한 이름이 아닙니다.');
			isNameOk = false;					
		}
	});
				
	// 최종 전송
	$('#formMember').submit(function(){
		
		if(!isUidOk){
			alert('아이디를 확인 하십시오.');
			return false; // 폼 전송 취소	
		}
		
		if(!isPassOk){
			alert('비밀번호를 확인 하십시오.');
			return false; // 폼 전송 취소	
		}
		
		if(!isNameOk){
			alert('이름를 확인 하십시오.');
			return false; // 폼 전송 취소	
		}
		
		/*if(!isNickOk){
			alert('별명을 확인 하십시오.');
			return false; // 폼 전송 취소	
		}
		*/
		
		if(!isEmailOk){
			alert('이메일을 확인 하십시오.');
			return false; // 폼 전송 취소	
		}
		
		if(!isHpOk){
			alert('휴대폰을 확인 하십시오.');
			return false; // 폼 전송 취소	
		}
						
		return true; // 폼 전송 시작
	});
	
}); // 유효성 검증 끝