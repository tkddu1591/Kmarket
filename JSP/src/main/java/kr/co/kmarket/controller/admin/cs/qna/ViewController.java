package kr.co.kmarket.controller.admin.cs.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kmarket.dto.KmCsQnaDTO;
import kr.co.kmarket.service.KmCsQnaService;

@WebServlet("/admin/cs/qna/view.do")
public class ViewController extends HttpServlet{


	private static final long serialVersionUID = -199877794397725844L;
	private KmCsQnaService service = KmCsQnaService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");

		KmCsQnaDTO dto = service.selectCsQna(no);
		if(dto.getAnswerComplete() == 2) {
			KmCsQnaDTO answerDto = service.selectCsQnaAnswer(no);
			req.setAttribute("answerDto", answerDto);
		}
//		logger.debug("notice 글 : " + dto.toString());

		req.setAttribute("no", no);
		req.setAttribute("dto", dto);
		RequestDispatcher dispatcher = req.getRequestDispatcher("view.jsp");
		dispatcher.forward(req, resp);		
		
	}
}
