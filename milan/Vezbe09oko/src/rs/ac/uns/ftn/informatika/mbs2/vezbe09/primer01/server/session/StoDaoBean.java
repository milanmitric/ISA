package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;

@Stateless
public class StoDaoBean extends GenericDaoBean<Sto, Integer> implements StoDaoLocal{
	public void remove(Sto s) {
		s = em.merge(s);
		s.getRestoran().getStolovi().remove(s);
		em.remove(s);
	}

}
