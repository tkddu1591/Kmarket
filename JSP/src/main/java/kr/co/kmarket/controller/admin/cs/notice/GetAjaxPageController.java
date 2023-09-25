package kr.co.kmarket.controller.admin.cs.notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.kmarket.dto.KmCsNoticeDTO;
import kr.co.kmarket.service.KmCsNoticeService;
import kr.co.kmarket.service.PageService;

@WebServlet("/admin/cs/notice/getAjaxPage.do")
public class GetAjaxPageController extends HttpServlet{

	
	private static final long serialVersionUID = 5589130612341291608L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsNoticeService noticeService = KmCsNoticeService.INSTANCE;
	private PageService pageService = PageService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String cate1 = req.getParameter("cate1");
		String keyword = req.getParameter("keyword");
		String pg = req.getParameter("pg");
		if(cate1 == null || cate1 == "") {
			cate1 = "0";
		} 

		if(keyword == null || keyword == "") {
			keyword = null;
		} 

		if(pg == null || pg == "") {
			pg = null;
		} 
		

        // 현재 페이지 번호
        int currentPage = pageService.getCurrentPage(pg);

        // 전체 게시물 갯수
        int total = noticeService.selectCsNoticeCountByAjax(cate1, keyword);

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
        
		Map<String, Integer> pageObj = new HashMap<>();
		
		pageObj.put("currentPage", currentPage);
		pageObj.put("lastPageNum", lastPageNum);
		pageObj.put("pageGroupStart", result[0]);
		pageObj.put("pageGroupEnd", result[1]);
		pageObj.put("pageStartNum", pageStartNum+1);
		
		JSONObject jsonObject = new JSONObject(pageObj);
		
		resp.setContentType("text/html; charset=UTF-8");

		//JSON 출력
		PrintWriter writer = resp.getWriter();
		writer.print(jsonObject.toString());
		
		
	}
}
