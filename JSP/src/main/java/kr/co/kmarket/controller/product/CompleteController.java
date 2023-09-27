package kr.co.kmarket.controller.product;

import kr.co.kmarket.dto.KmMemberPointDTO;
import kr.co.kmarket.dto.KmProductOrderDTO;
import kr.co.kmarket.dto.KmProductOrderItemDTO;
import kr.co.kmarket.service.*;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/product/complete.do")
public class CompleteController extends HttpServlet {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(CompleteController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        KmProductOrderDTO productOrderDTO = new KmProductOrderDTO();
        //ordUid, ordCount, ordPrice, ordDiscount, ordDelivery, savePoint, usedPoint, ordTotPrice, recipHp, recipZip, recipName, recipAddr1, recipAddr2, ordPayment, ordComplete, ordDate

        //km_prodcut_order insert------------------------------
        List<String> orderData = new java.util.ArrayList<String>();
        String ordUid = req.getParameter("ordUid");
        orderData.add(ordUid);
        orderData.add(req.getParameter("ordCount"));
        orderData.add(req.getParameter("ordPrice"));
        orderData.add(req.getParameter("ordDiscount"));
        orderData.add(req.getParameter("ordDelivery"));
        orderData.add(req.getParameter("savePoint"));
        if (req.getParameter("usedPoint") == null || req.getParameter("usedPoint").equals("0") || req.getParameter("usedPoint").isEmpty()) {
            orderData.add("0");
        } else {
            orderData.add(req.getParameter("usedPoint"));
        }
        orderData.add(req.getParameter("ordTotPrice"));
        orderData.add(req.getParameter("recipHp"));
        orderData.add(req.getParameter("recipZip"));
        orderData.add(req.getParameter("recipName"));
        orderData.add(req.getParameter("recipAddr1"));
        if (req.getParameter("recipAddr2") != null) {
            orderData.add(req.getParameter("recipAddr2"));
        } else {
            orderData.add("");
        }
        orderData.add(req.getParameter("ordPayment"));
        logger.info(req.getParameter("ordPayment"));

        List<String> orderDataTemp = new ArrayList<>();
        int j = 0;
        for (String data : orderData) {
            if (j == 8) {
                j++;
                orderDataTemp.add(data);
                continue;
            }
            orderDataTemp.add(data.replace(",", "").replace(" P", "").replace("-", ""));
            j++;
        }

        productOrderDTO.setOrdUid(orderDataTemp.get(0));
        productOrderDTO.setOrdCount(orderDataTemp.get(1));
        productOrderDTO.setOrdPrice(orderDataTemp.get(2));
        productOrderDTO.setOrdDiscount(orderDataTemp.get(3));
        productOrderDTO.setOrdDelivery(orderDataTemp.get(4));
        productOrderDTO.setSavePoint(orderDataTemp.get(5));
        productOrderDTO.setUsedPoint(orderDataTemp.get(6));
        productOrderDTO.setOrdTotPrice(orderDataTemp.get(7));
        productOrderDTO.setRecipHp(orderDataTemp.get(8));
        productOrderDTO.setRecipZip(orderDataTemp.get(9));
        productOrderDTO.setRecipName(orderDataTemp.get(10));
        productOrderDTO.setRecipAddr1(orderDataTemp.get(11));
        productOrderDTO.setRecipAddr2(orderDataTemp.get(12));
        productOrderDTO.setOrdPayment(orderDataTemp.get(13));
        productOrderDTO.setOrdComplete(1);
        KmProductOrderService kmProductOrderService = KmProductOrderService.INSTANCE;
        kmProductOrderService.insertOrder(productOrderDTO);
        req.setAttribute("order", productOrderDTO);
        //km_prodcut_order insert 완료-------------------------


        //km_product_order_item insert ------------------------
        String[] kmProductDTOS = req.getParameterValues("dto");

        //개별 item 값 전달
        KmProductOrderItemService kmProductOrderItemService = KmProductOrderItemService.INSTANCE;

        String[] prodName = req.getParameterValues("prodName");
        String[] descript = req.getParameterValues("descript");
        String[] thumb1 = req.getParameterValues("thumb1");
        //order No 셀렉
        int lastOrderNo = kmProductOrderService.selectLastOrderNo();
        req.setAttribute("orderNo", lastOrderNo);

        List<KmProductOrderItemDTO> kmProductOrderItemDTOS = new ArrayList<>();

        int i = 0;
        for (String kmProductDTO : kmProductDTOS) {
            // 0:prodNo
            // 1:count
            // 2:price
            // 3:discount
            // 4:point
            // 5:delivery
            // 6:total;
            KmProductOrderItemDTO kmProductOrderItemDTO = new KmProductOrderItemDTO();
            String[] orderItemData = kmProductDTO.toString().split(",", 10);

            kmProductOrderItemDTO.setOrdNo(lastOrderNo);
            kmProductOrderItemDTO.setProdNo(Integer.parseInt(orderItemData[0]));
            kmProductOrderItemDTO.setCount(Integer.parseInt(orderItemData[1]));
            kmProductOrderItemDTO.setPrice(Integer.parseInt(orderItemData[2]));
            kmProductOrderItemDTO.setDiscount(Integer.parseInt(orderItemData[3]));
            kmProductOrderItemDTO.setDelivery(Integer.parseInt(orderItemData[5]));
            kmProductOrderItemDTO.setPoint(Integer.parseInt(orderItemData[4]));
            kmProductOrderItemDTO.setTotal(Integer.parseInt(orderItemData[6]));
            kmProductOrderItemDTO.setProdName(prodName[i]);
            kmProductOrderItemDTO.setDescript(descript[i]);
            kmProductOrderItemDTO.setThumb1(thumb1[i]);
            i++;
            kmProductOrderItemDTOS.add(kmProductOrderItemDTO);

            kmProductOrderItemService.insertKmProductOrderItem(kmProductOrderItemDTO);

            KmProductService kmProductService = KmProductService.getInstance();

            //km_product stock 감소-----------------------
            kmProductService.updateProduct(kmProductOrderItemDTO.getCount(), kmProductOrderItemDTO.getProdNo());

        }

        //km_product_order_item insert 완료------------------------

        //km_cart_delete
        KmProductCartService kmProductCartService = KmProductCartService.INSTANCE;

        kmProductCartService.deleteCarts(ordUid);
        //km_cart_delete 완료------------------------


        //km_member point 변화
        KmMemberService memberService = KmMemberService.instance;
        KmMemberPointService kmMemberPointService = KmMemberPointService.INSTANCE;

        int point = productOrderDTO.getSavePoint() - productOrderDTO.getUsedPoint();

        //변화포인트 0 아닐때에만 작동
        if (point != 0) {
            memberService.updatePoint(ordUid, point);
            //km_member point 변화 완료------------------------

            //km_member_point 테이블 insert
            kmMemberPointService.insertKmMemberPoint(point, lastOrderNo, ordUid);
        }


        //개별 데이터 List로 view에 전달
        req.setAttribute("kmProductOrderItemDTOS", kmProductOrderItemDTOS);

        req.getRequestDispatcher("/product/complete.jsp").forward(req, resp);
    }
}
