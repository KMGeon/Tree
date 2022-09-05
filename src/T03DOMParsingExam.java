package kr.or.ddit.basic;

import java.io.*;
import java.net.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.*;

public class T03DOMParsingExam {
	public void parse() throws ParserConfigurationException, SAXException, IOException {
		String svcKey = "Grid_20150827000000000227_1"; // 레시피 재료 정보 조회 서비스
		String apiKey = "1df7e8571e8df3f8cbc9b87691ca7d3e4d04f03c593d477e52bf67b03f0b6e1c"; // 개인별 발급.
		String startIdx = "1"; // 레시피 재료 시작 순번
		String endIdx = "5"; // 레시피 재료 종료 순번
		String recipeId = "118"; // 래시피가 궁금한 음식 ID

		URL url = new URL("http://211.237.50.150:7080/openapi/" + apiKey + "/xml/" + svcKey + "/" + startIdx + "/"
				+ endIdx + "?RECIPE_ID=" + recipeId);
//        System.out.println(url);

		// XML 문서를 생성하기 위한 DocumentBuilder 객체 생성하기
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();

		// DOM 파서를 이용하여 파일로부터 문서 객체를 가져옴.
		Document document = builder.parse(url.toString());

		// DOM Document로부터 루트 엘리먼트 가져오기
		Element root = document.getDocumentElement();
		System.out.println("root 엘리먼트 태그명 : " + root.getTagName());

		// 전체 레시피 수 가져오기
		String totalCnt = root.getElementsByTagName("totalCnt").item(0).getTextContent();

		endIdx = totalCnt;

		url = new URL("http://211.237.50.150:7080/openapi/" + apiKey + "/xml/" + svcKey + "/" + startIdx + "/" + endIdx
				+ "?RECIPE_ID=" + recipeId);

		// DOM 파서를 이용하여 파일로부터 문서 객체를 가져옴.
		document = builder.parse(url.toString());

		// DOM Document로부터 루트 엘리먼트 가져오기
		root = document.getDocumentElement();

		String code = root.getElementsByTagName("code").item(0).getTextContent();

		if (code.equals("INFO-000")) {
			// 하위 엘리먼트 접근하기
			NodeList rowNodeList = root.getElementsByTagName("row");

			for (int i = 0; i < rowNodeList.getLength(); i++) {
				Element rowEl = (Element) rowNodeList.item(i);
				String rowNum = rowEl.getElementsByTagName("ROW_NUM").item(0).getTextContent();
				String irdntNm = rowEl.getElementsByTagName("IRDNT_NM").item(0).getTextContent();
				String irdntCptcy = rowEl.getElementsByTagName("IRDNT_CPCTY").item(0).getTextContent();
				String irdntTyNm = rowEl.getElementsByTagName("IRDNT_TY_NM").item(0).getTextContent();

				String result = String.format("%3s %8s %10s %10s %8s", rowNum, recipeId, irdntTyNm, irdntNm,
						irdntCptcy);
				System.out.println(result);
				System.out.println("---------------------------------------------");
			}
		}

	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		new T03DOMParsingExam().parse();
	}
}