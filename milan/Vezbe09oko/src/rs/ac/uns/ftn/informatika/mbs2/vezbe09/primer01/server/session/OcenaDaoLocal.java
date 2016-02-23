package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import javax.ejb.Local;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Ocena;

@Local
public interface OcenaDaoLocal extends GenericDaoLocal<Ocena, Integer>{
	
	public Double findAvgByRestoran(Integer restoranId);
	
	public Double findAvgFromFriendsByRestoran (Integer restoranId, Integer korisnikId);
	
	public List<Ocena> findByUser(Integer userId);

}
