package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.MenadzerDaoLocal;

public class MenadzerLoginController extends HttpServlet{

	private static final long serialVersionUID = -5893000729135469455L;
	
	@EJB
	private MenadzerDaoLocal menadzerDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String korisnickoIme = request.getParameter("korisnickoIme");
			String lozinka = request.getParameter("lozinka");
			
			if ((korisnickoIme == null) || (korisnickoIme.equals("")) || (lozinka == null) || (lozinka.equals(""))) {
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
				return;
			}
			
			Menadzer korisnik = menadzerDao.findMenadzerSaKorisnickimImenomILozinkom(korisnickoIme, lozinka);
			
			if (korisnik != null) {	
				HttpSession session = request.getSession(true);
				if(korisnik.isMenadzerSistema())
				{
					session.setAttribute("admin", korisnik);
				}
				else
				{
					session.setAttribute("menadzer", korisnik);
				}
				getServletContext().getRequestDispatcher("/ReadController").forward(request, response);
			}
			
		} catch (EJBException e) {
			if (e.getCause().getClass().equals(NoResultException.class)) {
				getServletContext().getRequestDispatcher("/LoginController").forward(request, response);

			} else {
				throw e;
			}
		} catch (ServletException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
