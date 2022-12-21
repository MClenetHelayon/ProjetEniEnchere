package org.eni.encheres.front;

import java.io.IOException;
import java.util.ArrayList;
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
import org.eni.encheres.back.bo.ArticleVendu;
import org.eni.encheres.back.bo.Categorie;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("isConnect")==null) {
			session.setAttribute("isConnect", false);
			session.setAttribute("userCo", null);
			
			System.out.println("path Servlet: " + request.getServletPath() + " isConnect: " + session.getAttribute("isConnect") + " userCo: " + session.getAttribute("userCo"));
		}
		try {
			List<Categorie> listCategorie = CategorieManager.getInstance().selectAll();
			request.setAttribute("listCategorie", listCategorie);
			List<ArticleVendu> listArticle = ArticleVenduManager.getInstance().selectAll();
			request.setAttribute("listArticle", listArticle);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String textComparer = request.getParameter("filtresText");
	    int id = Integer.parseInt(request.getParameter("choixCategorie"));
		request.setAttribute("idCat",id);
		request.setAttribute("txt",textComparer);
	    List<ArticleVendu> listArticle = new ArrayList<>();
		try {
		    if (id != 1 || !textComparer.isEmpty()) {
		        List<ArticleVendu> listComparantId = ArticleVenduManager.getInstance().selectAllByCateg(id);
		        List<ArticleVendu> listComparantText = ArticleVenduManager.getInstance().selectAllByNom(textComparer);
	
		        if (id != 1 && !textComparer.isEmpty()) {
		            for (ArticleVendu allBId : listComparantId) {
		                for (ArticleVendu allBText : listComparantText) {
		                    if (allBId.getNumArticle() == allBText.getNumArticle()) {
		                        listArticle.add(allBText);
		                    }
		                }
		            }
		            noDuplicata(listArticle);
		        } else if (textComparer.isEmpty()) {
		            listArticle = listComparantId;
		        } else if (id == 1) {
		            listArticle = listComparantText;
		        }
		    } else {
		        listArticle = ArticleVenduManager.getInstance().selectAll();
		    }
		    List<Categorie> listCategorie = CategorieManager.getInstance().selectAll();
		    request.setAttribute("listCategorie", listCategorie);
		    request.setAttribute("listArticle", listArticle);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
	    rd.forward(request, response);
	}
	private List<ArticleVendu> noDuplicata(List<ArticleVendu> vretour){
		ArticleVendu tmp = null;
		for(ArticleVendu art : vretour) {
			if(art.equals(tmp)) {
				vretour.remove(art);
			}
			tmp=art;
		}
		return vretour;
	}
	
}
