package edu.ldcollege.web.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserLoginController {

	@RequestMapping("/")
	public String viewLogin() {
		return "login";
	}
}
