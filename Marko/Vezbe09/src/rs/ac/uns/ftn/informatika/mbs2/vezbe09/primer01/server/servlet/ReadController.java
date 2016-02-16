package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.util.HashSet;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.JelovnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.VoziloDaoLocal;

public class ReadController extends HttpServlet {

	private static final long serialVersionUID = -6820366488786163882L;
	
	private static Logger log = Logger.getLogger(ReadController.class);
	
	private enum tip_stavke {
		HRANA,PICE;
	}
	
	@EJB
	private VoziloDaoLocal voziloDao;
	@EJB
	private RestoranDaoLocal restoranDao;
	@EJB
	private JelovnikDaoLocal jelovnikDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("admin") == null)
					&& (request.getSession().getAttribute("menadzer")) == null
					&& (request.getSession().getAttribute("korisnik") == null)) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}

			request.getServletContext().setAttribute("restorani", restoranDao.findAllOrederedName());
			request.getServletContext().setAttribute("jelovnici", jelovnikDao.findAll());
			
			HashSet<tip_stavke> tipovi = new HashSet<tip_stavke>();
			
			for(tip_stavke tip: tip_stavke.values())
			{ 
				tipovi.add(tip); 
			}
			request.getServletContext().setAttribute("tipovi_stavke", tipovi);
			
			getServletContext().getRequestDispatcher("/read.jsp").forward(request, response);
		
		} catch (ServletException e) {
			log.error(e);
			throw e;
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}