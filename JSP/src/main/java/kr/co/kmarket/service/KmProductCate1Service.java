package kr.co.kmarket.service;

import kr.co.kmarket.dao.KmProductCate1DAO;
import kr.co.kmarket.dto.KmProductCate1DTO;

import java.util.List;

public enum KmProductCate1Service {
    INSTANCE;

    private KmProductCate1DAO kmProductCate1DAO= KmProductCate1DAO.getInstance();
    public List<KmProductCate1DTO> selectCoates1(){
        return kmProductCate1DAO.selectCoates1();
    }
}
