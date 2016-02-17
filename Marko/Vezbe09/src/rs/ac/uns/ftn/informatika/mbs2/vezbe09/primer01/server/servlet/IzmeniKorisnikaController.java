package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;

public class IzmeniKorisnikaController extends HttpServlet{

	private static final long serialVersionUID = 8654829125135538127L;
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if ((request.getSession().getAttribute("korisnik") == null)){
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
		
		String kor_ime = ((Korisnik)(request.getSession().getAttribute("korisnik"))).getKorisnickoImeKorisnika();

		String lozinka = request.getParameter("lozinka");
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		String lozinka2 = request.getParameter("lozinka2");
		String novaLozinka = request.getParameter("novalozinka");
		
		Korisnik korisnik = korisnikDao.findKorisnikSaKorisnickimImenom(kor_ime);
		if(lozinka.equals("") || lozinka == null){
			
			korisnik.setImeKorisnika(ime);
			korisnik.setPrezimeKorisnika(prezime);
			korisnikDao.merge(korisnik);
			RequestDispatcher disp = request.getRequestDispatcher("./obavestenje.jsp");
			disp.forward(request, response);
		}else{
			String loz = ((Korisnik)(request.getSession().getAttribute("korisnik"))).getLozinkaKorisnika();

			if(lozinka.equals(loz)){
				
			
				if(novaLozinka.equals("") || novaLozinka == null || lozinka2.equals("") || lozinka2 == null)
				{
					String errorMessage = "Unesite novu lozniku 2 puta.";
					request.setAttribute("errormessage", errorMessage);
					RequestDispatcher disp = request.getRequestDispatcher("./IzmeniKorisnik.jsp");
					disp.forward(request, response);		
				}else{
					if(!lozinka2.equals(novaLozinka)) 
					{
						String errorMessage = "Loznike se ne ponavljaju. Molimo unesite podatke ponovo.";
						request.setAttribute("errormessage", errorMessage);
						RequestDispatcher disp = request.getRequestDispatcher("./IzmeniKorisnik.jsp");
						disp.forward(request, response);
					}else{
						korisnik.setImeKorisnika(ime);
						korisnik.setPrezimeKorisnika(prezime);
						korisnik.setLozinkaKorisnika(novaLozinka);
						korisnikDao.merge(korisnik);
						RequestDispatcher disp = request.getRequestDispatcher("./obavestenje.jsp");
						disp.forward(request, response);
					}
				}
			}else{
				String errorMessage = "Trenutna lozinka nije ispravna. Unesite ponovo.";
				request.setAttribute("errormessage", errorMessage);
				RequestDispatcher disp = request.getRequestDispatcher("./IzmeniKorisnik.jsp");
				disp.forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	

}
