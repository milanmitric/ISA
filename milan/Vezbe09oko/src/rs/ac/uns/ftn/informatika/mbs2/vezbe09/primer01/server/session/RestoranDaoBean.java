package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;

@Stateless
@Local(RestoranDaoLocal.class)
public class RestoranDaoBean extends GenericDaoBean<Restoran, Integer> 
	implements RestoranDaoLocal{
	
	@Override
	public void remove(Restoran r) {
		r = em.merge(r);
		//r.getJelovnik().getRestorani().remove(r);
		em.remove(r);		
	}

}
