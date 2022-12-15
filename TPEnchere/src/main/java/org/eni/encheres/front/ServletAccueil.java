package org.eni.encheres.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		
		session.setAttribute("isConnect", false);
		session.setAttribute("idUser", null);
		
		System.out.println("path Servlet: " + request.getServletPath() + " isConnect: " + session.getAttribute("isConnect") + " idUser: " + session.getAttribute("idUser"));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);
	}
}
