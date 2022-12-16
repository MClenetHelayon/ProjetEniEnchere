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
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/back/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
		rd.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur userCo = null;
		/*if(request.getParameter("etat").equals("skip")) {
			doGet(request, response);
		}else {*/
			try {
				userCo = UtilisateurManager.getInstance().connection(request.getParameter("identifiant"), request.getParameter("password"));
				System.out.println(userCo.toString());
				session.setAttribute("isConnect",true);
				session.setAttribute("userCo",userCo);
				session.setAttribute("userId",userCo.getIdUser());
				request.setAttribute("erreur","");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
				rd.forward(request, response);
			}catch (Exception e) {
				request.setAttribute("erreur","erreur!!!");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
				rd.forward(request, response);
			}

		//}

	}
}
