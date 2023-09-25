package kr.co.kmarket.controller.cs.faq;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.KmCsFaqDTO;
import kr.co.kmarket.dto.KmCsFaqRateDTO;
import kr.co.kmarket.dto.KmMemberDTO;
import kr.co.kmarket.service.KmCsFaqRateService;
import kr.co.kmarket.service.KmCsFaqService;

@WebServlet("/cs/faq/view.do")
public class ViewController extends HttpServlet{

	private static final long serialVersionUID = -3924872026552456268L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsFaqService service = KmCsFaqService.INSTANCE;
	private KmCsFaqRateService rateService = KmCsFaqRateService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		
		String no = req.getParameter("no");
		service.updateHit(no);
		KmCsFaqDTO dto = service.selectCsFaq(no);
		KmCsFaqDTO relatedFAQ = service.selectCsFaq(dto.getRelatedFaq()+"");
		
		//유저가 평가했는지 		
		HttpSession session = req.getSession();
		KmMemberDTO sessUser = (KmMemberDTO) session.getAttribute("sessUser");
		boolean isLogin = false;
		KmCsFaqRateDTO rateDTO = null;
		if(sessUser!=null) {
			isLogin = true;
			rateDTO = rateService.selectCsFaqRate(no, sessUser.getUid());
		}
		boolean isRated = false;
		int userRate = -1;
		if(rateDTO!=null && rateDTO.getUid() !=null) {
			//평가했다면 
			isRated = true;
			userRate = rateDTO.getRate();
			logger.debug(rateDTO.getRate()+"/" + rateDTO.getUid());
		}
		//전체 유저 평가 반응 
		int[] faqRatesAll = rateService.selectCsFaqRates(no);
		
		req.setAttribute("dto", dto);
		req.setAttribute("relatedFAQ", relatedFAQ);
		req.setAttribute("group", "view");
		req.setAttribute("cate1", dto.getCate1());

		req.setAttribute("isLogin", isLogin);
		req.setAttribute("isRated", isRated);
		req.setAttribute("userRate", userRate);
		req.setAttribute("faqRatesAll", faqRatesAll);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("view.jsp");
		dispatcher.forward(req, resp);
	}
	
	
}
