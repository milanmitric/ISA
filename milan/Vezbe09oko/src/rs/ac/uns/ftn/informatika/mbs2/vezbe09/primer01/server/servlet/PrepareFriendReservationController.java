package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Prijatelj;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.PrijateljDaoLocal;

public class PrepareFriendReservationController extends HttpServlet{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(PrepareFriendController.class);
	
	@EJB
	private PrijateljDaoLocal prijateljDao;
	
	@EJB
	private KorisnikDaoLocal korisnikDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ((request.getSession().getAttribute("korisnik")) == null) {
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
		
		try{
			Korisnik korisnik = (Korisnik)(request.getSession().getAttribute("korisnik"));
			
			List<Prijatelj> prijatelji = prijateljDao.findFriend(korisnik.getId());
			List<Korisnik> korisnici = korisnikDao.findAll();
			
			
			ArrayList<Korisnik> outList = new ArrayList<>();
			
			for(int i =0 ; i < prijatelji.size();i++){
				for (Korisnik korisnikTmp : korisnici) {
					if (prijatelji.get(i).getPrijatelj().getId().equals(korisnikTmp.getId())){
						outList.add(korisnikTmp);
					}
				}
			}
			
			String errorMessage = request.getParameter("errorMessage");
			if (errorMessage != null){
				request.setAttribute("errorMessage", errorMessage);
			}
			request.setAttribute("prijatelji", outList);
			
			
			getServletContext().getRequestDispatcher("/friendsReservation.jsp").forward(request, response);
	
			
			
		}
		catch (Exception e){
			log.info("Unexpected error: " + e.getMessage() + " caused by: " + e.getCause() + " stack trace: + "+ e.getStackTrace());
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
