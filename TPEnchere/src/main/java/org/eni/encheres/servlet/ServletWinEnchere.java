package org.eni.encheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bll.ArticleVenduManager;
import org.eni.encheres.bll.EnchereManager;
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletWinEnchere
 */
@WebServlet({"/winEnchere", "/finaliser"})
public class ServletWinEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articleId = request.getParameter("idArticle");
		
		if(articleId == null) {
			response.sendRedirect("./accueil");
			return;
		}

		try {
			ArticleVendu unArticle = ArticleVenduManager.getInstance().selectById(Integer.valueOf(articleId));
		
			Enchere enchereLePlusHaut = EnchereManager.getInstance().selectByIdArticle(Integer.valueOf(articleId));
			
			unArticle.setEnchereMax(enchereLePlusHaut);
			
			Utilisateur leVendeur = UtilisateurManager.getInstance().selectById(unArticle.getUser().getIdUser());
			
			if(unArticle.getEnchereMax() != null) {
				Utilisateur miseMaxUtilisateur = UtilisateurManager.getInstance().selectById(unArticle.getEnchereMax().getUser().getIdUser());
				
				request.setAttribute("miseMaxUtilisateur", miseMaxUtilisateur);
			} else {
				request.setAttribute("personne", "Personne");
			}

			request.setAttribute("unArticle", unArticle);
			request.setAttribute("leVendeur", leVendeur);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/VenteGagner.jsp");
			rd.forward(request, response);
			
		} catch (NumberFormatException | BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("finaliser")) {
			
		}
	}
}
