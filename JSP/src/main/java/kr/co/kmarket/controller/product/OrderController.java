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

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String[] kmProductDTOS = req.getParameterValues("dto");
        String[] prodName=req.getParameterValues("prodName");
        String[] descript = req.getParameterValues("descript");
        String[] thumb1 = req.getParameterValues("thumb1");

        String finalCount= null;
        String finalPrice= null;
        String finalDiscount= null;
        String finalPoint= null;
        String finalDelivery= null;
        String finalTotal= null;
        List<KmProductOrderItemDTO> kmProductOrderItemDTOS = new ArrayList<>();
        if(kmProductDTOS != null){
            KmProductCartService kmProductCartService = KmProductCartService.INSTANCE;
            int i = 0;
            //개별 item 값 전달
            for (String kmProductDTO : kmProductDTOS) {
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
                // 10: thumb1
                // 11: prodName
                // 12: descript

                finalCount =  req.getParameter("finalCount");
                finalPrice =  req.getParameter("finalPrice");
                finalDiscount =  req.getParameter("finalDiscount");
                finalPoint =  req.getParameter("finalPoint");
                finalDelivery =  req.getParameter("finalDelivery");
                finalTotal =  req.getParameter("finalTotal");
                KmProductOrderItemDTO kmProductOrderItemDTO = new KmProductOrderItemDTO();
                String[] cartItemData = kmProductDTO.toString().split(",", 13);
                kmProductOrderItemDTO.setProdNo(Integer.parseInt(cartItemData[2]));
                kmProductOrderItemDTO.setCount(Integer.parseInt(cartItemData[3]));
                kmProductOrderItemDTO.setPrice(Integer.parseInt(cartItemData[4]));
                kmProductOrderItemDTO.setDiscount(Integer.parseInt(cartItemData[5]));
                kmProductOrderItemDTO.setDelivery(Integer.parseInt(cartItemData[6]));
                kmProductOrderItemDTO.setPoint(Integer.parseInt(cartItemData[7]));
                kmProductOrderItemDTO.setTotal(Integer.parseInt(cartItemData[8]));
                kmProductOrderItemDTO.setProdName((cartItemData[11]));
                kmProductOrderItemDTO.setDescript((cartItemData[12]));
                kmProductOrderItemDTO.setThumb1((cartItemData[10]));
                kmProductOrderItemDTOS.add(kmProductOrderItemDTO);
                i++;
            }

        }else {

            /*                    <input type="hidden" name="prodNo" value="${kmProduct.prodNo}"/>
                    <input type="hidden" name="prodName" value="${kmProduct.prodName}"/>
                    <input type="hidden" name="descript" value="${kmProduct.descript}"/>
                    <input type="hidden" name="uid" value="${sessUser.uid}"/>
                    <input type="hidden" name="count" value="1"/>
                    <input type="hidden" name="price" value="${kmProduct.price}"/>
                    <input type="hidden" name="discount" value="${kmProduct.discount}"/>
                    <input type="hidden" name="delivery" value="${kmProduct.delivery}"/>
                    <input type="hidden" name="total" value="${kmProduct.total}"/>
                    <input type="hidden" name="point" value="${kmProduct.point}">
                    <input type="hidden" name="cate1" value="${cate1}">
                    <input type="hidden" name="cate2" value="${cate2}">
                    <input type="hidden" name="type" value="order">*/

            finalCount =  "1";
            finalPrice =    String.format("%,d",Integer.parseInt(req.getParameter("price")));
            finalDiscount =  (req.getParameter("discount"));
            finalDiscount = String.format("%,d",(Integer.parseInt(finalDiscount)*Integer.parseInt(req.getParameter("price"))/100));
            finalPoint =    String.format("%,d",Integer.parseInt(req.getParameter("point")));
            finalDelivery =    String.format("%,d",Integer.parseInt(req.getParameter("delivery")));
            finalTotal =    String.format("%,d",Integer.parseInt(req.getParameter("total")));
            KmProductOrderItemDTO kmProductOrderItemDTO = new KmProductOrderItemDTO();
            kmProductOrderItemDTO.setProdNo(req.getParameter("prodNo"));
            kmProductOrderItemDTO.setCount(req.getParameter("count"));
            kmProductOrderItemDTO.setPrice(req.getParameter("price"));
            kmProductOrderItemDTO.setDiscount(req.getParameter("discount"));
            kmProductOrderItemDTO.setDelivery(req.getParameter("delivery"));
            kmProductOrderItemDTO.setPoint(req.getParameter("point"));
            kmProductOrderItemDTO.setTotal(req.getParameter("total"));
            kmProductOrderItemDTO.setProdName(req.getParameter("prodName"));
            kmProductOrderItemDTO.setDescript(req.getParameter("descript"));
            kmProductOrderItemDTO.setThumb1(req.getParameter("thumb1"));
            kmProductOrderItemDTOS.add(kmProductOrderItemDTO);
        }

        req.setAttribute("kmProductOrderItemDTOS", kmProductOrderItemDTOS);
        logger.info(req.getParameter("finalCount"));
        req.setAttribute("finalCount",finalCount);
        req.setAttribute("finalPrice",finalPrice);
        req.setAttribute("finalDiscount",finalDiscount);
        req.setAttribute("finalPoint",finalPoint);
        req.setAttribute("finalDelivery",finalDelivery);
        req.setAttribute("finalTotal",finalTotal);



        req.getRequestDispatcher("/product/order.jsp").forward(req, resp);
    }
}
