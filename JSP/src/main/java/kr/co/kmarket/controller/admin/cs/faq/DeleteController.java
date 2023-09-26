package kr.co.kmarket.controller.admin.cs.faq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kmarket.service.KmCsFaqService;

@WebServlet("/admin/cs/faq/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = 4197282001844404843L;
	private KmCsFaqService service = KmCsFaqService.INSTANCE;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		
		service.deleteCsFaq(no);
		service.removeRelatedFaq(no);
		

		resp.sendRedirect("/JSP/admin/cs/faq/list.do?success=200");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] chks = req.getParameterValues("chk");

		for(String no : chks) {
			service.deleteCsFaq(no);
			service.removeRelatedFaq(no);
		}
		
		resp.sendRedirect("/JSP/admin/cs/faq/list.do?success=200");
	}
}
