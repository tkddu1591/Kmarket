package kr.co.kmarket.controller.product;

import kr.co.kmarket.dao.KmProductDAO;
import kr.co.kmarket.dao.KmProductReviewDAO;
import kr.co.kmarket.dto.KmProductCartDTO;
import kr.co.kmarket.dto.KmProductCate2DTO;
import kr.co.kmarket.dto.KmProductDTO;
import kr.co.kmarket.dto.KmProductReviewDTO;
import kr.co.kmarket.service.*;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/product/view.do")
public class ViewController extends HttpServlet {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(ViewController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String prodNo = req.getParameter("prodNo");
        HttpSession session = req.getSession();
        Map<Integer, String> sessCoates1Map = (Map<Integer, String>) session.getAttribute("sessCoates1Map");
        Map<Integer, Map<Integer, String>> sessCoates2Map = (Map<Integer, Map<Integer, String>>) session.getAttribute("sessCoates2Map");



        PageService pageService = PageService.getInstance();
        KmProductService kmProductService = KmProductService.getInstance();

        KmProductDTO kmProductDTO = kmProductService.selectProduct(prodNo);


        req.setAttribute("kmProduct", kmProductDTO);


        String pg = req.getParameter("pg");


        KmProductReviewService kmProductReviewService = KmProductReviewService.INSTANCE;

        // 현재 페이지 번호
        int currentPage = pageService.getCurrentPage(pg);

        // 전체 게시물 갯수
        int total = kmProductReviewService.selectKmProductReviewsCount(Integer.parseInt(prodNo));

        // 마지막 페이지 번호
        int lastPageNum = pageService.getLastPageNum(total);

        // 페이지 그룹 start, end 번호
        int[] result = pageService.getPageGroupNum(currentPage, lastPageNum);

        // 페이지 시작번호
        int pageStartNum = pageService.getPageStartNum(total, currentPage);

        // 시작 인덱스
        int start = pageService.getStartNum(currentPage);

        // 현재 페이지 게시물 조회
        List<KmProductReviewDTO> kmProductReviews = kmProductReviewService.selectKmProductReviews(prodNo, start);


        req.setAttribute("cate1", kmProductDTO.getProdCate1());
        req.setAttribute("cate2", kmProductDTO.getProdCate2());
        req.setAttribute("prodNo", prodNo);
        req.setAttribute("kmProductReviews", kmProductReviews);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("lastPageNum", lastPageNum);
        req.setAttribute("pageGroupStart", result[0]);
        req.setAttribute("pageGroupEnd", result[1]);
        req.setAttribute("pageStartNum", pageStartNum + 1);
        String success = req.getParameter("success");
        if (success == null) {
            success = "0";
        }
        req.setAttribute("success", success);


        req.getRequestDispatcher("/product/view.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        int cate1 = Integer.parseInt(req.getParameter("cate1"));
        int cate2 = Integer.parseInt(req.getParameter("cate2"));
        Map<Integer, String> sessCoates1Map = (Map<Integer, String>) session.getAttribute("sessCoates1Map");
        Map<Integer, Map<Integer, String>> sessCoates2Map = (Map<Integer, Map<Integer, String>>) session.getAttribute("sessCoates2Map");


        req.setAttribute("cate1",cate1);
        req.setAttribute("cate2",cate2);

        req.setAttribute("cate1Name",sessCoates1Map.get(cate1));
        if(cate2!=0) {
            req.setAttribute("cate2Name", sessCoates2Map.get(cate1).get(cate2));
        }
        // 현재 날짜/시간
        Date rDate = Calendar.getInstance().getTime();

        // 포맷팅 정의
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 포맷팅 적용
        String formatedNow = formatter.format(rDate);

        int prodNo = Integer.parseInt(req.getParameter("prodNo"));
        int count = Integer.parseInt(req.getParameter("count"));
        KmProductCartService kmProductCartService = KmProductCartService.INSTANCE;

        int check = 0;
        check = kmProductCartService.selectCartCountProd(prodNo);
        KmProductCartDTO kmProductCartDTO = new KmProductCartDTO();
        if(check>0) {
            //카트에 동일 물품이 있을 때 숫자만 늘림
            kmProductCartService.updateCartCount(prodNo, count);
        }else {
            //카트에 동일 물품이 없을 때,
            kmProductCartDTO.setUid(req.getParameter("uid"));
            kmProductCartDTO.setProdNo(prodNo);
            kmProductCartDTO.setCount(count);
            kmProductCartDTO.setPrice(req.getParameter("price"));
            kmProductCartDTO.setDiscount(req.getParameter("discount"));
            kmProductCartDTO.setPoint(req.getParameter("point"));
            kmProductCartDTO.setDelivery(req.getParameter("delivery"));
            kmProductCartDTO.setTotal(req.getParameter("total"));
            kmProductCartDTO.setrDate(formatedNow);

            kmProductCartService.insertCart(kmProductCartDTO);
        }

        resp.sendRedirect("/JSP/product/view.do?success=100&prodNo=" + kmProductCartDTO.getProdNo() + "&cate1=" + cate1 + "&cate2=" + cate2);
    }
}
