package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.MenadzerDaoLocal;

public class IzmeniMenadzeraController extends HttpServlet{

	private static final long serialVersionUID = -2805195381845706813L;
	
	@EJB
	private MenadzerDaoLocal menadzerDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if ((request.getSession().getAttribute("admin") == null) &&
				(request.getSession().getAttribute("menadzer") == null)) {
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
		String kor_ime ="";
		if(request.getSession().getAttribute("menadzer")!=null){
			kor_ime = ((Menadzer)(request.getSession().getAttribute("menadzer"))).getKorisnickoImeMenadzera();
		}else if(request.getSession().getAttribute("admin")!=null){
			kor_ime = ((Menadzer)(request.getSession().getAttribute("admin"))).getKorisnickoImeMenadzera();
		}
		//String korisnickoIme = request.getParameter("korisnicko_ime");
		String lozinka = request.getParameter("lozinka");
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		String lozinka2 = request.getParameter("lozinka2");
		String novaLozinka = request.getParameter("novalozinka");
		
		Menadzer korisnik = menadzerDao.findMenadzerSaKorisnickimImenom(kor_ime);
		if(lozinka.equals("") || lozinka == null){
			
			korisnik.setImeMenadzera(ime);
			korisnik.setPrezimeMenadzera(prezime);
			menadzerDao.merge(korisnik);
			RequestDispatcher disp = request.getRequestDispatcher("./obavestenje.jsp");
			disp.forward(request, response);
		}else{
			String loz ="";
			if(request.getSession().getAttribute("menadzer")!=null){
				loz = ((Menadzer)(request.getSession().getAttribute("menadzer"))).getLozinkaMenadzera();
			}else if(request.getSession().getAttribute("admin")!=null){
				loz = ((Menadzer)(request.getSession().getAttribute("admin"))).getLozinkaMenadzera();
			}
			if(lozinka.equals(loz)){
				
			
				if(novaLozinka.equals("") || novaLozinka == null || lozinka2.equals("") || lozinka2 == null)
				{
					String errorMessage = "Unesite novu lozniku 2 puta.";
					request.setAttribute("errormessage", errorMessage);
					RequestDispatcher disp = request.getRequestDispatcher("./IzmeniMenadzer.jsp");
					disp.forward(request, response);		
				}else{
					if(!lozinka2.equals(novaLozinka))
					{
						String errorMessage = "Loznike se ne ponavljaju. Molimo unesite podatke ponovo.";
						request.setAttribute("errormessage", errorMessage);
						RequestDispatcher disp = request.getRequestDispatcher("./IzmeniMenadzer.jsp");
						disp.forward(request, response);
					}else{
						korisnik.setImeMenadzera(ime);
						korisnik.setPrezimeMenadzera(prezime);
						korisnik.setLozinkaMenadzera(novaLozinka);
						menadzerDao.merge(korisnik);
						RequestDispatcher disp = request.getRequestDispatcher("./obavestenje.jsp");
						disp.forward(request, response);
					}
				}
			}else{
				String errorMessage = "Trenutna lozinka nije ispravna. Unesite ponovo.";
				request.setAttribute("errormessage", errorMessage);
				RequestDispatcher disp = request.getRequestDispatcher("./IzmeniMenadzer.jsp");
				disp.forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	

}
