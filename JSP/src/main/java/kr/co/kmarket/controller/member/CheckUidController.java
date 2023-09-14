package kr.co.kmarket.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.kmarket.service.KmMemberService;

@WebServlet("/member/checkUid.do")
public class CheckUidController extends HttpServlet{

	private static final long serialVersionUID = -1304759391298614895L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmMemberService service = KmMemberService.instance;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		
		logger.debug(uid); 
		
		int result = service.selectCountUid(uid);
		
		// JSON 생성
		JsonObject json = new JsonObject();
		json.addProperty("result", result);

		// JSON 출력
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	}
}
