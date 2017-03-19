package edu.ldcollege.web.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserLoginController {

	@RequestMapping("/")
	public String viewLogin() {
		return "login";
	}
	
	@RequestMapping(value = "/signin",method = RequestMethod.POST)
	public @ResponseBody String signin(@RequestParam("login") String login,
			@RequestParam("password") String password) {
		
		return "{\"is_error\":null,\"is_reload_page\":null,\"new_location\":\"/view/ldhomework\",\"msgs\":[{\"body\":\"Login required\",\"type\":\"3\"}]}";
	}
	
	@RequestMapping(value = "/distSession",method = RequestMethod.POST)
	public @ResponseBody String distSession(@RequestParam("login") String login,
			@RequestParam("password") String password) {
		
		return "{\"is_error\":null,\"is_reload_page\":null,\"new_location\":\"/view/ldhomework\",\"msgs\":[{\"body\":\"Login required\",\"type\":\"3\"}]}";
	}
}
