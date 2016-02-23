package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.JeloDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;

public class PrepareMenuController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(PrepareMenuController.class);
	
	@EJB
	private JeloDaoLocal jeloDao;
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			if ((request.getSession().getAttribute("korisnik")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			Restoran restoran = null;
			if (request.getParameter("restoran")!= null){
				restoran = restoranDao.findById(Integer.parseInt(request.getParameter("restoran")));
			}
			if (restoran != null){
				request.setAttribute("jela", jeloDao.findByRestoran(restoran.getId()));
				getServletContext().getRequestDispatcher("/viewMenu.jsp").forward(request, response);
			} else {
				System.out.println("NULL JE ");
			}
			
			
			
		}
		catch(Exception e){
			log.info(e.getStackTrace());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
