package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.StoDaoLocal;


public class PrepareAddTablesController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private RestoranDaoLocal restoranDao;
	@EJB
	private StoDaoLocal stoDao;
	
	
	private static Logger log = Logger.getLogger(PrepareAddTablesController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			if ((request.getSession().getAttribute("menadzer")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}

			
			String id = request.getParameter("restoranId");
			Integer red = null;
			Integer kolona = null;
			if (request.getParameter("red") != null && StringUtils.isNumeric(request.getParameter("red"))){
				red = Integer.parseInt(request.getParameter("red"));
			}
			if (request.getParameter("kolona") != null && StringUtils.isNumeric(request.getParameter("kolona"))){
				kolona = Integer.parseInt(request.getParameter("kolona"));
			}
			
			System.out.println("Id restorana: " + id);
			
			if ((id != null) && (!id.equals(""))) {
				List<Sto> kreiraniStolovi = null;
				
				try{
					
					kreiraniStolovi = stoDao.stoloviSaIdRestorana(Integer.parseInt(id));
				}catch(EJBException e){
					
					request.getSession(true).setAttribute("restoran", restoranDao.findById(Integer.parseInt(id)));
					getServletContext().getRequestDispatcher("/createTables.jsp").forward(request, response);
				}
				
				if (kreiraniStolovi == null || kreiraniStolovi.size() ==0){
					// Odabrao broj redova i kolona prvi put, napraviti toliko slobodnih stolova.
					if (red != null && kolona != null){
						System.out.println("RED " + red + " KOLONA " + kolona);
						Restoran restoran = restoranDao.findById(Integer.parseInt(id));
						restoran.setBrojKolonaRestorana(kolona);
						restoran.setBrojRedovaRestorana(red);
						restoranDao.merge(restoran);
						
						ArrayList<Sto> stolovi = createEmptyTables(red, kolona, restoran,true);
						for(Sto sto:stolovi){
							stoDao.persist(sto);
						}
						System.out.println("BROJ KREIRANIH PRAZNIH STOLOVA " + stolovi.size());
						request.setAttribute("stolovi", stolovi);
						request.getSession(true).setAttribute("restoran", restoran);
						getServletContext().getRequestDispatcher("/tables.jsp").forward(request, response);
					}
					else {
						request.getSession(true).setAttribute("restoran", restoranDao.findById(Integer.parseInt(id)));
						getServletContext().getRequestDispatcher("/createTables.jsp").forward(request, response);
					}
				}else {
					
					Restoran restoran = restoranDao.findById(Integer.parseInt(id));
					ArrayList<Sto> pronadjeniStolovi = prepareTables(kreiraniStolovi, restoran.getBrojRedovaRestorana(), restoran.getBrojKolonaRestorana(),  restoran);
					request.setAttribute("stolovi", pronadjeniStolovi);
					System.out.println("BROJ STOLOVA PRONADJENIH " + pronadjeniStolovi.size());
					request.getSession(true).setAttribute("restoran", restoran);
					request.setAttribute("first", "prvi");
					getServletContext().getRequestDispatcher("/tables.jsp").forward(request, response);
					
				}
				
				
			}
			
		} catch (ServletException e) {
			log.error(e);
			throw e;
		} catch (IOException e) {
			log.error(e);
			throw e;
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
		for(Sto sto:ret){
			System.out.println("STATUS " + sto.isSlobodan());
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
 
}
