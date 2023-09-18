package kr.co.kmarket.controller.cs.qna;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.KmCsCate1DTO;
import kr.co.kmarket.dto.KmCsCate2DTO;
import kr.co.kmarket.service.KmCsCateService;


@WebServlet("/cs/setCategoryList.do")
public class SetCategoryListController extends HttpServlet{

	private static final long serialVersionUID = -7801074301995661710L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KmCsCateService service = KmCsCateService.INSTANCE;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Map> depth1 = new ArrayList<>();
		Map<Integer, List> depth2 = new HashMap<>();
		
		//1차 카테고리 data 
		List<KmCsCate1DTO> cate1item = service.selectCSCate1s(0);
		
		for(KmCsCate1DTO dto1 : cate1item) {
			logger.debug("cate1 : " + dto1.getCate1());
			//1차 카테고리 List에 MapObject로 저장 
			Map<String, String> depth1_temp = new HashMap<>();
			depth1_temp.put("cate1", dto1.getCate1()+"");
			depth1_temp.put("c1Name", dto1.getC1Name());
			depth1.add(depth1_temp);
			
			//2차 카테고리 data
			List<KmCsCate2DTO> cate2item = service.selectCSCate2s(dto1.getCate1());
			
			//2차 카테고리 Map에 MapObject로 저장 
			List<Map> depth2_2 = new ArrayList<>();
			Map<String,String> depth2_temp = null;
			for(KmCsCate2DTO dto2 : cate2item) {
				depth2_temp = new HashMap<>();
				depth2_temp.put("cate1", dto2.getCate1()+"");
				depth2_temp.put("cate2", dto2.getCate2()+"");
				depth2_temp.put("c2Name", dto2.getC2Name());
				depth2_2.add(depth2_temp);
			}
			depth2.put(dto1.getCate1(), depth2_2);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("depth1", depth1);
		map.put("depth2", depth2);
		
		JSONObject jsonObject = new JSONObject(map);
		
		resp.setContentType("text/html; charset=UTF-8");

		//JSON 출력
		PrintWriter writer = resp.getWriter();
		writer.print(jsonObject.toString());
		
		
	}

}
