package demo.socket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import demo.socket.model.CustomMessage;
import demo.socket.model.UserCustommer;

@RestController
public class SocketController {
	
	private UserCustommer custommer;
	
	@RequestMapping("/hello")
	public String getMessage() {
		return "Well come to spring";
	}
	
	@RequestMapping(value="/NhapUser" , method=RequestMethod.GET)
	public ModelAndView getView(@RequestParam String user,@RequestParam String pass) {
		custommer=new UserCustommer();
		custommer.setUser(user);
		custommer.setPass(pass);
		return new ModelAndView("Display.html");
	}
	
	@RequestMapping("getUser")
	public UserCustommer getUser() {
		return custommer;
	}
	
	
	@MessageMapping("/sendmessage/{userId}")
	@SendTo("/all/message/{userId}")
	public CustomMessage getSendMessage(CustomMessage mess) throws InterruptedException {
		Thread.sleep(500);
		CustomMessage custom = new CustomMessage();
		custom.setMessage(mess.getMessage());
		return custom;
	}
	
}
