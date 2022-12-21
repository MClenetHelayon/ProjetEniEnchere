package org.eni.encheres.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bll.ArticleVenduManager;
import org.eni.encheres.back.bll.EnchereManager;
import org.eni.encheres.back.bll.UtilisateurManager;
import org.eni.encheres.back.bo.ArticleVendu;
import org.eni.encheres.back.bo.Enchere;
import org.eni.encheres.back.bo.Utilisateur;

/**
 * Servlet implementation class ServletWinEnchere
 */
@WebServlet({"/ServletWinEnchere", "/ServletFinaliser"})
public class ServletWinEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articleId = request.getParameter("idArticle");
		
		if(articleId == null) {
			response.sendRedirect("./ServletAccueil");
			return;
		}

		try {
			ArticleVendu unArticle = ArticleVenduManager.getInstance().selectById(Integer.valueOf(articleId));
		
			Enchere enchereLePlusHaut = EnchereManager.getInstance().selectByIdArticle(Integer.valueOf(articleId));
			
			unArticle.setEnchereMax(enchereLePlusHaut);
			
			Utilisateur leVendeur = UtilisateurManager.getInstance().selectById(unArticle.getUser().getIdUser());
			Utilisateur miseMaxUtilisateur = UtilisateurManager.getInstance().selectById(unArticle.getEnchereMax().getUser().getIdUser());

			request.setAttribute("unArticle", unArticle);
			request.setAttribute("leVendeur", leVendeur);
			request.setAttribute("miseMaxUtilisateur", miseMaxUtilisateur);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/VenteGagner.jsp");
			rd.forward(request, response);
			
		} catch (NumberFormatException | BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("ServletFinaliser")) {
			
		}
	}
}
