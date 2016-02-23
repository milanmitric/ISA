package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class CancelReservationController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(CancelReservationController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			if ((request.getSession().getAttribute("korisnik")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			HttpSession session =  request.getSession(true);
			session.removeAttribute("stoloviZaRezervaciju");
			response.sendRedirect(response.encodeURL("./welcomeUser.jsp"));
			
			
		}catch(Exception e){
			log.info(e.getStackTrace());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
