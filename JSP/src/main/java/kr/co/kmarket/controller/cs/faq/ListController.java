package kr.co.kmarket.controller.cs.faq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.KmCsFaqDTO;
import kr.co.kmarket.service.KmCsCateService;
import kr.co.kmarket.service.KmCsFaqService;

@WebServlet("/cs/faq/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 5258674197025893926L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsFaqService service = KmCsFaqService.INSTANCE;
	private KmCsCateService cateService = KmCsCateService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String cate1 = req.getParameter("cate1");
		String c1Name = cateService.selectCsC1Name(cate1);
		
		int cate1Num = Integer.parseInt(cate1);
		if(cate1Num < 20) {
			resp.sendRedirect("/JSP/cs/index.do?success=0");
		}else {
		
			List<List<KmCsFaqDTO>> faqList = new ArrayList<>();
			List<String> c2NameList = new ArrayList<>();
			
			HttpSession session = req.getSession();
			Map<String, Object> map = (Map<String, Object>) session.getAttribute("sessCsCates");
			Map<Integer, List> depth2 = (Map<Integer, List>) map.get("depth2");
			List<Map> depth2_2 = depth2.get(cate1Num);
			
			for(Map<String, String> depth2_temp : depth2_2) {
				String cate2 = depth2_temp.get("cate2");
				List<KmCsFaqDTO> faqByCate2 = service.selectCsFaqListByCate2(cate1, cate2);
				faqList.add(faqByCate2);
				c2NameList.add(depth2_temp.get("c2Name"));
			}
			
			req.setAttribute("group", "list");
			req.setAttribute("cate1", cate1);
			req.setAttribute("c1Name", c1Name);
			req.setAttribute("c2NameList", c2NameList);
			req.setAttribute("faqList", faqList);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
