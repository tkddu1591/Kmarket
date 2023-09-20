package kr.co.kmarket.service;

import kr.co.kmarket.dao.KmMemberTermsDAO;
import kr.co.kmarket.dao.KmProductOrderItemDAO;
import kr.co.kmarket.dto.KmProductOrderItemDTO;

public enum KmProductOrderItemService {
    INSTANCE;
    KmProductOrderItemDAO kmProductOrderItemDAO = KmProductOrderItemDAO.getInstance();

    public void insertKmProductOrderItem(KmProductOrderItemDTO kmProductOrderItemDTO){
        kmProductOrderItemDAO.insertKmProductOrderItem(kmProductOrderItemDTO);
    }
}
