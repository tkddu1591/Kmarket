package kr.co.kmarket.controller.admin.cs.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import kr.co.kmarket.dto.KmCsQnaDTO;
import kr.co.kmarket.service.KmCsQnaService;

@WebServlet("/admin/cs/qna/updateAnswer.do")
public class UpdateAnswerController extends HttpServlet{

	private static final long serialVersionUID = 3469856428052978233L;
	private KmCsQnaService service = KmCsQnaService.INSTANCE;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		String answerComplete = req.getParameter("answerComplete");
		
		int result = service.updateCsQnaAnswerComplete(no, answerComplete);

		//JSON 출력
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		String answer = req.getParameter("answer");
		String kind = req.getParameter("kind");
		String writer = req.getParameter("writer");
		String regip = req.getRemoteAddr();
		KmCsQnaDTO dto = new KmCsQnaDTO();
		dto.setParent(no);
		dto.setContent(answer);
		dto.setWriter(writer);
		dto.setRegip(regip);
		int result = 0;
		switch(kind) {
		case "update":
			result = service.updateCsQnaAnswer(dto);
			break;
		case "insert":
			result = service.insertCsQnaAnswer(dto);
			service.updateCsQnaAnswerComplete(no, "2");
			break;
		}

		//JSON 출력
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);

	}
}
