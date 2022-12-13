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
		String 	pseudo = request.getParameter("pseudo"),
				nom = request.getParameter("nom"),
				prenom = request.getParameter("prenom"),
				email = request.getParameter("email"),
				telephone = request.getParameter("telephone"),
				codePostal = request.getParameter("codePostal"),
				rue = request.getParameter("rue"),
				ville = request.getParameter("ville"),
				motDePasse = request.getParameter("motDePasse"),
				confirmation = request.getParameter("confirmation");
		if(confirmation.equals(motDePasse)) {
			Utilisateur tryToConnect = new Utilisateur(pseudo,nom,prenom,email,telephone,codePostal,rue,ville,motDePasse,0,false);
			UtilisateurManager.getInstance().insert(tryToConnect);
			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CreationCompte.jsp");
			rd.forward(request, response);
		}

	}

}
