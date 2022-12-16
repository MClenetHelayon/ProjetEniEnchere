package org.eni.encheres.front;

import java.io.IOException;
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
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/DetailVente.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			int userId = (int) session.getAttribute("userId");
			
			String articleId = request.getParameter("idArticle");
			String encherirArticle = request.getParameter("maProposition");
			
			Utilisateur enchereUserById = UtilisateurManager.getInstance().selectById(userId);
			ArticleVendu unArticle = ArticleVenduManager.getInstance().selectById(Integer.valueOf(articleId));
			
			Enchere insererUnMontant = new Enchere(LocalDateTime.now(), Integer.valueOf(encherirArticle), enchereUserById, unArticle);
			
			
			// TODO à tester
			// TODO verifier le changement du prix initial par le client
			EnchereManager.getInstance().insert(insererUnMontant);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
	}
}
