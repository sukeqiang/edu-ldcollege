package edu.ldcollege.web.ctrl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import edu.ldcollege.domain.LdHomeWork;
import edu.ldcollege.service.LdHomeworkService;

@Controller
public class LdHomeworkFeedBackController {

	@Resource(name = "ldHomeworkService")
	LdHomeworkService ldHomeworkService;
	
	
	@RequestMapping("/view/ldhomeworkfb")
	public String viewLldhomework() {
		return "ldhomework-fb-list.html";
	}
	
	@RequestMapping(value = "/ldHomeWorkList",produces = "application/json;charset=UTF-8")
	public @ResponseBody String ldHomeWorkList() {
		List<LdHomeWork> ldHomeWorkList = ldHomeworkService.selectLdhomeworkByClassIdLessionId(6, 10, "create_date", "desc");
		return "{\"total\":" + ldHomeWorkList.size() + ",\"rows\":" + JSON.toJSONString(ldHomeWorkList) + "}";
	}
}
