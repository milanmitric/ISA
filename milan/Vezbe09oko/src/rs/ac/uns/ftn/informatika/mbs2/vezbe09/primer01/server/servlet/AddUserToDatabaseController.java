package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;

public class AddUserToDatabaseController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String korisnickoIme = null;
			String lozinka = null;
			String ime = null;
			String prezime = null;
			
			if (request.getParameter("ime") != null && !request.getParameter("ime").equals("")){
				ime = request.getParameter("ime");
			}
			if (request.getParameter("prezime") != null && !request.getParameter("prezime").equals("")){
				prezime = request.getParameter("prezime");
			}
			if (request.getParameter("korisnickoIme") != null && !request.getParameter("korisnickoIme").equals("")){
				korisnickoIme = request.getParameter("korisnickoIme");
			}
			if (request.getParameter("lozinka") != null && !request.getParameter("lozinka").equals("")){
				lozinka = request.getParameter("lozinka");
			}
			if (ime != null && korisnickoIme != null && prezime != null && lozinka != null){
				Korisnik k = new Korisnik();
				k.setImeKorisnika(ime);
				k.setPrezimeKorisnika(prezime);
				k.setKorisnickoImeKorisnika(korisnickoIme);
				k.setLozinkaKorisnika(lozinka);
				
				korisnikDao.persist(k);
				
				request.setAttribute("poruka", "Uspesno ste se registrovali");
				
				getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
