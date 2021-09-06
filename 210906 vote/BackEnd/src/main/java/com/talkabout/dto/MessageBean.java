package com.talkabout.dto;

public class MessageBean {
    private String name;
    private String message;
    private VoteCnt votecnt;
    private Boolean server;

    public VoteCnt getVotecnt() {
		return votecnt;
	}

	public void setVotecnt(VoteCnt votecnt) {
		this.votecnt = votecnt;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getServer() {
        return server;
    }

    public void setServer(Boolean server) {
        this.server = server;
    }

}