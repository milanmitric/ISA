package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.RestoranDaoLocal;


public class PretragaRestoranaController extends HttpServlet{

	private static final long serialVersionUID = 6890849759742368694L;
	
	@EJB
	private RestoranDaoLocal restoranDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String naziv = request.getParameter("naziv");
		String tip = request.getParameter("tip");
		
		boolean imaNaziv = false;
		boolean imaTip = false;
		
		if(naziv!=null && !naziv.equals("")){
			imaNaziv = true;
		}
		if(tip!=null && !tip.equals("")){
			imaTip = true;
		}
		
		if(!imaNaziv && !imaTip){
			request.getServletContext().setAttribute("restorani", restoranDao.findAllOrederedName());
			RequestDispatcher disp = request.getRequestDispatcher("./read.jsp");
			disp.forward(request, response);
			return;
		}else if(imaNaziv && !imaTip){
			request.getServletContext().setAttribute("restorani", restoranDao.findAllbyName(naziv));
			RequestDispatcher disp = request.getRequestDispatcher("./read.jsp");
			disp.forward(request, response);
			return;
			
		}else if(!imaNaziv && imaTip){
			request.getServletContext().setAttribute("restorani", restoranDao.findAllbyTip(tip));
			RequestDispatcher disp = request.getRequestDispatcher("./read.jsp");
			disp.forward(request, response);
			return;
			
		}else if(imaNaziv && imaTip){
			request.getServletContext().setAttribute("restorani", restoranDao.findAllbyTipAndName(tip, naziv));
			RequestDispatcher disp = request.getRequestDispatcher("./read.jsp");
			disp.forward(request, response);
			return;
			
		}
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
