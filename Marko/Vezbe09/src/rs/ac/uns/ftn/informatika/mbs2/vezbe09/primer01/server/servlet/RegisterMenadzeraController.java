package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.MenadzerDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;



public class RegisterMenadzeraController extends HttpServlet{

	private static final long serialVersionUID = 7538354551030056092L;
	
	@EJB
	private MenadzerDaoLocal menadzerDao;
	
	@EJB 
	private RestoranDaoLocal restoranDao;
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if ((request.getSession().getAttribute("admin") == null)) {
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
		
		String korisnickoIme = request.getParameter("korisnicko_ime");
		String lozinka = request.getParameter("lozinka");
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		String lozinka2 = request.getParameter("lozinka2");
		
		Integer id_restorana = 1;
		
		try{
			id_restorana = Integer.parseInt(request.getParameter("restoran"));
		}catch(NumberFormatException e){
			
		}
		
		if(!lozinka2.equals(lozinka))
		{
			String errorMessage = "Loznike se ne ponavljaju. Molimo unesite podatke ponovo.";
			request.setAttribute("errormessage", errorMessage);
			request.setAttribute("ime", ime);
			request.setAttribute("prezime", prezime);
			request.setAttribute("korisnicko_ime", korisnickoIme);
			RequestDispatcher disp = request.getRequestDispatcher("./DodajMenadzera.jsp");
			disp.forward(request, response);
		}else
		{
			try {
				
				Menadzer korisnik = menadzerDao.findMenadzerSaKorisnickimImenom(korisnickoIme);
				
				if (korisnik != null) {	
	
					String errorMessage = "Postoji menadzer sa tim korisnickim imenom.";
					request.setAttribute("errormessage", errorMessage);
					RequestDispatcher disp = request.getRequestDispatcher("./DodajMenadzera.jsp");
					disp.forward(request, response);
				}
				
			} catch (EJBException e) {
				if (e.getCause().getClass().equals(NoResultException.class)) {
					
					try {
						
						Korisnik korisnik = korisnikDao.findKorisnikSaKorisnickimImenom(korisnickoIme);
						if (korisnik != null) {	
			
							String errorMessage = "Postoji korisnik sa tim korisnickim imenom.";
							request.setAttribute("errormessage", errorMessage);
							RequestDispatcher disp = request.getRequestDispatcher("./DodajMenadzera.jsp");
							disp.forward(request, response);
						}
						
					} catch (EJBException ej) {
						if (ej.getCause().getClass().equals(NoResultException.class)) {
							
							Restoran restoran = restoranDao.findById(id_restorana);
							
							Menadzer noviKorisnik = new Menadzer(ime, prezime, korisnickoIme, lozinka, restoran, null, false);
							
							menadzerDao.persist(noviKorisnik);
							
							getServletContext().getRequestDispatcher("/ReadController").forward(request, response);
							
						} else {
							throw ej;
						}
					} catch (ServletException ej) {
						throw ej;
					} catch (IOException ej) {
						throw ej;
					}
				
					
					
				} else {
					throw e;
				}
			} catch (ServletException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			}
		}
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
	
	

}
