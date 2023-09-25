package kr.co.kmarket.controller.admin.cs.qna;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kmarket.service.KmCsQnaService;

@WebServlet("/admin/cs/qna/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = 893248497209617753L;
	private KmCsQnaService service = KmCsQnaService.INSTANCE;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		
		service.deleteCsQna(no);
		

		resp.sendRedirect("/JSP/admin/cs/qna/list.do?success=200");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] chks = req.getParameterValues("chk");

		for(String no : chks) {
			service.deleteCsQna(no);
		}
		
		resp.sendRedirect("/JSP/admin/cs/qna/list.do?success=200");
	}
}
