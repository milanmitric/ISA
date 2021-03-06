package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Jelovnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.StavkaJelovnika;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.JelovnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.StavkaJelovnikaDaoLocal;

public class StavkaController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	@EJB
	private StavkaJelovnikaDaoLocal stavkaDao;
	
	@EJB
	private JelovnikDaoLocal jelovnikDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if ((request.getSession().getAttribute("admin") == null)
				&& (request.getSession().getAttribute("menadzer")) == null) {
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
		
		
		
		String naziv = request.getParameter("naziv");
		String tip = request.getParameter("tip");
		String opis = request.getParameter("opis");
		
		Integer jelovnik = 0;
		
		try{
			jelovnik = Integer.parseInt(request.getParameter("itemId"));
		}catch(NumberFormatException e){
			
		}
		
		Integer cena = 0;
		
		try{
			cena = Integer.parseInt(request.getParameter("cena"));
		}catch(NumberFormatException e){
			
		}
		
		Jelovnik j = jelovnikDao.findById(jelovnik);
		
		
		StavkaJelovnika stavka = new StavkaJelovnika();
		stavka.setNazivStavke(naziv);
		stavka.setOpisStavke(opis);
		stavka.setTipStavke(tip);
		stavka.setJelovnik(j);
		stavka.setCenaStavke(cena);
		
		stavkaDao.persist(stavka);
		
		@SuppressWarnings("unchecked")
		ArrayList<StavkaJelovnika> stavke = (ArrayList<StavkaJelovnika>) request.getServletContext().getAttribute("stavkaID");
		stavke.add(stavka);
		request.getServletContext().setAttribute("stavkaID", stavke);
		
		getServletContext().getRequestDispatcher("/PrikazRestorana.jsp").forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
