package kr.co.kmarket.controller.admin.product;

import java.io.IOException;
import java.io.ObjectInputFilter.Config;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import kr.co.kmarket.dto.KmCsQnaDTO;
import kr.co.kmarket.dto.KmProductDTO;
import kr.co.kmarket.service.KmProductService;

@WebServlet("/admin/productlist/modify.do")
public class ModifyController extends HttpServlet {

	private static final long serialVersionUID = 8348467550638944485L;
    private String ctxPath;
    
    private KmProductService kpService = KmProductService.getInstance();
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	ctxPath = config.getServletContext().getContextPath();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String prodNo = req.getParameter("prodNo");
    	String prodCate1 = req.getParameter("prodCate1");
    	String prodCate2 = req.getParameter("prodCate2");
    	String prodName = req.getParameter("prodName");
    	String descript = req.getParameter("descript");
    	String company = req.getParameter("company");
    	String price = req.getParameter("price");
    	String discount = req.getParameter("discount");
    	String point = req.getParameter("point");
    	String stock = req.getParameter("stock");
    	String seller = req.getParameter("seller");
    	String delivery = req.getParameter("delivery");
    	String thumb1 = req.getParameter("thumb1");
    	String thumb2 = req.getParameter("thumb2");
    	String thumb3 = req.getParameter("thumb3");
    	String detail = req.getParameter("detail");
    	String status = req.getParameter("status");
    	String duty = req.getParameter("duty");
    	String receipt = req.getParameter("receipt");
    	String bizType = req.getParameter("bizType");
    	String origin = req.getParameter("origin");
    	
    	KmProductDTO dto = kpService.selectProduct(prodNo);
    	//KmProductDTO dto = kpService.selectProduct(prodCate1);
		/*
		 * KmProductDTO dto = kpService.selectProduct(prodCate2); KmProductDTO dto =
		 * kpService.selectProduct(prodName); KmProductDTO dto =
		 * kpService.selectProduct(descript); KmProductDTO dto =
		 * kpService.selectProduct(company); KmProductDTO dto =
		 * kpService.selectProduct(price); KmProductDTO dto =
		 * kpService.selectProduct(discount); KmProductDTO dto =
		 * kpService.selectProduct(point); KmProductDTO dto =
		 * kpService.selectProduct(stock); KmProductDTO dto =
		 * kpService.selectProduct(seller); KmProductDTO dto =
		 * kpService.selectProduct(delivery); KmProductDTO dto =
		 * kpService.selectProduct(thumb1); KmProductDTO dto =
		 * kpService.selectProduct(thumb2); KmProductDTO dto =
		 * kpService.selectProduct(thumb3); KmProductDTO dto =
		 * kpService.selectProduct(detail); KmProductDTO dto =
		 * kpService.selectProduct(status); KmProductDTO dto =
		 * kpService.selectProduct(duty); KmProductDTO dto =
		 * kpService.selectProduct(receipt); KmProductDTO dto =
		 * kpService.selectProduct(bizType); KmProductDTO dto =
		 * kpService.selectProduct(origin);
		 */
    	req.setAttribute("prodNo", dto);
    	req.setAttribute("prodCate1", dto);
    	req.setAttribute("prodCate2", dto);
    	req.setAttribute("prodName", dto);
    	req.setAttribute("descript", dto);
    	req.setAttribute("company", dto);
    	req.setAttribute("price", dto);
    	req.setAttribute("discount", dto);
    	req.setAttribute("point", dto);
    	req.setAttribute("stock", dto);
    	req.setAttribute("seller", dto);
    	req.setAttribute("delivery", dto);
    	req.setAttribute("thumb1", dto);
    	req.setAttribute("thumb2", dto);
    	req.setAttribute("thumb3", dto);
    	req.setAttribute("detail", dto);
    	req.setAttribute("status", dto);
    	req.setAttribute("duty", dto);
    	req.setAttribute("receipt", dto);
    	req.setAttribute("bizType", dto);
    	req.setAttribute("origin", dto);
    	
    	RequestDispatcher dispatcher = req.getRequestDispatcher("modify.jsp");
        dispatcher.forward(req, resp);	
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
		KmProductDTO dto = kpService.uploadFile(req, ctxPath);
		
		// list Update 
		kpService.updateregilist(dto);
	
		resp.sendRedirect(ctxPath + "/admin/product/list.do?no="+dto.getProdNo());		
    }
	
}
