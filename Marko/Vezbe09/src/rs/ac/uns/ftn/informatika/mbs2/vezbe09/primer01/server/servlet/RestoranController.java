package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Jelovnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.JelovnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;

public class RestoranController extends HttpServlet{

	private static final long serialVersionUID = 6699276854605887078L;

	@EJB
	private RestoranDaoLocal restoranDao;
	
	@EJB
	private JelovnikDaoLocal jelovnikDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String naziv = request.getParameter("naziv");
		String adresa = request.getParameter("adresa");
		String mail = request.getParameter("mail");
		Integer telefon = 0;
		try
		{
			telefon = Integer.parseInt(request.getParameter("telefon"));
		}catch(NumberFormatException n)
		{
		
		}
		Integer udaljenost = 0;
		try
		{
			udaljenost = Integer.parseInt(request.getParameter("udaljenost"));
		}catch(NumberFormatException n)
		{
			
		}
		//Integer idJelovnika = Integer.parseInt(request.getParameter("jelovnik"));
		Integer idJelovnika = 1;
		if ((request.getSession().getAttribute("admin")) == null) {
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
		
		Restoran restoran = new Restoran();
		
		restoran.setNazivRestorana(naziv);
		restoran.setAdresaRestoran(adresa);
		restoran.setMailRestoran(mail);
		restoran.setTelefonRestoran(telefon);
		restoran.setUdaljenostRestoran(udaljenost);
		Jelovnik jelovnik = jelovnikDao.findById(idJelovnika);
		restoran.setJelovnik(jelovnik);
		//jelovnik.add(restoran);
		
		restoranDao.persist(restoran);
		
		getServletContext().getRequestDispatcher("/ReadController").forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
