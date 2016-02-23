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
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.OcenaDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;

public class PrepareReadRestaurantsController extends HttpServlet{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(PrepareReadRestaurantsController.class);

	@EJB
	private RestoranDaoLocal restoranDao;
	
	@EJB
	private OcenaDaoLocal ocenaDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if ((request.getSession().getAttribute("korisnik")) != null){
				ArrayList<Restoran> restorani = new ArrayList<Restoran>( restoranDao.findAll());
				for (Restoran r : restorani){
					try {
						Korisnik korisnik= (Korisnik) (request.getSession().getAttribute("korisnik"));
						Double ocena = ocenaDao.findAvgByRestoran(r.getId());
						Double ocenaPrijatelj = ocenaDao.findAvgFromFriendsByRestoran(r.getId(), korisnik.getId());
						System.out.println("OCENA " + ocena);
						r.setProsecnaOcenaRestorana(ocena);
						r.setProsecnaOcenaPrijateljaRestorana(ocenaPrijatelj);
					} catch (EJBException e){
						System.out.println("GRESKA STRASNA ");
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						System.out.println(e.getCause());
					}
					
				}
				request.getServletContext().setAttribute("restorani",restorani);
				getServletContext().getRequestDispatcher("/readRestaurants.jsp").forward(request, response);
			} 
			else {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}

			
			
			
		
		} catch (ServletException e) {
			log.error(e);
			throw e;
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
