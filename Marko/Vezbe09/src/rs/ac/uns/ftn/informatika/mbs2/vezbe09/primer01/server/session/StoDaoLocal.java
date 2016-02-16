package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;

public interface StoDaoLocal extends GenericDaoLocal<Sto, Integer>{
	
	public List<Sto> stoloviSaIdRestorana(Integer id);

}
