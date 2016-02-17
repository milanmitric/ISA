package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.RezervisaniStolovi;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;

public class RezervisiDaoBean extends GenericDaoBean<RezervisaniStolovi, Integer>
	implements RezervisiDaoLocal{
	
	@SuppressWarnings("unchecked")
	public List<Sto> vratiZauzete(Integer id, String datum, Integer pocinje, Integer trajanje){
		
		Integer zavrsava = pocinje + trajanje;
		
		Query q = em
				.createQuery("SELECT s.sto FROM RezervisaniStolovi s "
						+ "WHERE s.sto.restoran.id like :id AND s.datumRezervacije like: datum "
						+ "AND s.satRezervacije BETWEEN pocinje AND zavrsava "
						+ "AND s.satRezervacije + s.trajanjeRezervacije BETWEEN pocinje AND zavrsava");
		q.setParameter("id", id);
		q.setParameter("datum", datum);
		q.setParameter("pocinje", pocinje);
		q.setParameter("zavrsava", zavrsava);
		List<Sto> result = (List<Sto>) q.getResultList();
		return result;
	}

}
