package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.PozvaniPrijatelj;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Rezervacija;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.PozvaniPrijateljDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RezervacijaDaoLocal;

public class ConfirmInvitationController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private RezervacijaDaoLocal rezervacijaDao;
	
	@EJB
	private PozvaniPrijateljDaoLocal pozvaniDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			String prijatelj = null;
			String rezervacija = null;
			
			if (request.getParameter("p") != null && !request.getParameter("p").equals("")){
				prijatelj = request.getParameter("p");
			}
			if (request.getParameter("r") != null && !request.getParameter("r").equals("")){
				rezervacija = request.getParameter("r");
			}
			Korisnik k = new Korisnik();
			if (prijatelj != null && rezervacija != null){
				k = korisnikDao.findById(Integer.parseInt(prijatelj));
				Rezervacija r = rezervacijaDao.findById(Integer.parseInt(rezervacija));
				
				PozvaniPrijatelj p =  new PozvaniPrijatelj();
				p.setKorisnik(k);
				p.setRezervacija(r);
				pozvaniDao.persist(p);
			}
			request.setAttribute("poruka", "Uspesno ste prihvatili pozivnicu za rezervaciju!");
			request.setAttribute("korisnikId", k.getId());
			getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
			
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
