package edu.ldcollege.viewmodel;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("uploadJSONModel")
@Scope(scopeName = "prototype",proxyMode = ScopedProxyMode.NO)
public class UploadJSONModel {

	private String result;
	
	private String desc;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
