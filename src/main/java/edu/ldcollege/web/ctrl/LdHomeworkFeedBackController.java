package edu.ldcollege.web.ctrl;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ldcollege.domain.LdHomeWork;
import edu.ldcollege.service.LdHomeworkService;
import edu.ldcollege.viewmodel.ViewModel;

@Controller
public class LdHomeworkFeedBackController {

	@Resource(name = "ldHomeworkService")
	LdHomeworkService ldHomeworkService;
	
	
	@RequestMapping("/view/ldhomeworkfb")
	public String viewLldhomework() {
		return "ldhomework-fb-list.html";
	}
	
	@RequestMapping(value = "/ldHomeWorkList",method = RequestMethod.GET)
	public @ResponseBody ViewModel<LdHomeWork> ldHomeWorkList(@RequestParam("classId") String classId,
			@RequestParam("lessionId") String lessionId) {
		ViewModel<LdHomeWork> viewModel = ldHomeworkService.selectLdhomeworkByClassIdLessionId(Integer.parseInt(classId), 
				Integer.parseInt(lessionId), "create_date", "desc");
		return viewModel;
	}
}
