package kr.co.kmarket.controller.cs.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.kmarket.service.KmCsQnaService;

@WebServlet("/cs/qna/fileDownload.do")
public class FileDownloadContoller extends HttpServlet{

	private static final long serialVersionUID = 3520163111660989970L;

	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	private KmCsQnaService service = KmCsQnaService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//파일 조회 
		String fileName = req.getParameter("fileName");
		
		
		int result = service.downloadFile(req, resp, fileName);
		
	}
	
}
