<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>
    $(document).ready(function(){
        const success = ${success}; // 파라미터를 이렇게 받을 수도 있네
        if(success == 200){ // LogoutController 에서 redirect로 login.do?success=200 했을 때 이 success 값을 변수로 지정하고 if문 진행
            alert('정상적으로 로그아웃 되었습니다.');
        } else if(success == 102){
            alert('관리자로 로그인해주세요.');
        }
    });
</script>
        <main id="member">
            <div class="login">
                <nav>
                    <h1>로그인</h1>                    
                </nav>
                
				<form action="${ctxPath}/member/login.do" method="post">

                    <table border="0">
                        <tr>
                            <td>아이디</td>
                            <td><input type="text" name="uid" placeholder="아이디 입력"></td>
                        </tr>
                        <tr>
                            <td>비밀번호</td>
                            <td><input type="password" name="pass" placeholder="비밀번호 입력"></td>
                        </tr>
                    </table>					
					<input type="submit" value="로그인" />
					<span>
                        <label><input type="checkbox" name="auto" value="1"/>자동 로그인</label> <!-- value값 추가, 1이면 checkbox 체크된 것 -->
						<a href="${ctxPath}/member/findId.do">아이디찾기</a>
						<a href="${ctxPath}/member/findPass.do">비밀번호찾기</a>
						<a href="${ctxPath}/member/join.do">회원가입</a>
					</span>

                    <a href="#" class="banner"><img src="./img/member_login_banner.jpg" alt="1만원 할인 쿠폰 받기"></a>

				</form>
				<img src="./img/member_certifi_logo.gif" alt="banner">
            </div>
        </main>        
<%@ include file="./_footer.jsp" %>