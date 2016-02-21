package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Jelo;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.JeloDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;

public class CreateMealController  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(CreateMealController.class);
	@EJB
	private JeloDaoLocal jeloDao;
	@EJB
	private RestoranDaoLocal restoranDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			if ((request.getSession().getAttribute("menadzer")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			Menadzer m =  (Menadzer)request.getSession(true).getAttribute("menadzer");
			
			String naziv = null;
			String opis = null;
			Double cena = null;
			Integer restoranId = null;
			
			if (request.getParameter("naziv") != null && !request.getParameter("naziv").equals("")){
				naziv = request.getParameter("naziv");
			}
			if (request.getParameter("opis") != null && !request.getParameter("opis").equals("")){
				opis = request.getParameter("opis");
			}
			if (request.getParameter("cena") != null && !request.getParameter("cena").equals("")){
				cena =  Double.parseDouble(request.getParameter("cena"));
			}
			if (request.getParameter("restoran") != null && !request.getParameter("restoran").equals("")){
				restoranId =  Integer.parseInt(request.getParameter("restoran"));
			}
			
			Jelo jelo = new Jelo();
			jelo.setCena(cena);
			jelo.setOpis(opis);
			jelo.setNaziv(naziv);
			jelo.setRestoran(restoranDao.findById(restoranId));
			
			try{
				jeloDao.persist(jelo);
			} catch (EJBException e){
				request.setAttribute("errorMessage", "Desila se greska, pokusajte ponovo");
				getServletContext().getRequestDispatcher("/managerRestaurant.jsp").forward(request, response);
			}
			
			
			request.setAttribute("restoran", restoranDao.findById(m.getRestoran().getId()));
			request.setAttribute("jela", jeloDao.findByRestoran(m.getRestoran().getId()));
			getServletContext().getRequestDispatcher("/managerRestaurant.jsp").forward(request, response);
			
			
			
		}catch(Exception e){
			log.info(e);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	

}
