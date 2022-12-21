package org.eni.encheres.front;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bll.ArticleVenduManager;
import org.eni.encheres.back.bll.CategorieManager;
import org.eni.encheres.back.bo.ArticleVendu;
import org.eni.encheres.back.bo.Categorie;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Categorie> listCategorie = CategorieManager.getInstance().selectAll();
			
			request.setAttribute("listCategorie", listCategorie);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			List<ArticleVendu> listArticle = ArticleVenduManager.getInstance().selectAll();
			
			request.setAttribute("listArticle", listArticle);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		/* add filtre liste article */
		//request.getParameter("")
	}
}
