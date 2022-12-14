package org.eni.encheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletProfil
 */
@WebServlet({"/profil", "/editProfil"})
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int value;
		
		try {
			if(request.getParameter("idUser") != null) {
				value = Integer.valueOf((String) request.getParameter("idUser"));
			} else {
				value = (int) session.getAttribute("userId");
			}
			
			Utilisateur unUtilisateur = UtilisateurManager.getInstance().selectById(value);
			
			request.setAttribute("unUtilisateur", unUtilisateur);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("statut","erreur");
			request.setAttribute("info", e.getMessage());
		}
		
		if(request.getServletPath().equals("/profil")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Profil.jsp");
			rd.forward(request, response);
		} else if(request.getServletPath().equals("/editProfil")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EditProfil.jsp");
			rd.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./accueil");
	}
}
