package kr.co.kmarket.service;

import kr.co.kmarket.dao.KmProductCate2DAO;
import kr.co.kmarket.dto.KmProductCate2DTO;

public enum KmProductCate2Service {
    INSTANCE;
    private KmProductCate2DAO dao= KmProductCate2DAO.getInstance();

    public KmProductCate2DTO selectCateName(KmProductCate2DTO kmProductCate2DTO) {
        return dao.selectCateName(kmProductCate2DTO);
    }

}
