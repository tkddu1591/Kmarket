package kr.co.kmarket.controller.admin.cs.notice;

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

import kr.co.kmarket.dto.KmCsNoticeDTO;
import kr.co.kmarket.dto.KmMemberDTO;
import kr.co.kmarket.service.KmCsNoticeService;

@WebServlet("/admin/cs/notice/update.do")
public class ModifyController extends HttpServlet{
	
	private static final long serialVersionUID = 2111317440871883967L;

	private String ctxPath;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsNoticeService service = KmCsNoticeService.INSTANCE;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ctxPath = config.getServletContext().getContextPath();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");

		
		KmCsNoticeDTO dto = service.selectCsNotice(no);
		
		logger.debug("notice 글 : " + dto.toString());
		req.setAttribute("no", no);
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("update.jsp");
		dispatcher.forward(req, resp);		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		
		// noticeUpdate 
	//	service.updateCsNotice(dto);
	
		//resp.sendRedirect(ctxPath + "/admin//cs/notice/view.do?no="+dto.getNoticeNo());		
		
	}
}
