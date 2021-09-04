package com.talkabout.dto;

import java.util.Date;

public class Message {
	

		public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

		public enum MessageType{
			ENTER, TALK
		}
	    private String username;
	    private String content;
	    private MessageType type;
		private Date date;

	
		public Message(String username, String content, Date date,MessageType type) {
	        this.username = username;
	        this.content = content;
	        this.date = date;
	        this.type = type;
	    }
		
	    public MessageType getType() {
				return type;
			}
			public void setType(MessageType type) {
				this.type = type;
			}
	    public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}
}
