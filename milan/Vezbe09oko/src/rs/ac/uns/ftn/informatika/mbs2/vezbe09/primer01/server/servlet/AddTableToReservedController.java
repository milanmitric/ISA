package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.StoDaoLocal;

public class AddTableToReservedController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(AddTableToReservedController.class);
	
	@EJB
	private StoDaoLocal stoDao;
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			if ((request.getSession().getAttribute("korisnik")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			String restoranId = null;
			String kolona = null;
			String red = null;
			if (request.getParameter("restoranId") != null && !request.getParameter("restoranId").equals("")){
				restoranId = request.getParameter("restoranId");
			}
			if (request.getParameter("kolona") != null && !request.getParameter("kolona").equals("")){
				kolona = request.getParameter("kolona");
			}
			if (request.getParameter("red") != null && !request.getParameter("red").equals("")){
				red = request.getParameter("red");
			}
			
			Restoran restoran;
			Sto sto = null;
			if (restoranId != null && red != null && kolona != null){
				restoran = restoranDao.findById((Integer.parseInt(restoranId)));
				try{
					sto = stoDao.stoRestoranaSaRedIKolona(Integer.parseInt(restoranId), Integer.parseInt(red),Integer.parseInt(kolona));
				}
				catch(Exception e){
				}
				
				
			}
			else {
				System.out.println("NEKO JE NULL : RED " + red + " KOLONA " + kolona + " ID " + restoranId);
			}
			
			if (sto != null){
				sto.setRezervisan(1);
				HttpSession session = request.getSession(true);
				ArrayList<Sto> stoloviZaRezervaciju;
				if (session.getAttribute("stoloviZaRezervaciju") == null){
					 stoloviZaRezervaciju = new ArrayList<>();
					 session.setAttribute("stoloviZaRezervaciju", stoloviZaRezervaciju);
				} else {
					stoloviZaRezervaciju = (ArrayList<Sto>) session.getAttribute("stoloviZaRezervaciju");
				}
				
				boolean sadrzi = false;
				int index = -1;
				for (int i = 0; i < stoloviZaRezervaciju.size();i++){
					if (stoloviZaRezervaciju.get(i).getKolona() == sto.getKolona()
					&&  stoloviZaRezervaciju.get(i).getRed() == sto.getRed()){
						sadrzi = true;
						index = i;
					}
				}
				
				if (sadrzi){
					System.out.println("SADRZI");
					System.out.println(stoloviZaRezervaciju.size());
					stoloviZaRezervaciju.remove(index);
					System.out.println(stoloviZaRezervaciju.size());
				} else{
					System.out.println("NE SADRZI");
					stoloviZaRezervaciju.add(sto);
				}
				
				request.setAttribute("restoranId", restoranId);
				getServletContext().getRequestDispatcher("/PrepareReservationController").forward(request, response);
			}
			
			
		} catch(Exception e){
			log.info(e.getStackTrace());
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
