package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.OstvarenaPrijateljstva;


public interface PrijateljDaoLocal extends GenericDaoLocal<OstvarenaPrijateljstva, Integer>{

	public List<OstvarenaPrijateljstva> findFriends(Integer id);
}
