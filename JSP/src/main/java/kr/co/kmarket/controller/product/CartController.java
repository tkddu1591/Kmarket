package kr.co.kmarket.controller.product;

import kr.co.kmarket.dto.KmMemberDTO;
import kr.co.kmarket.dto.KmProductCartDTO;
import kr.co.kmarket.dto.KmProductDTO;
import kr.co.kmarket.service.KmProductCartService;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@WebServlet("/product/cart.do")
public class CartController extends HttpServlet {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(CartController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession();
        KmMemberDTO sessUser = (KmMemberDTO) session.getAttribute("sessUser");


        KmProductCartService kmProductCartService = KmProductCartService.INSTANCE;
        List<KmProductCartDTO> kmProductCartDTOS = kmProductCartService.selectCarts(sessUser.getUid());

        req.setAttribute("kmProductCartDTOS", kmProductCartDTOS);

        req.getRequestDispatcher("/product/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*<input type="hidden" name="prodNo" value="${kmProduct.prodNo}"/>
                    <input type="hidden" name="uid" value="${sessUser.uid}"/>
                    <input type="hidden" name="count" value="1"/>
                    <input type="hidden" name="price" value="${kmProduct.price}"/>
                    <input type="hidden" name="discount" value="${kmProduct.point}"/>
                    <input type="hidden" name="delivery" value="${kmProduct.delivery}"/>
                    <input type="hidden" name="total" value="${kmProduct.total}"/>
            */


        req.setCharacterEncoding("utf-8");
        String[] kmProductDTOS = req.getParameterValues("dto");
        KmProductCartService kmProductCartService = KmProductCartService.INSTANCE;
        for(String kmProductDTO : kmProductDTOS) {
            String[] terms = kmProductDTO.toString().split(",",2);
            kmProductCartService.deleteCart(terms[0]);
        }
/*        ProductService productService = new ProductService();
        for(int i = 0; i < productNos.length; i++) {
            productService.deleteProduct(Integer.parseInt(productNos[i]));
        }*/



        resp.sendRedirect("./cart.do");
    }
}
