package kr.co.kmarket.controller;

import kr.co.kmarket.dto.KmMemberDTO;
import kr.co.kmarket.dto.KmProductCate1DTO;
import kr.co.kmarket.dto.KmProductCate2DTO;
import kr.co.kmarket.service.KmProductCate1Service;
import kr.co.kmarket.service.KmProductCate2Service;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(value = {"", "/index.do"})
public class IndexController extends HttpServlet {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IndexController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}