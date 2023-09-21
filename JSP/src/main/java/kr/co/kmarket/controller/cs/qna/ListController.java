package kr.co.kmarket.controller.cs.qna;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.KmCsQnaDTO;
import kr.co.kmarket.service.KmCsCateService;
import kr.co.kmarket.service.KmCsQnaService;
import kr.co.kmarket.service.PageService;

@WebServlet("/cs/qna/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = -4092848915441871453L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private PageService pageService = PageService.getInstance();
	private KmCsQnaService qnaService = KmCsQnaService.INSTANCE;
	private KmCsCateService cateService = KmCsCateService.INSTANCE;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String cate1 = req.getParameter("cate1");
		String c1Name="";
		if(cate1 == null) {
			cate1 = "0";
		} else {
			// cate1 이름
			c1Name = cateService.selectCsC1Name(cate1);
		}
		
		String pg    = req.getParameter("pg");
		

        // 현재 페이지 번호
        int currentPage = pageService.getCurrentPage(pg);

        // 전체 게시물 갯수
        int total = qnaService.selectCsQnaCount(cate1);

        // 마지막 페이지 번호
        int lastPageNum = pageService.getLastPageNum(total);

        // 페이지 그룹 start, end 번호
        int[] result = pageService.getPageGroupNum(currentPage, lastPageNum);

        // 페이지 시작번호
        int pageStartNum = pageService.getPageStartNum(total, currentPage);

        // 시작 인덱스
        int start = pageService.getStartNum(currentPage);

        // 페이지 변수 확인 
        logger.debug("start : " + start + "/currentPage : " +currentPage + "/total : " + total + "/pg : " + pg);
        // 현재 페이지 게시물 조회
        List<KmCsQnaDTO> qnaList = qnaService.selectCsQnaList(cate1, start);


        req.setAttribute("cate1", cate1);
        req.setAttribute("c1Name", c1Name);
        req.setAttribute("qnaList", qnaList);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("lastPageNum", lastPageNum);
        req.setAttribute("pageGroupStart", result[0]);
        req.setAttribute("pageGroupEnd", result[1]);
        req.setAttribute("pageStartNum", pageStartNum+1);

		req.setAttribute("group", "list");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
		dispatcher.forward(req, resp);
	}
}
