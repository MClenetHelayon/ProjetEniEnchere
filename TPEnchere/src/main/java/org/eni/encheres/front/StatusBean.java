package org.eni.encheres.front;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class StatusBean {
	
	private static StatusBean instance;
	
	private StatusBean() {}
	
	public static StatusBean getInstance() {
		if(instance == null) {
			instance = new StatusBean();
		}
		
		return instance;
	}
	
	@PostConstruct
	public HttpSession init(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		session.setAttribute("isConnect", true);
		
		return session;
	}
}
