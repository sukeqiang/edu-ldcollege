package edu.ldcollege.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadingUtil {
	/** 
     * 上传单个文件，并返回其在服务器中的存储路径 
     *  
     * @param aFile 
     * @return 
     * @throws FileNotFoundException 
     * @throws IOException 
     */  
    public static String uploadFile(String filePath,MultipartFile aFile) throws IOException {  
        String path = initFilePath(filePath,aFile.getOriginalFilename());  
        try {  
        	aFile.transferTo(new File(path));
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }  
        return path;  
    }
    
    public static String removeFile(String filePath,String fileName) throws IOException {
    	String path = initFilePath(filePath,fileName);  
    	File  file = new File(filePath.concat(fileName));
    	FileUtils.forceDelete(file);
    	return path;
    }
    
    /** 
     * 
     * @param name 
     * @return 
     */  
    private static String initFilePath(String filePath, String name) throws IOException {
        File file = new File(filePath);
        FileUtils.forceMkdir(file);
        return filePath.concat(name); 
    } 
}
