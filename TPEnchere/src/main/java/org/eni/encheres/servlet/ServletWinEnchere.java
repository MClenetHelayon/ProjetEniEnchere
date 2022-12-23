package org.eni.encheres.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
@WebServlet({"/winEnchere", "/finaliser", "/historique"})
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
		
		if(request.getServletPath().equals("/historique")) {
			try {
				ArticleVendu unArticle = ArticleVenduManager.getInstance().selectById(Integer.valueOf(articleId));
				
				List<Enchere> listEnchere = EnchereManager.getInstance().selectListByIdArticle(Integer.valueOf(articleId));
				
				if(unArticle.getEtatVente() == 2) {
					request.setAttribute("listEnchere", listEnchere);
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ListHistorique.jsp");
					rd.forward(request, response);
				} else {
					response.sendRedirect("./accueil");
					return;
				}
			} catch (NumberFormatException | BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("statut","erreur");
				request.setAttribute("info", e.getMessage());
			}
		} else {
			try {
				ArticleVendu unArticle = ArticleVenduManager.getInstance().selectById(Integer.valueOf(articleId));
				
				Enchere enchereLePlusHaut = EnchereManager.getInstance().selectMiseMax(Integer.valueOf(articleId));
				
				unArticle.addEnchere(enchereLePlusHaut);
				
				Utilisateur leVendeur = UtilisateurManager.getInstance().selectById(unArticle.getUser().getIdUser());
				
				if(enchereLePlusHaut != null) {
					request.setAttribute("miseMaxUtilisateur", enchereLePlusHaut);
					
					String info = request.getParameter("info") == null ? "" : request.getParameter("info");
					
					if(info.equals("ok")) {
						request.setAttribute("statut", "ok");
						request.setAttribute("info", "Article reçu");
					}
				} else {
					request.setAttribute("personne", "Personne");
				}
	
				request.setAttribute("unArticle", unArticle);
				request.setAttribute("leVendeur", leVendeur);
				
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/VenteGagner.jsp");
				rd.forward(request, response);
				
			} catch (NumberFormatException | BusinessException e) {
				e.printStackTrace();
				request.setAttribute("statut","erreur");
				request.setAttribute("info", e.getMessage());
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./accueil");
	}
}
