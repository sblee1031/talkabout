package com.talkabout.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.stereotype.Service;

import com.talkabout.dto.Debate;
import com.talkabout.dto.DebateDetail;
@Service
public class MailService {
	static String  _email = "psyy2244@gmail.com"; 
	static String  _password = "talkabout1234";
	public static void main(String[] args) {
		//sendMail("psyy2244@gmail.com", "talkabout1234");
	}

	public static void sendMail(Debate deb, DebateDetail dd1, DebateDetail dd2) {
		System.out.println("=========Start TalkAbout Mail ~!");
		String subject = "새로운 토론이 요청되었습니다.";
		String fromMail = "psyy2244@gmail.com";
		String fromName = "TA관리자";
		String toMail = "leebbong001@naver.com"; // 콤마(,) 나열 가능 // mail contents
//, andykr1234@gmail.com , ajs9518@gmail.com, choim940328@gmail.com
		StringBuffer contents = new StringBuffer();
		contents.append("<div style='text-align:center;'><h1>새로운 토론이 요청되었습니다.</h1>\n");
		contents.append("<h2> 관리자님의 승인을 기다립니다.</h2>\n<hr/>");
		contents.append("<h2>주제 : ");
		contents.append(deb.getDebate_topic()+"</h2>\n");
		contents.append("<h2>토론 진행 시간 : "+deb.getDebate_time()+"분</h2>\n");
		contents.append("<h2>시작시간 : "+deb.getDebate_startDate()+"</h2>\n");
		contents.append("<h1>토론자1 : "+dd1.getDiscussor().getMember_nickName()+"</h1>\n");
		contents.append("<h1>토론자2 : "+dd2.getDiscussor().getMember_nickName()+"</h1>\n");
		contents.append("<h3>언제나 수고하시는 TA관리자님 화이팅!!!</h3><br></div>"); // mail properties

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // use Gmail
		props.put("mail.smtp.port", "587"); // set port
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true"); // use TLS
		Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() { // set authenticator
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(_email, _password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(fromMail, MimeUtility.encodeText(fromName, "UTF-8", "B"))); // 한글의 경우
																											// encoding
																											// 필요
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
			message.setSubject(subject);
			message.setContent(contents.toString(), "text/html;charset=UTF-8"); // 내용 설정 (HTML 형식)
			message.setSentDate(new java.util.Date());
			Transport t = mailSession.getTransport("smtp");
			t.connect(_email, _password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			System.out.println("메일발송 성공!");
			System.out.println("=========End TalkAbout Mail ~!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void approveMail(Debate deb, DebateDetail dd1, DebateDetail dd2) {
		System.out.println("=========Start TalkAbout Mail ~!");
		String subject = "토론이 승인 되었습니다.";
		String fromMail = "psyy2244@gmail.com";
		String fromName = "TA관리자";
		String toMail = dd1.getDiscussor().getMember_email()+" ,"+dd2.getDiscussor().getMember_email(); // 콤마(,) 나열 가능 // mail contents
//, andykr1234@gmail.com , ajs9518@gmail.com, choim940328@gmail.com
		StringBuffer contents = new StringBuffer();
		contents.append("<div style='text-align:center;'><h1>토론이 승인되었습니다.</h1>\n");
		contents.append("<h2>토론 정보를 확인 해주세요.</h2>\n<hr/>");
		contents.append("<h2>주제 : ");
		contents.append(deb.getDebate_topic()+"</h2>\n");
		contents.append("<h2>토론 진행 시간 : "+deb.getDebate_time()+"분</h2>\n");
		contents.append("<h2>시작시간 : "+deb.getDebate_startDate()+"</h2>\n");
		contents.append("<h1>토론자1 : "+dd1.getDiscussor().getMember_nickName()+"</h1>\n");
		contents.append("<h1>토론자2 : "+dd2.getDiscussor().getMember_nickName()+"</h1>\n");
		contents.append("<hr/><p>즐거운 토론 되시기 바랍니다.</p><br></div>"); // mail properties

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // use Gmail
		props.put("mail.smtp.port", "587"); // set port
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true"); // use TLS
		Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() { // set authenticator
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(_email, _password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(fromMail, MimeUtility.encodeText(fromName, "UTF-8", "B"))); // 한글의 경우
																											// encoding
																											// 필요
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
			message.setSubject(subject);
			message.setContent(contents.toString(), "text/html;charset=UTF-8"); // 내용 설정 (HTML 형식)
			message.setSentDate(new java.util.Date());
			Transport t = mailSession.getTransport("smtp");
			t.connect(_email, _password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			System.out.println("메일발송 성공!");
			System.out.println("=========End TalkAbout Mail ~!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
