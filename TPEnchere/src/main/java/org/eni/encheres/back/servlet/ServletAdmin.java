package org.eni.encheres.back.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bll.UtilisateurManager;
import org.eni.encheres.back.bo.Utilisateur;

/**
 * Servlet implementation class ServletAdmin
 */
@WebServlet({"/ServletAdmin","/adminLock","/adminDelete"})
public class ServletAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Utilisateur> LUser = new ArrayList<Utilisateur>();
		if(request.getServletPath().equals("/ServletAdmin")) {

			listUser(request, response, LUser);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/administration.jsp");
			rd.forward(request, response);
		}
		if(request.getServletPath().equals("/adminLock")) {
			
		}
		if(request.getServletPath().equals("/adminDelete")) {
			int id =Integer.parseInt(request.getParameter("id"));
			try {
				UtilisateurManager.getInstance().delete(id);
				listUser(request, response, LUser);
				RequestDispatcher rd = request.getRequestDispatcher("/ServletAdmin");
				rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void listUser(HttpServletRequest request, HttpServletResponse response,List<Utilisateur> LUser) {
		try {
			LUser = UtilisateurManager.getInstance().selectAll();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("LUser",LUser);

	}
}
