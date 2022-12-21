package org.eni.encheres.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
import org.eni.encheres.bll.EnchereManager;
import org.eni.encheres.bll.RetraitManager;
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Retrait;
import org.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletVente
 */
@WebServlet({"/vente", "/readVente", "/annuleVente", "/detailVente"})
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
		String articleId = request.getParameter("idArticle");
		int idUser = (int) session.getAttribute("userId");
		
		
		
		if(request.getServletPath().equals("/detailVente")) {
			if(articleId == null) {
				response.sendRedirect("./accueil");
				return;
			}
			
			try {
				LocalDate today = LocalDate.now();
				
				ArticleVendu unArticle = ArticleVenduManager.getInstance().selectById(Integer.valueOf(articleId));

				if(today.isAfter(unArticle.getDateFin()) || today.isEqual(unArticle.getDateFin())) {
					response.sendRedirect("./winEnchere?idArticle=" + articleId);
				} else {
					Enchere enchereLePlusHaut = EnchereManager.getInstance().selectByIdArticle(Integer.valueOf(articleId));
					
					if(enchereLePlusHaut == null) {
						request.setAttribute("maxMontantUser", null);
						
					} else {
						Utilisateur miseMaxUtilisateur = UtilisateurManager.getInstance().selectById(unArticle.getEnchereMax().getUser().getIdUser());
						
						request.setAttribute("maxMontantUser", miseMaxUtilisateur.getPseudo());
					}
					
					if(session.getAttribute("userCo") != null) {
						Utilisateur unUtilisateur = (Utilisateur) session.getAttribute("userCo");
						
						if(unUtilisateur.isBloque() == true) {
							request.setAttribute("erreur", "Bloquer par l'administateur");
						}
					}
					
					request.setAttribute("unArticle", unArticle);
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/DetailVente.jsp");
					rd.forward(request, response);
				}
				
			} catch (NumberFormatException | BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				List<Categorie> listCategorie = CategorieManager.getInstance().selectAll();
				
				request.setAttribute("listCategorie", listCategorie);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			if(request.getServletPath().equals("/vente")) {
				try {
					Utilisateur retraitUserById = UtilisateurManager.getInstance().selectById(idUser);
					
					request.setAttribute("RetraitUserById", retraitUserById);
					
					reqDispatcher(request, response, "Vente");
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(request.getServletPath().equals("/readVente")) {
				if(articleId == null) {
					response.sendRedirect("./accueil");
					return;
				}
				
				try {
					ArticleVendu unArticle = ArticleVenduManager.getInstance().selectById(Integer.valueOf(articleId));
					
					request.setAttribute("unArticle", unArticle);
					//request.setAttribute("statutBalise", "readonly");
					
					reqDispatcher(request, response, "Vente");
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(request.getServletPath().equals("/annuleVente")) {
				if(articleId == null) {
					response.sendRedirect("./accueil");
					return;
				}
				
				try {
					Retrait unRetrait = RetraitManager.getInstance().selectById(Integer.valueOf(articleId));
					
					if(unRetrait != null) {
						RetraitManager.getInstance().delete(Integer.valueOf(articleId));
					}
					
					ArticleVenduManager.getInstance().delete(Integer.valueOf(articleId));
					
					response.sendRedirect("./accueil");
				} catch (NumberFormatException | BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		if(request.getServletPath().equals("/detailVente")) {
			try {
				String articleId = request.getParameter("idArticle");
				String encherirArticle = request.getParameter("maProposition");
				
				ArticleVendu unArticle = ArticleVenduManager.getInstance().selectById(Integer.valueOf(articleId));
				
				unArticle.setPrixVente(Integer.valueOf(encherirArticle));
				
				ArticleVenduManager.getInstance().update(unArticle);
				
				Utilisateur utilisateurById = UtilisateurManager.getInstance().selectById((int) session.getAttribute("userId"));
				
				Enchere unEnchere = new Enchere(LocalDateTime.now(), unArticle.getPrixVente(), utilisateurById, unArticle);
				
				EnchereManager.getInstance().update(unEnchere);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
			doGet(request, response);
		} else {
			int utiliateurId = (int) session.getAttribute("userId");
			
			try {
				Utilisateur siAutoriser = UtilisateurManager.getInstance().selectById(utiliateurId);
				
				if(siAutoriser.isBloque()) {
					//efnef
				} else if(request.getServletPath().equals("/vente") || request.getServletPath().equals("/readVente")) {
					
					String nomArticle = request.getParameter("article");
					String descriptionArticle = request.getParameter("description");
					String filtreCategorieArticle = request.getParameter("choixCategorie");
					//String photoArticle = request.getParameter("photo"); // plus tard
					String prixInitialArticle = request.getParameter("miseAPrix");
					String dateDebutEnchereArticle = request.getParameter("dateFinEnchere");
					String dateFinEnchereArticle = request.getParameter("dateDebutEnchere");
					String rueVendeur = request.getParameter("rue").trim();
					String codePostalVendeur = request.getParameter("codePostal").trim();
					String villeVendeur = request.getParameter("ville").trim();
					
					try {
						Categorie uneCategorie = CategorieManager.getInstance().selectById(Integer.parseInt(filtreCategorieArticle));
						Utilisateur unUtilisateur = UtilisateurManager.getInstance().selectById(utiliateurId);
						
						if((!rueVendeur.isEmpty() || !rueVendeur.isBlank()) && (!codePostalVendeur.isEmpty() || !codePostalVendeur.isBlank()) && (!villeVendeur.isEmpty() || !villeVendeur.isBlank())) {
							unUtilisateur.setRue(rueVendeur);
							unUtilisateur.setCodePostal(codePostalVendeur);
							unUtilisateur.setVille(villeVendeur);
						} else {
							unUtilisateur.setRue("");
							unUtilisateur.setCodePostal("");
							unUtilisateur.setVille("");
						}
						
						ArticleVendu unArticle = new ArticleVendu(nomArticle, descriptionArticle, LocalDate.parse(dateDebutEnchereArticle), LocalDate.parse(dateFinEnchereArticle), 
								Integer.valueOf(prixInitialArticle), 0, unUtilisateur, uneCategorie, 0);
						
						ArticleVenduManager.getInstance().insert(unArticle);
						
						response.sendRedirect("./accueil");
					} catch (NumberFormatException | BusinessException e) {
						e.printStackTrace();
						request.setAttribute("erreur", e);
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Vente.jsp");
						rd.forward(request, response);
					}
				}
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
