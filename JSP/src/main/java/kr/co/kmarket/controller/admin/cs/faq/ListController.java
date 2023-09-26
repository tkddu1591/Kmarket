package kr.co.kmarket.controller.admin.cs.faq;

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

import kr.co.kmarket.dto.KmCsCate2DTO;
import kr.co.kmarket.dto.KmCsFaqDTO;
import kr.co.kmarket.service.KmCsCateService;
import kr.co.kmarket.service.KmCsFaqService;
import kr.co.kmarket.service.PageService;

@WebServlet("/admin/cs/faq/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = -845256819560444082L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsFaqService faqService = KmCsFaqService.INSTANCE;
	private PageService pageService = PageService.getInstance();
	private KmCsCateService cateService = KmCsCateService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String cate1 = req.getParameter("cate1");
		if(cate1 == null || cate1=="") {
			cate1 = "20";
		} 
		String cate2 = req.getParameter("cate2");
		if(cate2 == null || cate2=="") {
			cate2 = "10";
		} 
		String c2Name = cateService.selectCsC2Name(cate1, cate2);
		List<KmCsCate2DTO> cate2List = cateService.selectCSCate2s(Integer.parseInt(cate1));
        // 현재 페이지 게시물 조회
		List<KmCsFaqDTO> faqList = faqService.selectCsFaqListByCate2(cate1,cate2);

        req.setAttribute("faqList", faqList);
        req.setAttribute("cate2List", cate2List);
        req.setAttribute("cate1", cate1);
        req.setAttribute("cate2", cate2);
        req.setAttribute("c2Name", c2Name);
		//req.setAttribute("group", "list");
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String cate1 = req.getParameter("cate1");
		if(cate1 == null || cate1=="") {
			cate1 = "20";
		} 
		String cate2 = req.getParameter("cate2");
		if(cate2 == null || cate2=="") {
			cate2 = "10";
		} 
        // 현재 페이지 게시물 조회
		List<KmCsFaqDTO> faqList = faqService.selectCsFaqListByCate2(cate1,cate2);

		Gson gson = new Gson();
		String jsonString = gson.toJson(faqList);
		
		resp.setContentType("text/html; charset=UTF-8");

		//JSON 출력
		PrintWriter writer = resp.getWriter();
		writer.print(jsonString);

		
	}
}
