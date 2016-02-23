package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Ocena;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Rezervacija;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.OcenaDaoBean;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.OcenaDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RezervacijaDaoLocal;

public class AddReviewController extends HttpServlet{
	
	
	private static Logger log = Logger.getLogger(AddReviewController.class);
	
	@EJB
	private OcenaDaoLocal ocenaDao;
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private RezervacijaDaoLocal rezeravacijaDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			Integer korId = Integer.parseInt(request.getParameter("korisnik"));
			Integer rezId = Integer.parseInt(request.getParameter("rezervacija"));
			Integer ocena =Integer.parseInt(request.getParameter("ocena"));
			
			Rezervacija rez = rezeravacijaDao.findById(rezId);
			Korisnik kor = korisnikDao.findById(korId);
			
			Ocena o = new Ocena();
			o.setKorisnik(kor);
			o.setRezervacija(rez);
			o.setOcena(ocena);
			
			ocenaDao.persist(o);
			getServletContext().getRequestDispatcher("/PrepareReviewController").forward(request, response);
			
		} catch (Exception e){
			log.info("Unexpected error: " + e.getMessage() + " caused by: " + e.getCause() + " stack trace: + "+ e.getStackTrace());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
