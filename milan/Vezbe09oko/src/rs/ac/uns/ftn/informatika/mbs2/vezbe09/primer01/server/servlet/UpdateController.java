package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;

public class UpdateController extends HttpServlet {
	
	private static final long serialVersionUID = 4676416691336033321L;
	
	private static Logger log = Logger.getLogger(UpdateController.class);

	@EJB
	private RestoranDaoLocal restoranDao;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try
		{
			String nazivRestorana = null;
			String adresaRestorana = null;
			String mailRestorana = null;
			String telefonRestorana = null;
			Integer udaljenostRestorana = null;
			String vrsta= null;
			
			
			if (request.getParameter("nazivRestorana")!= null){
				nazivRestorana = request.getParameter("nazivRestorana");
			}
			if (request.getParameter("adresaRestorana")!= null){
				adresaRestorana =request.getParameter("adresaRestorana");
			}
			if (request.getParameter("mailRestorana") != null){
				mailRestorana = request.getParameter("mailRestorana");
			}
			if (request.getParameter("telefonRestorana") != null){
				telefonRestorana = request.getParameter("telefonRestorana");
			}
			if (request.getParameter("udaljenostRestorana") != null){
				udaljenostRestorana = Integer.parseInt(request.getParameter("udaljenostRestorana")); 
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
			
			
			Integer restoranId = Integer.parseInt(request.getParameter("restoranId"));
			restoran.setId(restoranId);
			
			restoranDao.merge(restoran);
			
			getServletContext().getRequestDispatcher("/readRestaurants.jsp").forward(request, response);
		
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
