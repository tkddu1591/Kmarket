package kr.co.kmarket.controller.admin.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/admin/product/register/delete.do")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = -2546805682249228568L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
				}
				// 리다이렉트
				resp.sendRedirect("/admin/product/list.do");
			}
	}

