package com.talkabout.service.mailauth;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticaion extends Authenticator{
	 PasswordAuthentication account;
	 
	    public void MyAuthentication(){
	        String id = "leebbong001";
	        String pw = "naver5835";
	        account = new PasswordAuthentication(id, pw);
	    }
	 
	    public PasswordAuthentication getPasswordAuthentication(){
	        return account;
	    }
}
