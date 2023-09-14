package kr.co.kmarket.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.KmMemberDTO;
import kr.co.kmarket.service.KmMemberService;

@WebServlet("/member/registerSeller.do") //servlet은 webapp이 시작단위
public class RegisterSellerController extends HttpServlet{

	private static final long serialVersionUID = -2026989742877369639L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmMemberService service =  KmMemberService.instance; 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/registerSeller.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("km_uid");
		String pass = req.getParameter("km_pass");
		String company = req.getParameter("kms_company");
		String ceo = req.getParameter("kms_ceo");
		String comRegNum = req.getParameter("kms_corp_reg");
		String bizRegNum = req.getParameter("kms_online_reg");
		String tel = req.getParameter("kms_tel");
		String fax = req.getParameter("kms_fax");
		String email = req.getParameter("kms_email");
		String zip = req.getParameter("kms_zip");
		String addr1 = req.getParameter("kms_addr1");
		String addr2 = req.getParameter("kms_addr2");
		String name = req.getParameter("km_name");
		String gender = req.getParameter("km_gender");
		String hp = req.getParameter("km_hp");
		String regip = req.getRemoteAddr();
		
		logger.debug(uid);
		logger.debug(pass);
		logger.debug(company);
		logger.debug(ceo);
		logger.debug(comRegNum);
		logger.debug(bizRegNum);
		logger.debug(tel);
		logger.debug(fax);
		logger.debug(email);
		logger.debug(zip);
		logger.debug(addr1);
		logger.debug(addr2);
		logger.debug(name);
		logger.debug(gender);
		logger.debug(hp);
		logger.debug(regip);
		
		KmMemberDTO dto = new KmMemberDTO();
		dto.setUid(uid);
		dto.setPass(pass);
		dto.setName(name); // name, gender, hp NOT NULL 이라 속성 지정해줘야함
		dto.setGender(gender);
		dto.setHp(hp);
		dto.setEmail(email);
		dto.setType(2); //type 직접 지정해주기, 판매자회원 : 2
		dto.setZip(zip);
		dto.setAddr1(addr1);
		dto.setAddr2(addr2);
		dto.setCompany(company);
		dto.setCeo(ceo);
		dto.setBizRegNum(bizRegNum);
		dto.setComRegNum(comRegNum);
		dto.setTel(tel);
		dto.setManager(name);
		dto.setManagerHp(hp);
		dto.setFax(fax);
		dto.setRegIp(regip);
		
		service.insertMember(dto);
		
		resp.sendRedirect("JSP/member/login.do"); // ${ctxPath} 참조 못한다고!! 절대경로
	}
}
