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
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.PrijateljDaoLocal;

public class ObrisiPrijateljaController extends HttpServlet{
	
	private static final long serialVersionUID = 4689749037715845389L;

	@EJB
	PrijateljDaoLocal prijateljDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = 0;
		
		try{
			id = Integer.parseInt(request.getParameter("itemId"));
		}catch(NumberFormatException e){
			
		}
		Integer korisnikId = ((Korisnik)request.getSession().getAttribute("korisnik")).getId();
		
		OstvarenaPrijateljstva prijatelj = prijateljDao.findById(id);
		prijateljDao.remove(prijatelj);
		
		List<OstvarenaPrijateljstva> lista = prijateljDao.findFriends(korisnikId);
		
		request.getSession().setAttribute("prijatelji", lista);
		RequestDispatcher disp = request.getRequestDispatcher("./PrikazPrijatelja.jsp");
		disp.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
