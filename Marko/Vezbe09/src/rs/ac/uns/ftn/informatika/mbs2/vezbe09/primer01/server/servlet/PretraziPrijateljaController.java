package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;

public class PretraziPrijateljaController extends HttpServlet{

	private static final long serialVersionUID = -7357425441179846408L;

	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		boolean imaIme = false;
		boolean imaPrezime = false;
		
		if(ime!=null && !ime.equals("")){
			imaIme = true;
		}
		if(prezime!=null && !prezime.equals("")){
			imaPrezime = true;
		}
		
		Integer korisnik_id = ((Korisnik)request.getSession().getAttribute("korisnik")).getId();
		
		List<Korisnik> korisnici;;
		
		if(!imaIme && !imaPrezime){
			String errorMessage = "Morate uneti ime i/ili prezime.";
			request.setAttribute("errormessage", errorMessage);
			RequestDispatcher disp = request.getRequestDispatcher("./DodajPrijatelja.jsp");
			disp.forward(request, response);
			return;
		}else if(imaIme && !imaPrezime){
			korisnici = korisnikDao.findKorisnikSaImenom(ime, korisnik_id);
			if(korisnici.isEmpty()){
				request.setAttribute("errormessage", "Ne postoji korisnik sa tim imenom.");
			}
			
			request.setAttribute("listaPrijatelja", korisnici);
			RequestDispatcher disp = request.getRequestDispatcher("./DodajPrijatelja.jsp");
			disp.forward(request, response);
			return;
		}else if(!imaIme && imaPrezime){
			korisnici = korisnikDao.findKorisnikSaPrezimenom(prezime, korisnik_id);
			if(korisnici.isEmpty()){
				request.setAttribute("errormessage", "Ne postoji korisnik sa tim prezimenom.");
			}
			request.setAttribute("listaPrijatelja", korisnici);
			RequestDispatcher disp = request.getRequestDispatcher("./DodajPrijatelja.jsp");
			disp.forward(request, response);
			return;
		}else if(imaIme && imaPrezime){
			korisnici = korisnikDao.findKorisnikSaImenomIprezimenom(ime, prezime, korisnik_id);
			if(korisnici.isEmpty()){
				request.setAttribute("errormessage", "Ne postoji korisnik sa tim imenom i prezimenom.");
			}
			request.setAttribute("listaPrijatelja", korisnici);

			RequestDispatcher disp = request.getRequestDispatcher("./DodajPrijatelja.jsp");
			disp.forward(request, response);
			return;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
