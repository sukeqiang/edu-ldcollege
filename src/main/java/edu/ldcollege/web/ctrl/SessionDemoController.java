package edu.ldcollege.web.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ldcollege.orm.domain.UserInfoBean;

@Controller
public class SessionDemoController {

	@RequestMapping(value = "/distSession", method = RequestMethod.GET)
	public String getListMyInfoPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfoBean user = (UserInfoBean)session.getAttribute("userSession");
		if(user == null) {
			return "/view/login";
		}
		return "/view/listMyInfo";
	}
	
	@RequestMapping(value = "/userLogin")
	public String userLogin() {
		
		return "login";
	}
	
	@RequestMapping(value = "/listMyInfo",method = RequestMethod.POST)
	public String listMyInfoPage(@RequestParam("name") String name, @RequestParam("age") String age, HttpServletRequest request) {
		UserInfoBean user = new UserInfoBean();
		user.setAge(Integer.parseInt(age));
		user.setName(name);
		request.getSession().setAttribute("userSession", user);
		return "/redirectPage";
	}
	
	@RequestMapping(value = "/redirectPage",method = RequestMethod.GET)
	public String redirectPage() {
		return "/view/listMyInfo";
	}
	
	@RequestMapping(value = "/getListMyInfo",method = RequestMethod.POST)
	public @ResponseBody UserInfoBean getListMyInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfoBean user = (UserInfoBean)session.getAttribute("userSession");
		return user;
	}
}
