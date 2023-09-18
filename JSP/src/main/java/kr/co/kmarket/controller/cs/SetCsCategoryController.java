package kr.co.kmarket.controller.cs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.KmCsCate1DTO;
import kr.co.kmarket.dto.KmCsCate2DTO;
import kr.co.kmarket.service.KmCsCateService;


@WebServlet("/cs/setCsCategory.do")
public class SetCsCategoryController extends HttpServlet{

	private static final long serialVersionUID = -7801074301995661710L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsCateService service = KmCsCateService.INSTANCE;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		Map<String, Object> sessCsCates = (Map<String, Object>) session.getAttribute("sessCsCates");
		
		
		JSONObject jsonObject = new JSONObject(sessCsCates);
		
		resp.setContentType("text/html; charset=UTF-8");

		//JSON 출력
		PrintWriter writer = resp.getWriter();
		writer.print(jsonObject.toString());
		
		
	}

}
