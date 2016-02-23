package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Rezervacija;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.RezervisaniSto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.PrijateljDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RezervacijaDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RezervisaniStoDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.StoDaoLocal;

public class PrepareReservationController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(PrepareReservationController.class);
	
	@EJB
	private StoDaoLocal stoDao;
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	@EJB
	private PrijateljDaoLocal prijateljDao;
	
	@EJB
	private RezervacijaDaoLocal rezervacijaDao;
	
	@EJB
	private RezervisaniStoDaoLocal rezervisaniStoDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			if ((request.getSession().getAttribute("korisnik")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			String id = request.getParameter("restoranId");
			String datum = request.getParameter("datum");
			try
			{
				SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
				datum = format.format(new Date(datum));
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			String vrijeme = request.getParameter("vrijeme");
			System.out.println("DATUM JE " + datum);
			String trajanje = request.getParameter("trajanje");
			
			
			
			if (request.getParameter("first") != null){
				HttpSession session =  request.getSession(true);
				session.removeAttribute("stoloviZaRezervaciju");
				session.setAttribute("datum", datum);
				session.setAttribute("vrijeme", vrijeme);
				session.setAttribute("trajanje", trajanje);
			}
			
			
			
			
			if ((id != null) && (!id.equals(""))) {
				System.out.println(vrijeme);
				List<Sto> kreiraniStolovi = null;
				
				try{
					kreiraniStolovi = stoDao.stoloviSaIdRestorana(Integer.parseInt(id));
					
				}catch(EJBException e){
					
				}
				if (kreiraniStolovi == null || kreiraniStolovi.size() ==0){
					System.out.println("NEMA STOLOVA");
					
				}else{
					System.out.println("USAO");
					Restoran restoran = restoranDao.findById(Integer.parseInt(id));
					ArrayList<Sto> pronadjeniStolovi = prepareTables(kreiraniStolovi, restoran.getBrojRedovaRestorana(), restoran.getBrojKolonaRestorana(),  restoran);
					pronadjeniStolovi = checkPreviouslyReserved(pronadjeniStolovi, request, Integer.parseInt(id));
					if (checkReserved(pronadjeniStolovi, request) != null){
						pronadjeniStolovi = checkReserved(pronadjeniStolovi, request);
					}
					
					request.setAttribute("stolovi", pronadjeniStolovi);
					request.getSession(true).setAttribute("restoran", restoran);
					getServletContext().getRequestDispatcher("/reserveTables.jsp").forward(request, response);
					System.out.println("PROSAO");
				}
				
				
			}
			
			
		}catch(Exception e){
			log.info(e.getStackTrace());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
private ArrayList<Sto> prepareTables(List<Sto> inputTables,int red, int kolona,Restoran r){
		
		ArrayList<Sto> ret = createEmptyTables(red, kolona, r,false);
		
		for(Sto sto: ret){
			for(Sto tmpSto: inputTables){
				if (tmpSto.getKolona() == sto.getKolona() && tmpSto.getRed() == sto.getRed()){
					sto.setSlobodan(true);
				}
			}
		}
		
		
		
		return ret;
		
	}
	
	private ArrayList<Sto> createEmptyTables(int red, int kolona,Restoran r,boolean status){
		
		
		ArrayList<Sto> ret = new ArrayList<>();
		
		for(int i = 1; i <= red;i++){
			for (int j = 1; j <= kolona;j++){
				Sto sto = new Sto();
				sto.setRed(i);
				sto.setKolona(j);
				sto.setRestoran(r);
				sto.setSlobodan(status);
				ret.add(sto);
			}
		}
		return ret;
	}
	
	private ArrayList<Sto> checkReserved(ArrayList<Sto> input, HttpServletRequest request){
		
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("stoloviZaRezervaciju") == null){
			return null;
		} else{
			
			ArrayList<Sto> stoloviZaRezervaciju = (ArrayList<Sto>) session.getAttribute("stoloviZaRezervaciju");
			
			ArrayList<Sto> ret = new ArrayList<>(input);
			
			for(Sto rezervisani: stoloviZaRezervaciju){
				for(Sto svi: ret){
					if (rezervisani.getKolona() == svi.getKolona() && rezervisani.getRed() == svi.getRed()){
						if(svi.getRezervisan() != 2){
							svi.setRezervisan(1);
						}
					}
				}
				
			}
			
			return ret;
		}
		
	}
	
	private ArrayList<Sto> checkPreviouslyReserved(ArrayList<Sto> input, HttpServletRequest request, Integer restoranId){
		HttpSession session = request.getSession(true);
		String datum = (String) session.getAttribute("datum");
		Integer vrijeme =  Integer.parseInt((String)session.getAttribute("vrijeme"));
		Integer trajanje = Integer.parseInt((String) session.getAttribute("trajanje"));
		
	
	
		ArrayList<Sto> stolovi = new ArrayList<>();
		try{
			stolovi = new ArrayList<>(rezervacijaDao.getStoByDateAndTime(datum, vrijeme, trajanje, restoranId));
		}catch (EJBException e){
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.getStackTrace());
		}
		
		
		ArrayList<Sto> ret = new ArrayList<>(input);
		
		for(Sto rezervisani: stolovi){
			for(Sto svi: ret){
				if (rezervisani.getKolona() == svi.getKolona() && rezervisani.getRed() == svi.getRed()){
						svi.setRezervisan(2);
				}
			}
			
		}
		
		return ret;
		
	}

}
