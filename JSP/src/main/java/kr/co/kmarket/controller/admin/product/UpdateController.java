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

import com.oreilly.servlet.MultipartRequest;

import kr.co.kmarket.dto.KmProductDTO;
import kr.co.kmarket.service.KmProductService;

@WebServlet("/admin/product/update.do")
public class UpdateController extends HttpServlet{

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
		 
		String prodNo = req.getParameter("no");
		HttpSession session = req.getSession();
		
		KmProductDTO product = kpService.selectProduct(prodNo);
		logger.debug(" product : " + product.toString());
		req.setAttribute("product", product);
		RequestDispatcher dispatcher = req.getRequestDispatcher("update.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploadPath = kpService.getFilePath(req);
		String ctxPath  = kpService.getCtxPath(req);
		MultipartRequest mr = kpService.uploadFile(req, uploadPath);  
	
		String prodNo 		= mr.getParameter("no");
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
		String thumb1 		= mr.getParameter("thumb1");
		String thumb2 		= mr.getParameter("thumb2");
		String thumb3 		= mr.getParameter("thumb3");
		String detail 		= mr.getParameter("detail");
		String thumb1New 		= mr.getOriginalFileName("thumb1New");
		String thumb2New		= mr.getOriginalFileName("thumb2New");
		String thumb3New 		= mr.getOriginalFileName("thumb3New");
		String detailNew		= mr.getOriginalFileName("detailNew");
		
		String status 		= mr.getParameter("status");
		String duty 		= mr.getParameter("duty");
		String receipt 		= mr.getParameter("receipt");
		String bizType 		= mr.getParameter("bizType");
		String origin 		= mr.getParameter("origin");
		String ip = req.getRemoteAddr();
		
		//새로 입력한 파일이 있으면 해당 파일로 수정하고 기존 파일 삭제 
		if(thumb1New != null) {
			kpService.deletefile(ctxPath, thumb1);
			thumb1 = kpService.renameToFile(req, uploadPath, thumb1New, prodCate1, prodCate2);
		}
		if(thumb2New != null) {
			kpService.deletefile(ctxPath, thumb2);
			thumb2 = kpService.renameToFile(req, uploadPath, thumb2New, prodCate1, prodCate2);
		}
		if(thumb3New != null) {
			kpService.deletefile(ctxPath, thumb3);
			thumb3 = kpService.renameToFile(req, uploadPath, thumb3New, prodCate1, prodCate2);
		}
		if(detailNew != null) {
			kpService.deletefile(ctxPath, detail);
			detail = kpService.renameToFile(req, uploadPath, detailNew, prodCate1, prodCate2);
		}

		KmProductDTO dto = new KmProductDTO(uploadPath);
		dto.setProdNo(prodNo);
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
		logger.debug("updateDTO : " + dto.toString());
		
		kpService.updateProduct(dto);
		
		resp.sendRedirect("/JSP/admin/product/list.do");		
	}
}
