package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.MenadzerDaoLocal;

public class UpdateUserController extends HttpServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private MenadzerDaoLocal menadzerDao;
	
	private static Logger log = Logger.getLogger(UpdateUserController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String ime = request.getParameter("ime");
			String prezime = request.getParameter("prezime");
			String korisnickoIme  = request.getParameter("korisnickoIme");
			String lozinka = request.getParameter("lozinka");
			Integer korisnikId = Integer.parseInt(request.getParameter("korisnikId"));
			
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
			System.out.println(k.getKorisnickoImeKorisnika());
			if(menadzerDao.checkIfExists(k.getKorisnickoImeKorisnika())){
				request.setAttribute("errorMessage", "Vec postoji korisnik sa tim korisnickim imenom");
				getServletContext().getRequestDispatcher("/userProfile.jsp").forward(request, response);
				return;
			}
			try{
				korisnikDao.merge(k);
				HttpSession session = request.getSession(true);
				session.removeAttribute("korisnik");
				session.setAttribute("korisnik", k);
				response.sendRedirect("./userProfile.jsp");
				return;
				
			} catch(EJBException ejb){
				request.setAttribute("errorMessage", "Vec postoji korisnik sa tim korisnickim imenom");
				getServletContext().getRequestDispatcher("/userProfile.jsp").forward(request, response);
				return;
			}
			
		
			
			
		}catch (Exception e) {
			log.error(e);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
