package kr.co.kmarket.controller.cs.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.kmarket.dto.KmCsQnaDTO;
import kr.co.kmarket.service.KmCsQnaService;

@WebServlet("/cs/qna/write.do")
public class WriteController extends HttpServlet{

	private static final long serialVersionUID = 7313779117121587822L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsQnaService service = KmCsQnaService.INSTANCE;

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
		
		MultipartRequest mr = service.uploadFile(req);

		// 폼 데이터 수신 
		String writer = mr.getParameter("writer");
		String cate1 = mr.getParameter("cate1");
		String cate2 = mr.getParameter("cate2");
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String[] fileName = new String[4];
		fileName[0] = mr.getOriginalFileName("file1");
		fileName[1] = mr.getOriginalFileName("file2");
		fileName[2] = mr.getOriginalFileName("file3");
		fileName[3] = mr.getOriginalFileName("file4");
		String ordNo = mr.getParameter("ordNo");
		String prodNo = mr.getParameter("prodNo");
		String parent = null;
		String answerComplete = null;
		String regip = req.getRemoteAddr();
		

		logger.debug("title : " + title);
		logger.debug("content : " + content);
		logger.debug("writer : " + writer);
		logger.debug("file1 : " + fileName[0]);
		logger.debug("file2 : " + fileName[1]);
		logger.debug("file3 : " + fileName[2]);
		logger.debug("file4 : " + fileName[3]);

		// 파일 수정 
		for(int i=0; i<fileName.length; i++) {
			if(fileName[i] != null) {
				fileName[i] = service.renameToFile(req, fileName[i]);
			}
		}
		
		// 글 DTO 생성
		KmCsQnaDTO dto = new KmCsQnaDTO();
		dto.setCate1(cate1);
		dto.setCate2(cate2);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile1(fileName[0]);
		dto.setFile2(fileName[1]);
		dto.setFile3(fileName[2]);
		dto.setFile4(fileName[3]);
		dto.setWriter(writer);
		dto.setOrdNo(ordNo);
		dto.setProdNo(prodNo);
		dto.setParent(parent);
		dto.setAnswerComplete(answerComplete);
		dto.setRegip(regip);

		// qna Insert 
		int qnaNo = service.insertQna(dto);
	
		logger.info("WriteController write INFO : " + dto.toString());
		resp.sendRedirect(ctxPath + "/admin/cs/qna/view.do?no="+qnaNo);		
		
		
	}
}
