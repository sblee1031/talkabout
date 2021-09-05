package com.talkabout.control;

import static java.lang.String.format;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.talkabout.dto.MessageBean;

@Controller
public class SocketController {
	
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;
	
	@MessageMapping("/sendMessage/{room}")
    public void sendToAll(@DestinationVariable String room, @Payload MessageBean message) {
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
        headerAccessor.getSessionAttributes().put("name", message.getName());
        messagingTemplate.convertAndSend(format("/topic/%s", room), message);
    }
}
