<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script src="${ctxPath}/member/js/validation.js"></script>
<script>
	window.onload = function(){
		const btnPassChange = document.getElementById('btnPassChange');
		const formFindPassChange = document.getElementById('formFindPassChange');
		
		btnPassChange.onclick = function(e){
			e.preventDefault();
			
			if(isPassOk){
				formFindPassChange.submit();
			}else{
				alert('변경 비밀번호가 유효하지 않거나 일치하지 않습니다.');
			}
		}
	}
</script>

<main id="user">
    <section class="find findPassChange">
        <form id="formFindPassChange" action="${ctxPath}/member/findPassChange.do" method="post">
        	<input type="hidden" name="uid" value="${sessionScope.uid}"> <!-- FindPassController에서 session 만든 걸 여기서 이용하네 -->
            <table border="0">
                <caption>비밀번호 변경</caption>                        
                <tr>
                    <td>아이디</td>
                    <td>${uid}</td>
                </tr>
                <tr>
                    <td>새 비밀번호</td>
                    <td>
                        <input type="password" name="km_pass1" placeholder="새 비밀번호 입력"/> <!-- validation.js에서 pass1, pass2가 아니라 km_pass1, km_pass2로 찾는다. -->
                    </td>
                </tr>
                <tr>
                    <td>새 비밀번호 확인</td>
                    <td>
                        <input type="password" name="km_pass2" placeholder="새 비밀번호 입력 확인"/>
                        <span class="resultPass"></span> <!-- validation.js에서 resultPass에 글자 표시하는 거 놓치면 안되지! -->
                    </td>
                </tr>
            </table>                                        
        </form>
        
        <p>
            비밀번호를 변경해 주세요.<br>
            영문, 숫자, 특수문자를 사용하여 8자 이상 입력해 주세요.                    
        </p>

        <div>
            <a href="${ctxPath}/member/login.do" class="btn btnCancel">취소</a>
            <a href="#" id="btnPassChange" class="btn btnNext">비밀번호 변경</a>
        </div>
    </section>
</main>
<%@ include file="./_footer.jsp" %>