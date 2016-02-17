package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.OstvarenaPrijateljstva;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RezervisiDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.StoDaoLocal;

public class RezervisiSto2Controller extends HttpServlet{

	private static final long serialVersionUID = -3171666069948172361L;

	@EJB
	private StoDaoLocal stoDao;
	
	@EJB
	private RezervisiDaoLocal rezervacijaDao;
	
	@EJB
	private OstvarenaPrijateljstva prijateljstvaDao;
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id_stola = Integer.parseInt(request.getParameter("stoID"));

		request.getSession().setAttribute("id_stola", id_stola);
		RequestDispatcher disp = request.getRequestDispatcher("./RezervisiSto.jsp");
		disp.forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}
}
