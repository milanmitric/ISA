package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.StavkaJelovnika;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.StavkaJelovnikaDaoLocal;

public class StavkaController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	@EJB
	private StavkaJelovnikaDaoLocal stavkaDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String naziv = request.getParameter("naziv");
		String tip = request.getParameter("tip");
		String opis = request.getParameter("Opis");
		
		if ((request.getSession().getAttribute("admin")) == null) {
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
		
		StavkaJelovnika stavka = new StavkaJelovnika(naziv, tip, opis);
		
		stavkaDao.persist(stavka);
		
		getServletContext().getRequestDispatcher("/ReadController").forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
