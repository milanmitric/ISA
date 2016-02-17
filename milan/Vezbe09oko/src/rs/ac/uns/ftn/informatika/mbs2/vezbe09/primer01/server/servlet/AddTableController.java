package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.StoDaoLocal;

public class AddTableController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	private StoDaoLocal stoDao;
	@EJB
	private RestoranDaoLocal restoranDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ((request.getSession().getAttribute("admin")) == null) {
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
		
		String restoran = null;
		Integer red = null;
		Integer kolona = null;
		
		if ((request.getParameter("restoranId") != null) && (!"".equals(request.getParameter("restoranId")))) {
			restoran = request.getParameter("restoranId");
			System.out.println(restoran);
		}
		
		if ((request.getParameter("kolona") != null) 	&& (!"".equals(request.getParameter("kolona")))) {
			kolona = new Integer(request.getParameter("kolona"));
			System.out.println(kolona);
		}
		
		if ((request.getParameter("red") != null) 	&& (!"".equals(request.getParameter("red")))) {
			red = new Integer(request.getParameter("red"));
			System.out.println(red);
		}
		System.out.println("USAO");
		// DA nadjem sve stolove koje pripadaju restorani sa ID restoran 
		List<Sto> tmp = stoDao.stoloviSaIdRestorana(Integer.parseInt(restoran));
		Sto toDelete =null;
		for (Sto stotmp : tmp) {
			if (stotmp.getKolona().equals(kolona) && stotmp.getRed().equals(red)){
				toDelete = stotmp;
			}
		}
		
		if (toDelete != null){
			stoDao.remove(toDelete);
		}else{
			Sto sto = new Sto();
			sto.setKolona(kolona);
			sto.setRed(red);
			
			Restoran r = restoranDao.findById(Integer.parseInt(restoran));
			sto.setRestoran(r);
			

			stoDao.persist(sto);
		}
		

		request.setAttribute("kreiraniStolovi",stoDao.stoloviSaIdRestorana(Integer.parseInt(restoran)));
		System.out.println((stoDao.stoloviSaIdRestorana(Integer.parseInt(restoran))).size());
		request.getSession(true).setAttribute("restoran", restoranDao.findById(Integer.parseInt(restoran)));
		getServletContext().getRequestDispatcher("/createTables.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
