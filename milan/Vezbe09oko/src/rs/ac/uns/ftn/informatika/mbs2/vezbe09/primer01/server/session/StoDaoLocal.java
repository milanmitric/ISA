package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import javax.ejb.Local;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;

@Local
public interface StoDaoLocal extends GenericDaoLocal<Sto, Integer>{
	public List<Sto> stoloviSaIdRestorana(Integer id);
	
	public Sto stoRestoranaSaRedIKolona(Integer id, Integer red, Integer kolona);

}
