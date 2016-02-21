package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.JeloDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.MenadzerDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;

public class ManagerPrepareRestaurantController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private MenadzerDaoLocal menadzerDao;
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	@EJB
	private JeloDaoLocal jeloDao;
	
	private static Logger log = Logger.getLogger(ManagerPrepareRestaurantController.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			if ((request.getSession().getAttribute("menadzer")) == null){
				response.sendRedirect(response.encodeURL("./login.jsp"));
			}
			
			
			Menadzer m =  (Menadzer)request.getSession(true).getAttribute("menadzer");
			
			if (m == null){
				
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			if (m.getRestoran() != null){
				request.setAttribute("restoran", restoranDao.findById(m.getRestoran().getId()));
				request.setAttribute("jela", jeloDao.findByRestoran(m.getRestoran().getId()));
				getServletContext().getRequestDispatcher("/managerRestaurant.jsp").forward(request, response);
			}
			
		}catch(Exception e){
			log.error(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
