package kr.co.kmarket.controller.admin.product;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.KmProductDTO;
import kr.co.kmarket.service.KmProductService;

@WebServlet("/admin/product/delete.do")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = -2546805682249228568L;
	private String ctxPath;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmProductService kpService = KmProductService.getInstance();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ctxPath = config.getServletContext().getContextPath();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String no = req.getParameter("no");

		KmProductDTO dto = kpService.selectProduct(no);
		// 파일 삭제용 이름 
		List<String> file = dto.getFile();

		// DB  remove
		kpService.removeProduct(no);
		
		// 파일 삭제(디렉터리) 
		if(file.size() != 0) {
			String path = kpService.getCtxPath(req);
			for(String fileName : file) {
				kpService.deletefile(path, fileName);
			}
		}
		
		resp.sendRedirect(ctxPath + "/admin/product/list.do?success=200");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] chks = req.getParameterValues("chk");

		for(String no : chks) {
			kpService.removeProduct(no);
		}
		
		resp.sendRedirect(ctxPath + "/admin/product/list.do?success=200");
	}
}

