package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.PozvaniPrijatelj;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Rezervacija;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.RezervisaniSto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;

@Stateless
public class NapraviRezervacijuDaoBean implements NapraviRezervacijuDaoLocal {
	
	@EJB
	private RezervacijaDaoLocal rezervacijaDao; 

	@EJB
	private RezervisaniStoDaoLocal rezervisaniStoDao;
	
	@EJB
	private PozvaniPrijateljDaoLocal pozvaniPrijateljiDao;
	
	@PersistenceUnit(unitName = "Vezbe091")
	protected EntityManagerFactory factory;

	@EJB
	private TestLocal testDao;
	
	public void dodajRezervaciju(ArrayList<Sto> stolovi,Korisnik korisnik,String datum,Integer vrijeme,Integer trajanje,ArrayList<Korisnik> prijatelji ){
		
		EntityManager manager = factory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		Connection connection = null;
		
		try{
			
			Restoran restoran = stolovi.get(0).getRestoran();
			
			Rezervacija rezervacija = new Rezervacija();
			rezervacija.setDatum(datum);
			rezervacija.setKorisnik(korisnik);
			rezervacija.setSat(vrijeme);
			rezervacija.setTrajanje(trajanje);
			rezervacija.setKorisnik(korisnik);
			rezervacija.setRestoran(restoran);
			
			ArrayList<RezervisaniSto> rezervisaniStolovi = new ArrayList<>(stolovi.size());
			
			for(Sto sto: stolovi){
				RezervisaniSto rezSto = new RezervisaniSto();
				rezSto.setSto(sto);
				rezSto.setRezervacija(rezervacija);
				rezervisaniStolovi.add(rezSto);
			}
			System.out.println("STOLOVI " + rezervisaniStolovi.size());
			ArrayList<PozvaniPrijatelj> pozvaniPrijatelji = new ArrayList<>(prijatelji.size());
			
			for(Korisnik k: prijatelji){
				PozvaniPrijatelj p = new PozvaniPrijatelj();
				p.setKorisnik(korisnik);
				p.setRezervacija(rezervacija);
				pozvaniPrijatelji.add(p);
			}
			System.out.println("PRIJATELJI " + pozvaniPrijatelji.size());
			connection = manager.unwrap(java.sql.Connection.class);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			tx.begin();
			rezervacijaDao.persist(rezervacija);
			
			for(RezervisaniSto sto: rezervisaniStolovi){
				rezervisaniStoDao.persist(sto);
			}
			tx.commit();
			
			StringBuilder message = new StringBuilder();
			message.append("http://localhost:8080/Vezbe09/");
			message.append("ConfirmInvitationController?");
			
			StringBuilder email;
			for(Korisnik prijatelj: prijatelji){
				email = new StringBuilder();
				email.append(message
						+ "p="+prijatelj.getId()
						+ "&r="+rezervacija.getId()
						+ "&korisnickoIme="+prijatelj.getKorisnickoImeKorisnika());
				testDao.test(email.toString());
			}
			

		}
		catch(SQLException e){
			tx.rollback();
			System.out.println("SQL GRESKA");
		}
		catch(EJBException e){
			
			tx.rollback();
			throw e;
		}
		
	}

}
