package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Vozilo;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.MenadzerDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.StoDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.VoziloDaoLocal;

public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = -6495686002772669396L;
	
	private static Logger log = Logger.getLogger(DeleteController.class);
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	@EJB
	private StoDaoLocal stoDao;
	
	@EJB
	private MenadzerDaoLocal menadzerDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("admin")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			doDelete(getId(request), getTableName(request));
			
			if (getTableName(request).equals("restoran")){
				getServletContext().getRequestDispatcher("/ReadController").forward(request, response);
			}
			else if (getTableName(request).equals("menadzer")){
				getServletContext().getRequestDispatcher("/PrepareManagersController").forward(request, response);
			}
			
			
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
	
	/**
	 * Uzima id recorda koji brise.
	 * @param request Http request.
	 * @return Pronadjeni ID - null ako ga ne nadje.
	 */
	private String getId(HttpServletRequest request){
		
		String ret = null;
		ret = request.getParameter("restoran");
		if (ret != null){
			return ret;
		}
		ret = request.getParameter("sto");
		if (ret != null){
			return ret;
		}
		ret = request.getParameter("menadzer");
		if (ret != null){
			return ret;
		}
		return ret;
	}
	/**
	 * Uzima ime tabele iz koje se brise.
	 * @param request  Http request.
	 * @return Pronadjeno ime tabele - null ako ne nadje.
	 */
	private String getTableName(HttpServletRequest request){
		String ret = null;
		
		ret = request.getParameter("restoran");
		if (ret != null){
			return "restoran";
		}
		ret = request.getParameter("sto");
		if (ret != null){
			return "sto";
		}
		ret = request.getParameter("menadzer");
		if (ret != null){
			return "menadzer";
		}
		return ret;
	}
	
	/**
	 * Brise odgovarajuci record pozivajuci session bean za odgovarajucu tabelu.
	 * @param id Id recorda koji se brise.
	 * @param tableName Ime tabele iz koje se brise.
	 */
	private void doDelete(String id, String tableName){
		if (tableName.equals("restoran")){
			Restoran restoran = restoranDao.findById(Integer.parseInt(id));
			restoranDao.remove(restoran);
		} else if (tableName.equals("sto")){
			Sto sto = stoDao.findById(Integer.parseInt(id));
			stoDao.remove(sto);
		}else if (tableName.equals("menadzer")){
			Menadzer menadzer = menadzerDao.findById(Integer.parseInt(id));
			menadzerDao.remove(menadzer);
		}
		
	}
}
