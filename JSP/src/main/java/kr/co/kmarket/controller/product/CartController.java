package kr.co.kmarket.controller.product;

import kr.co.kmarket.dto.KmProductCartDTO;
import kr.co.kmarket.service.KmProductCartService;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/product/cart.do")
public class CartController extends HttpServlet {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(CartController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

/*        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession();
        String uid = (String) session.getAttribute("user");*/

        String uid = "user";

        KmProductCartService kmProductCartService= KmProductCartService.INSTANCE;
        List<KmProductCartDTO> kmProductCartDTOS = kmProductCartService.selectCarts(uid);

        logger.info(String.valueOf(kmProductCartDTOS.get(0).getProdNo()));
        req.setAttribute("kmProductCartDTOS", kmProductCartDTOS);

        req.getRequestDispatcher("/product/cart.jsp").forward(req, resp);
    }
}
