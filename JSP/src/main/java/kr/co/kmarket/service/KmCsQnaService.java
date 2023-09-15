package kr.co.kmarket.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.cj.util.StringUtils;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.kmarket.dao.KmCsQnaDAO;
import kr.co.kmarket.dto.KmCsQnaDTO;
public enum KmCsQnaService {
	
	INSTANCE;
	private KmCsQnaDAO dao = KmCsQnaDAO.getInstance();
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int insertCsQna(KmCsQnaDTO dto) {
		return dao.insertCsQna(dto);
	}
	public KmCsQnaDTO selectCsQna(String no) {
		return dao.selectCsQna(no);
	}
	public KmCsQnaDTO selectCsQnaAnswer(String parent) {
		return dao.selectCsQnaAnswer(parent);
	}
	public List<KmCsQnaDTO> selectCsQnaList(String cate1, int start) {
		return dao.selectCsQnaList(cate1, start);
	}
	public int selectCsQnaCount(String cate1) {
		return dao.selectCsQnaCount(cate1);
	}

	public void updateCsQna(KmCsQnaDTO dto) {
		dao.updateCsQna(dto);
	}
	public void deleteCsQna(String no) {
		dao.deleteCsQna(no);
	}
	
	//추가
	
	// 업로드 경로 구하기 
	public String getFilePath(HttpServletRequest req) {
		// 파일 업로드 경로 구하기
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/upload");		
		return path;
	}

	public MultipartRequest uploadFile(HttpServletRequest req) {
		//파일 경로 구하기 
		String path = getFilePath(req);

		// 최대 업로드 파일 크기
		int maxSize = 1024 * 1024 * 5; //5MB 
		MultipartRequest mr = null;
		
		try {
			// 파일 업로드 및 Multipart 객체 생성   (스트림 처리)
			mr = new MultipartRequest(req,  	//request 객체
									  path, 	//파일경로
									  maxSize, 	//파일Max크기
									  "UTF-8", 	//인코딩(UTF-8)
									  new DefaultFileRenamePolicy()); //defaultRename

			logger.debug("path : " + path);
			
			/*
			// 다중 파일 업로드 -모든 part들을 가져옴
			Collection<Part> parts = req.getParts();
		
			for(Part part : parts) {
				//파일에 저장하기 
				if(StringUtils.hasWildcards(part.getSubmittedFileName())) {
					String fullPath = path + part.getSubmittedFileName();
					part.write(fullPath);
					logger.debug("fullPath : " + fullPath);
				}
			}
			*/
			/*
		    String[] params = mr.getParameterValues("fileUpload");
			*/
		} catch (Exception e) {
			logger.error("error qna - uploadFile() : " + e.getMessage());
		}
		return mr;
	}
	// 파일명 수정 
	public String renameToFile(HttpServletRequest req, String oName) {
		
		String path = getFilePath(req);
		
		int i = oName.lastIndexOf(".");
		String ext = oName.substring(i); //확장자 (.포함)
		
		String uuid = UUID.randomUUID().toString();
		String sName = uuid + ext;
		
		File f1 = new File(path + "/" + oName); //oName으로 저장된 파일 객체 
		File f2 = new File(path + "/" + sName); //가상의 파일 객체
		
		// 파일명 수정 
		f1.renameTo(f2); //f2로 파일명 수정
		
		return sName;
	}

	// 파일 다운로드 
	public int downloadFile(HttpServletRequest req, HttpServletResponse resp, String fileName)  {

		int result = 0;
		/** 다운로드 처리 로직 **/
		try {
			//resp 파일 다운로드 헤더 수정 
			resp.setContentType("application/octet-stream"); ///resp = 서버에서 클라이언트로 넘어가는 내장 객체 . /// application파일이다. 
			resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileName, "utf-8")); ////파일에 대한 정보 
			resp.setHeader("Content-Transfer-Encoding", "binary");
			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "private");
			
			//resp 파일 스트림 작업 
			String path = getFilePath(req);
			
			File file = new File(path+"/"+ fileName);
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)); /// /upload폴더에서 읽어옴 
			BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());/// resp객체를 통해 사용자에게 보냄
			
			while(true){
				int data = bis.read();
				if(data == -1){
					break;
				}
				bos.write(data);
			}
			bos.close();
			bis.close();
			result = 1;
		} catch (Exception e) {
			logger.error("error downloadFile() : " + e.getMessage());
		}
		return result;
	}
}
