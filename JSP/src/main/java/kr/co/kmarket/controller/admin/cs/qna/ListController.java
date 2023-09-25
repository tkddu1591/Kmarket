package kr.co.kmarket.controller.admin.cs.qna;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import kr.co.kmarket.dto.KmCsQnaDTO;
import kr.co.kmarket.service.KmCsQnaService;
import kr.co.kmarket.service.PageService;

@WebServlet("/admin/cs/qna/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = -3892249416934267587L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsQnaService qnaService = KmCsQnaService.INSTANCE;
	private PageService pageService = PageService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String cate1 = req.getParameter("cate1");
		if(cate1 == null || cate1=="") {
			cate1 = "0";
		} 

		String cate2 = req.getParameter("cate2");
		if(cate2 == null || cate2=="") {
			cate2 = "0";
		} 

		String pg   = req.getParameter("pg");
		if(pg == null || pg=="") {
			pg = null;
		} 
		// cate1 이름
		// String c1Name = cateService.selectCsC1Name(cate1);

        // 현재 페이지 번호
        int currentPage = pageService.getCurrentPage(pg);

        // 전체 게시물 갯수
        int total = qnaService.selectCsQnaCountForAdmin(cate1, cate2);

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
		List<KmCsQnaDTO> qnaList = qnaService.selectCsQnaListForAdmin(cate1, cate2, start);

        req.setAttribute("cate1", cate1);
        req.setAttribute("cate2", cate2);
        req.setAttribute("qnaList", qnaList);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("lastPageNum", lastPageNum);
        req.setAttribute("pageGroupStart", result[0]);
        req.setAttribute("pageGroupEnd", result[1]);
        req.setAttribute("pageStartNum", pageStartNum+1);

		
		RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String cate1 = req.getParameter("cate1");
		if(cate1 == null || cate1=="") {
			cate1 = "0";
		} 

		String cate2 = req.getParameter("cate2");
		if(cate2 == null || cate2=="") {
			cate2 = "0";
		} 

		String pg   = req.getParameter("pg");
		if(pg == null || pg=="") {
			pg = null;
		} 
		// cate1 이름
		// String c1Name = cateService.selectCsC1Name(cate1);

        // 현재 페이지 번호
        int currentPage = pageService.getCurrentPage(pg);

        // 시작 인덱스
        int start = pageService.getStartNum(currentPage);

        // 현재 페이지 게시물 조회
		List<KmCsQnaDTO> qnaList = qnaService.selectCsQnaListForAdmin(cate1, cate2, start);

		Gson gson = new Gson();
		String jsonString = gson.toJson(qnaList);
		
		resp.setContentType("text/html; charset=UTF-8");

		//JSON 출력
		PrintWriter writer = resp.getWriter();
		writer.print(jsonString);
	}
}
