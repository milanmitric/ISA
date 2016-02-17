package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.MenadzerDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;

public class UpdateManagerController extends HttpServlet {
	
	private static final long serialVersionUID = 4676416691336033321L;
	
	private static Logger log = Logger.getLogger(UpdateController.class);

	@EJB
	private MenadzerDaoLocal menadzerDao;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try
		{
			String ime = null;
			String prezime = null;
			String korisnickoIme = null;
			String lozinka = null;
			
			
			if (request.getParameter("ime")!= null){
				ime = request.getParameter("ime");
			}
			if (request.getParameter("prezime")!= null){
				prezime =request.getParameter("prezime");
			}
			if (request.getParameter("korisnickoIme") != null){
				korisnickoIme = request.getParameter("korisnickoIme");
			}
			if (request.getParameter("lozinka") != null){
				lozinka = request.getParameter("lozinka");
			}
			
			Integer menadzerId = Integer.parseInt(request.getParameter("menadzerId"));
			
			Menadzer menadzer =  menadzerDao.findById(menadzerId);
			
			if(ime != null && !ime.equals("")){
				menadzer.setIme(ime);
			} 
			
			if (prezime != null && !prezime.equals("")){
				menadzer.setPrezime(prezime);
			}
			if (korisnickoIme != null && !korisnickoIme.equals("")){
				menadzer.setKorisnickoIme(korisnickoIme);
			}
			
			if (lozinka != null && !lozinka.equals("")){
				menadzer.setLozinka(lozinka);
			}
			
		
			
			menadzerDao.merge(menadzer);
			
			getServletContext().getRequestDispatcher("/PrepareManagersController").forward(request, response);
		
		} catch (ServletException e) {
			log.error(e);
			throw e;
		} catch (IOException e) {
			log.error(e);
			throw e;
		//} catch (ParseException e) {
		//	log.error(e);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
