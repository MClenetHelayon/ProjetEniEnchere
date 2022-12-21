package org.eni.encheres.front;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bll.ArticleVenduManager;
import org.eni.encheres.back.bll.EnchereManager;
import org.eni.encheres.back.bll.UtilisateurManager;
import org.eni.encheres.back.bo.ArticleVendu;
import org.eni.encheres.back.bo.Enchere;
import org.eni.encheres.back.bo.Utilisateur;

/**
 * Servlet implementation class ServletDetailVente
 */
@WebServlet("/ServletDetailVente")
public class ServletDetailVente extends HttpServlet {
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
			LocalDate today = LocalDate.now();
			
			ArticleVendu unArticle = ArticleVenduManager.getInstance().selectById(Integer.valueOf(articleId));

			if(today.isAfter(unArticle.getDateFin()) || today.isEqual(unArticle.getDateFin())) {
				response.sendRedirect("./ServletWinEnchere?idArticle=" + articleId);
			} else {
				Enchere enchereLePlusHaut = EnchereManager.getInstance().selectByIdArticle(Integer.valueOf(articleId));
				
				if(enchereLePlusHaut == null) {
					request.setAttribute("maxMontantUser", null);
					
				} else {
					Utilisateur miseMaxUtilisateur = UtilisateurManager.getInstance().selectById(unArticle.getEnchereMax().getUser().getIdUser());
					
					request.setAttribute("maxMontantUser", miseMaxUtilisateur.getPseudo());
				}
				
				request.setAttribute("unArticle", unArticle);
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/DetailVente.jsp");
				rd.forward(request, response);
			}
			
		} catch (NumberFormatException | BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			
			String articleId = request.getParameter("idArticle");
			String encherirArticle = request.getParameter("maProposition");
			
			ArticleVendu unArticle = ArticleVenduManager.getInstance().selectById(Integer.valueOf(articleId));
			
			unArticle.setPrixVente(Integer.valueOf(encherirArticle));
			
			ArticleVenduManager.getInstance().update(unArticle);
			
			Utilisateur utilisateurById = UtilisateurManager.getInstance().selectById((int) session.getAttribute("userId"));
			
			Enchere unEnchere = new Enchere(LocalDateTime.now(), unArticle.getPrixVente(), utilisateurById, unArticle);
			
			EnchereManager.getInstance().update(unEnchere);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
	}
}
