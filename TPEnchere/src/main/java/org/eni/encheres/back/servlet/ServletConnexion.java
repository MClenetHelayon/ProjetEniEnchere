package org.eni.encheres.back.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.back.BusinessException;
import org.eni.encheres.back.bll.UtilisateurManager;
import org.eni.encheres.back.bo.Utilisateur;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/back/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(Cookie c : cookies) {
				Utilisateur u = null;
				try {
					u = UtilisateurManager.getInstance().selectById(Integer.valueOf(c.getValue()));
					if(u!=null) {
						createSession(request, u);
						returnBack(request, response,"/ServletAccueil");
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
		try {
			userCo =  UtilisateurManager.getInstance().connection(request.getParameter("identifiant"), request.getParameter("password"));
			createSession(request, userCo);
			if(request.getParameter("alwaysOpen").equals("on")) {
		        String idUser = Integer.toString(userCo.getIdUser());		        
		        Cookie cookie = new Cookie("id", idUser);
		        cookie.setMaxAge(10000);
		        response.addCookie(cookie);
			}
			
			returnBack(request, response,"/ServletAccueil");
		}catch (Exception e) {
			request.setAttribute("erreur","erreur !!!!");
			returnBack(request, response,"/WEB-INF/Connexion.jsp");
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
	private void createSession(HttpServletRequest request,Utilisateur u) {
		HttpSession session = request.getSession();
		session.setAttribute("isConnect",true);
		session.setAttribute("userCo",u);
		session.setAttribute("userId",u.getIdUser());
		request.setAttribute("erreur","");
	}
}
