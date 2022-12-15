package org.eni.encheres.back.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.back.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletDeconnexion
 */
@WebServlet({"/back/ServletDeconnexion","/delete"})
public class ServletDeconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/delete")) {
			int idUser = Integer.parseInt(request.getParameter("idUser"));
			UtilisateurManager.getInstance().delete(idUser);
		}
		HttpSession session = request.getSession();
		session.setAttribute("isConnect", null);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
