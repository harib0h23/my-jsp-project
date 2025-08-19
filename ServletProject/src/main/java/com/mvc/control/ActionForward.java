package com.mvc.control;

/* 비지니스 로직 수행 후 Servlet에서 반환하는 객체
 *  	이동할 url과 이동방법을 저장함.
 */

public class ActionForward {
	
	private String url;
	private boolean redirect;
	
	public ActionForward() {
		// TODO Auto-generated constructor stub
	}
	
	public ActionForward(String url) {
		this.url = url;
	}
	
	public ActionForward(String url, boolean redirect) {
		this.url = url;
		this.redirect = redirect;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
	
	
}
