package kr.co.kmarket.service;

import java.net.Authenticator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dao.KmMemberDAO;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.KmMemberDTO;

public enum KmMemberService {
	instance;
	
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmMemberDAO dao = new KmMemberDAO();
	private static String generatedCode; // 인증코드 생성시 필요, 왜 static(정적변수)??
	
public void insertMember(KmMemberDTO dto) {
		dao.insertMember(dto);
	}
	
	public KmMemberDTO selectMember(String uid, String pass) {
		return dao.selectMember(uid, pass);
	}
	
	public KmMemberDTO selectMemberByUid(String uid) {
		return dao.selectMemberByUid(uid);
	}
	
	public List<KmMemberDTO> selectMembers() {
		return dao.selectMembers();
	}
	
	public void updateMember(KmMemberDTO dto) {
		dao.updateMember(dto);
	}
	
	public void deleteMember(String uid) {
		dao.deleteMember(uid);
	}
	
	// 추가
	public int selectCountUid(String uid) {
		return dao.selectCountUid(uid);
	}
	
	public int selectCountHp(String hp) {
		return dao.selectCountHp(hp); // selectCountUid 되 있어서 휴대폰 중복체크 안 됐던 것
	}
	
	public int selectCountEmail(String email) {
		return dao.selectCountEmail(email);
	}
	
	public KmMemberDTO selectMemberByNameAndEmail(String name, String email) {
		return dao.selectMemberByNameAndEmail(name, email);
	}
	
	public int selectCountNameAndEmail(String name, String email) {
		return dao.selectCountNameAndEmail(name, email);
	}
	
	public int selectCountUidAndEmail(String uid, String email) {
		return dao.selectCountUidAndEmail(uid, email);
	}
	
	public int sendCodeByEmail(String receiver) { // dao에서 가져오는 거 아님
		
		// 인증코드 생성
		int code = ThreadLocalRandom.current().nextInt(100000, 1000000);		
		generatedCode = String.valueOf(code);
		
		// 기본정보
		String sender = "hansan9611@gmail.com";
		String password = "cdcelknzyvxywzqb";
		String title = "Kmarket 인증코드 입니다.";
		String content = "<h1>인증코드는 " + code + "</h1>";
		
		// Gmail SMTP 서버 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		// Gmail STMP 세션 생성
		Session gmailSession = Session.getInstance(props, new javax.mail.Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});
		
		// 메일 발송
		int status = 0;
		Message message = new MimeMessage(gmailSession);
		logger.debug("인증코드 : " + generatedCode);
		try{
			logger.info("here1...");
			message.setFrom(new InternetAddress(sender, "보내는 사람", "UTF-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html;charset=UTF-8");
			Transport.send(message);
			status = 1; // 메일 발송하면 status 1로 하네
			
		}catch(Exception e){
			status = 0;
			logger.error("sendCodeByEmail() error : " + e.getMessage());
		}
		
		return status;
	}// sendCodeByEmail end
	
	public int confirmCodeByEmail(String code) {
		
		if(code.equals(generatedCode)) {
			logger.info("return 1...");
			return 1;
		}else {
			logger.info("return 0...");
			return 0;
		}
	}
	
	public void updatePass(String uid, String pass) {
		dao.updatePass(uid, pass);
	}

	public void updatePoint(String ordUid, int point) {
	dao.updatePoint(ordUid, point);
	}
}
