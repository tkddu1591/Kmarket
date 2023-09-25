package kr.co.kmarket.filter;

import kr.co.kmarket.dto.KmMemberDTO;
import kr.co.kmarket.dto.KmProductCate1DTO;
import kr.co.kmarket.dto.KmProductCate2DTO;
import kr.co.kmarket.service.KmProductCate1Service;
import kr.co.kmarket.service.KmProductCate2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CateFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");
        // 현재 세션 가져오기
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession();

        if (session.getAttribute("sessCoates1") == null) {



            //첫 접속시 세션에 카테고리 부여
            KmProductCate2Service kmProductCate2Service = KmProductCate2Service.INSTANCE;
            KmProductCate1Service kmProductCate1Service = KmProductCate1Service.INSTANCE;
            List<KmProductCate2DTO> sessCoates2First = kmProductCate2Service.selectCoates2();
            List<KmProductCate1DTO> sessCoates1First = kmProductCate1Service.selectCoates1();

            //카테고리 1, 2 맵으로 세팅
            Map<Integer, String> sessCoates1Map = new HashMap<>();
            Map<Integer, Map<Integer, String>> sessCoates2Map = new HashMap<>();

            for (KmProductCate1DTO cate1 : sessCoates1First) {
                //카테 번호 받으면 네임으로 변환
                sessCoates1Map.put(cate1.getCate1(), cate1.getC1Name());

                Map<Integer, String> sessCoates2MapTemp = new HashMap<>();
                for (KmProductCate2DTO cate2 : sessCoates2First) {
                    if (cate1.getCate1() == (cate2.getCate1())) {
                        sessCoates2MapTemp.put(cate2.getCate2(), cate2.getC2Name());
                    }
                }
                sessCoates2Map.put(cate1.getCate1(), sessCoates2MapTemp);
            }

            session.setAttribute("sessCoates1", sessCoates1First);
            session.setAttribute("sessCoates2", sessCoates2First);
            session.setAttribute("sessCoates1Map", sessCoates1Map);
            session.setAttribute("sessCoates2Map", sessCoates2Map);

        }
        chain.doFilter(req, resp);
    }
}
