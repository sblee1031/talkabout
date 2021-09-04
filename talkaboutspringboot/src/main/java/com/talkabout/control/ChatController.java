package com.talkabout.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.talkabout.dto.Message;

//@CrossOrigin(origins="*")
//@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:8888","http://localhost:3000","http://localhost:9999"})

@RestController
public class ChatController {
	
    @MessageMapping("/discussor")
    @SendTo("/topic/discussor")
    public Message boradCast(Message message){
    	System.out.println("discussor");
    	System.out.println(message.getUsername());
    	System.out.println(message.getContent());

        return message;
    }
    
    @MessageMapping("/audience")
    @SendTo("/topic/audience")
    public Message boradCast2(Message message){	
    	System.out.println("audience");
    	System.out.println(message.getUsername());
    	System.out.println(message.getContent());
        return message;
    }
    
    
    private static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<>());
    @OnOpen
    //@SendTo("/topic/roomId")
	public Message handleOpen(Session userSession) {
		// 클라이언트가 접속하면 WebSocket세션을 리스트에 저장한다.
		sessionUsers.add(userSession);
		// 콘솔에 접속 로그를 출력한다.
		System.out.println("client is now connected..." + userSession.getId());
		Message m =new Message();
		m.setContent(userSession.getId());
		return m;
	}
//	@Autowired
//	private SimpMessagingTemplate webSocket;

//	@MessageMapping("/sendTo")
//	@SendTo("/topics/sendTo")
//	public String SendToMessage() throws Exception {
//		return "SendTo11";
//	}
//
//	@MessageMapping("/Template")
//	public void SendTemplateMessage() {
//		webSocket.convertAndSend("/topics/template", "Template");
//	}
//
//	@RequestMapping(value = "/api")
//	public void SendAPI() {
//		webSocket.convertAndSend("/topics/api", "API");
//	}

}
