package edu.ldcollege.web.ctrl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.ldcollege.orm.domain.LdHomeWork;
import edu.ldcollege.service.LdHomeworkService;
import edu.ldcollege.utils.FileUploadingUtil;
import edu.ldcollege.utils.SpringContextUtil;
import edu.ldcollege.web.view.ResponseCommonJSONModel;

@Controller
public class LdHomeworkUploadController {

	@Autowired
	@Value("${upload.filepath}")
	private String filePath ;
	
	@Resource(name = "ldHomeworkService")
	LdHomeworkService ldHomeworkService;
	
	@RequestMapping("/view/ldhomework")
	public String viewLldhomework() {
		return "ldhomework-upload";
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST) 
	public @ResponseBody ResponseCommonJSONModel uploadFileHandler(@RequestParam("classId") String classId,
			@RequestParam("lessionId") String lessionId, @RequestParam("userId") String userId,
			@RequestParam("file") MultipartFile file) { 
		ResponseCommonJSONModel upload = SpringContextUtil.getBean("uploadJSONModel",ResponseCommonJSONModel.class);
		if (!file.isEmpty()) {
            try {
            	LdHomeWork ldHomeWork = ldHomeworkService.getLdhomeworkByCLUId(Integer.parseInt(classId),
            			Integer.parseInt(lessionId),Integer.parseInt(userId));
            	if (ldHomeWork == null) {
            		ldHomeWork = SpringContextUtil.getBean("ldHomeWorkBean",LdHomeWork.class);
                	ldHomeWork.setClassId(Long.parseLong(classId));
                	ldHomeWork.setLessionId(Long.parseLong(lessionId));
                	ldHomeWork.setUserId(Long.parseLong(userId));
                	ldHomeWork.setNegativeCount(0);
                	ldHomeWork.setStarCount(0);
                	ldHomeWork.setCorrectFlag("0");
                	ldHomeWork.setBestFlag("2");
                	ldHomeWork.setHomeworkFilepath(filePath);
                	ldHomeWork.setHomeworkFilename(file.getOriginalFilename());
            	} else {
            		FileUploadingUtil.removeFile(filePath, ldHomeWork.getHomeworkFilename());
            		ldHomeWork.setHomeworkFilepath(filePath);
            		ldHomeWork.setHomeworkFilename(file.getOriginalFilename());
            	}
            	
            	ldHomeworkService.saveLdHomeWorkByOnDuplicateKeyUpdate(ldHomeWork);
            	FileUploadingUtil.uploadFile(filePath, file);
            	upload.setResult("1");
            } catch (Exception e) {
            	e.printStackTrace();
            	upload.setResult("0");
            }
        }else {
        	upload.setResult("2");
        }
		return upload;
	}
}
