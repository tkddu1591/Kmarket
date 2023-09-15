<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- 
	ctxPath
	 - Context Root 경로 전역변수 
	 - _header.jsp 5줄 참고
-->
<script>
	//auto hyphen
	const hypenTel = (target) => {
		target.value = target.value
				.replace(/[^0-9]/g, '')
				.replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
	}

</script>
<script src="${ctxPath}/member/js/zipcode.js"></script> <!-- script link가 아니라 src임 -->
<script src="${ctxPath}/member/js/validation.js"></script>
<script src="${ctxPath}/member/js/checkUser.js"></script>
        <main id="member">
            <div class="register">
                <nav>
                    <h1>일반 회원가입</h1>
                </nav>
				<form id="formMember" action="${ctxPath}/member/register.do" method="POST"> <!-- registerController에서 post로 수신해야하기 때문에, signup에서 register.do 로 넘어갈 때 get 전송하자  -->
					<section>
						<table>
							<caption>필수 정보입력</caption>
							<tr>
								<th><span class="essential">*</span>아이디</th>
								<td>
									<input type="text" name="km_uid" placeholder="아이디를 입력" required /> 
									<span class="msgId">영문, 숫자로 4~12자까지 설정해 주세요.</span>
									<span class="resultId"></span>
								</td>
							</tr>
							<tr>
								<th><span class="essential">*</span>비밀번호</th>
								<td>
									<input type="password" name="km_pass1" placeholder="비밀번호를 입력" required /> 
									<span class="msgPass">영문, 숫자, 특수문자를 조합하여 8~12자까지 설정해 주세요.</span>
								</td>
							</tr>
							<tr>
								<th><span class="essential">*</span>비밀번호확인</th>
								<td>
									<input type="password" name="km_pass2" placeholder="비밀번호를 확인" required /> 
									<span class="msgPass">비밀번호 재입력</span>
									<span class="resultPass"></span>
								</td>
							</tr>
						</table>
					</section>
					<section>
						<table>
							<caption>기본 정보입력</caption>
							<tr>
								<th><span class="essential">*</span>이름</th>
								<td>
									<input type="text" name="km_name" placeholder="이름을 입력" required /> 
									<span class="resultName"></span>
								</td>
							</tr>
							<tr>
								<th><span class="essential">*</span>성별</th>
								<td>
									<label><input type="radio" name="km_gender" value="1" checked>&nbsp;남</label> 
									<label><input type="radio" name="km_gender" value="2">&nbsp;여</label>
								</td> <!-- name을 똑같은걸로해야 radio 효과가 적용됨, name이 다르니까 km_gender / gender 되있어서 안됨-->
							</tr>
							<tr>
								<th><span class="essential">*</span>EMAIL</th>
								<td>
									<input type="email" name="km_email" placeholder="이메일 입력" required />
									<span id="resultEmail"></span>
								</td>
							</tr>
							<tr>
								<th><span class="essential">*</span>휴대폰</th>
								<td>
									<input type="text" name="km_hp"  oninput="hypenTel(this)" placeholder="휴대폰 번호 입력" required  minlength="13" maxlength="13"/>
									 <span class="msgHp"> - 포함 13자리를 입력하세요.</span>
									 <span id="resultHp"></span>
								</td>
							</tr>
							<tr class="addr">
								<th>주소</th>
								<td>
									<div>
										<input type="text" name="km_zip" id="zip" placeholder="우편번호 입력 클릭" onclick="zipcode()" readonly/> <!-- onclick="zipcode()" 이것만 추가해주면 된다. zipcode.js에서 function zipcode() -->
									</div>
									<div>
										<input type="text" name="km_addr1" id="addr1" size="50"
											placeholder="주소를 검색하세요." readonly/>
									</div>
									<div>
										<input type="text" name="km_addr2" id="addr2" size="50" placeholder="상세주소를 입력하세요." />
									</div>
								</td>
							</tr>
						</table>
		
					</section>
					<div>
						<input type="submit" class="join" value="회원가입" />
					</div>
				</form>
            </div>
        </main>        
<%@ include file="./_footer.jsp" %>