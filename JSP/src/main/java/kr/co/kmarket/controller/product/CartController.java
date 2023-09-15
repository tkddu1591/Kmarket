package kr.co.kmarket.controller.product;

import kr.co.kmarket.dto.KmMemberDTO;
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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

        logger.info(String.valueOf(kmProductCartDTOS.get(0).getProdNo()));
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

        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        int cate1 = Integer.parseInt(req.getParameter("cate1"));
        int cate2 = Integer.parseInt(req.getParameter("cate2"));
        Map<Integer, String> sessCoates1Map = (Map<Integer, String>) session.getAttribute("sessCoates1Map");
        Map<Integer, Map<Integer, String>> sessCoates2Map = (Map<Integer, Map<Integer, String>>) session.getAttribute("sessCoates2Map");


        req.setAttribute("cate1",cate1);
        req.setAttribute("cate2",cate2);

        req.setAttribute("cate1Name",sessCoates1Map.get(cate1));
        if(cate2!=0) {
            req.setAttribute("cate2Name", sessCoates2Map.get(cate1).get(cate2));
        }
        // 현재 날짜/시간
        Date rDate = Calendar.getInstance().getTime();

        // 포맷팅 정의
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 포맷팅 적용
        String formatedNow = formatter.format(rDate);

        KmProductCartDTO kmProductCartDTO = new KmProductCartDTO();
        kmProductCartDTO.setUid(req.getParameter("uid"));
        kmProductCartDTO.setProdNo(req.getParameter("prodNo"));
        kmProductCartDTO.setCount(req.getParameter("count"));
        kmProductCartDTO.setPrice(req.getParameter("price"));
        kmProductCartDTO.setDiscount(req.getParameter("discount"));
        kmProductCartDTO.setPoint(req.getParameter("point"));
        kmProductCartDTO.setDelivery(req.getParameter("delivery"));
        kmProductCartDTO.setTotal(req.getParameter("total"));
        kmProductCartDTO.setrDate(formatedNow);


        KmProductCartService kmProductCartService = KmProductCartService.INSTANCE;

        kmProductCartService.insertCart(kmProductCartDTO);


        resp.sendRedirect("/JSP/product/view.do?success=100&prodNo=" + kmProductCartDTO.getProdNo() + "&cate1=" + cate1 + "&cate2=" + cate2);
    }
}
