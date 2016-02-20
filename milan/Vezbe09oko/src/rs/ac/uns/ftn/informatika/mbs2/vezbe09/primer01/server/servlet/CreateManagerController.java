package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.MenadzerDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;

public class CreateManagerController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(CreateController.class);
	
	@EJB
	private MenadzerDaoLocal menadzerDao;
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		
		try{
			String ime = null;
			String prezime = null;
			String korisnickoIme = null;
			String lozinka = null;
			boolean isSystemManager = false;
			String restoran = null;
			
			if ((request.getSession().getAttribute("admin")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			if ((request.getParameter("ime") != null) && (!"".equals(request.getParameter("ime")))) {
				ime = request.getParameter("ime");
			}
			if ((request.getParameter("prezime") != null) && (!"".equals(request.getParameter("prezime")))) {
				prezime = request.getParameter("prezime");
			}
			if ((request.getParameter("korisnickoIme") != null) && (!"".equals(request.getParameter("korisnickoIme")))) {
				korisnickoIme = request.getParameter("korisnickoIme");
			}
			if ((request.getParameter("lozinka") != null) && (!"".equals(request.getParameter("lozinka")))) {
				lozinka = request.getParameter("lozinka");
			}
			if (request.getParameter("sistemMenadzer")!= null){
				String booleanValue = request.getParameter("sistemMenadzer");
				if (booleanValue.equals("on")){
					isSystemManager = true;
				} else {
					isSystemManager = false;
				}
				
			}
			if (request.getParameter("restoran") != null){
				restoran = request.getParameter("restoran");
			}
			
			Menadzer menadzer = new Menadzer();
			
			if (ime != null){
				menadzer.setIme(ime);
			}
			
			if (prezime != null){
				menadzer.setPrezime(prezime);
			}
			
			if (korisnickoIme != null){
				menadzer.setKorisnickoIme(korisnickoIme);
			}
			
			if (lozinka != null) {
				menadzer.setLozinka(lozinka);
			}
			
			menadzer.setSistemMenadzer(isSystemManager);
			
			if (restoran != null && !restoran.equals("nema")){
				Restoran r = restoranDao.findById(Integer.parseInt(restoran));
				menadzer.setRestoran(r);
			}
			
			if (korisnikDao.checkIfExists(korisnickoIme)){
				request.setAttribute("errorMessage", "Vec postoji korisnik sa tim korisnickim imenom");
				getServletContext().getRequestDispatcher("/PrepareManagersController").forward(request, response);
				return;
			}
			
			try{
				menadzerDao.persist(menadzer);
			} catch(EJBException ejb){
				request.setAttribute("errorMessage", "Vec postoji korisnik sa tim korisnickim imenom");
				getServletContext().getRequestDispatcher("/PrepareManagersController").forward(request, response);
				return;
			}

			getServletContext().getRequestDispatcher("/PrepareManagersController").forward(request, response);
			
			
		}catch (ServletException e) {
			log.error(e);
			throw e;
		}catch (IOException e) {
			log.error(e.getStackTrace());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
