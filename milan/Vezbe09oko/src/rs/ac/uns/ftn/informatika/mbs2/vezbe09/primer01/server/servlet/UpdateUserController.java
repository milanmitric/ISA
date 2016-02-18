package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;

public class UpdateUserController extends HttpServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	private static Logger log = Logger.getLogger(UpdateUserController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String ime = request.getParameter("ime");
			String prezime = request.getParameter("prezime");
			String korisnickoIme  = request.getParameter("korisnickoIme");
			String lozinka = request.getParameter("lozinka");
			Integer korisnikId = Integer.parseInt(request.getParameter("korisnikId"));
			
			System.out.println("Ime :" + ime + " prezime " + prezime + " korisnickoIme " + korisnickoIme + " lozinka " + lozinka);
			Korisnik k = korisnikDao.findById(korisnikId);
			
			if (ime != null){
				k.setImeKorisnika(ime);
			}
			
			if (prezime != null){
				k.setPrezimeKorisnika(prezime);
			}
			
			// Are required in .jsp.
			k.setKorisnickoImeKorisnika(korisnickoIme);
			k.setLozinkaKorisnika(lozinka);
			
			korisnikDao.merge(k);
			
			HttpSession session = request.getSession(true);
			session.removeAttribute("korisnik");
			session.setAttribute("korisnik", k);
			response.sendRedirect("./userProfile.jsp");
			
			
		}catch (Exception e) {
			log.error(e);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
