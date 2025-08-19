package com.mvc.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.control.ActionForward;

/* 모든 구현체(IndexAction) 클래스가 구현할 인터페이스, 비즈니스로직을
 * 재정의할 메소드
 */

//메소드만
public interface Action {
	
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse reponse) throws IOException;
	
}
