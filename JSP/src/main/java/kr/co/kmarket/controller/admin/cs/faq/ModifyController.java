package kr.co.kmarket.controller.admin.cs.faq;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.KmCsFaqDTO;
import kr.co.kmarket.service.KmCsFaqService;

@WebServlet("/admin/cs/faq/update.do")
public class ModifyController extends HttpServlet {
	
	private static final long serialVersionUID = -2638823186705519053L;
	private KmCsFaqService service = KmCsFaqService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");

		KmCsFaqDTO dto = service.selectCsFaq(no);
		if(dto.getRelatedFaq() != 0) {
			KmCsFaqDTO relatedDto = service.selectCsFaq(dto.getRelatedFaq()+"");
			req.setAttribute("relatedDto", relatedDto);
			//logger.debug("연관 문의 : " + relatedDto.toString());
		}
//		logger.debug("notice 글 : " + dto.toString());

		req.setAttribute("no", no);
		req.setAttribute("dto", dto);
		RequestDispatcher dispatcher = req.getRequestDispatcher("update.jsp");
		dispatcher.forward(req, resp);		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 파일 업로드 및 dto 생성 
		String no = req.getParameter("no");
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String relatedFaq = req.getParameter("relatedFaq");
		String writer = req.getParameter("writer");
		String regip = req.getRemoteAddr();
		KmCsFaqDTO dto = new KmCsFaqDTO();
		dto.setFaqNo(no);
		dto.setCate1(cate1);
		dto.setCate2(cate2);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setRelatedFaq(relatedFaq);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		
		// faq Insert 
		service.updateCsFaq(dto);
	
		logger.debug("WriteController write INFO : " + dto.toString());
		resp.sendRedirect("/JSP/admin/cs/faq/view.do?no="+no);		
	}
}
