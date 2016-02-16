package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.StoDaoLocal;

public class DefinisiRasporedController extends HttpServlet{

	private static final long serialVersionUID = 1323844340413107587L;
	
	@EJB
	private StoDaoLocal stoDao;
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if ((request.getSession().getAttribute("menadzer") == null)){
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
		
		Restoran restoran  = (Restoran)request.getServletContext().getAttribute("restoranID");
		
		Integer kapacitet = Integer.parseInt(request.getParameter("kapacitet"));
		Integer red = Integer.parseInt(request.getParameter("red"));
		Integer kolona = Integer.parseInt(request.getParameter("kolona"));
		
		restoran.setKolona(kolona);
		restoran.setRed(red);
		restoranDao.merge(restoran);
		
		request.getServletContext().setAttribute("restoranID", restoran);
		
		
		ArrayList<Integer> redovi = new ArrayList<Integer>();
		ArrayList<Integer> kolone = new ArrayList<Integer>();
		
		for(int i= 0; i<red ; i++){
			redovi.add(i);
		}
		for(int i= 0; i<kolona; i++){
			kolone.add(i);
		}
		
		ArrayList<Sto> lista = new ArrayList<Sto>();
		
		for(int i=0; i<red; i++){
			for(int j=0; j<kolona; j++){
				Sto sto = new Sto(restoran, i, j , true, true);
				stoDao.persist(sto);
				lista.add(sto);
			}
		}
		request.getServletContext().setAttribute("stolovi", lista);
		request.getServletContext().setAttribute("redovi", redovi);
		request.getServletContext().setAttribute("kolone", kolone);
		request.getServletContext().setAttribute("kapacitet", kapacitet);
		request.getServletContext().setAttribute("broj_redova", red);
		
		RequestDispatcher disp = request.getRequestDispatcher("./PrikazRestorana.jsp");
		disp.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
