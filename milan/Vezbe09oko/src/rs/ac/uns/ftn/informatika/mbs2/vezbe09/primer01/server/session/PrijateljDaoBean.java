package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Prijatelj;

@Stateless
public class PrijateljDaoBean extends GenericDaoBean<Prijatelj, Integer> implements PrijateljDaoLocal{

	@Override
	public List<Prijatelj> findFriend(Integer id) {
		Query q = em
				.createQuery("SELECT k FROM Prijatelj k WHERE k.korisnik.id like :id ORDER BY k.korisnik.imeKorisnika ASC , k.korisnik.prezimeKorisnika ASC");
		q.setParameter("id", id);
		List<Prijatelj> result = (List<Prijatelj>) q.getResultList();
		return result;
	}
	
	public Integer findFriendshipByFriends(Integer userId, Integer friendId){		
		Query q = em
				.createQuery("SELECT m FROM Prijatelj m WHERE m.korisnik.id like :userId AND m.prijatelj.id like :friendId");
		q.setParameter("userId", userId);
		q.setParameter("friendId", friendId);
		
		Prijatelj p = (Prijatelj)q.getSingleResult(); 
		return p.getId();
	}
	

}
