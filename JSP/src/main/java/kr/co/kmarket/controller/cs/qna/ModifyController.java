package kr.co.kmarket.controller.cs.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.kmarket.dto.KmCsQnaDTO;
import kr.co.kmarket.dto.KmMemberDTO;
import kr.co.kmarket.service.KmCsQnaService;

@WebServlet("/cs/qna/modify.do")
public class ModifyController extends HttpServlet{
	
	private static final long serialVersionUID = -1280258657856389912L;
	private String ctxPath;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsQnaService service = KmCsQnaService.INSTANCE;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ctxPath = config.getServletContext().getContextPath();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");

		
		KmCsQnaDTO dto = service.selectCsQna(no);
		HttpSession session = req.getSession();
		KmMemberDTO sessUser = (KmMemberDTO) session.getAttribute("sessUser");
		/*
		// 글 작성자가 아닐 경우
		if(sessUser==null || !sessUser.getUid().equals(dto.getWriter())) {
			resp.sendRedirect(ctxPath + "/cs/qna/list.do?success=102");
			return;
		}
			*/	
		logger.debug("qna 글 : " + dto.toString());
		req.setAttribute("group", "write");
		req.setAttribute("no", no);
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("modify.jsp");
		dispatcher.forward(req, resp);		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		// 글 DTO 생성
		KmCsQnaDTO dto = service.uploadFile(req);
		
		// qnaUpdate 
		service.updateCsQna(dto);
	
		resp.sendRedirect(ctxPath + "/cs/qna/view.do?no="+dto.getQnaNo());		
		
	}
}
