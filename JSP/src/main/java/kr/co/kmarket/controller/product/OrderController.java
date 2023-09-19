package kr.co.kmarket.controller.product;

import kr.co.kmarket.dto.KmProductOrderItemDTO;
import kr.co.kmarket.service.KmProductCartService;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/product/order.do")
public class OrderController extends HttpServlet {
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(OrderController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String[] kmProductDTOS = req.getParameterValues("dto");
        String[] prodName=req.getParameterValues("prodName");
        String[] descript = req.getParameterValues("descript");
        KmProductCartService kmProductCartService = KmProductCartService.INSTANCE;
        List<KmProductOrderItemDTO> kmProductOrderItemDTOS = new ArrayList<>();
        int i =0;
        //개별 item 값 전달
        for(String kmProductDTO : kmProductDTOS) {
            // 0: cartNo,
            // 1: uid,
            // 2: prodNo,
            // 3: count,
            // 4: price,
            // 5: discount,
            // 6: delivery,
            // 7: point,
            // 8: total,
            // 9: rDate
            KmProductOrderItemDTO kmProductOrderItemDTO = new KmProductOrderItemDTO();
            String[] cartItemData = kmProductDTO.toString().split(",",10);
            kmProductOrderItemDTO.setProdNo(Integer.parseInt(cartItemData[2]));
            kmProductOrderItemDTO.setCount(Integer.parseInt(cartItemData[3]));
            kmProductOrderItemDTO.setPrice(Integer.parseInt(cartItemData[4]));
            kmProductOrderItemDTO.setDiscount(Integer.parseInt(cartItemData[5]));
            kmProductOrderItemDTO.setDelivery(Integer.parseInt(cartItemData[6]));
            kmProductOrderItemDTO.setPoint(Integer.parseInt(cartItemData[7]));
            kmProductOrderItemDTO.setTotal(Integer.parseInt(cartItemData[8]));
            kmProductOrderItemDTO.setProdName(prodName[i]);
            kmProductOrderItemDTO.setDescript(descript[i]);
            kmProductOrderItemDTOS.add(kmProductOrderItemDTO);
            i++;
        }
        req.setAttribute("kmProductOrderItemDTOS", kmProductOrderItemDTOS);


        // 0: finalCount
        // 1: finalPrice
        // 2: finalDiscount
        // 3: finalPoint
        // 4: finalDelivery
        // 5: finalTotal
        logger.info(req.getParameter("finalCount"));
        req.setAttribute("finalCount", req.getParameter("finalCount"));
        req.setAttribute("finalPrice", req.getParameter("finalPrice"));
        req.setAttribute("finalDiscount", req.getParameter("finalDiscount"));
        req.setAttribute("finalPoint", req.getParameter("finalPoint"));
        req.setAttribute("finalDelivery", req.getParameter("finalDelivery"));
        req.setAttribute("finalTotal", req.getParameter("finalTotal"));



        req.getRequestDispatcher("/product/order.jsp").forward(req, resp);
    }
}
