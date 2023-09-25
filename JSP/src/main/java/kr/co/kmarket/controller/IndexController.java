package kr.co.kmarket.controller;

import kr.co.kmarket.dto.KmMemberDTO;
import kr.co.kmarket.dto.KmProductCate1DTO;
import kr.co.kmarket.dto.KmProductCate2DTO;
import kr.co.kmarket.dto.KmProductDTO;
import kr.co.kmarket.service.KmMemberService;
import kr.co.kmarket.service.KmProductCate1Service;
import kr.co.kmarket.service.KmProductCate2Service;
import kr.co.kmarket.service.KmProductService;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(value = {"", "/index.do"})
public class IndexController extends HttpServlet {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IndexController.class);
    //private KmMemberService service = KmMemberService.instance;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<KmProductDTO> hitDTOS = new ArrayList<>();
        List<KmProductDTO> goodDTOS = new ArrayList<>();
        List<KmProductDTO> newDTOS = new ArrayList<>();
        List<KmProductDTO> discountDTOS = new ArrayList<>();
        List<KmProductDTO> soldDTOS = new ArrayList<>();

        KmProductService kmProductService = KmProductService.getInstance();

        hitDTOS = kmProductService.selectProducts("1");
        goodDTOS = kmProductService.selectProducts("31");
        newDTOS = kmProductService.selectProducts("51");
        discountDTOS = kmProductService.selectProducts("101");
        soldDTOS = kmProductService.selectProducts("11");

        req.setAttribute("hitDTOS", hitDTOS);
        req.setAttribute("goodDTOS", goodDTOS);
        req.setAttribute("newDTOS", newDTOS);
        req.setAttribute("discountDTOS", discountDTOS);
        req.setAttribute("soldDTOS", soldDTOS);


        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}