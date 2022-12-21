package org.eni.encheres.back.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletDeconnexion
 */
@WebServlet({"/ServletDeconnexion","/delete"})
public class ServletDeconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/delete")) {
			HttpSession session = request.getSession();
			
			int idUser = (int) session.getAttribute("userId");
			
			try {
				UtilisateurManager.getInstance().delete(idUser);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("isConnect", null);
		session.setAttribute("userCo",null);
		session.setAttribute("userId",null);
		
		response.sendRedirect("./ServletAccueil");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
