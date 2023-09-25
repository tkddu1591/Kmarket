package kr.co.kmarket.controller.cs.qna;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
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

@WebServlet("/cs/qna/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = 6222507197183884921L;
	private String ctxPath;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsQnaService qnaService = KmCsQnaService.INSTANCE;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ctxPath = config.getServletContext().getContextPath();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		
		KmCsQnaDTO dto = qnaService.selectCsQna(no);
		
		HttpSession session = req.getSession();
		KmMemberDTO sessUser = (KmMemberDTO) session.getAttribute("sessUser");
		
		// 글 작성자가 아닐 경우
		if(sessUser==null || !sessUser.getUid().equals(dto.getWriter())) {
			resp.sendRedirect(ctxPath + "/cs/qna/list.do?success=101");
		}

		// 파일 삭제용 이름 
		List<String> file = dto.getFile();
		// 게시글 삭제 
		qnaService.deleteCsQna(no);
		
		// 파일 삭제(디렉터리) 
		if(file.size() != 0) {
			String path = qnaService.getCtxPath(req);
			for(String fileName : file) {
				qnaService.deletefile(path, fileName);
			}
		}
		

		resp.sendRedirect(ctxPath + "/cs/qna/list.do?success=100");
		
	}

	
}
