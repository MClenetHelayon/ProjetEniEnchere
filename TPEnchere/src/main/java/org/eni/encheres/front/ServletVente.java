package org.eni.encheres.front;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bll.ArticleVenduManager;
import org.eni.encheres.back.bll.CategorieManager;
import org.eni.encheres.back.bll.UtilisateurManager;
import org.eni.encheres.back.bo.ArticleVendu;
import org.eni.encheres.back.bo.Categorie;
import org.eni.encheres.back.bo.Utilisateur;

/**
 * Servlet implementation class ServletVente
 */
@WebServlet({"/ServletVente", "/ServletReadVente", "/ServletAnnuleVente"})
public class ServletVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private void reqDispatcher(HttpServletRequest req, HttpServletResponse resp, String nameFile) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/" + nameFile + ".jsp");
		rd.forward(req, resp);
	}
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int idUser = (int) session.getAttribute("userId");
		
		try {
			List<Categorie> listCategorie = CategorieManager.getInstance().selectAll();
			
			request.setAttribute("listCategorie", listCategorie);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(request.getServletPath().equals("/ServletVente")) {
			try {
				Utilisateur retraitUserById = UtilisateurManager.getInstance().selectById(idUser);
				
				request.setAttribute("RetraitUserById", retraitUserById);
				
				reqDispatcher(request, response, "Vente");
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(request.getServletPath().equals("/ServletReadVente")) {
			String articleId = request.getParameter("idArticle");
			
			try {
				ArticleVendu unArticle = ArticleVenduManager.getInstance().selectById(Integer.valueOf(articleId));
				
				request.setAttribute("unArticle", unArticle);
				request.setAttribute("statutBalise", "readonly");
				
				reqDispatcher(request, response, "Vente");
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(request.getServletPath().equals("/ServletAnnuleVente")) {
			String idArticle = request.getParameter("idArticle");
			
			try {
				ArticleVenduManager.getInstance().delete(Integer.valueOf(idArticle));
				
				reqDispatcher(request, response, "Accueil");
			} catch (NumberFormatException | BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		int utiliateurId = (int) session.getAttribute("userId");
		
		String nomArticle = request.getParameter("article");
		String descriptionArticle = request.getParameter("description");
		String filtreCategorieArticle = request.getParameter("choixCategorie");
		//String photoArticle = request.getParameter("photo"); // plus tard
		String prixInitialArticle = request.getParameter("miseAPrix");
		String dateDebutEnchereArticle = request.getParameter("dateFinEnchere");
		String dateFinEnchereArticle = request.getParameter("dateDebutEnchere");
		
		try {
			Categorie uneCategorie = CategorieManager.getInstance().selectById(Integer.parseInt(filtreCategorieArticle));
			Utilisateur unUtilisateur = UtilisateurManager.getInstance().selectById(utiliateurId);
			
			ArticleVendu unArticle = new ArticleVendu(nomArticle, descriptionArticle, LocalDate.parse(dateDebutEnchereArticle), LocalDate.parse(dateFinEnchereArticle), 
					Integer.valueOf(prixInitialArticle), 0, unUtilisateur, uneCategorie, 0);
			
			ArticleVenduManager.getInstance().insert(unArticle);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
			rd.forward(request, response);
			
		} catch (NumberFormatException | BusinessException e) {
			e.printStackTrace();
			request.setAttribute("erreur", e);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Vente.jsp");
			rd.forward(request, response);
		}		
	}

}
