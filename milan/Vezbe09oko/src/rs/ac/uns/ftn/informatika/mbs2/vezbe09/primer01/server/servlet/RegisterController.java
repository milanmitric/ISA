package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.KorisnikDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.MenadzerDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.TestBean;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.TestLocal;

public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	private static Logger log = Logger.getLogger(RegisterController.class);

	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private MenadzerDaoLocal menadzerDao;
	
	@EJB
	private TestLocal testDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String korisnickoIme = request.getParameter("korisnickoIme");
		String lozinka1 = request.getParameter("lozinka1");
		String lozinka2 = request.getParameter("lozinka2");
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		
		try {
			if (korisnikDao.checkIfExists(korisnickoIme) || menadzerDao.checkIfExists(korisnickoIme)  ){
				String errorMessage = "Postoji korisnik sa tim korisnickim imenom.";
				request.setAttribute("errormessage", errorMessage);
				RequestDispatcher disp = request.getRequestDispatcher("./register.jsp");
				disp.forward(request, response);
			} else if(!lozinka1.equals(lozinka2)){
				String errorMessage = "Lozinke se ne poklapaju";
				request.setAttribute("errormessage", errorMessage);
				RequestDispatcher disp = request.getRequestDispatcher("./register.jsp");
				disp.forward(request, response);
				
			} else { 
			
				StringBuilder message = new StringBuilder();
				message.append("http://localhost:8080/Vezbe09/");
				message.append("AddUserToDatabaseController?"
						+ "ime="+ime
						+ "&prezime="+prezime
						+ "&korisnickoIme="+korisnickoIme
						+ "&lozinka="+lozinka1);
				try{
					testDao.test(message.toString());
				} catch (Exception k){
					System.out.println(k.getMessage());
					System.out.println(k.getCause());
					System.out.println(k.getStackTrace());
				}
				
				request.setAttribute("poruka", "Uspesno ste registrovali! Potvrdite na mejl rezervaciju!");
				
				getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
				
				
			} 
			
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
