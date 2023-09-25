package kr.co.kmarket.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.cj.util.StringUtils;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.kmarket.dao.KmCsQnaDAO;
import kr.co.kmarket.dto.KmCsQnaDTO;
public enum KmCsQnaService {
	
	INSTANCE;
	
	private String qnaUploadPath = "/upload/qna";
	
	private KmCsQnaDAO dao = KmCsQnaDAO.getInstance();
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int insertCsQna(KmCsQnaDTO dto) {
		return dao.insertCsQna(dto);
	}
	public int insertCsQnaAnswer(KmCsQnaDTO dto) {
		return dao.insertCsQnaAnswer(dto);
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
	public List<KmCsQnaDTO> selectCsQnaListForAdmin(String cate1, String cate2, int start) {
		return dao.selectCsQnaListForAdmin(cate1, cate2, start);
	}
	public int selectCsQnaCountForAdmin(String cate1, String cate2) {
		return dao.selectCsQnaCountForAdmin(cate1, cate2);
	}
	public int selectCsQnaCount(String cate1) {
		return dao.selectCsQnaCount(cate1);
	}
	public List<KmCsQnaDTO> selectLatests(int size){
		return dao.selectLatests(size);
	}

	public void updateCsQna(KmCsQnaDTO dto) {
		dao.updateCsQna(dto);
	}
	public int updateCsQnaAnswer(KmCsQnaDTO dto) {
		return dao.updateCsQnaAnswer(dto);
	}
	public int updateCsQnaAnswerComplete(String no, String status) {
		return dao.updateCsQnaAnswerComplete(no, status);
	}
	public void deleteCsQna(String no) {
		dao.deleteCsQna(no);
	}
	
	//추가

	// 업로드 경로 구하기 
	public String getQnaFilePath(HttpServletRequest req) {
		// 파일 업로드 경로 구하기
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath(qnaUploadPath);		
		return path;
	}
	// 업로드 경로 구하기 
	public String getCtxPath(HttpServletRequest req) {
		// 파일 업로드 경로 구하기
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/");		
		return path;
	}

	public KmCsQnaDTO uploadFile(HttpServletRequest req) {
		int maxSize = 10*1024*1024; // 10mb
		String realPath = getQnaFilePath(req);
		String ctxPath = getCtxPath(req);
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
					inputs.put(item.getFieldName(), item.getString());
				} else {
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

		// 파일 리스트 업데이트 & 삭제  
		List<String> existedFileList = new ArrayList<>();
		List<String> deletedFileList = new ArrayList<>();
		List<String> modifiedFinalFileList = new ArrayList<>();
 		
		for(int i=0; i<4; i++) {
			existedFileList.add(inputs.get("existedFile"+(i+1)+"_1"));
			deletedFileList.add(inputs.get("existedFile"+(i+1)+"_2"));
			
			if(existedFileList.get(i) !=null &&  deletedFileList.get(i) != null) {
				// 기존 첨부된 파일이 삭제되지 않음 
				modifiedFinalFileList.add(existedFileList.get(i));
			} else if(existedFileList.get(i) !=null &&  deletedFileList.get(i) == null){
				// 기존 첨부된 파일이 삭제됨 -> 파일 삭제
				deletefile(ctxPath,  existedFileList.get(i));
			}
			logger.debug("existedFileList " + i + " : " + existedFileList.get(i));
			logger.debug("deletedFileList " + i + " : " + deletedFileList.get(i));
			
			if(files.size() > i) {
				files.set(i,renameToFile(req, files.get(i)));
				logger.debug(files.get(i) + "/" + i +"번째 파일");
			} else {
				files.add(null);
			}
		}

		// modify 
		if(inputs.get("type") !=null ){
			modifiedFinalFileList.addAll(files);
			logger.debug("deletedFileList  " + deletedFileList.toString());
			files = modifiedFinalFileList;
		}
		
		String regip = req.getRemoteAddr();
		// 글 DTO 생성
		KmCsQnaDTO dto = new KmCsQnaDTO();
		if(inputs.get("no")!=null)dto.setQnaNo(inputs.get("no"));
		if(inputs.get("cate1")!=null)dto.setCate1(inputs.get("cate1"));
		if(inputs.get("cate2")!=null)dto.setCate2(inputs.get("cate2"));
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
		
		return dto;
	}
	// 파일명 수정 
	public String renameToFile(HttpServletRequest req, String oName) {
		
		String path = getQnaFilePath(req);
		
		int i = oName.lastIndexOf(".");
		String ext = oName.substring(i); //확장자 (.포함)
		
		String uuid = UUID.randomUUID().toString();
		String sName = uuid + ext;
		
		File f1 = new File(path + "/" + oName); //oName으로 저장된 파일 객체 
		File f2 = new File(path + "/" + sName); //가상의 파일 객체
		
		// 파일명 수정 
		f1.renameTo(f2); //f2로 파일명 수정
		
		return qnaUploadPath + "/" + sName;
	}
	//파일 삭제 
	public void deletefile(String path, String fileName) {
		File f = new File(path + "/" + fileName);
		if(f.exists()) {
			f.delete();
			logger.debug("파일 삭제 : " + fileName);
		}
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
			String path = getCtxPath(req);
			
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
