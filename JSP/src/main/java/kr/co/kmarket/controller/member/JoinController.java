package kr.co.kmarket.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/member/join.do")
public class JoinController extends HttpServlet {

	private static final long serialVersionUID = -9165570067205844583L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/join.jsp");
		dispatcher.forward(req, resp);
	}
	
}
