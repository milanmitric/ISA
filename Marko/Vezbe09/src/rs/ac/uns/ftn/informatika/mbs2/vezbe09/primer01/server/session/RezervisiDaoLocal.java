package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.RezervisaniStolovi;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;

public interface RezervisiDaoLocal extends GenericDaoLocal<RezervisaniStolovi, Integer>{
	
	public List<Sto> vratiZauzete(Integer id, String datum, Integer pocinje, Integer trajanje);

}
