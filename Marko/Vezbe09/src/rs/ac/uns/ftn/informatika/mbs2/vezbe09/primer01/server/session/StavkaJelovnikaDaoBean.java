package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.StavkaJelovnika;

@Stateless
@Local(StavkaJelovnikaDaoLocal.class)
public class StavkaJelovnikaDaoBean extends GenericDaoBean<StavkaJelovnika, Integer>
	implements StavkaJelovnikaDaoLocal {

	@SuppressWarnings("unchecked")
	@Override
	public List<StavkaJelovnika> findStavkeSaIDJelovnika(Integer id) {
		
		Query q = em
				.createNamedQuery("findStavkeSaIDJelovnika");
		q.setParameter("id", id);
		List<StavkaJelovnika> result = (List<StavkaJelovnika>) q.getResultList();
		return result;
	}

}
