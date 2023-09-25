package kr.co.kmarket.controller.admin.cs.notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import kr.co.kmarket.dto.KmCsNoticeDTO;
import kr.co.kmarket.service.KmCsNoticeService;
import kr.co.kmarket.service.PageService;

@WebServlet("/admin/cs/notice/getAjaxList.do")
public class GetAjaxListController extends HttpServlet{

	private static final long serialVersionUID = 4251876212944423166L;
	
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

        // 시작 인덱스
        int start = pageService.getStartNum(currentPage);
        // 페이지 변수 확인 
        
		
		
		List<KmCsNoticeDTO> noticeList = noticeService.selectCsNoticeListByAjax(cate1, keyword, start);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(noticeList);
		
		resp.setContentType("text/html; charset=UTF-8");

		//JSON 출력
		PrintWriter writer = resp.getWriter();
		writer.print(jsonString);
		
	}
}
