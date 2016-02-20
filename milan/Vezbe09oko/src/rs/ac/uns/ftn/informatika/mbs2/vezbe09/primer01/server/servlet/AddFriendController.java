package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Prijatelj;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.PrijateljDaoLocal;

public class AddFriendController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(AddFriendController.class);
	
	@EJB
	private PrijateljDaoLocal prijateljDao;
	
	@EJB 
	private KorisnikDaoLocal korisnikDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			Integer korisnikId =  Integer.parseInt(request.getParameter("korisnikId"));
			Integer prijateljId = Integer.parseInt(request.getParameter("prijateljId"));
			
			if (!checkIfExists(korisnikId, prijateljId)){
				Prijatelj prijatelj = new Prijatelj();
				Korisnik k = korisnikDao.findById(korisnikId);
				Korisnik p = korisnikDao.findById(prijateljId);
				
				prijatelj.setKorisnik(k);
				prijatelj.setPrijatelj(p);
				
				prijateljDao.persist(prijatelj);
				getServletContext().getRequestDispatcher("/PrepareFriendController").forward(request, response);
			} else{
				request.setAttribute("errorMessage", "Vec ste prijatelj sa odabranim korisnikom.");
				getServletContext().getRequestDispatcher("/PrepareFriendController").forward(request, response);
			}
			
			
		} catch(Exception e){
			log.info("Unexpected error: " + e.getMessage() + " caused by: " + e.getCause() + " stack trace: + "+ e.getStackTrace());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@SuppressWarnings("finally")
	private boolean checkIfExists(Integer korisnikId, Integer prijateljId){
		try{
			Integer id = prijateljDao.findFriendshipByFriends(korisnikId, prijateljId);
			if (id != null){
				log.info("Already friend user: " + korisnikId + " friend " + prijateljId);
				return true;
			}
		}catch(EJBException e){
			return false;
		} 
		
		return false;
		
	}

}
