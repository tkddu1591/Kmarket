package kr.co.kmarket.controller;

import kr.co.kmarket.dto.KmMemberDTO;
import kr.co.kmarket.dto.KmProductCate1DTO;
import kr.co.kmarket.dto.KmProductCate2DTO;
import kr.co.kmarket.service.KmProductCate1Service;
import kr.co.kmarket.service.KmProductCate2Service;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(value = {"", "/index.do"})
public class IndexController extends HttpServlet {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IndexController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 현재 세션 가져오기
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        KmMemberDTO sessUser = (KmMemberDTO) session.getAttribute("sessUser");

        //첫 접속시 세션에 카테고리 부여
        KmProductCate2Service kmProductCate2Service = KmProductCate2Service.INSTANCE;
        KmProductCate1Service kmProductCate1Service = KmProductCate1Service.INSTANCE;

        if (session.getAttribute("sessCoates1") == null) {
            List<KmProductCate2DTO> sessCoates2First = kmProductCate2Service.selectCoates2();
            List<KmProductCate1DTO> sessCoates1First = kmProductCate1Service.selectCoates1();

            //카테고리 1, 2 맵으로 세팅
            Map<Integer, String> sessCoates1Map = new HashMap<>();
            Map<Integer, Map<Integer, String>> sessCoates2Map = new HashMap<>();

            for (KmProductCate1DTO cate1 : sessCoates1First) {
                //카테 번호 받으면 네임으로 변환
                sessCoates1Map.put(cate1.getCate1(), cate1.getC1Name());

                Map<Integer, String> sessCoates2MapTemp = new HashMap<>();
                for (KmProductCate2DTO cate2 : sessCoates2First) {
                    if (cate1.getCate1() == (cate2.getCate1())) {
                        sessCoates2MapTemp.put(cate2.getCate2(), cate2.getC2Name());
                    }
                }
                sessCoates2Map.put(cate1.getCate1(), sessCoates2MapTemp);
            }

            session.setAttribute("sessCoates1", sessCoates1First);
            session.setAttribute("sessCoates2", sessCoates2First);
            session.setAttribute("sessCoates1Map", sessCoates1Map);
            session.setAttribute("sessCoates2Map", sessCoates2Map);

        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}