package org.eni.encheres.back.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import org.eni.encheres.back.bll.UtilisateurManager;
import org.eni.encheres.back.bo.ArticleVendu;
import org.eni.encheres.back.bo.Categorie;
import org.eni.encheres.back.bo.Utilisateur;
import org.eni.encheres.back.utilitaire.FicheMethodeBool;

/**
 * Servlet implementation class ServletAdmin
 */
@WebServlet({"/adminUser","/adminLock","/adminDelete","/adminArtCateg","/updateArtCateg"})
public class ServletAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Utilisateur> LUser = new ArrayList<Utilisateur>();
		if(request.getServletPath().equals("/adminUser")) {
			listUser(request, response, LUser);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adminMembre.jsp");
			rd.forward(request, response);
		}
		if(request.getServletPath().equals("/adminLock")) {
			int id =Integer.parseInt(request.getParameter("id"));
			try {
				Utilisateur u = UtilisateurManager.getInstance().selectById(id);
				UtilisateurManager.getInstance().update(new Utilisateur(	u.getIdUser(),
																			u.getPseudo(),
																			u.getNom(),
																			u.getPrenom(),
																			u.getEmail(),
																			u.getTelephone(),
																			u.getRue(),
																			u.getCodePostal(),
																			u.getVille(),
																			u.getMdp(),
																			FicheMethodeBool.reverseBool(u.isBloque())));
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		if(request.getServletPath().equals("/adminDelete")) {
			int id =Integer.parseInt(request.getParameter("id"));
			try {
				UtilisateurManager.getInstance().delete(id);
				listUser(request, response, LUser);
				RequestDispatcher rd = request.getRequestDispatcher("/adminUser");
				rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		if(request.getServletPath().equals("/adminArtCateg")) {
			rebootCateg(request,response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ArticleVendu> LArticle = new ArrayList<ArticleVendu>();
		if(request.getServletPath().equals("/adminArtCateg")) {
			int id = Integer.parseInt(request.getParameter("choixCategorie"));
			try {
				if(id==1) {
					LArticle = ArticleVenduManager.getInstance().selectAll();
					request.setAttribute("LArticle",LArticle);
				}else {
					LArticle = ArticleVenduManager.getInstance().selectAllByCateg(id);
					for(ArticleVendu a : LArticle) {
						System.out.println(a.getNom());
					}
					request.setAttribute("LArticle",LArticle);
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			doGet(request, response);
		}
		if(request.getServletPath().equals("/updateArtCateg")) {
			int id = Integer.parseInt(request.getParameter("newChoixCategorie"));
			int idArt = Integer.parseInt(request.getParameter("idArt"));
			try {
				Categorie categ = CategorieManager.getInstance().selectById(id);
				ArticleVendu a = ArticleVenduManager.getInstance().selectById(idArt);
				ArticleVenduManager.getInstance().update(new ArticleVendu(	a.getNumArticle(),
																			a.getNom(),
																			a.getDescription(),
																			a.getDateDeb(),
																			a.getDateFin(),
																			a.getPrixInit(),
																			a.getPrixVente(),
																			a.getUser(),
																			categ,
																			a.getEtatVente()));
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			rebootCateg(request,response);
		}
	}
	private void listUser(HttpServletRequest request, HttpServletResponse response,List<Utilisateur> LUser) {
		try {
			LUser = UtilisateurManager.getInstance().selectAll();
			request.setAttribute("LUser",LUser);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	private void rebootCateg(HttpServletRequest request, HttpServletResponse response) {
		List<Categorie> LCateg = new ArrayList<Categorie>();
		try {
			LCateg = CategorieManager.getInstance().selectAll();
			request.setAttribute("listCategorie",LCateg);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adminCateg.jsp");
			rd.forward(request, response);
		} catch (BusinessException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
