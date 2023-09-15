package kr.co.kmarket.service;

import java.util.List;

import kr.co.kmarket.dao.KmMemberDAO;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.KmMemberDTO;

public enum KmMemberService {
	instance;
	
	private KmMemberDAO dao = new KmMemberDAO();
	
public void insertMember(KmMemberDTO dto) {
		dao.insertMember(dto);
	}
	
	public KmMemberDTO selectMember(String uid, String pass) {
		return dao.selectMember(uid, pass);
	}
	
	public List<KmMemberDTO> selectMembers() {
		return dao.selectMembers();
	}
	
	public void updateMember(KmMemberDTO dto) {
		dao.updateMember(dto);
	}
	
	public void deleteMember(String uid) {
		dao.deleteMember(uid);
	}
	
	// 추가
	public int selectCountUid(String uid) {
		return dao.selectCountUid(uid);
	}
	
	public int selectCountHp(String hp) {
		return dao.selectCountHp(hp);
	}
	
	public int selectCountEmail(String email) {
		return dao.selectCountEmail(email);
	}
}
