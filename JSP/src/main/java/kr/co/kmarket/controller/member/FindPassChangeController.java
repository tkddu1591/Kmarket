package kr.co.kmarket.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.service.KmMemberService;

@WebServlet("/member/findPassChange.do")
public class FindPassChangeController extends HttpServlet{

	private static final long serialVersionUID = -1385504317336703902L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmMemberService service = KmMemberService.instance;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// get으로 받는 건 FindPassController 에서 redirect 한 걸 받은 것
		HttpSession session = req.getSession();
		
		// FindPassController에서 session에 setAttribute 해서 uid 속성으로 저장해놓은 걸 여기서는 getAttribute 하네, uid가 있다는 걸 확인함으로써 비밀번호 변경 페이지를 보여주는 것 / 다운캐스팅 (String)
		String uid = (String) session.getAttribute("uid"); 
		
		// findPassChange.do로 바로 들어올 때 uid가 없으면 화면 뜨지 않도록 uid == null 조건 넣어주는건가?? uid가 있어야 findPassChange페이지를 보여주기 위해
		if(uid == null) {
			resp.sendRedirect("/JSP/member/findPass.do");
		}else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/member/findPassChange.jsp");
			dispatcher.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("uid");
		String pass = req.getParameter("km_pass1"); // name=pass 가 아니잖아!!
		
		service.updatePass(uid, pass); // DAO에서 매개변수 쓴 순서에 맞게 파라미터 지정해놨으므로, DAO랑 똑같이 매개변수 순서 맞춘다
		
		resp.sendRedirect("/JSP/member/login.do?success=300");
	}

}
