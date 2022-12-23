package org.eni.encheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.BusinessException;
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet({"/inscription","/update","/forgotMdp", "/mdpOublier"})
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/mdpOublier")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/mdpOublie.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
			rd.forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/mdpOublier")) {
			doGet(request, response);
		} else {
			HttpSession session = request.getSession();
			String 	motDePasse = request.getParameter("motDePasse"),
					confirmation = request.getParameter("confirmation");
			boolean verif = true;
			if(!confirmation.equals(motDePasse)) {
				verif = false;
			}
			if(request.getServletPath().equals("/update")) {
				try {
					if(!request.getParameter("motDePasseOld").equals(UtilisateurManager.getInstance().selectById(Integer.parseInt(request.getParameter("idUser"))).getMdp())) {
						verif = false;
					}
				} catch (NumberFormatException e2) {
					e2.printStackTrace();
				} catch (BusinessException e2) {
					e2.printStackTrace();
				}
				try {
					UtilisateurManager.getInstance().update(new Utilisateur(	Integer.parseInt(request.getParameter("idUser")),
																				request.getParameter("pseudo"),
																				request.getParameter("nom"),
																				request.getParameter("prenom"),
																				request.getParameter("email"),
																				request.getParameter("telephone"),
																				request.getParameter("codePostal"),
																				request.getParameter("rue"),
																				request.getParameter("ville"),
																				motDePasse,
																				false));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (BusinessException e1) {
					e1.printStackTrace();
				}
	
				
				if(!verif) {
					request.setAttribute("statut","erreur");
					request.setAttribute("info","Une erreur est survenu");
				}else {
					request.setAttribute("statut","ok");
					request.setAttribute("info","Changement Effectué");
					Utilisateur userCo = null;
					try {
						userCo = UtilisateurManager.getInstance().selectById(Integer.parseInt(request.getParameter("idUser")));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (BusinessException e) {
						e.printStackTrace();
					}
					session.setAttribute("userCo",userCo);
				}
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EditProfil.jsp");
				rd.forward(request, response);
			}
			if(request.getServletPath().equals("/inscription")) {
					try {
						UtilisateurManager.getInstance().insert(new Utilisateur(	request.getParameter("pseudo"),
																					request.getParameter("nom"),
																					request.getParameter("prenom"),
																					request.getParameter("email"),
																					request.getParameter("telephone"),
																					request.getParameter("codePostal"),
																					request.getParameter("rue"),
																					request.getParameter("ville"),
																					motDePasse,
																					0,
																					false,
																					false));
					} catch (BusinessException e) {
						e.printStackTrace();
						verif = false;
					}
	
	
				if(!verif) {
					request.setAttribute("statut","erreur");
					request.setAttribute("info","Une erreur est survenu");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CreationCompte.jsp");
					rd.forward(request, response);
				}else {
					Cookie[] cookies = request.getCookies();
					for (Cookie c : cookies) {
						if(c.getName().equals("id")) {
							c.setValue(null);
					        response.addCookie(c);
							session.setAttribute("cookie",null);
						}
					}
					response.sendRedirect("./connexion");
				}
			}
			if(request.getServletPath().equals("/forgotMdp")) {
				Utilisateur user = null;
				try {
					user = UtilisateurManager.getInstance().mdpOublier(request.getParameter("email"));

					if(user.getNom()!=null) {
						UtilisateurManager.getInstance().update(new Utilisateur(user.getIdUser(),
								user.getPseudo(),
								user.getNom(),
								user.getPrenom(),
								user.getEmail(),
								user.getTelephone(),
								user.getCodePostal(),
								user.getRue(),
								user.getVille(),
								motDePasse,
								false));
					}
				} catch (BusinessException e) {
					e.printStackTrace();
				}
				if(!verif) {
					request.setAttribute("statut","erreur");
					request.setAttribute("info","Une erreur est survenu");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/mdpOublie.jsp");
					rd.forward(request, response);
				}else {
					doGet(request, response);
				}
			}
		}
	}
}
