package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;

@Stateless
public class StoDaoBean extends GenericDaoBean<Sto, Integer> implements StoDaoLocal{
	public void remove(Sto s) {
		s = em.merge(s);
		s.getRestoran().getStolovi().remove(s);
		em.remove(s);
	}
	
	@SuppressWarnings("unchecked")
	 public List<Sto> stoloviSaIdRestorana(Integer id) {
	  
	  Query q = em.createQuery("SELECT k FROM Sto k WHERE k.restoran.id like :id");
	  q.setParameter("id", id);
	  List<Sto> result = (List<Sto>) q.getResultList();
	  return result;
	 }
	
	public Sto stoRestoranaSaRedIKolona(Integer id, Integer red, Integer kolona){
		Query q = em.createQuery("SELECT s FROM Sto s WHERE s.restoran.id like :id AND s.red like :red AND s.kolona like :kolona");
		  q.setParameter("id", id);
		  q.setParameter("red",red);
		  q.setParameter("kolona", kolona);
		  Sto result = (Sto) q.getSingleResult();
		  return result;
	}

}
