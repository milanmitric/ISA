package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.StavkaJelovnika;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.StavkaJelovnikaDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.StoDaoLocal;




public class PrikazRestoranaController extends HttpServlet{

	private static final long serialVersionUID = 8931665993685317625L;

	@EJB
	private RestoranDaoLocal restoranDao;
	
	@EJB
	private StavkaJelovnikaDaoLocal stavkaDao;
	
	@EJB
	private StoDaoLocal stoDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if ((request.getSession().getAttribute("admin") == null)
				&& (request.getSession().getAttribute("menadzer")) == null
				&& (request.getSession().getAttribute("korisnik") == null)){
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
		
		Integer id = 1;
		try
		{
			id = Integer.parseInt(request.getParameter("itemId"));
		}catch(NumberFormatException e)
		{
		}
		Restoran restoran = restoranDao.findById(id);
		ArrayList<StavkaJelovnika> stavke = new ArrayList<StavkaJelovnika>();
		if(restoran.getJelovnik() != null)
		{
			Integer id_jelvonika = restoran.getJelovnik().getId();
			List<StavkaJelovnika> st = (List<StavkaJelovnika>) stavkaDao.findStavkeSaIDJelovnika(id_jelvonika);
			for (StavkaJelovnika s : st) {
				stavke.add(s);
			}
		}
		
		List<Sto> stolovi = (List<Sto>) stoDao.stoloviSaIdRestorana(id);
		request.getServletContext().setAttribute("stolovi", stolovi);
		
		request.getServletContext().setAttribute("stavkaID", stavke);
		request.getServletContext().setAttribute("restoranID", restoran);
		RequestDispatcher disp = request.getRequestDispatcher("./PrikazRestorana.jsp");
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
