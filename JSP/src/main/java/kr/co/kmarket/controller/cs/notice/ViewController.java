package kr.co.kmarket.controller.cs.notice;

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

import kr.co.kmarket.dto.KmCsNoticeDTO;
import kr.co.kmarket.service.KmCsNoticeService;

@WebServlet("/cs/notice/view.do")
public class ViewController extends HttpServlet{

	private static final long serialVersionUID = 824910349917258619L;	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsNoticeService service = KmCsNoticeService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		service.updateHit(no);
		KmCsNoticeDTO dto = service.selectCsNotice(no);
		
//		logger.debug("notice 글 : " + dto.toString());

		req.setAttribute("group", "view");
		req.setAttribute("no", no);
		req.setAttribute("cate1", dto.getCate1());
		req.setAttribute("dto", dto);
		RequestDispatcher dispatcher = req.getRequestDispatcher("view.jsp");
		dispatcher.forward(req, resp);		
		
	}
}
