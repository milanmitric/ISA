package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Rezervacija;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;

@Stateless
public class RezervacijaDaoBean extends GenericDaoBean<Rezervacija, Integer> implements RezervacijaDaoLocal{
		public List<Sto> getStoByDateAndTime(String datum, Integer vrijeme, Integer trajanje, Integer restoranId){
			
			Integer pocinje = vrijeme;
			Integer zavrsava = pocinje + trajanje;
			Query q = em.createQuery("SELECT s.sto FROM RezervisaniSto s WHERE s.sto.restoran.id like :restoranId and "
					 + " s.rezervacija.id in (SELECT r.id FROM Rezervacija r where r.datum like  :datum "
					 + "and (r.sat between :pocinje and :zavrsava "
					 + "or r.sat <= :pocinje and r.sat + r.trajanje >= :zavrsava "
					 + "or r.sat + r.trajanje between :pocinje and :zavrsava))");
			q.setParameter("datum", datum);
			q.setParameter("pocinje", pocinje);
			q.setParameter("zavrsava", zavrsava);
			q.setParameter("restoranId", restoranId);
			
			List<Sto> ret = (List<Sto>) q.getResultList();
			return ret;
		}
		
	
		public ArrayList<Rezervacija> getByUser(Integer userId){
			
			
			Query q = em.createQuery("SELECT r FROM Rezervacija r WHERE r.korisnik.id like :userId");
			q.setParameter("userId", userId);
			
			@SuppressWarnings("unchecked")
			ArrayList<Rezervacija> kreiraneRezervacije = new ArrayList<>((List<Rezervacija>)q.getResultList());
			Query q1 = em.createQuery("SELECT p.rezervacija FROM PozvaniPrijatelj p WHERE p.korisnik.id like :userId");
			q1.setParameter("userId", userId);
			ArrayList<Rezervacija> pozvaneRezervacije = new ArrayList<>((List<Rezervacija>)q1.getResultList());
			
			
			kreiraneRezervacije.removeAll(pozvaneRezervacije);
			kreiraneRezervacije.addAll(pozvaneRezervacije);
			
			return kreiraneRezervacije;
		}

		
}
