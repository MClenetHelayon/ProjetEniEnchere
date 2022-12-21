package org.eni.encheres.erreur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletErreur500
 */
@WebServlet({"/erreur500","/erreur404"})
public class ServletErreur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/erreur500")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageErreurs/err500.jsp");
			rd.forward(request, response);
		}
		if(request.getServletPath().equals("/erreur404")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageErreurs/err404.jsp");
			rd.forward(request, response);
		}
	}

}
