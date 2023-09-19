package kr.co.kmarket.controller.product;

import kr.co.kmarket.dto.KmProductOrderDTO;
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
        resp.setContentType("text/html;charset=utf-8");
        KmProductOrderDTO productOrderDTO = new KmProductOrderDTO();
        //ordUid, ordCount, ordPrice, ordDiscount, ordDelivery, savePoint, usedPoint, ordTotPrice, recipHp, recipZip, recipName, recipAddr1, recipAddr2, ordPayment, ordComplete, ordDate

        List<String> orderData = new java.util.ArrayList<String>();
        orderData.add(req.getParameter("ordUid"));
        logger.info(orderData.get(0));
        orderData.add(req.getParameter("ordCount"));
        orderData.add(req.getParameter("ordPrice"));
        orderData.add(req.getParameter("ordDiscount"));
        orderData.add(req.getParameter("ordDelivery"));
        orderData.add(req.getParameter("savePoint"));
        if(req.getParameter("usedPoint") != null) {
            orderData.add(req.getParameter("usedPoint"));
        }else {
            orderData.add("");
        }
        orderData.add(req.getParameter("ordTotPrice"));
        orderData.add(req.getParameter("recipHp"));
        orderData.add(req.getParameter("recipZip"));
        orderData.add(req.getParameter("recipName"));
        orderData.add(req.getParameter("recipAddr1"));
        if(req.getParameter("recipAddr2") != null) {
            orderData.add(req.getParameter("recipAddr2"));
        }else {
            orderData.add("");
        }
        orderData.add(req.getParameter("ordPayment"));

        List<String> orderDataTemp = new ArrayList<>();
        int i =0;
        for(String data : orderData) {
            orderDataTemp.add(data.replace(",","").replace(" P","").replace("-",""));
            logger.info(orderDataTemp.get(i));
            i++;
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



        req.getRequestDispatcher("/product/complete.jsp").forward(req, resp);
    }
}
