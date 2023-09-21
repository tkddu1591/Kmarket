package kr.co.kmarket.service;

import kr.co.kmarket.dao.KmMemberPointDAO;

public enum KmMemberPointService {
    INSTANCE;

    KmMemberPointDAO kmMemberPointDAO = KmMemberPointDAO.getInstance();
    public void insertKmMemberPoint(int point, int lastOrderNo, String ordUid) {
        kmMemberPointDAO.insertKmMemberPoint(point, lastOrderNo, ordUid);
    }
}
