package kr.co.kmarket.controller.admin.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.KmProductCate2DTO;
import kr.co.kmarket.dto.KmProductDTO;
import kr.co.kmarket.service.KmProductCate2Service;
import kr.co.kmarket.service.KmProductService;
import kr.co.kmarket.service.PageService;

@WebServlet("/admin/product/list.do")
public class ListController extends HttpServlet{
    private static final long serialVersionUID = 5993656574541195493L;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");


        String pg = req.getParameter("pg");
        String condition = req.getParameter("condition");

        if (condition == null || condition.equals("")) {
            condition = "71";
        }

        String search = req.getParameter("search");

        KmProductService kmProductService = KmProductService.getInstance();
        PageService pageService = PageService.getInstance();


        // 현재 페이지 번호
        int currentPage = pageService.getCurrentPage(pg);

        // 전체 게시물 갯수
        int total = 0;
        if (search == null || search.equals("")) {
            total = kmProductService.selectKmProductsCountAll();
        } else {
            total = kmProductService.selectCountProductsSearch(req.getParameter("search"), req.getParameter("condition"));
        }


        // 마지막 페이지 번호
        int lastPageNum = pageService.getLastPageNum(total);

        // 페이지 그룹 start, end 번호
        int[] result = pageService.getPageGroupNum(currentPage, lastPageNum);

        // 페이지 시작번호
        int pageStartNum = pageService.getPageStartNum(total, currentPage);

        // 시작 인덱스
        int start = pageService.getStartNum(currentPage);


        KmProductCate2DTO kmProductCate2DTO = new KmProductCate2DTO();
        // 현재 페이지 게시물 조회
        List<KmProductDTO> kmProducts = new ArrayList<>();

        if (search == null || search.equals("")) {
            kmProducts = kmProductService.selectKmProductsCateL10(kmProductCate2DTO, start, condition);
        }else {
            kmProducts = kmProductService.selectProductsSearch(req.getParameter("search"), start, req.getParameter("condition"));
        }


        req.setAttribute("condition", condition);
        req.setAttribute("search", search);
        req.setAttribute("kmProductDTOS", kmProducts);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("lastPageNum", lastPageNum);
        req.setAttribute("pageGroupStart", result[0]);
        req.setAttribute("pageGroupEnd", result[1]);
        req.setAttribute("pageStartNum", pageStartNum + 1);


        KmProductCate2Service kmProductCate2Service = KmProductCate2Service.INSTANCE;


        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/product/list.jsp");
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");


        String pg = req.getParameter("pg");
        String condition = req.getParameter("condition");

        if (condition == null || condition.equals("")) {
            condition = "71";
        }

        String search = req.getParameter("search");

        KmProductService kmProductService = KmProductService.getInstance();
        PageService pageService = PageService.getInstance();

        logger.info(req.getParameter("search"));
        logger.info(req.getParameter("condition"));
        // 현재 페이지 번호
        int currentPage = pageService.getCurrentPage(pg);

        // 전체 게시물 갯수
        int total = 0;
        if (search == null || search.equals("")) {
            total = kmProductService.selectKmProductsCountAll();
        } else {
            total = kmProductService.selectCountProductsSearch(req.getParameter("search"), req.getParameter("condition"));
            logger.info(String.valueOf(total));
        }


        // 마지막 페이지 번호
        int lastPageNum = pageService.getLastPageNum(total);

        // 페이지 그룹 start, end 번호
        int[] result = pageService.getPageGroupNum(currentPage, lastPageNum);

        // 페이지 시작번호
        int pageStartNum = pageService.getPageStartNum(total, currentPage);

        // 시작 인덱스
        int start = pageService.getStartNum(currentPage);


        KmProductCate2DTO kmProductCate2DTO = new KmProductCate2DTO();
        // 현재 페이지 게시물 조회
        List<KmProductDTO> kmProducts = new ArrayList<>();

        if (search == null) {
            kmProducts = kmProductService.selectKmProductsCateL10(kmProductCate2DTO, start, condition);
        }else {
            kmProducts = kmProductService.selectProductsSearch(req.getParameter("search"), start, req.getParameter("condition"));

        }


        req.setAttribute("condition", condition);
        req.setAttribute("search", search);
        req.setAttribute("kmProductDTOS", kmProducts);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("lastPageNum", lastPageNum);
        req.setAttribute("pageGroupStart", result[0]);
        req.setAttribute("pageGroupEnd", result[1]);
        req.setAttribute("pageStartNum", pageStartNum + 1);


        KmProductCate2Service kmProductCate2Service = KmProductCate2Service.INSTANCE;


        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/product/list.jsp");
        dispatcher.forward(req, resp);
    }
}
