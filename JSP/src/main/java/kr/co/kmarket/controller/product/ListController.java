package kr.co.kmarket.controller.product;

import kr.co.kmarket.dto.KmProductCate2DTO;
import kr.co.kmarket.dto.KmProductDTO;
import kr.co.kmarket.service.KmProductService;
import kr.co.kmarket.service.PageService;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product/list.do")
public class ListController extends HttpServlet {
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(ListController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");

        KmProductCate2DTO kmProductCate2DTO = new KmProductCate2DTO();

        kmProductCate2DTO.setCate1(req.getParameter("cate1"));
        kmProductCate2DTO.setCate2(req.getParameter("cate2"));
        String pg = req.getParameter("pg");
        String condition = req.getParameter("condition");
        if(kmProductCate2DTO.getCate2()==null || kmProductCate2DTO.getCate2().equals("")) {
            kmProductCate2DTO.setCate2("10");
        }
        if(condition==null || condition.equals("")) {
            condition = "11";
        }

        KmProductService kmProductService = KmProductService.getInstance();
        PageService pageService = PageService.getInstance();


        req.setAttribute("cate1", kmProductCate2DTO.getCate1());
        req.setAttribute("cate2", kmProductCate2DTO.getCate2());


        // 현재 페이지 번호
        int currentPage = pageService.getCurrentPage(pg);

        // 전체 게시물 갯수
        int total = kmProductService.selectKmProductsCountCate(kmProductCate2DTO.getCate1(), kmProductCate2DTO.getCate2());

        // 마지막 페이지 번호
        int lastPageNum = pageService.getLastPageNum(total);

        // 페이지 그룹 start, end 번호
        int[] result = pageService.getPageGroupNum(currentPage, lastPageNum);

        // 페이지 시작번호
        int pageStartNum = pageService.getPageStartNum(total, currentPage);

        // 시작 인덱스
        int start = pageService.getStartNum(currentPage);

        // 현재 페이지 게시물 조회
        List<KmProductDTO> kmProducts = kmProductService.selectKmProductsCateL10(kmProductCate2DTO, start, condition);


        req.setAttribute("cate1", kmProductCate2DTO.getCate1());
        req.setAttribute("cate2", kmProductCate2DTO.getCate2());
        req.setAttribute("condition", condition);
        req.setAttribute("KmProductDTOS", kmProducts);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("lastPageNum", lastPageNum);
        req.setAttribute("pageGroupStart", result[0]);
        req.setAttribute("pageGroupEnd", result[1]);
        req.setAttribute("pageStartNum", pageStartNum+1);
        
        
        req.getRequestDispatcher("/product/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
