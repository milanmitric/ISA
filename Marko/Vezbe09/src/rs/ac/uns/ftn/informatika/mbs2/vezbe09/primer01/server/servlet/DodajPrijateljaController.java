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
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.OstvarenaPrijateljstva;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.PrijateljDaoLocal;

public class DodajPrijateljaController extends HttpServlet{

	private static final long serialVersionUID = 7542245502886975405L;

	@EJB
	KorisnikDaoLocal korisnikDao;
	
	@EJB
	PrijateljDaoLocal prijateljDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = 0;
		try{
			id = Integer.parseInt(request.getParameter("itemId"));
		}catch(NumberFormatException e){
			
		}
		
		@SuppressWarnings("unchecked")
		List<OstvarenaPrijateljstva> lista = (List<OstvarenaPrijateljstva>)request.getSession().getAttribute("prijatelji");
		
		for(OstvarenaPrijateljstva opp : lista){
			if(opp.getPrijatelj().getId().equals(id)){
				String imamessage = "Korisnik vec postoji u vasoj listi prijatelja. Unesite ponovo podatke za pretragu.";
				request.setAttribute("imamessage", imamessage);
				RequestDispatcher disp = request.getRequestDispatcher("./DodajPrijatelja.jsp");
				disp.forward(request, response);
				return;
			}
		}
		
		Korisnik korisnik = (Korisnik)request.getSession().getAttribute("korisnik");
		Korisnik prijatelj = korisnikDao.findById(id);
		
		OstvarenaPrijateljstva ostp = new OstvarenaPrijateljstva(korisnik, prijatelj);
		
		prijateljDao.persist(ostp);
		
		List<OstvarenaPrijateljstva> prijatelji = prijateljDao.findFriends(korisnik.getId());
		
		request.getSession().setAttribute("prijatelji", prijatelji);
		
		RequestDispatcher disp = request.getRequestDispatcher("./PrikazPrijatelja.jsp");
		disp.forward(request, response);
		return;
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
