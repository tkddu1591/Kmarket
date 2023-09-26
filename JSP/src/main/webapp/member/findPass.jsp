<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script src="${ctxPath}/member/js/validation.js"></script>
<script src="${ctxPath}/member/js/authEmail.js"></script>
<script>
	$(function(){
		$('.btnNext').click(function(e){
			e.preventDefault();
			
			if(isEmailOk){
				$('#formFindPass').submit();
			}else{
				alert('이메일 인증을 수행하십시오');
			}
		});
	});
</script>
<main id="user">
    <section class="find findPass">
        <form id="formFindPass" action="${ctxPath}/member/findPass.do" method="post">
        	<input type="hidden" name="type" value="FIND_PASS"/>
            <table border="0">
                <caption>비밀번호 찾기</caption>                        
                <tr>
                    <td>아이디</td>
                    <td><input type="text" name="uid" placeholder="아이디 입력"/></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td>
                        <div>
                            <input type="email" name="email" placeholder="이메일 입력"/>
                            <button type="button" id="btnEmailCode" class="btnAuth">인증번호 받기</button>
                            <span class="resultEmailForPass"></span>
                        </div>
                        <div>
                            <input type="text" name="auth" disabled placeholder="인증번호 입력"/>
                            <button type="button" id="btnEmailAuth" class="btnConfirm">확인</button>
                        </div>
                    </td>
                </tr>                        
            </table>                                        
        </form>
        
        <p>
            비밀번호를 찾고자 하는 아이디와 이메일을 입력해 주세요.<br>                    
            회원가입시 입력한 아이디와 이메일 주소가 같아야, 인증번호를 받을 수 있습니다.<br>
            인증번호를 입력 후 확인 버튼을 누르세요.
        </p>

        <div>
            <a href="${ctxPath}/member/login.do" class="btn btnCancel">취소</a>
            <a href="#" class="btn btnNext">다음</a>
        </div>
    </section>
</main>
<%@ include file="./_footer.jsp" %>