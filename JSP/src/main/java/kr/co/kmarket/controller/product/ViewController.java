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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/product/view.do")
public class ViewController extends HttpServlet {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(ViewController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String prodNo =req.getParameter("prodNo");
        String c1 =req.getParameter("c1");
        String c2 =req.getParameter("c2");



        PageService pageService = PageService.getInstance();
        KmProductService kmProductService = KmProductService.getInstance();

        KmProductDTO kmProductDTO = kmProductService.selectProduct(prodNo);



        req.setAttribute("kmProduct", kmProductDTO);


        String pg = req.getParameter("pg");



        KmProductReviewService kmProductReviewService = KmProductReviewService.INSTANCE;

        // 현재 페이지 번호
        int currentPage = pageService.getCurrentPage(pg);

        // 전체 게시물 갯수
        int total = kmProductReviewService.selectKmProductReviewsCount(prodNo);

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


        req.setAttribute("c1", c1);
        req.setAttribute("c2", c2);
        req.setAttribute("prodNo", prodNo);
        req.setAttribute("kmProductReviews", kmProductReviews);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("lastPageNum", lastPageNum);
        req.setAttribute("pageGroupStart", result[0]);
        req.setAttribute("pageGroupEnd", result[1]);
        req.setAttribute("pageStartNum", pageStartNum+1);
        



        req.getRequestDispatcher("/product/view.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String c1 =req.getParameter("c1");
        String c2 =req.getParameter("c2");

        // 현재 날짜/시간
        Date rDate = Calendar.getInstance().getTime();

        // 포맷팅 정의
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 포맷팅 적용
        String formatedNow = formatter.format(rDate);

        KmProductCartDTO kmProductCartDTO = new KmProductCartDTO();
        kmProductCartDTO.setUid( req.getParameter("uid"));
        kmProductCartDTO.setProdNo( req.getParameter("prodNo"));
        kmProductCartDTO.setCount( req.getParameter("count"));
        kmProductCartDTO.setPrice( req.getParameter("price"));
        kmProductCartDTO.setDiscount( req.getParameter("discount"));
        kmProductCartDTO.setPoint( req.getParameter("point"));
        kmProductCartDTO.setDelivery( req.getParameter("delivery"));
        kmProductCartDTO.setTotal( req.getParameter("total"));
        kmProductCartDTO.setrDate(formatedNow);


        KmProductCartService kmProductCartService = KmProductCartService.INSTANCE;

        kmProductCartService.insertCart(kmProductCartDTO);


        resp.sendRedirect("/product/view.do?prodNo="+kmProductCartDTO.getProdNo()+"&c1="+c1+"&c2="+c2);
    }
}
