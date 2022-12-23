package org.eni.encheres.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bll.ArticleVenduManager;
import org.eni.encheres.bll.CategorieManager;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.utilitaire.FicheMethodeBool;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			List<Categorie> listCategorie = CategorieManager.getInstance().selectAll();
			request.setAttribute("listCategorie", listCategorie);
			List<ArticleVendu> listArticle = ArticleVenduManager.getInstance().selectAll();
			request.setAttribute("listArticle", listArticle);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("statut","erreur");
			request.setAttribute("info", e.getMessage());
		}
		
		if(session.getAttribute("userCo") != null) {
			Utilisateur unUtilisateur = (Utilisateur) session.getAttribute("userCo");
			
			if(unUtilisateur.isBloque() == true) {
				request.setAttribute("statut","erreur");
				request.setAttribute("info","Bloquer par l'administateur");
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    String textComparer = request.getParameter("filtresText");
	    int id = Integer.parseInt(request.getParameter("choixCategorie"));
	    
	    

	    
	    request.setAttribute("idCat", id);
	    request.setAttribute("txt", textComparer);
	    List<ArticleVendu> listArticle = new ArrayList<ArticleVendu>(), listTmp = new ArrayList<ArticleVendu>();
	    try {

	        if (id != 1 || !textComparer.isEmpty()) {
	            List<ArticleVendu> listComparantId = ArticleVenduManager.getInstance().selectAllByCateg(id);
	            List<ArticleVendu> listComparantText = ArticleVenduManager.getInstance().selectAllByNom(textComparer);

	            if (id != 1 && !textComparer.isEmpty()) {
	                for (ArticleVendu allBId : listComparantId) {
	                    for (ArticleVendu allBText : listComparantText) {
	                        if (allBId.getNumArticle() == allBText.getNumArticle()) {
	                            listTmp.add(allBText);
	                        }
	                    }
	                }
	                noDuplicata(listArticle);
	            } else if (textComparer.isEmpty()) {
	                listTmp = listComparantId;
	            } else if (id == 1) {
	                listTmp = listComparantText;
	            }
	        } else {
	            listTmp = ArticleVenduManager.getInstance().selectAll();
	        }
	        listArticle = filtrageCheck(request, listArticle, listTmp);
		    List<Categorie> listCategorie = CategorieManager.getInstance().selectAll();
		    request.setAttribute("listCategorie", listCategorie);
	        request.setAttribute("listArticle", listArticle);
	    } catch (BusinessException e) {
	    	e.printStackTrace();
			request.setAttribute("statut","erreur");
			request.setAttribute("info", e.getMessage());
	    }
		request.setAttribute("listArticle", listArticle);
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
	    rd.forward(request, response);
	}
	private List<ArticleVendu> filtrageCheck(HttpServletRequest request,List<ArticleVendu>vretour,List<ArticleVendu>need){
	    HttpSession session = request.getSession();
        Utilisateur lUser = (Utilisateur) session.getAttribute("userCo");
	    boolean achats = radio(request.getParameter("achatsVentes")),filtre0,filtre1,filtre2;
	    if(achats) {
		    filtre0 = checkbox(request.getParameter("achats0"));
		    filtre1 = checkbox(request.getParameter("achats1"));
		    filtre2 = checkbox(request.getParameter("achats2"));

	    }else {
		    filtre0 = checkbox(request.getParameter("ventes0"));
		    filtre1 = checkbox(request.getParameter("ventes1"));
		    filtre2 = checkbox(request.getParameter("ventes2"));
	    }
	    request.setAttribute("achatsCheck",achats);
	    request.setAttribute("ventesCheck",FicheMethodeBool.reverseBool(achats));
	    request.setAttribute("check0",filtre0);
	    request.setAttribute("check1",filtre1);
	    request.setAttribute("check2",filtre2);
        if(achats) {
        	if(!filtre0&&!filtre1&&!filtre2) {
        		vretour = need;
        	}
        	else {
    			factoAdd(filtre0,request,vretour,need,0);
    			factoAdd(filtre1,request,vretour,need,1);
    			factoAdd(filtre2,request,vretour,need,2);
        	}
        }else {
        	if(!filtre0&&!filtre1&&!filtre2) {
        		for (ArticleVendu a : need) {
					if(a.getUser().getIdUser()==lUser.getIdUser()) {
						vretour.add(a);
					}
				}
        	}
        	else {
        		factoAdd(filtre0,request,vretour,need,0,lUser);
        		factoAdd(filtre1,request,vretour,need,1,lUser);
        		factoAdd(filtre2,request,vretour,need,2,lUser);
        	}
        }
        return vretour;
	}
	private List<ArticleVendu> factoAdd(boolean filtre,HttpServletRequest request,List<ArticleVendu>vretour,List<ArticleVendu>need,int i){
		if(filtre){
			for (ArticleVendu a : need) {
				if(a.getEtatVente()==i) {
					vretour.add(a);
				}
			}
		}
		return vretour;
	}
	private List<ArticleVendu> factoAdd(boolean filtre,HttpServletRequest request,List<ArticleVendu>vretour,List<ArticleVendu>need,int i,Utilisateur lUser){
		if(filtre){
			for (ArticleVendu a : need) {
				if(a.getUser().getIdUser()==lUser.getIdUser()&&a.getEtatVente()==i) {
					vretour.add(a);
				}
			}
		}
		return vretour;
	}
	private boolean checkbox(String parameter) {
	    return parameter != null;
	}
	private boolean radio(String parameter) {
		boolean vretour = false;
		if(parameter.equals("achats")) {
			vretour=true;
		}
		return vretour;
	}
	private void noDuplicata(List<ArticleVendu> listArticle) {
	    Set<ArticleVendu> set = new HashSet<ArticleVendu>(listArticle);
	    listArticle.clear();
	    listArticle.addAll(set);
	}
}
