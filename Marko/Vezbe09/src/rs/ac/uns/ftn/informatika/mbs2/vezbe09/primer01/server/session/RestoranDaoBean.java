package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;

@Stateless
@Local(RestoranDaoLocal.class)
public class RestoranDaoBean extends GenericDaoBean<Restoran, Integer> 
	implements RestoranDaoLocal{
	
	public void remove(Restoran r) {
		
		r = em.merge(r);
		r.getJelovnik().getRestorani().remove(r);
		em.remove(r);
	}
	
	@SuppressWarnings("unchecked")
	public List<Restoran> findAllOrederedName() {
		Query q = em.createQuery("SELECT r FROM Restoran r ORDER BY r.nazivRestorana ASC");
		List<Restoran> result = q.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Restoran> findAllbyName(String naziv) {
		Query q = em.createQuery("SELECT r FROM Restoran r WHERE r.nazivRestorana like :naziv ORDER BY r.nazivRestorana ASC");
		q.setParameter("naziv", naziv);
		List<Restoran> result = q.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Restoran> findAllbyTip(String tip) {
		Query q = em.createQuery("SELECT r FROM Restoran r WHERE r.tipRestoran like :tip ORDER BY r.nazivRestorana ASC");
		q.setParameter("tip", tip);
		List<Restoran> result = q.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Restoran> findAllbyTipAndName(String tip , String naziv) {
		Query q = em.createQuery("SELECT r FROM Restoran r WHERE r.tipRestoran like :tip AND r.nazivRestorana like :naziv ORDER BY r.nazivRestorana ASC");
		q.setParameter("tip", tip);
		q.setParameter("naziv", naziv);
		List<Restoran> result = q.getResultList();
		return result;
	}
	

}
