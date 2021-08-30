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

	public static void main(String[] args) {
		//sendMail("psyy2244@gmail.com", "talkabout1234");
	}

	public static void sendMail(String _email, String _password, Debate deb, DebateDetail dd1, DebateDetail dd2) {

		System.out.println("Start JavaMail ~!");
		String subject = "새로운 토론이 요청되었습니다.";
		String fromMail = "psyy2244@gmail.com";
		String fromName = "TA관리자";
		String toMail = "leebbong001@naver.com"; // 콤마(,) 나열 가능 // mail contents

		StringBuffer contents = new StringBuffer();
		contents.append("<h1>새로운 토론이 요청되었습니다.</h1>\n");
		contents.append("<h2> 관리자님의 승인을 기다립니다.</h2>\n<hr/>");
		contents.append("<h3>주제 : ");
		contents.append(deb.getDebate_topic()+"</h3>\n");
		contents.append("<h3>토론 진행 시간 : "+deb.getDebate_time()+"분</h3>\n");
		contents.append("<h3>시작시간 : "+deb.getDebate_startDate()+"</h3>\n");
		contents.append("<h2>토론자1 : "+dd1.getDiscussor().getMember_nickName()+"</h2>\n");
		contents.append("<h2>토론자2 : "+dd2.getDiscussor().getMember_nickName()+"</h2>\n");
		contents.append("<p>언제나 화이팅</p><br>"); // mail properties

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
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
