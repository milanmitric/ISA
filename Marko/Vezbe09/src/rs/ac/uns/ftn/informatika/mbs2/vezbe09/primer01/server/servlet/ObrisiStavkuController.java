package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.StavkaJelovnika;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.StavkaJelovnikaDaoLocal;

public class ObrisiStavkuController extends HttpServlet{

	private static final long serialVersionUID = -7637024369156455471L;
	
	@EJB
	private StavkaJelovnikaDaoLocal stavkaDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("menadzer")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}

			String stavkaId = null;
			stavkaId = request.getParameter("stavkaId");

			StavkaJelovnika stavka = stavkaDao.findById(Integer.parseInt(stavkaId));
			stavkaDao.remove(stavka);

			getServletContext().getRequestDispatcher("/PrikazRestoranaController").forward(request, response);
			
		} catch (ServletException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
