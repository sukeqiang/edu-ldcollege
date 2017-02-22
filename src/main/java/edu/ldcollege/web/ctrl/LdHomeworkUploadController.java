package edu.ldcollege.web.ctrl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class LdHomeworkUploadController {

	@RequestMapping("/view/ldhomework")
	public String viewLldhomework() {
		return "ldhomework-upload.html";
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = "text/html;charset=UTF-8") 
	public @ResponseBody String uploadFileHandler(@RequestParam("file") MultipartFile file) { 
		if (!file.isEmpty()) {
            try {
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();
                file.transferTo(new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename()));
                return "{\"result\":\"ok\"}";
            } catch (Exception e) {
                return "File upload file";
            }
        } else {
            return "File is empty";
        }
	}
}
