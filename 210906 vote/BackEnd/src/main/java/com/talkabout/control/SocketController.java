package com.talkabout.control;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.talkabout.dto.MessageBean;
import com.talkabout.dto.VoteCnt;
import com.talkabout.exception.FindException;
import com.talkabout.service.DebateBattleService;

@Controller
public class SocketController {
	
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;
	
	@Autowired
	private DebateBattleService service;
	
	@MessageMapping("/sendMessage/{room}")
    public void sendToAll(@DestinationVariable String room, @Payload MessageBean message) {
		Map<String, String> voteOne = new HashMap<String, String>();
		Map<String, String> voteTwo = new HashMap<String, String>();
		Map<String, String> voteThree = new HashMap<String, String>();
		VoteCnt cnt = new VoteCnt();
		try {
			voteOne = service.voteCnt(Integer.parseInt(room), 1);
			voteTwo = service.voteCnt(Integer.parseInt(room), 2);//중립
			voteThree = service.voteCnt(Integer.parseInt(room), 3);
			if(voteOne == null) {
				cnt.setAgree(0);
			} else {
//				System.out.println("=>"+voteOne.toString());
//				int a = Integer.parseInt((String)voteOne.get("cnt"));
//				System.out.println("=>"+(Object)voteOne.get("cnt"));
				cnt.setAgree((Object)voteOne.get("cnt"));
			}
			if(voteThree == null) {
				cnt.setDisagree(0);
			} else {
				cnt.setDisagree((Object)voteThree.get("cnt"));
			}
			if(voteTwo == null) {
				cnt.setNeutrality(0);
			} else {
				cnt.setNeutrality((Object)voteTwo.get("cnt"));
			}
			message.setVotecnt(cnt);
			System.out.println("message"+message.toString());
		} catch (NumberFormatException | FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
        messagingTemplate.convertAndSend(format("/topic/%s", room), message);
    }
	
	@MessageMapping("/addUser/{room}")
    public void addUser(@DestinationVariable String room, @Payload MessageBean message,
            SimpMessageHeaderAccessor headerAccessor) {
        String currentRoom = (String) headerAccessor.getSessionAttributes().put("room", room);
        if (currentRoom != null) {
            MessageBean leaveMessage = new MessageBean();
            leaveMessage.setName(message.getName());
            leaveMessage.setMessage(message.getName() + " has disconnected!");
            leaveMessage.setServer(true);
            messagingTemplate.convertAndSend(format("/channel/%s", currentRoom), leaveMessage);
        }
        Map<String, String> voteOne = new HashMap<String, String>();
		Map<String, String> voteTwo = new HashMap<String, String>();
		Map<String, String> voteThree = new HashMap<String, String>();
		VoteCnt cnt = new VoteCnt();
		try {
			voteOne = service.voteCnt(Integer.parseInt(room), 1);
			voteTwo = service.voteCnt(Integer.parseInt(room), 2);//중립
			voteThree = service.voteCnt(Integer.parseInt(room), 3);
			if(voteOne == null) {
				cnt.setAgree(0);
			} else {
//				System.out.println("=>"+voteOne.toString());
//				int a = Integer.parseInt((String)voteOne.get("cnt"));
//				System.out.println("=>"+(Object)voteOne.get("cnt"));
				cnt.setAgree((Object)voteOne.get("cnt"));
			}
			if(voteThree == null) {
				cnt.setDisagree(0);
			} else {
				cnt.setDisagree((Object)voteThree.get("cnt"));
			}
			if(voteTwo == null) {
				cnt.setNeutrality(0);
			} else {
				cnt.setNeutrality((Object)voteTwo.get("cnt"));
			}
			message.setVotecnt(cnt);
			System.out.println("message"+message.toString());
		} catch (NumberFormatException | FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        headerAccessor.getSessionAttributes().put("name", message.getName());
        messagingTemplate.convertAndSend(format("/topic/%s", room), message);
    }
}
