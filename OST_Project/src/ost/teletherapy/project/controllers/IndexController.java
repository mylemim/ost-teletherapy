package ost.teletherapy.project.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ost.teletherapy.project.dal.DataAccessManager;
import ost.teletherapy.project.user.UserType;
/**
 * Servlet implementation class Controller
 */
@WebServlet("/index")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexController() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		
		UserType type = DataAccessManager.loginUser(username, password);
		
		if(type == UserType.Therapist)
			response.sendRedirect(request.getContextPath()+"/therapist");
		else if(type == UserType.Patient)
			response.sendRedirect(request.getContextPath()+"/patient");
		else
			response.sendRedirect(request.getContextPath()+"/index");
	}
}
