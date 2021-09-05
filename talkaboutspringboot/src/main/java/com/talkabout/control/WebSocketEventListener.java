package com.talkabout.control;

import static java.lang.String.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.talkabout.dto.MessageBean;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection.");
    }

  @EventListener
  public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {

    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
    String name = (String) headerAccessor.getSessionAttributes().get("name");
    String room = (String) headerAccessor.getSessionAttributes().get("room");

    System.out.println("Disconnect detected from " + name + " in room " + room);

    if (name != null) {
      logger.info(name + " has disconnected!");

      MessageBean message = new MessageBean();
      message.setName(name);
      message.setMessage(name + " has disconnected!");
      message.setServer(true);
      messagingTemplate.convertAndSend(format("/topic/%s", room), message);
    }
  }
  
}
