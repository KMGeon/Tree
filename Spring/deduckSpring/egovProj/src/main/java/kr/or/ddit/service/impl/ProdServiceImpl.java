package kr.or.ddit.service.impl;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.BookMapper;
import kr.or.ddit.mapper.ProdMapper;
import kr.or.ddit.service.BookService;
import kr.or.ddit.service.ProdService;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProdServiceImpl implements ProdService {
	@Autowired
	ProdMapper ProdMapper;

	@Override
	public JSONObject amtSale() {
		// ***다른점 json object 만들기
		List<Map<String, Object>> list = this.ProdMapper.amtSale();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			log.info(map.toString());
		}
		// JsonObject를 만들어보자
		JSONObject data = new JSONObject();
		/*
		 * "cols":[ {"id":"", "label":"상품명", "pattern":"", "type":"string"}, {"id":"",
		 * "label":"금액", "pattern":"", "type":"number"} ]
		 */
		// 1.cols 배열에 넣기
		// JSON컬럼 객체
		JSONObject col1 = new JSONObject();
		JSONObject col2 = new JSONObject();

		JSONArray title = new JSONArray();
		col1.put("lable", "상품명");
		col1.put("type", "string");
		col2.put("lable", "금액");
		col2.put("type", "number");
		title.add(col1);
		title.add(col2);
		// 위에 데이터를 cols에 넣으면 된다. -> data에 넣으면 된다.
		data.put("cols", title);

		/*
		 * "rows":[ {"c":[{"v":"Mushrooms"},{"v":3}]}, {"c":[{"v":"Onions"},{"v":1}]},
		 * {"c":[{"v":"Olives"},{"v":1}]}, {"c":[{"v":"Zucchini"},{"v":1}]},
		 * {"c":[{"v":"Pepperoni"},{"v":2}]} ]
		 */
		// 2.rows에 넣기
		JSONArray body = new JSONArray();
		for (Map<String, Object> map : list) {
			JSONObject prodName = new JSONObject();
			prodName.put("v", map.get("PRODNAME"));// 상품명

			JSONObject money = new JSONObject();
			money.put("v", map.get("MONEY"));

			JSONArray row = new JSONArray();
			row.add(prodName);
			row.add(money);

			// {}jsonobject이고 []면 jsonarray
			JSONObject cell = new JSONObject();
			cell.put("c", row);
			body.add(cell);// 레코드 1행 추가
		}
		data.put("rows", body);
		return data;
	}
}
