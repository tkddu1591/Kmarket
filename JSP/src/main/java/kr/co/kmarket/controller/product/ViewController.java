package kr.co.kmarket.controller.product;

import kr.co.kmarket.dao.KmProductDAO;
import kr.co.kmarket.dao.KmProductReviewDAO;
import kr.co.kmarket.dto.KmProductDTO;
import kr.co.kmarket.dto.KmProductReviewDTO;
import kr.co.kmarket.service.KmProductService;
import kr.co.kmarket.service.KmProductReviewService;
import kr.co.kmarket.service.PageService;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product/view.do")
public class ViewController extends HttpServlet {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(ViewController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String prodNo =req.getParameter("prodNo");

        PageService pageService = PageService.getInstance();
        KmProductService kmProductService = KmProductService.getInstance();

        logger.info("prodNo : " + prodNo);
        KmProductDTO kmProductDTO = kmProductService.selectProduct(prodNo);

        logger.info("prodNo : " + kmProductDTO.getProdNo());
        logger.info("prodName : " + kmProductDTO.getProdName());
        logger.info("prodPrice : " + kmProductDTO.getPrice());
        logger.info("prodStock : " + kmProductDTO.getStock());
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
        List<KmProductReviewDTO> kmProductReviews = kmProductReviewService.selectKmProductReviews(prodNo);


        req.setAttribute("prodNo", prodNo);
        req.setAttribute("kmProductReviews", kmProductReviews);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("lastPageNum", lastPageNum);
        req.setAttribute("pageGroupStart", result[0]);
        req.setAttribute("pageGroupEnd", result[1]);
        req.setAttribute("pageStartNum", pageStartNum+1);
        

        List<KmProductReviewDTO> kmProductReviewReviewsDTO = KmProductReviewService.selectKmProductReviews(prodNo);





        req.getRequestDispatcher("/product/view.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
