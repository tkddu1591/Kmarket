package kr.co.kmarket.controller.cs.qna;

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

import kr.co.kmarket.dto.KmCsQnaDTO;
import kr.co.kmarket.dto.KmMemberDTO;
import kr.co.kmarket.service.KmCsQnaService;

@WebServlet("/cs/qna/view.do")
public class ViewController extends HttpServlet{

	private static final long serialVersionUID = 824910349917258619L;	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsQnaService service = KmCsQnaService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		String cate1 = req.getParameter("cate1");

		HttpSession session = req.getSession();
		KmMemberDTO sessUser = (KmMemberDTO) session.getAttribute("sessUser");
		
		KmCsQnaDTO dto = service.selectCsQna(no);
		if(sessUser == null) {
			resp.sendRedirect("list.do?success=301");
		}else if(!dto.getWriter().equals(sessUser.getUid()) && sessUser.getType() != 9 ) {
			//작성자 본인이나 관리자가 아니면 
			resp.sendRedirect("list.do?success=301");
		} else {
		
			KmCsQnaDTO answer = service.selectCsQnaAnswer(no);//}
			
			logger.debug("qna 글 : " + dto.toString());
			/*
			if(dto.getAnswerComplete() == 2) {
			 
				logger.debug("qna 답변 : " + answer.toString());
				
			}
			*/
			req.setAttribute("group", "view");
			req.setAttribute("no", no);
			req.setAttribute("cate1", cate1);
			req.setAttribute("dto", dto);
			req.setAttribute("answer", answer);
			RequestDispatcher dispatcher = req.getRequestDispatcher("view.jsp");
			dispatcher.forward(req, resp);	
		
		}
	}
	
}
