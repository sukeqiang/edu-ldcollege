package edu.ldcollege.exception.web;

public class HttpRequestParameterResolveException extends Exception{

	private static final long serialVersionUID = 1L;

	public HttpRequestParameterResolveException() {
		super();
	}
	
	public HttpRequestParameterResolveException(String message) {
		super(message);
	}
	
	public HttpRequestParameterResolveException(HttpRequestParameterResolveException ex) {
		super(ex.getMessage(), ex);
	}
	
}
