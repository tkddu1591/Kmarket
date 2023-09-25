package kr.co.kmarket.controller.admin.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.KmProductDTO;
import kr.co.kmarket.service.KmProductService;

@WebServlet("/admin/product/modify.do")
public class ModifyController extends HttpServlet{

	private static final long serialVersionUID = 3455791473958570385L;
	private String ctxPath;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmProductService kpService = KmProductService.getInstance();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ctxPath = config.getServletContext().getContextPath();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		String prodNo = req.getParameter("prodNo");
		HttpSession session = req.getSession();
		
		req.setAttribute("prodNo", prodNo);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/modify.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		KmProductDTO dto = new KmProductDTO();
		
		kpService.updateProduct(dto);
		
		resp.sendRedirect(ctxPath + "/admin/product/list.do?no="+dto.getProdNo());		
	}
}
