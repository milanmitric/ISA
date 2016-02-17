package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;

public interface RestoranDaoLocal extends GenericDaoLocal<Restoran, Integer>{
	
	public List<Restoran> findAllOrederedName();
	
	public List<Restoran> findAllbyTip(String tip);
	
	public List<Restoran> findAllbyName(String naziv);
	public List<Restoran> findAllbyTipAndName(String tip , String naziv);

}
