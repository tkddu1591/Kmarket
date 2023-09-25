<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script src="${ctxPath}/member/js/validation.js"></script> <!-- vlidation.js를 왜 참조해야하지?? / 아, isEmailOk 변수 선언하기 때문이다!!-->
<script src="${ctxPath}/member/js/authEmail.js"></script> <!-- 그리고 validation에서 선언된 isEmailOk를 authEmail.js에서 참조하기 위해 validation.js보다 밑에 쓴다 -->
<script>
	$(function(){ // onload 빠트리지말기!
		
		$('.btnNext').click(function(e){
			
			e.preventDefault; // 우선 a태그 기능을 막아야지!
			
			//alert('click!');
			
			if(isEmailOk){
				
				$('#formFindId').submit();
			
			}else{
				alert('이메일 인증을 수행하십시오');
			}
		});	
	});
</script>
<main id="user">
    <section class="find findId">
        <form id="formFindId" action="${ctxPath}/member/findIdResult.do" method="POST">
        	<input type="hidden" name="type" value="FIND_ID"/>
            <table border="0">
                <caption>아이디 찾기</caption>
                <tr>
                    <td>이름</td>
                    <td><input type="text" name="name" placeholder="이름 입력"/></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td>
                        <div>
                            <input type="email" name="email" placeholder="이메일 입력"/> <!-- disabled 삭제 -->
                            <button type="button" id="btnEmailCode" class="btnAuth">인증번호 받기</button>
                            <span class="resultEmailForId"></span>
                        </div>
                        <div>
                            <input type="text" name="auth" placeholder="인증번호 입력"/>
                            <button type="button" id="btnEmailAuth" class="btnConfirm">확인</button> <!-- type="button" submit으로 수정 -->
                        </div>
                    </td>
                </tr>                        
            </table>                                        
        </form>
        
        <p>
            회원가입시 이메일 주소와 입력한 이메일 주소가 같아야, 인증번호를 받을 수 있습니다.<br>
            인증번호를 입력 후 확인 버튼을 누르세요.
        </p>

        <div>
            <a href="${ctxPath}/member/login.do" class="btn btnCancel">취소</a>
            <a href="#" class="btn btnNext">다음</a> <!-- script 작성으로 a태그를 통해 form 전송한다 -->
        </div>
    </section>
</main>
<%@ include file="./_footer.jsp" %>