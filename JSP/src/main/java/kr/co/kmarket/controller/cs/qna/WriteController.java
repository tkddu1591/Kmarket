package kr.co.kmarket.controller.cs.qna;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileUploadException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



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
		
			// 파일 업로드 및 dto 생성 
			KmCsQnaDTO dto = service.uploadFile(req);
			
			// qna Insert 
			int qnaNo = service.insertCsQna(dto);
		
			logger.info("WriteController write INFO : " + dto.toString());
			resp.sendRedirect(ctxPath + "/cs/qna/view.do?no="+qnaNo);		
			
	}
}
