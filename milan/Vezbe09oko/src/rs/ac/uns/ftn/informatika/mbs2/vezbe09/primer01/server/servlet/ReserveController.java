package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Rezervacija;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.NapraviRezervacijuDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.PozvaniPrijateljDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RezervacijaDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RezervisaniStoDaoLocal;

public class ReserveController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(ReserveController.class);
	
	@EJB
	private NapraviRezervacijuDaoLocal napraviRezervacijuDao;
	
	@EJB
	private KorisnikDaoLocal korisnikDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			String[] prijateljiStr = request.getParameterValues("prijatelji[]");
			System.out.println(prijateljiStr[0]);
			ArrayList<Korisnik> prijatelji = new ArrayList<>(); 
			for (String str : prijateljiStr){
				if (!str.equals("skd")){
					Korisnik k = korisnikDao.findById(Integer.parseInt(str));
					System.out.println(k.getImeKorisnika());
					prijatelji.add(k);
				}
			}
			
			@SuppressWarnings("unchecked")
			ArrayList<Sto> stolovi = (ArrayList<Sto>) request.getSession().getAttribute("stoloviZaRezervaciju");
			Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
			String datum = (String) request.getSession().getAttribute("datum");
			Integer vrijeme = Integer.parseInt((String) request.getSession().getAttribute("vrijeme"));
			Integer trajanje = Integer.parseInt((String) request.getSession().getAttribute("trajanje"));
			
			try{
				napraviRezervacijuDao.dodajRezervaciju(stolovi, k, datum, vrijeme, trajanje, prijatelji);
			} catch(EJBException e){
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
				System.out.println(e.getCausedByException());
				System.out.println(e.getStackTrace());
				System.out.println("GRESKA");
			}
			
			getServletContext().getRequestDispatcher("/welcomeUser.jsp").forward(request, response);
			
		}catch(Exception e){
			log.info("Unexpected error: " + e.getMessage() + " caused by: " + e.getCause() + " stack trace: + "+ e.getStackTrace());
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
