package kr.or.funding.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class MailUtil  {

	public static void sendMail(String _email,String _password,String _cMail, String mbsAddr , String mbsNm,String ptNm,String pyAm,String pyQty) {
	    System.out.println("Start JavaMail API Test ~!");

	    String subject = "펀딩메이트 결재내역";
	    String fromMail = "wnsgur0718@naver.com";
	    String fromName = "펀딩메이트";
//	    String toMail = "메렁"; // 콤마(,) 나열 가능
	    String toMail = _cMail;

	    // mail contents
	    StringBuffer contents = new StringBuffer();
	    contents.append("<h1>제품명:" + ptNm +"</h1>\n");
	    contents.append("<h1>회원이름:" + mbsNm +"</h1>\n");
	    contents.append("<h1>개수:" + pyQty +"개</h1>\n");
	    contents.append("<h1>결재금액:" + pyAm +"원</h1>\n");
	    contents.append("<h1>배송지:" + mbsAddr +"</h1>\n");
	    
//	    contents.append("<p>Nice to meet you ~! :)</p><br>");

	    // mail properties
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.naver.com"); // use naver mail
	    props.put("mail.smtp.port", "465"); // set port

	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true"); // use TLS
	    props.put("mail.smtps.ssl.checkserveridentity", "true");
	    props.put("mail.debug", "true");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    

	    Session mailSession = Session.getInstance(props,
	            new javax.mail.Authenticator() { // set authenticator
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(_email, _password);
	                }
	            });

	    try {
	        MimeMessage message = new MimeMessage(mailSession);

	        message.setFrom(new InternetAddress(fromMail, MimeUtility.encodeText(fromName, "UTF-8", "B"))); // 한글의 경우 encoding 필요
	        message.setRecipients(
	            Message.RecipientType.TO, 
	            InternetAddress.parse(toMail)
	        );
	        message.setSubject(subject);
	        message.setContent(contents.toString(), "text/html;charset=UTF-8"); // 내용 설정 (HTML 형식)
	        message.setSentDate(new java.util.Date());

	        Transport t = mailSession.getTransport("smtp");
	        t.connect(_email, _password);
	        t.sendMessage(message, message.getAllRecipients());
	        t.close();

	        System.out.println("Done Done ~!");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
