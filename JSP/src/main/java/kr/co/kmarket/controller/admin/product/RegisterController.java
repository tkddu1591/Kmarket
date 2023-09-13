package kr.co.kmarket.controller.admin.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

@WebServlet
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 2934084334974500821L;
	
	private String ctxPath;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ctxPath = config.getServletContext().getContextPath();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/productRegister.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = pService.
		
		String prodName 	= mr.getParameter("prodName");
		String descript 	= mr.getParameter(descript);
		String company 		= mr.getParameter(company);
		String price 		= mr.getParameter(price);
		String discount 	= mr.getParameter(discount);
		String point 		= mr.getParameter(point);
		String stock 		= mr.getParameter(stock);
		String delivery 	= mr.getParameter(delivery);
		String thumb1 		= mr.getParameter(thumb1);
		String thumb2 		= mr.getParameter(thumb2);
		String thumb3 		= mr.getParameter(thumb3);
		
		String status 		= mr.getParameter(status);
		String duty 		= mr.getParameter(duty);
		String receipt 		= mr.getParameter(receipt);
		String bizType 		= mr.getParameter(bizType);
		String origin 		= mr.getParameter(origin);
		
		
	}
}
