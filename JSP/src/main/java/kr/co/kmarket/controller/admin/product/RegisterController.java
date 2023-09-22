package kr.co.kmarket.controller.admin.product;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.kmarket.dao.KmProductDAO;
import kr.co.kmarket.dto.KmProductDTO;
import kr.co.kmarket.service.KmProductService;

@WebServlet("/admin/product/register.do")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 2934084334974500821L;
	
	private String ctxPath;
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(KmProductDAO.class);
	private KmProductService kpService = KmProductService.getInstance();
	//private KmProductService kpService = KmProductService.getInstance();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ctxPath = config.getServletContext().getContextPath();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/product/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploadPath = kpService.getFilePath(req);
		MultipartRequest mr = kpService.uploadFile(req, uploadPath);  
	
		String prodCate1 	= mr.getParameter("prodCate1"); //cate12
		String prodCate2 	= mr.getParameter("prodCate2");
		String prodName 	= mr.getParameter("prodName");
		String descript 	= mr.getParameter("descript");
		String company 		= mr.getParameter("company");
		String price 		= mr.getParameter("price");
		String discount 	= mr.getParameter("discount");
		String point 		= mr.getParameter("point");
		String stock 		= mr.getParameter("stock");
		String seller 		= mr.getParameter("seller");
		String delivery 	= mr.getParameter("delivery");
		String thumb1 		= mr.getOriginalFileName("thumb1");
		String thumb2 		= mr.getOriginalFileName("thumb2");
		String thumb3 		= mr.getOriginalFileName("thumb3");
		String detail 		= mr.getOriginalFileName("detail");
		
		String status 		= mr.getParameter("status");
		String duty 		= mr.getParameter("duty");
		String receipt 		= mr.getParameter("receipt");
		String bizType 		= mr.getParameter("bizType");
		String origin 		= mr.getParameter("origin");
		String ip = req.getRemoteAddr();
		

		thumb1 =  kpService.renameToFile(req, uploadPath, thumb1, prodCate1, prodCate2);
		thumb2 =  kpService.renameToFile(req, uploadPath, thumb2, prodCate1, prodCate2);
		thumb3 =  kpService.renameToFile(req, uploadPath, thumb3, prodCate1, prodCate2);
		detail =  kpService.renameToFile(req, uploadPath, detail, prodCate1, prodCate2);
		
		
		KmProductDTO dto = new KmProductDTO(uploadPath);
		dto.setProdCate1(prodCate1);
		dto.setProdCate2(prodCate2);
		dto.setProdName(prodName);
		dto.setDescript(descript);
		dto.setCompany(company);
		dto.setPrice(price);
		dto.setDiscount(discount);
		dto.setPoint(point);
		dto.setStock(stock);
		dto.setSeller(seller);
		dto.setDelivery(delivery);
		dto.setThumb1(thumb1);
		dto.setThumb2(thumb2);
		dto.setThumb3(thumb3);
		dto.setDetail(detail);
		dto.setStatus(status);
		dto.setDuty(duty);
		dto.setReceipt(receipt);
		dto.setBizType(bizType);
		dto.setOrigin(origin);
		dto.setIp(ip);
		logger.info(dto.getC1Name());
		kpService.insertProduct(dto);
		
		logger.debug("ip : " + ip);
			
		
		resp.sendRedirect(ctxPath+"/admin/product/list.do?sucess=200");
		
	}
}
