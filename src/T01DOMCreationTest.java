package kr.or.ddit.basic;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;



/*
 * xml dom을 이용한 xml 문서 생성하기
 */
public class T01DOMCreationTest {

	/*
	 * w3c(world wide web consortium) dom(doucument object model)표준에 대하여.. DOM은 문서에
	 * 접근하는 표준방법으로서 이를 이용하면 플랫폼 및 개발언어에 독립적으로 문서의 내용 구조 및 스타일 정보를 동적으로 핸들링(접근 , 수정 ,
	 * 삭제 , 생성) 할 수 있다.
	 * 
	 * W3C DOM 표준은 다음과 같이 크게 3가지로 나누어 볼 수 있다.
	 * 
	 * 1.CORE DOM - 모든 문서타입을 위한 핵심 표준 모델(API) 2.XML DOM - XML문서를 위한 표준 모델 3.HTTP DOM
	 * - HTML문서를 위한 표준 모델
	 * 
	 * 예를 들면 HTML DOM은 HTML 엘리먼트 요소 및 속성 정보를 핸들링 하기 위한 인터페이스를 제공한다.
	 * 
	 * 
	 */
	public void createDoc() throws ParserConfigurationException, TransformerException, FileNotFoundException, IOException {
//	xml문서를 생성하기 위한 documentBuilder 객체 생성하기
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = dbf.newDocumentBuilder();

//		
		// document 객체 생성
		Document document = builder.newDocument();

		// root 엘리먼트 생성
		org.w3c.dom.Element root = document.createElement("root");

		// booklist 엘리먼트 생성
		org.w3c.dom.Element booklist = document.createElement("booklist");

//		book엘리먼트 생성 및 속성값 설정하기
		org.w3c.dom.Element book = document.createElement("book");
		book.setAttribute("isbn", "B001");
		book.setAttribute("kind", "JAVA");

//		자식 엘리먼트 생성 및 설정
		org.w3c.dom.Element title = document.createElement("title");
		title.setTextContent("자바초급");
		org.w3c.dom.Element author = document.createElement("author");
		author.setTextContent("이순신");
		org.w3c.dom.Element price = document.createElement("price");
		price.setTextContent("25000");

		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);

//		book 엘리멘트에 book 엘리먼트 추가하기
		booklist.appendChild(book);
//		=====================================================================================
//		book엘리먼트 생성 및 속성값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B002");
		book.setAttribute("kind", "JAVA");

//		자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("자바고급");
		author = document.createElement("author");
		author.setTextContent("강감찬");
		price = document.createElement("price");
		price.setTextContent("35000");

		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);

//		book 엘리멘트에 book 엘리먼트 추가하기
		booklist.appendChild(book);
//		=====================================================================================
//		book엘리먼트 생성 및 속성값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B003");
		book.setAttribute("kind", "DB");

//		자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("디비설계");
		author = document.createElement("author");
		author.setTextContent("안하용");
		price = document.createElement("price");
		price.setTextContent("45000");

		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);

//		book 엘리멘트에 book 엘리먼트 추가하기
		booklist.appendChild(book);
//		=====================================================================================
//		book엘리먼트 생성 및 속성값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B004");
		book.setAttribute("kind", "DB");

//		자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("디비구현");
		author = document.createElement("author");
		author.setTextContent("김연수");
		price = document.createElement("price");
		price.setTextContent("55000");

		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);

//		book 엘리멘트에 book 엘리먼트 추가하기
		booklist.appendChild(book);

//		==============================================================================
//		루트 엘리멘트에 BOOKLIST 추가하기
		root.appendChild(booklist);
//		document에 root엘리먼트 추가하기
		document.appendChild(root);

//		XML 문자열로 변환하기 
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		DOMSource source = new DOMSource(document);

		StreamResult result = new StreamResult(out);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();

		javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();

//		출력 인코딩 설정
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

//		들여쓰기 활성화하고 들여쓰기 크기 설정(공백 2칸)
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-aount", "2");

//		변환작업 시작
		transformer.transform(source, result);

//		파일 문서 저장하기
		try (FileOutputStream fos = new FileOutputStream("./src/new_book.xml")) {
			fos.write(out.toByteArray());
		}

	}

	public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, TransformerException, IOException {
		new T01DOMCreationTest().createDoc();
	}
}
