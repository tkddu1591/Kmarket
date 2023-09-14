package kr.co.kmarket.controller;

import kr.co.kmarket.dto.KmMemberDTO;
import kr.co.kmarket.dto.KmProductCate2DTO;
import kr.co.kmarket.service.KmProductCate2Service;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/index.do")
public class IndexController extends HttpServlet {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IndexController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 현재 세션 가져오기
        HttpSession session = req.getSession();
        HttpSession session2 = req.getSession();
        KmMemberDTO sessUser = (KmMemberDTO) session.getAttribute("sessUser");

        //첫 접속시 세션에 카테고리 부여
        KmProductCate2Service kmProductCate2Service = KmProductCate2Service.INSTANCE;
        if(session2.getAttribute("sessCoates")==null) {
            List<KmProductCate2DTO> sessCoatesFirst = kmProductCate2Service.selectCoates();
            session2.setAttribute("sessCoates", sessCoatesFirst);
            List<KmProductCate2DTO> sessCoates = (List<KmProductCate2DTO>) session2.getAttribute("sessCoates");
            logger.info("newSessCoates : {}", sessCoates);
        }else {
            List<KmProductCate2DTO> sessCoates = (List<KmProductCate2DTO>) session2.getAttribute("sessCoates");
            logger.info(sessCoates.get(15).getC2Name());
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
