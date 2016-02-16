package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.OstvarenaPrijateljstva;

@Stateless
@Local(PrijateljDaoLocal.class)
public class PrijateljDaoBean extends GenericDaoBean<OstvarenaPrijateljstva, Integer>
	implements PrijateljDaoLocal{

	@SuppressWarnings("unchecked")
	public List<OstvarenaPrijateljstva> findFriends(Integer id) {
		
		Query q = em
				.createQuery("SELECT k FROM OstvarenaPrijateljstva k WHERE k.korisnik.id like :id ORDER BY k.korisnik.imeKorisnika ASC , k.korisnik.prezimeKorisnika ASC");
		q.setParameter("id", id);
		List<OstvarenaPrijateljstva> result = (List<OstvarenaPrijateljstva>) q.getResultList();
		return result;
	}

}
