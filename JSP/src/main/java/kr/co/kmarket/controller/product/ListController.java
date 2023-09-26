package kr.co.kmarket.controller.product;

import kr.co.kmarket.dto.KmMemberDTO;
import kr.co.kmarket.dto.KmProductCate2DTO;
import kr.co.kmarket.dto.KmProductDTO;
import kr.co.kmarket.service.KmProductCate2Service;
import kr.co.kmarket.service.KmProductService;
import kr.co.kmarket.service.PageService;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/product/list.do")
public class ListController extends HttpServlet {
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(ListController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        String search = req.getParameter("search");
        KmProductCate2DTO kmProductCate2DTO = new KmProductCate2DTO();
        int cate1 = 0;
        if (req.getParameter("cate1") != null && !req.getParameter("cate1").equals("")) {
            cate1 = Integer.parseInt(req.getParameter("cate1"));

        }
        int cate2 = 0;
        if (req.getParameter("cate2") != null && !req.getParameter("cate2").equals("")) {
            cate2 = Integer.parseInt(req.getParameter("cate2"));

        }
        kmProductCate2DTO.setCate1(cate1);
        kmProductCate2DTO.setCate2(cate2);

        String pg = req.getParameter("pg");
        String condition = req.getParameter("condition");
        if (condition == null || condition.equals("")) {
            condition = "11";
        }

        req.setAttribute("condition", condition);
        KmProductService kmProductService = KmProductService.getInstance();
        PageService pageService = PageService.getInstance();

        //카테고리 1, 2 받아오기
        Map<Integer, String> sessCoates1Map = (Map<Integer, String>) session.getAttribute("sessCoates1Map");
        Map<Integer, Map<Integer, String>> sessCoates2Map = (Map<Integer, Map<Integer, String>>) session.getAttribute("sessCoates2Map");

        req.setAttribute("c1Name", sessCoates1Map.get(cate1));
        if (cate2 != 0) {
            req.setAttribute("c2Name", sessCoates2Map.get(cate1).get(cate2));
        }

        // 현재 페이지 번호
        int currentPage = pageService.getCurrentPage(pg);

        // 전체 게시물 갯수
        int total = 0;
        if (search == null || search.equals("")) {
            total = kmProductService.selectKmProductsCountCate(kmProductCate2DTO.getCate1(), (kmProductCate2DTO.getCate2()));
        } else {
            total = kmProductService.selectCountProductsSearch(search,"prodName");
        }

        // 마지막 페이지 번호
        int lastPageNum = pageService.getLastPageNum(total);

        // 페이지 그룹 start, end 번호
        int[] result = pageService.getPageGroupNum(currentPage, lastPageNum);

        // 페이지 시작번호
        int pageStartNum = pageService.getPageStartNum(total, currentPage);

        // 시작 인덱스
        int start = pageService.getStartNum(currentPage);

        // 현재 페이지 게시물 조회
        List<KmProductDTO> kmProducts = new ArrayList<KmProductDTO>();
        if (search == null || search.equals("")) {
            kmProducts = kmProductService.selectKmProductsCateL10(kmProductCate2DTO, start, condition);
        } else {
            kmProducts = kmProductService.selectProductsSearch(search, start, "prodName");
        }


        if (search == null || search.equals("")) {
            req.setAttribute("cate1", cate1);
            req.setAttribute("cate2", cate2);
            req.setAttribute("condition", condition);
        }else {
            req.setAttribute("search", search);
        }
        req.setAttribute("KmProductDTOS", kmProducts);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("lastPageNum", lastPageNum);
        req.setAttribute("pageGroupStart", result[0]);
        req.setAttribute("pageGroupEnd", result[1]);
        req.setAttribute("pageStartNum", pageStartNum + 1);


        KmProductCate2Service kmProductCate2Service = KmProductCate2Service.INSTANCE;
        kmProductCate2DTO = kmProductCate2Service.selectCateName(kmProductCate2DTO);
        req.setAttribute("kmProductCate2DTO", kmProductCate2DTO);


        req.getRequestDispatcher("/product/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
