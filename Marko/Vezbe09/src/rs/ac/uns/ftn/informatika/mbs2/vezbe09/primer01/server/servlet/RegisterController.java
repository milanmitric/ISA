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
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.MenadzerDaoLocal;

public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	private static Logger log = Logger.getLogger(RegisterController.class);

	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private MenadzerDaoLocal menadzerDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		String korisnickoIme = request.getParameter("korisnicko_ime");
		String lozinka = request.getParameter("lozinka");
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		String lozinka2 = request.getParameter("lozinka2");
		
		if(!lozinka2.equals(lozinka))
		{
			String errorMessage = "Loznike se ne ponavljaju. Molimo unesite podatke ponovo.";
			request.setAttribute("errormessage", errorMessage);
			request.setAttribute("ime", ime);
			request.setAttribute("prezime", prezime);
			request.setAttribute("korisnicko_ime", korisnickoIme);
			RequestDispatcher disp = request.getRequestDispatcher("./register.jsp");
			disp.forward(request, response);
		}else
		{
			try {
				
				Korisnik korisnik = korisnikDao.findKorisnikSaKorisnickimImenom(korisnickoIme);
				if (korisnik != null) {	
	
					String errorMessage = "Postoji korisnik sa tim korisnickim imenom.";
					request.setAttribute("errormessage", errorMessage);
					RequestDispatcher disp = request.getRequestDispatcher("./register.jsp");
					disp.forward(request, response);
				}
				
			} catch (EJBException e) {
				if (e.getCause().getClass().equals(NoResultException.class)) {
				
					try{
						Menadzer menadzer = menadzerDao.findMenadzerSaKorisnickimImenom(korisnickoIme);
						if (menadzer != null) {	
			
							String errorMessage = "Postoji korisnik sa tim korisnickim imenom.";
							request.setAttribute("errormessage", errorMessage);
							RequestDispatcher disp = request.getRequestDispatcher("./register.jsp");
							disp.forward(request, response);
						}
					}catch (EJBException ej) {
						if (ej.getCause().getClass().equals(NoResultException.class)) {
							
							Korisnik noviKorisnik = new Korisnik(ime, prezime, korisnickoIme, lozinka);
							
							korisnikDao.persist(noviKorisnik);
							
							
							HttpSession session = request.getSession(true);
							session.setAttribute("korisnik", noviKorisnik);
							log.info("Korisnik " + noviKorisnik.getKorisnickoImeKorisnika() + " se prijavio.");
							getServletContext().getRequestDispatcher("/ReadController").forward(request, response);
							
							
						} else {
							throw ej;
						}
					} catch (ServletException ej) {
						log.error(ej);
						throw ej;
					} catch (IOException ej) {
						log.error(ej);
						throw ej;
					}
						
					
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
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
