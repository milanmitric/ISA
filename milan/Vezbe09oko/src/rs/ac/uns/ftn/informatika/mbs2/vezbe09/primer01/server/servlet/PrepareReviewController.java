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
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Ocena;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Rezervacija;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.OcenaDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RezervacijaDaoLocal;

public class PrepareReviewController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(PrepareReviewController.class);
	
	@EJB
	private RezervacijaDaoLocal rezevacijaDao;
	
	@EJB
	private OcenaDaoLocal ocenaDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if ((request.getSession().getAttribute("korisnik")) == null) {
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
		
		try{
			Korisnik k = (Korisnik)(request.getSession().getAttribute("korisnik"));
			ArrayList<Rezervacija> rez = new ArrayList<>();
			ArrayList<Ocena> ocene = new ArrayList<>();
			try{
				rez= rezevacijaDao.getByUser(k.getId());
				ocene = new ArrayList<>(ocenaDao.findByUser(k.getId()));
			} catch (EJBException e){
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
				System.out.println(e.getStackTrace());
			}

			for (Rezervacija r:rez){
				for (Ocena o: ocene){
					if (r.equals(o.getRezervacija())){
						r.setOcena(o.getOcena());
					}
				}
			}
			request.setAttribute("rezervacije", rez);
			getServletContext().getRequestDispatcher("/reviews.jsp").forward(request, response);
			
			
		} catch (Exception e){
			log.info("Unexpected error: " + e.getMessage() + " caused by: " + e.getCause() + " stack trace: + "+ e.getStackTrace());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
