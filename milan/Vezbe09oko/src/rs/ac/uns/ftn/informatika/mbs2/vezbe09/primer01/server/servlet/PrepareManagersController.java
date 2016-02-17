package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.persistence.Transient;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.MenadzerDaoLocal;

public class PrepareManagersController extends HttpServlet{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	
	@EJB
	private MenadzerDaoLocal menadzerDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if ((request.getSession().getAttribute("admin")) == null) {
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
		System.out.println("USAO U CITANJE");
		request.setAttribute("menadzeri", menadzerDao.findAll());
		getServletContext().getRequestDispatcher("/managers.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
