package kr.co.kmarket.controller.admin.cs.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kmarket.dto.KmCsNoticeDTO;
import kr.co.kmarket.service.KmCsNoticeService;

@WebServlet("/admin/cs/notice/view.do")
public class ViewController extends HttpServlet{

	private static final long serialVersionUID = 5490256359092545978L;

	private KmCsNoticeService service = KmCsNoticeService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");

		KmCsNoticeDTO dto = service.selectCsNotice(no);
		
//		logger.debug("notice 글 : " + dto.toString());

		req.setAttribute("group", "view");
		req.setAttribute("no", no);
		req.setAttribute("dto", dto);
		RequestDispatcher dispatcher = req.getRequestDispatcher("view.jsp");
		dispatcher.forward(req, resp);		
		
	}
}
