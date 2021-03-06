package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.util.List;

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
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.OstvarenaPrijateljstva;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.PrijateljDaoLocal;

public class LoginController extends HttpServlet {

	private static final long serialVersionUID = -7345471861052209628L;
	
	private static Logger log = Logger.getLogger(LoginController.class);

	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private PrijateljDaoLocal prijateljDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String korisnickoIme = request.getParameter("korisnickoIme");
			String lozinka = request.getParameter("lozinka");
			
			if ((korisnickoIme == null) || (korisnickoIme.equals("")) || (lozinka == null) || (lozinka.equals(""))) {
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
				return;
			}
			
			Korisnik korisnik = korisnikDao.findKorisnikSaKorisnickimImenomILozinkom(korisnickoIme, lozinka);
			
			if (korisnik != null) {	
				HttpSession session = request.getSession(true);
				List<OstvarenaPrijateljstva> prijatelji = prijateljDao.findFriends(korisnik.getId());
				
				session.setAttribute("korisnik", korisnik);
				session.setAttribute("prijatelji", prijatelji);
				
				log.info("Korisnik " + korisnik.getKorisnickoImeKorisnika() + " se prijavio.");
				getServletContext().getRequestDispatcher("/ReadController").forward(request, response);
			}
			
		} catch (EJBException e) {
			if (e.getCause().getClass().equals(NoResultException.class)) {
				String errorMessage = "Neispravni podaci.";
				request.setAttribute("errormessage", errorMessage);

				RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
				disp.forward(request, response);
				
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
