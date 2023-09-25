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

import kr.co.kmarket.service.KmProductService;

@WebServlet("/admin/product/register/delete.do")
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

/*
				tkddu1591: fService, aService 없어서 작동안함.

				String prodNo = req.getParameter("prodNo");
				logger.debug("prodNo : " + prodNo);

				// 파일 삭제(DB)
				int result = fService.deleteFile(no);

				// 글 삭제
				aService.deleteArticle(no);

				// 파일 삭제(Directory)
				if(result > 0) {

					String path	= aService.getFilePath(req);

					File file = new File(path+"/"+"파일명");

					if(file.exists()) {
							file.delete();
					}
				}*/
				// 리다이렉트
				resp.sendRedirect("/admin/product/list.do");

		String prodNo = req.getParameter("prodNo");
		
		 List<String> file = dto.getFile();
		 kpService.deleteProduct(prodNo);
		
		 if(file.size() != 0) {
			 String path = kpService.getFilePath(req);
			 for(int prodNo : file) {
					kpService.deletefile(path, prodNo);
				}
			}
			
		resp.sendRedirect(ctxPath + "/admin/product/list.do?success=100");
				
			}
	}

