package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Jelo;

@Stateless
public class JeloDaoBean extends GenericDaoBean<Jelo, Integer> implements JeloDaoLocal{ 
	
	public List<Jelo> findByRestoran(Integer restoranId){
		Query q = em.createQuery("Select j FROM Jelo j WHERE j.restoran.id like: id");
		q.setParameter("id", restoranId);
		
		@SuppressWarnings("unchecked")
		List<Jelo> ret = (List<Jelo>)q.getResultList();
		
		return ret;
	}

}
