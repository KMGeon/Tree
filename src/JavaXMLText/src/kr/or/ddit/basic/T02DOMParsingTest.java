package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class T02DOMParsingTest {
	public void parse() throws ParserConfigurationException, SAXException, IOException {

		// XML 문서를 생성하기 위한 DocumentBuilder 객체 생성하기
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();

		// DOM parser를 이용하여 파일로부터 문서 객체를 가져옴.
		Document document = builder.parse(new File("./src/new_book.xml"));
		
		// Document 객체로부터 루트 엘리먼트 가져오기
		Element root = document.getDocumentElement();
		System.out.println("루트 엘리먼트 태그명 : " + root.getTagName());
		
		// 하위 엘리먼트 접근방법1: getElementByTagName()메소드 이용
		NodeList bookNodeList = root.getElementsByTagName("book");
		Node firstBookNode = bookNodeList.item(0); // 첫번째 항목
		Element firstBookElement = (Element) firstBookNode;
		
		// 속성 접근방법1: 엘리먼트 객체의 getAttribute() 이용
		System.out.println("엘리먼트 객체의 getAttribute() 이용 => " + firstBookElement.getAttribute("isbn"));
		
		// 속성 접근방법2: 노드객체의 getAttributes() 이용 (속성노드 접근하기), nodeMap.getNamedItem("isbn")는 노드 타입으로 리턴됨
		NamedNodeMap nodeMap = firstBookNode.getAttributes();
		System.out.println("노드객체 getAttributes() 이용 => " + nodeMap.getNamedItem("isbn").getNodeValue());
		
		// 하위 엘리먼트 접근방법2: getChildNodes()메소드 이용
		NodeList firstBookChildNoseList = firstBookNode.getChildNodes();
		
		// 엔터키에 해당하는 부분이 읽힐수 있기 때문에 getChildNodes()보다는 
		// getElementByTagName()을 이용해 접근하는 방법이 좋다.
		Node titleNode = firstBookChildNoseList.item(1); 
		// xml파일의 title 엘리먼트를 쓰려고 엔터키(공백도 읽힌다.)를 입력했기 때문에 0번째가 아니라 1번이다.
		Element titleElement = (Element) titleNode;
		System.out.println("titleElement.getTagName() => " + titleElement.getTagName());
		System.out.println("titleElement.getTextContent() => " + titleElement.getTextContent());
		
		// 전체 출력하기
		System.out.println("------------------------------------------------------------------");
		System.out.printf("%8s %8s %15s %10s %8s\n", "ISBN","분류","제목","저자","가격");
		System.out.println("------------------------------------------------------------------");
		
		for(int i=0; i<bookNodeList.getLength(); i++) {
			Node bookNode = bookNodeList.item(i);
			Element bookElement = (Element) bookNode;
			String isbn = bookElement.getAttribute("isbn");
			String kind = bookElement.getAttribute("kind");
			String title = bookElement.getElementsByTagName("title").item(0).getTextContent().trim();
			String author = bookElement.getElementsByTagName("author").item(0).getTextContent().trim();
			String price = bookElement.getElementsByTagName("price").item(0).getTextContent().trim();
			String str = String.format("%8s %8s %15s %10s %8s", isbn, kind, title, author, price);
			
			System.out.println(str);
		}

	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		new T02DOMParsingTest().parse();
	}
}
