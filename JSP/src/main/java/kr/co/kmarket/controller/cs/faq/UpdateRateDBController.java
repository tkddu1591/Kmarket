package kr.co.kmarket.controller.cs.faq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.kmarket.dto.KmCsFaqRateDTO;
import kr.co.kmarket.service.KmCsFaqRateService;
import kr.co.kmarket.service.KmCsFaqService;

@WebServlet("/cs/faq/updateRateDB.do")
public class UpdateRateDBController extends HttpServlet {

	private static final long serialVersionUID = -3787884902226798989L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsFaqService service = KmCsFaqService.INSTANCE;
	private KmCsFaqRateService rateService = KmCsFaqRateService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		String userRate = req.getParameter("userRate");
		String uid = req.getParameter("uid");
		
		KmCsFaqRateDTO dto = new KmCsFaqRateDTO();
		dto.setFaqNo(no);
		dto.setRate(userRate);
		dto.setUid(uid);
		//기존 평점이 있다면 삭제하고 
		rateService.deleteCsFaqRate(dto);
		//새로운 평점 부여 
		int result = rateService.insertCsFaqRate(dto);

		//JSON 출력
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
		
	}
}
