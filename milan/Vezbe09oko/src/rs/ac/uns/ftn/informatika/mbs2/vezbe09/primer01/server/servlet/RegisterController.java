package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;

public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	private static Logger log = Logger.getLogger(RegisterController.class);

	@EJB
	private KorisnikDaoLocal korisnikDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String korisnickoIme = request.getParameter("korisnicko_ime");
		String lozinka = request.getParameter("lozinka");
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		
		try {
			Korisnik korisnik = korisnikDao.findKorisnikSaKorisnickimImenomILozinkom(korisnickoIme, lozinka);
			if (korisnik != null) {	

				String errorMessage = "Postoji korisnik sa tim korisnickim imenom.";
				request.setAttribute("errormessage", errorMessage);
				RequestDispatcher disp = request.getRequestDispatcher("./register.jsp");
				disp.forward(request, response);
			}
			
		} catch (EJBException e) {
			if (e.getCause().getClass().equals(NoResultException.class)) {
			
				Korisnik noviKorisnik = new Korisnik(ime, prezime, korisnickoIme, lozinka);
				
				korisnikDao.persist(noviKorisnik);
				
				
				HttpSession session = request.getSession(true);
				session.setAttribute("korisnik", noviKorisnik);
				log.info("Korisnik " + noviKorisnik.getKorisnickoImeKorisnika() + " se prijavio.");
				getServletContext().getRequestDispatcher("/ReadController").forward(request, response);
				
			} else {
				throw e;
			}
		} catch (ServletException e) {
			log.error(e);
			throw e;
		} catch (IOException e) {
			log.error(e);
			throw e;
		}
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
