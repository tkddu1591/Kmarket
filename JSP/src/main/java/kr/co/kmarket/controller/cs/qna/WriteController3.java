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

@WebServlet("/cs/qna/write3.do")
public class WriteController3 extends HttpServlet{

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
		RequestDispatcher dispatcher = req.getRequestDispatcher("write3.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int maxSize = 10*1024*1024; // 10mb
		ServletContext context = req.getServletContext();
		String realPath = service.getFilePath(req);
		ArrayList saveFiles = new ArrayList();
		ArrayList origFiles = new ArrayList();
		
		Map<String, String> inputs = new HashMap<>();
		List<String> files = new ArrayList<>();


			try {
				
				DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
				diskFileItemFactory.setRepository(new File(realPath));
				diskFileItemFactory.setSizeThreshold(maxSize);
				diskFileItemFactory.setDefaultCharset("utf-8");
				ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);


				List<FileItem> items = fileUpload.parseRequest(req);
				for (FileItem item : items) {
					if (item.isFormField()) {
						logger.debug(String.format("[파일형식이 아닌 파라미터] 파라미터명: %s, 파일 명: %s, 파일크기: %s bytes <br>", item.getFieldName(), item.getString(), item.getSize()));
						inputs.put(item.getFieldName(), item.getString());
					} else {
						logger.debug(String.format("[파일형식 파라미터] 파라미터명: %s, 파일 명: %s, 파일 크기: %s bytes <br>", item.getFieldName(), item.getName(), item.getSize()));
						if (item.getSize() > 0) {
							String separator = File.separator;
							int index = item.getName().lastIndexOf(separator);
							String fileName = item.getName().substring(index + 1);
							File uploadFile = new File(realPath + separator + fileName);
							item.write(uploadFile);
							files.add(item.getName());
							logger.debug(item.getName());
						}
					}

				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 파일 수정 
			for(int i=0; i<4; i++) {
				if(files.size() > i) {
					files.set(i,service.renameToFile(req, files.get(i)));
					logger.debug(files.get(i) + "/" + i +"번째 파일");
				} else {
					files.add(null);
				}
				
			}
			String regip = req.getRemoteAddr();
			// 글 DTO 생성
			KmCsQnaDTO dto = new KmCsQnaDTO();
			dto.setCate1(inputs.get("cate1"));
			dto.setCate2(inputs.get("cate2"));
			dto.setTitle(inputs.get("title"));
			dto.setContent(inputs.get("content"));
			dto.setFile1(files.get(0));
			dto.setFile2(files.get(1));
			dto.setFile3(files.get(2));
			dto.setFile4(files.get(3));
			dto.setWriter(inputs.get("writer"));
			dto.setOrdNo(inputs.get("ordNo"));
			dto.setProdNo(inputs.get("prodNo"));
			dto.setParent(inputs.get("parent"));
			dto.setAnswerComplete(inputs.get("answerComplete"));
			dto.setRegip(regip);
			
			
			// qna Insert 
			int qnaNo = service.insertCsQna(dto);
		
			logger.info("WriteController write INFO : " + dto.toString());
			resp.sendRedirect(ctxPath + "/cs/qna/view.do?no="+qnaNo);		
			
	}
}