package kr.co.kmarket.service;

import kr.co.kmarket.dao.KmProductDAO;
import kr.co.kmarket.dto.KmProductCate2DTO;
import kr.co.kmarket.dto.KmProductDTO;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class KmProductService {
  
  
    private Logger logger = LoggerFactory.getLogger(this.getClass());
  
    private static KmProductService INSTANCE = new KmProductService();
    private static KmProductDAO dao = KmProductDAO.getInstance();
    private KmProductService(){}
    public static KmProductService getInstance(){
        return INSTANCE;
    }

    // 0912 상엽님
    public List<KmProductDTO> selectKmProductsCateL10(KmProductCate2DTO kmProductCate2DTO, int start, String condition){
        return dao.selectKmProductsCateL10(kmProductCate2DTO, start, condition);
    }
    public int selectKmProductsCountCate(String cate1, String cate2){
        return dao.selectKmProductsCountCate(cate1, cate2);
    }
  
  
    //0912 수현님

    public void insertProduct(KmProductDTO dto) {
        dao.insertProduct(dto);
    }
    public KmProductDTO selectProduct(String prodNo) {
        return dao.selectProduct(prodNo);
    }
    public List<KmProductDTO> selectProduct(int start) {
        return dao.selectProducts(start);
    }
    public List<KmProductDTO> selectProducts(String cate, int start) {
        return dao.selectProducts(cate, start);
    }
    public void updateProduct(KmProductDTO dto) {
        dao.updateProduct(dto);
    }
    public void deleteProduct(int prodNo) {
        dao.deleteProduct(prodNo);
    }


    public int selectCountProductsTotal() {
    	return dao.selectCountProductsTotal();
    }
    public int selectCountProductsTotal(String cate) {
    	return dao.selectCountProductsTotal(cate);
    }
    
    // 썸네일 업로드
    
    // 업로드 경로 구하기
   	public String getFilePath(HttpServletRequest req) {
   		
   		ServletContext ctx = req.getServletContext();
   		String path = ctx.getRealPath("/thumb");
		return null;
	}
   	// 파일명수정
   	public String renameToFile(HttpServletRequest req, String path, String oName) {
   		
   		int i = oName.lastIndexOf(".");
   		String ext = oName.substring(i);
   		
   		String uuid = UUID.randomUUID().toString();
   		String sName = uuid + ext;
   		
   		File f1 = new File(path+"/"+oName);
   		File f2 = new File(path+"/"+sName);
   		
   		f1.renameTo(f2);
   		
   		return sName;
   	}
    // 파일업로드
   	public MultipartRequest uploadFile(HttpServletRequest req, String path) {
		
		// 최대 업로드 파일 크기
		int maxSize = 1024 * 1024 * 10;
		
		// 파일 업로드 및 Multipart 객체 생성
		MultipartRequest mr = null;
		
		try {
				mr = new MultipartRequest(req,
												path,
												maxSize,
												"UTF-8",
											new DefaultFileRenamePolicy());
		} catch (IOException e) {
			logger.error("uploadFile : " + e.getMessage());
		}
		return mr;				
	}
}