package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Vozilo;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.GorivoDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.MenjacDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.VoziloDaoLocal;

public class CreateController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	
	private static Logger log = Logger.getLogger(CreateController.class);

	@EJB
	private RestoranDaoLocal restoranDao;
	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String nazivRestorana = null;
			String adresaRestorana = null;
			String mailRestorana = null;
			String telefonRestorana = null;
			Integer udaljenostRestorana = null;
			String vrsta= null;
			
			
			if (request.getParameter("naziv")!= null){
				nazivRestorana = request.getParameter("naziv");
			}
			if (request.getParameter("adresa")!= null){
				adresaRestorana =request.getParameter("adresa");
			}
			if (request.getParameter("mail") != null){
				mailRestorana = request.getParameter("mail");
			}
			if (request.getParameter("telefon") != null){
				telefonRestorana = request.getParameter("telefon");
			}
			if (request.getParameter("udaljenost") != null){
				udaljenostRestorana = Integer.parseInt(request.getParameter("udaljenost")); 
			}
			
			if (request.getParameter("vrsta")!= null){
				vrsta = request.getParameter("vrsta");
			}
			
			Restoran restoran = new Restoran();
			
			if(telefonRestorana != null && !telefonRestorana.equals("")){
				restoran.setTelefonRestorana(telefonRestorana);
			} 
			
			if (nazivRestorana != null && !nazivRestorana.equals("")){
				restoran.setNazivRestorana(nazivRestorana);
			}
			if (adresaRestorana != null && !adresaRestorana.equals("")){
				restoran.setAdresaRestorana(adresaRestorana);
			}
			
			if (mailRestorana != null && !mailRestorana.equals("")){
				restoran.setMailRestorana(mailRestorana);
			}
			
			if (udaljenostRestorana != null && !udaljenostRestorana.equals("")){
				restoran.setUdaljenostRestorana(udaljenostRestorana);
			}
			
			if (vrsta != null && !vrsta.equals("")){
				restoran.setVrstaRestorana(vrsta);
			}
			
			try{
				restoranDao.persist(restoran);
				request.getServletContext().setAttribute("restorani",restoranDao.findAll());
				getServletContext().getRequestDispatcher("/readRestaurants.jsp").forward(request, response);
			} catch (EJBException e){
				System.out.println(e.getMessage());
			}
			
		} catch (Exception e) {
			log.error(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
