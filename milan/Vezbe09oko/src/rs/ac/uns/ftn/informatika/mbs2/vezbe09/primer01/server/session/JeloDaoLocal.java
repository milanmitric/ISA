package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import javax.ejb.Local;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Jelo;

@Local
public interface JeloDaoLocal extends GenericDaoLocal<Jelo, Integer>{
	
	public List<Jelo> findByRestoran(Integer restoranId);

}
