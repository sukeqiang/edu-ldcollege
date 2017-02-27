package edu.ldcollege.web.ctrl;


import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ldcollege.exception.web.HttpRequestParameterResolveException;
import edu.ldcollege.orm.domain.LdHomeWork;
import edu.ldcollege.orm.domain.LdHomeWorkFB;
import edu.ldcollege.service.LdHomeworkService;
import edu.ldcollege.utils.SpringContextUtil;
import edu.ldcollege.web.params.CommentWrapper;
import edu.ldcollege.web.view.ResponseCommonJSONModel;
import edu.ldcollege.web.view.ViewModel;

@Controller
public class LdHomeworkFeedBackController {

	@Resource(name = "ldHomeworkService")
	LdHomeworkService ldHomeworkService;
	
	@RequestMapping("/view/ldhomeworkfb")
	public String viewLldhomework(@RequestParam("myId") String myId,
			@RequestParam("classId") String classId,
			@RequestParam("lessionId") String lessionId) {
		if(StringUtils.isBlank(myId) || StringUtils.isBlank(classId) || StringUtils.isBlank(lessionId)) {
			
		}
		return "ldhomework-fb-list?myId=".concat(myId)
				.concat("&classId=").concat(classId)
				.concat("&lessionId=").concat(lessionId);
	}
	
	@RequestMapping(value = "/ldHomeWorkList",method = RequestMethod.GET)
	public @ResponseBody ViewModel<LdHomeWork> ldHomeWorkList(@RequestParam("classId") String classId,
			@RequestParam("lessionId") String lessionId) {
		ViewModel<LdHomeWork> viewModel = ldHomeworkService.selectLdhomeworkByClassIdLessionId(Integer.parseInt(classId), 
				Integer.parseInt(lessionId), "create_date", "desc");
		return viewModel;
	}
	
	@RequestMapping(value = "/ldHomeWorkComment",method = RequestMethod.POST)
	public @ResponseBody ResponseCommonJSONModel commitComment(CommentWrapper commentWrapper) {
		ResponseCommonJSONModel responseJson = SpringContextUtil.getBean("uploadJSONModel",ResponseCommonJSONModel.class);
		LdHomeWorkFB ldHomeWorkFB = SpringContextUtil.getBean("ldHomeWorkFBBean",LdHomeWorkFB.class);
		try{
			ldHomeWorkFB.setHomeworkId(Long.parseLong(commentWrapper.getHomeworkId()));
			ldHomeWorkFB.setUserId(Long.parseLong(commentWrapper.getUserId()));
			ldHomeWorkFB.setLevelFlag(commentWrapper.getLevelFlag());
			ldHomeWorkFB.setMark(commentWrapper.getMark());
			ldHomeWorkFB.setMyId(Long.parseLong(commentWrapper.getMyId()));
			ldHomeworkService.saveCommentHomeWork(ldHomeWorkFB, Integer.parseInt(commentWrapper.getHomeworkId()), Integer.parseInt(commentWrapper.getEvaluate()));
			responseJson.setResult("1");
		}catch(Exception e) {
			e.printStackTrace();
			responseJson.setResult("0");
			responseJson.setDesc("提交评论出现问题!");
		}
		return responseJson;
	}
	
	@RequestMapping("downloadFile")
	public ResponseEntity<byte[]> downloadFile(@RequestParam("homeworkId") String homeworkId,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		LdHomeWork ldHomeWork = ldHomeworkService.selectByPrimaryKey(Long.parseLong(homeworkId));
		FileInputStream in = null;
		ResponseEntity<byte[]> responseEntity = null;
		try {
			byte[] body=null;
			in = new FileInputStream(ldHomeWork.getHomeworkFilepath().concat(ldHomeWork.getHomeworkFilename()));
			body = new byte[in.available()];
	        in.read(body);
			HttpHeaders headers=new HttpHeaders();
			if(request.getHeader("User-Agent").toUpperCase().indexOf("WINDOWS") > 0) {  
				headers.add("Content-Disposition","attachment;"+ "filename=" + 
						new String(ldHomeWork.getHomeworkFilename().getBytes("GBK"),"ISO8859-1"));  
			}else{
				//firefox、chrome、safari、opera  
				headers.add("Content-Disposition","attachment;"+  
			 "filename="+ new String(ldHomeWork.getHomeworkFilename().getBytes("UTF-8"), "ISO8859-1") );  
			}
	        responseEntity=new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
        return responseEntity;
	}
	
}
