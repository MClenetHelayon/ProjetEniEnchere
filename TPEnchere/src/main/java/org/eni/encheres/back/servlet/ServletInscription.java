package org.eni.encheres.back.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.back.bll.UtilisateurManager;
import org.eni.encheres.back.bo.Utilisateur;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String 	motDePasse = request.getParameter("motDePasse"),
				confirmation = request.getParameter("confirmation");
		if(confirmation.equals(motDePasse)) {
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
			
			RequestDispatcher rd = request.getRequestDispatcher("/back/ServletConnexion");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CreationCompte.jsp");
			rd.forward(request, response);
		}

	}

}
