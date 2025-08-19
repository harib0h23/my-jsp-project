package com.mvc.action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mvc.control.ActionForward;

/* Action 인터페이스의 메소드를 재정의 하고 있는 클래스.
 * 비즈니스 로직의 구현체임.
 */

public class IndexAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws IOException {
		
		System.out.println("IndexAction의 execute() 수행됨!!");
		return new ActionForward("index.jsp", false);
	}

}
