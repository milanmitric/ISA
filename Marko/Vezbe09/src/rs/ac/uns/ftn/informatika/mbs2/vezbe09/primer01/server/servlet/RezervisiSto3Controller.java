package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.RezervisaniStolovi;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RezervisiDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.StoDaoLocal;

public class RezervisiSto3Controller extends HttpServlet{

	private static final long serialVersionUID = -6400457089970587846L;
	
	@EJB
	private RezervisiDaoLocal rezervacijaDao;
	
	@EJB
	private StoDaoLocal stoDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String datum = (String) request.getSession().getAttribute("datumSession");
		Integer sat = Integer.parseInt((String) request.getSession().getAttribute("satiIzabrano"));
		Integer trajanje = Integer.parseInt((String) request.getSession().getAttribute("trajanje"));
		
		Integer id_stola = Integer.parseInt((String) request.getSession().getAttribute("id_stola"));
		
		Sto sto = stoDao.findById(id_stola);
		Korisnik korisnik = (Korisnik) request.getSession().getAttribute("korisnik");
		
		RezervisaniStolovi rzs = new RezervisaniStolovi(datum, sat, trajanje, sto, korisnik);
		
		rezervacijaDao.persist(rzs);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
