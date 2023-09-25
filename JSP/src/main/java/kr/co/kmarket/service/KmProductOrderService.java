package kr.co.kmarket.service;

import kr.co.kmarket.dao.KmProductOrderDAO;
import kr.co.kmarket.dao.KmProductOrderItemDAO;
import kr.co.kmarket.dto.KmProductOrderDTO;

public enum KmProductOrderService {
    INSTANCE;
    KmProductOrderDAO kmProductOrderDAO = KmProductOrderDAO.getInstance();
    public int selectLastOrderNo() {
        return kmProductOrderDAO.selectLastOrderNo();
    }

    public void insertOrder(KmProductOrderDTO productOrderDTO) {
        kmProductOrderDAO.insertOrder(productOrderDTO);
    }
}
