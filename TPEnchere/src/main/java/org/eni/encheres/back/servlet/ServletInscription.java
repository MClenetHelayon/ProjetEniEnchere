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
import org.eni.encheres.back.bo.Utilisateur;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet({"/ServletInscription","/update"})
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String 	motDePasse = request.getParameter("motDePasse"),
				confirmation = request.getParameter("confirmation");
		boolean verif = true;
		if(!confirmation.equals(motDePasse)) {
			verif = false;
		}
		if(request.getServletPath().equals("/update")) {
			if(!request.getParameter("motDePasseOld").equals(UtilisateurManager.getInstance().selectById(Integer.parseInt(request.getParameter("idUser"))).getMdp())) {
				verif = false;
			}
			UtilisateurManager.getInstance().update(new Utilisateur(	Integer.parseInt(request.getParameter("idUser")),
																		request.getParameter("pseudo"),
																		request.getParameter("nom"),
																		request.getParameter("prenom"),
																		request.getParameter("email"),
																		request.getParameter("telephone"),
																		request.getParameter("codePostal"),
																		request.getParameter("rue"),
																		request.getParameter("ville"),
																		motDePasse));

			
			if(!verif) {
				request.setAttribute("result","Une erreur est survenu");
			}else {
				request.setAttribute("result","Changement Effectué");
				Utilisateur userCo = UtilisateurManager.getInstance().selectById(Integer.parseInt(request.getParameter("idUser")));
				session.setAttribute("userCo",userCo);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EditProfil.jsp");
			rd.forward(request, response);
		}
		if(request.getServletPath().equals("/ServletInscription")) {
				UtilisateurManager.getInstance().insert(new Utilisateur(	request.getParameter("pseudo"),
																			request.getParameter("nom"),
																			request.getParameter("prenom"),
																			request.getParameter("email"),
																			request.getParameter("telephone"),
																			request.getParameter("codePostal"),
																			request.getParameter("rue"),
																			request.getParameter("ville"),
																			motDePasse,
																			0,
																			false));


			if(!verif) {
				request.setAttribute("result","Une erreur est survenu");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CreationCompte.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/back/ServletConnexion");
				rd.forward(request, response);
			}
		}
	}
}
