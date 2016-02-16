package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Jelovnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.StavkaJelovnika;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.JelovnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;

public class JelovnikController extends HttpServlet{

	
	private static final long serialVersionUID = -2933830682713498595L;
	
	@EJB
	private JelovnikDaoLocal jelovnikDao;
	
	@EJB
	private RestoranDaoLocal restoranDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String naziv = request.getParameter("naziv");
		if ((request.getSession().getAttribute("admin") == null)
				&& (request.getSession().getAttribute("menadzer")) == null) {
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
	
		Restoran restoran = (Restoran)request.getServletContext().getAttribute("restoranID");
		ArrayList<StavkaJelovnika> stavke = new ArrayList<StavkaJelovnika>();
		request.getServletContext().setAttribute("stavkaID", stavke);
		Jelovnik jelovnik = new Jelovnik();
		jelovnik.setNazivJelovnika(naziv);
		restoran.setJelovnik(jelovnik);
		
		jelovnikDao.persist(jelovnik);
		restoranDao.merge(restoran);
		
		getServletContext().getRequestDispatcher("/PrikazRestorana.jsp").forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
