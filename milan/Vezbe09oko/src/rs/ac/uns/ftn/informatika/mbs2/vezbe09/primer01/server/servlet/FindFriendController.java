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
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.PrijateljDaoLocal;

public class FindFriendController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(FindFriendController.class);
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			String ime = request.getParameter("ime");
			String prezime = request.getParameter("prezime");
			Integer korisnikId = Integer.parseInt(request.getParameter("korisnikId"));
			
			request.setAttribute("korisnici", getUsers(ime, prezime, korisnikId));
			getServletContext().getRequestDispatcher("/findFriendResult.jsp").forward(request, response);
			
		}
		catch (Exception e){
			log.info("Unexpected error: " + e.getMessage() + " caused by: " + e.getCause() + " stack trace: + "+ e.getStackTrace());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private ArrayList<Korisnik> getUsers(String ime, String prezime, Integer korisnikId){
		
		List<Korisnik> korisnici = korisnikDao.findAll();
		
		ArrayList<Korisnik> outList = new ArrayList<>();
		for(Korisnik k : korisnici){
			// Name or lastname same and isn't current user that is adding friends.
			if (checkUser(ime, prezime, k) && !k.getId().equals(korisnikId)){
				outList.add(k);
			}
		}
		
		return outList;
	}
	
	private boolean checkUser(String ime,String prezime, Korisnik k){
		
		if ((ime!= null && !ime.equals("") )&& (prezime != null && !prezime.equals(""))){
			if (k.getImeKorisnika().equals(ime) && k.getPrezimeKorisnika().equals(prezime)){
				return true;
			} else {
				return false;
			}
		}
		else if ((ime == null || ime.equals("")) && (prezime != null && !prezime.equals(""))){
			if (k.getPrezimeKorisnika().equals(prezime)){
				return true;
			} else {
				return false;
			}
		} else if ((ime != null && !ime.equals(""))&& (prezime == null || prezime.equals(""))){
			if(k.getImeKorisnika().equals(ime)){
				return true;
			} else{
				return false;
			}
		} else {
			return false;
		}
	}

}
