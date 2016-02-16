package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.StoDaoLocal;

public class AktivanStoController extends HttpServlet{

	private static final long serialVersionUID = -5171847823323950807L;
	
	@EJB
	private StoDaoLocal stoDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer sto_id = Integer.parseInt(request.getParameter("stoID"));
		
		Sto sto = stoDao.findById(sto_id);
		
		if(sto.isAktivanSto()){
			sto.setAktivanSto(false);
		}else{
			sto.setAktivanSto(true);
		}
		
		
		stoDao.merge(sto);
		
		if(request.getServletContext().getAttribute("stolovi") != null){
			request.getServletContext().setAttribute("stolovi", null);
		}
		
		List<Sto> stolovi = (List<Sto>) stoDao.stoloviSaIdRestorana(sto.getRestoran().getId());
		
		request.getServletContext().setAttribute("stolovi", stolovi);
		
		
		RequestDispatcher disp = request.getRequestDispatcher("./PrikazRestorana.jsp");
		disp.forward(request, response);
		
		
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
