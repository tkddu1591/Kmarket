<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
        <main id="member">
            <div class="register">
                <nav>
                    <h1>일반 회원가입</h1>
                </nav>
				<form action="${ctxPath}/member/register.do" method="POST"> <!-- registerController에서 post로 수신해야하기 때문에, signup에서 register.do 로 넘어갈 때 get 전송하자  -->
					<section>
						<table>
							<caption>필수 정보입력</caption>
							<tr>
								<th><span class="essential">*</span>아이디</th>
								<td>
									<input type="text" name="km_uid" placeholder="아이디를 입력" required /> 
									<span class="msgId">영문, 숫자로 4~12자까지 설정해 주세요.</span>
									<span class="uidResult"></span>
								</td>
							</tr>
							<tr>
								<th><span class="essential">*</span>비밀번호</th>
								<td>
									<input type="password" name="km_pass" placeholder="비밀번호를 입력" required /> 
									<span class="msgPass">영문, 숫자, 특수문자를 조합하여 8~12자까지 설정해 주세요.</span>
								</td>
							</tr>
							<tr>
								<th><span class="essential">*</span>비밀번호확인</th>
								<td>
									<input type="password" name="km_pass" placeholder="비밀번호를 확인" required /> 
									<span class="msgPass">비밀번호 재입력</span>
									<span class="passResult"></span>
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
									<span class="nameResult"></span>
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
									<span class="resultEmail"></span>
								</td>
							</tr>
							<tr>
								<th><span class="essential">*</span>휴대폰</th>
								<td>
									<input type="text" name="km_hp" maxlength="13" placeholder="휴대폰번호 입력" required />
									 <span class="msgHp"> - 포함 13자리를 입력하세요.</span>
									 <span class="resultHp"></span>
								</td>
							</tr>
							<tr class="addr">
								<th>주소</th>
								<td>
									<div>
										<input type="text" name="km_zip" id="zip" placeholder="우편번호 입력 클릭" readonly />
									</div>
									<div>
										<input type="text" name="km_addr1" id="addr1" size="50"
											placeholder="주소를 검색하세요." readonly />
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