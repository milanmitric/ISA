package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Ocena;

@Stateless
public class OcenaDaoBean extends GenericDaoBean<Ocena, Integer> implements OcenaDaoLocal{ 
	
	public Double findAvgByRestoran(Integer restoranId){
		
		String queryText = "SELECT o.ocena FROM Ocena o WHERE o.rezervacija.restoran.id like :restoranId"; 
		Query q= em.createQuery(queryText);
		q.setParameter("restoranId", restoranId);
		Double ret = -1.0d;
		ArrayList<Integer> ocene = new ArrayList<>();
		try{
			ocene = new ArrayList<Integer>((List<Integer>)q.getResultList());
			ret = 0d;
		} catch(EJBException e){
			ret = -1.0;
		}
		 
		for(Integer ocena : ocene){
			ret+= ocena;
		}
		
		if (!ret.equals(-1.0) && ocene.size() != 0){
			ret /= ocene.size();
		}
		
		
		return ret;
	}
	
	public List<Ocena> findByUser(Integer userId){
		
		String queryText = "SELECT o FROM Ocena o where o.korisnik.id like :userId";
		Query q = em.createQuery(queryText);
		q.setParameter("userId", userId);
		
		@SuppressWarnings("unchecked")
		List<Ocena> ret  = (List<Ocena>)q.getResultList();
		return ret;
		
	}

	public Double findAvgFromFriendsByRestoran (Integer restoranId, Integer korisnikId){
		
		String queryText = "SELECT o.ocena FROM Ocena o WHERE o.rezervacija.restoran.id like :restoranId and (o.korisnik.id like :korisnikId"
				+ " or o.korisnik.id in (SELECT p.prijatelj.id FROM Prijatelj p WHERE p.korisnik.id like :korisnikId))";
		
		Query q= em.createQuery(queryText);
		q.setParameter("restoranId", restoranId);
		q.setParameter("korisnikId", korisnikId);
		
		Double ret = -1.0d;
		ArrayList<Integer> ocene = new ArrayList<>();
		try{
			ocene = new ArrayList<Integer>((List<Integer>)q.getResultList());
			ret = 0d;
		} catch(EJBException e){
			ret = -1.0;
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			System.out.println(e.getCause());
		}
		 
		System.out.println(ocene.size());
		for(Integer ocena : ocene){
			ret+= ocena;
		}
		
		if (!ret.equals(-1.0) && ocene.size() != 0){
			ret /= ocene.size();
		}
		return ret;
	}
}
