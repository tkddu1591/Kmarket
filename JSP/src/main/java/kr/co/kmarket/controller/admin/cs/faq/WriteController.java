package kr.co.kmarket.controller.admin.cs.faq;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import kr.co.kmarket.dto.KmCsFaqDTO;
import kr.co.kmarket.service.KmCsFaqService;

@WebServlet("/admin/cs/faq/write.do")
public class WriteController extends HttpServlet{

	private static final long serialVersionUID = 7313779117121587822L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsFaqService service = KmCsFaqService.INSTANCE;

	private String ctxPath;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ctxPath = config.getServletContext().getContextPath();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("group", "write");
		RequestDispatcher dispatcher = req.getRequestDispatcher("write.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			// 파일 업로드 및 dto 생성 
			String cate1 = req.getParameter("cate1");
			String cate2 = req.getParameter("cate2");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String relatedFaq = req.getParameter("relatedFaq");
			String writer = req.getParameter("writer");
			String regip = req.getRemoteAddr();
			KmCsFaqDTO dto = new KmCsFaqDTO();
			dto.setCate1(cate1);
			dto.setCate2(cate2);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setRelatedFaq(relatedFaq);
			dto.setWriter(writer);
			dto.setRegip(regip);
			
			
			// faq Insert 
			int faqNo = service.insertCsFaq(dto);
		
			logger.debug("WriteController write INFO : " + dto.toString());
			resp.sendRedirect(ctxPath + "/admin/cs/faq/view.do?no="+faqNo);		
			
	}
}
