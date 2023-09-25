package kr.co.kmarket.controller.admin.cs.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kmarket.service.KmCsNoticeService;

@WebServlet("/admin/cs/notice/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = 4197282001844404843L;
	private KmCsNoticeService service = KmCsNoticeService.INSTANCE;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		
		service.deleteCsNotice(no);
		

		resp.sendRedirect("/JSP/admin/cs/notice/list.do?success=200");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] chks = req.getParameterValues("chk");

		for(String no : chks) {
			service.deleteCsNotice(no);
		}
		
		resp.sendRedirect("/JSP/admin/cs/notice/list.do?success=200");
	}
}
