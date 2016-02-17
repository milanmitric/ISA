package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RezervisiDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.StoDaoLocal;


public class RezervisiStoController extends HttpServlet{

	private static final long serialVersionUID = 8671297593946965529L;

	@EJB
	private RezervisiDaoLocal rezervisiDao;
	
	@EJB
	private StoDaoLocal stoDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Cache-Control", "no-cache, max-age=0, must-revalidate, no-store");
		String datum = request.getParameter("datum");
		String time = request.getParameter("vreme");
		Integer trajanje = Integer.parseInt(request.getParameter("trajanje"));
		
		System.out.println("Datum je: " + datum + ", vreme je: " + time + ",trajanje :" + trajanje);
		
		Integer satiIzabrano = Integer.parseInt((time.split(":")[0]));
		
		Calendar c = Calendar.getInstance();
		Date today = c.getTime();
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance(); 
		
		String timeParse = (dateFormat.format(cal.getTime()).split(" "))[1];
		Integer satiSada = Integer.parseInt((timeParse.split(":")[0]));
		
		String[] parseDatum = datum.split("-");
		int godina = Integer.parseInt(parseDatum[0]);
		int mesec = Integer.parseInt(parseDatum[1]) - 1;
		int dan= Integer.parseInt(parseDatum[2]);

		c.set(Calendar.YEAR, godina);
		c.set(Calendar.MONTH, mesec);
		c.set(Calendar.DAY_OF_MONTH, dan);
		
		Date date = c.getTime();
		
		if(date.before(today)){

			String errormessage = "Ne mozete izabrati datum pre danasnjeg.";
			request.setAttribute("errormessage", errormessage);
			
			RequestDispatcher disp = request.getRequestDispatcher("./RezervisiSto.jsp");
			disp.forward(request, response);
			return;
		}
		if(date.equals(today)){
			if(satiIzabrano<= satiSada){
	
				String errormessage = "Mozete zakazati najranije za sledeci sat.";
				request.setAttribute("errormessage", errormessage);
				
				RequestDispatcher disp = request.getRequestDispatcher("./RezervisiSto.jsp");
				disp.forward(request, response);
				return;
			}
		}
		
		Integer id = ((Restoran)request.getServletContext().getAttribute("restoranID")).getId();
		
		List<Sto> stolovi = stoDao.stoloviSaIdRestorana(id);
		
		ArrayList<Sto> listaStolova = new ArrayList<Sto>();
		for(Sto s: stolovi){
			listaStolova.add(s);
		}
		
		try{
			List<Sto> stoloviZauzeti = rezervisiDao.vratiZauzete(id, datum, satiIzabrano, trajanje);
			for(int i=0; i<stoloviZauzeti.size(); i++){
				if(listaStolova.contains(stoloviZauzeti.get(i))){
					listaStolova.get(i).setSlobodanSto(false);
				}
			}
		
		}catch(NullPointerException e){
			
		}
	
		request.setAttribute("stoloviSlobodni", listaStolova);
		request.getSession().setAttribute("datumSession", datum);
		request.getSession().setAttribute("satiIzabrano", satiIzabrano);
		request.getSession().setAttribute("trajanje", trajanje);
				
		RequestDispatcher disp = request.getRequestDispatcher("./RezervisiSto.jsp");
		disp.forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
