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
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		HttpSession session = request.getSession();
		if(cookies!=null&&session.getAttribute("cookie")!=null) {
			for(Cookie c : cookies) {
				Utilisateur u = null;
				try {
					u = UtilisateurManager.getInstance().selectById(Integer.valueOf(c.getValue()));
					if(u!=null) {
						createSession(request, u,session);
						returnBack(request, response,"/accueil");
						break;
					}
				} catch (NumberFormatException | BusinessException e){returnBack(request, response,"/WEB-INF/Connexion.jsp");}
			}
		}else {
			returnBack(request, response,"/WEB-INF/Connexion.jsp");
		}

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur userCo = null;
		HttpSession session = request.getSession();
		boolean bool = false;
		try {
			userCo =  UtilisateurManager.getInstance().connection(request.getParameter("identifiant"), request.getParameter("password"));
			createSession(request, userCo,session);
			bool = true;
			
			response.sendRedirect("./accueil");
		}catch (Exception e) {
			request.setAttribute("statut","erreur");
			request.setAttribute("info","Connexion impossible");
			
			returnBack(request, response,"/WEB-INF/Connexion.jsp");
		}
		
		if(checkBox(request)&&bool) {
	        String idUser = Integer.toString(userCo.getIdUser());		        
	        Cookie cookie = new Cookie("id", idUser);
	        cookie.setMaxAge(10000);
	        response.addCookie(cookie);
	        session.setAttribute("cookie",cookie);
		}
	}
	
	private void returnBack(HttpServletRequest request, HttpServletResponse response,String link) {
		RequestDispatcher rd = request.getRequestDispatcher(link);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	private void createSession(HttpServletRequest request,Utilisateur u,HttpSession session) {
		session.setAttribute("isConnect",true);
		session.setAttribute("userCo",u);
		session.setAttribute("userId",u.getIdUser());
		request.setAttribute("erreur","");
	}
	private boolean checkBox(HttpServletRequest request) {
		boolean vretour = false;
		if(request.getParameter("alwaysOpen")!=null) {
			vretour = true;
		}
		return vretour;
	}
}
