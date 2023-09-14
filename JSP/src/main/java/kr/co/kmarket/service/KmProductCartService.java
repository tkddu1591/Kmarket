package kr.co.kmarket.service;

import kr.co.kmarket.dao.KmProductCartDAO;
import kr.co.kmarket.dto.KmProductCartDTO;

import java.util.List;

public enum KmProductCartService {
    INSTANCE;
    private KmProductCartDAO kmProductCartDao = KmProductCartDAO.getInstance();

    public void insertCart(KmProductCartDTO kmProductCartDTO) {
        kmProductCartDao.insertCart(kmProductCartDTO);
    }

    public void deleteCartUid(String uid){
        kmProductCartDao.deleteCartUid(uid);
    }

    public List<KmProductCartDTO> selectCarts(String uid){
        return kmProductCartDao.selectCarts(uid);
    }

}
