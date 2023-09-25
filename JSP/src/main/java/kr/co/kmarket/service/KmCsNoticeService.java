package kr.co.kmarket.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import kr.co.kmarket.dao.KmCsNoticeDAO;
import kr.co.kmarket.dto.KmCsNoticeDTO;
public enum KmCsNoticeService {
	
	INSTANCE;
	
	private String noticeUploadPath = "/upload/notice";
	
	private KmCsNoticeDAO dao = KmCsNoticeDAO.getInstance();
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int insertCsNotice(KmCsNoticeDTO dto) {
		return dao.insertCsNotice(dto);
	}
	public KmCsNoticeDTO selectCsNotice(String no) {
		return dao.selectCsNotice(no);
	}
	public List<KmCsNoticeDTO> selectCsNoticeList(String cate1, int start) {
		return dao.selectCsNoticeList(cate1, start);
	}
	public List<KmCsNoticeDTO> selectCsNoticeListByAjax(String cate1, String keyword, int start){
		return dao.selectCsNoticeListByAjax(cate1, keyword, start);
	}
	public int selectCsNoticeCount(String cate1) {
		return dao.selectCsNoticeCount(cate1);
	}
	public int selectCsNoticeCountByAjax(String cate1, String keyword) {
		return dao.selectCsNoticeCountByAjax(cate1, keyword);
	}
	public List<KmCsNoticeDTO> selectLatests(int size){
		return dao.selectLatests(size);
	}
	public void updateCsNotice(KmCsNoticeDTO dto) {
		dao.updateCsNotice(dto);
	}
	public void updateHit(String no) {
		dao.updateHit(no);
	}
	public void deleteCsNotice(String no) {
		dao.deleteCsNotice(no);
	}
	

}
