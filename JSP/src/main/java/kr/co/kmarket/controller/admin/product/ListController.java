package kr.co.kmarket.controller.admin.product;

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

import kr.co.kmarket.dto.KmProductDTO;

@WebServlet("/admin/product/list.do")
public class ListController extends HttpServlet{
	private static final long serialVersionUID = 5993656574541195493L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pg = req.getParameter("pg");
		
		
		int start = 0;
		int currentPage = 1;
		int total = 0;
		int lastPageNum = 0;
		int pageGroupCurrent = 1;
		int pageGroupStart = 1;
		int pageGroupEnd = 0;
		int pageStartNum = 0;
		
		if(pg != null)  {
				currentPage = Integer.parseInt(pg);
		}
		
		start = (currentPage -1) * 7;
		
		if(total % 7 == 0) {
				lastPageNum = (total / 7);
		}else {
				lastPageNum = (total / 7) + 1;
		}
		
		pageGroupCurrent = (int) Math.ceil(currentPage / 7.0);
		pageGroupStart = (pageGroupCurrent - 1) * 7 + 1;
		pageGroupEnd = pageGroupCurrent * 7;
		
		if(pageGroupEnd > lastPageNum) {
				pageGroupEnd = lastPageNum;
		}
		
		
		
		logger.debug("currentPage : " + currentPage);
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lasePageNum",lastPageNum);
		req.setAttribute("pageGroupStart", pageGroupStart);
		req.setAttribute("pageGroupEnd", pageGroupEnd);
		req.setAttribute("pageStartNum", pageStartNum+1);
		
		//req.setAttribute(pg, pg);
		
	
		RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
		dispatcher.forward(req, resp);
	}
	
	
}
