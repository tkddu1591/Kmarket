package kr.co.kmarket.controller.cs;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.KmCsNoticeDTO;
import kr.co.kmarket.dto.KmCsQnaDTO;
import kr.co.kmarket.service.KmCsNoticeService;
import kr.co.kmarket.service.KmCsQnaService;

@WebServlet(value = {"/cs", "/cs/", "/cs/index.do"})
public class IndexController extends HttpServlet{
	
	private static final long serialVersionUID = -562345946672080681L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private KmCsQnaService qnaService = KmCsQnaService.INSTANCE;
	private KmCsNoticeService noticeService = KmCsNoticeService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		List<KmCsQnaDTO> latestQnas = qnaService.selectLatests(5);
		List<KmCsNoticeDTO> latestNotices = noticeService.selectLatests(5);
		//List<KmCsFaqDTO> latests3 = service.selectLatests("story", 5);
		
		req.setAttribute("latestQnas", latestQnas);
		req.setAttribute("latestNotices", latestNotices);
		//req.setAttribute("latests3", latests3);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
		dispatcher.forward(req, resp);
	}
}
