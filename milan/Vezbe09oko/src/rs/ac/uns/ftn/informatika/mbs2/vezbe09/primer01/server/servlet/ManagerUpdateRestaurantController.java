package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;

public class ManagerUpdateRestaurantController  extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private RestoranDaoLocal restoranDao;
	
	private static Logger log = Logger.getLogger(ManagerUpdateRestaurantController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			if ((request.getSession().getAttribute("menadzer")) == null){
				response.sendRedirect(response.encodeURL("./login.jsp"));
			}
			
			String naziv = null;
			String vrsta = null;
			
			if (request.getParameter("nazivRestorana")!= null){
				naziv = request.getParameter("nazivRestorana");
			}
			
			if (request.getParameter("vrsta")!= null){
				vrsta = request.getParameter("vrsta");
			}
			
			
			Menadzer m =  (Menadzer)request.getSession(true).getAttribute("menadzer");
			
			Restoran restoran = m.getRestoran();
			if (restoran != null){
				
				if (naziv != null){
					restoran.setNazivRestorana(naziv);
				}
				
				if (vrsta != null){
					restoran.setVrstaRestorana(vrsta);
				}
				
				restoranDao.merge(restoran);
				request.setAttribute("restoran", restoran);
				getServletContext().getRequestDispatcher("/ManagerPrepareRestaurantController").forward(request, response);
			}
		
			
		}catch(Exception e){
			log.error(e);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
