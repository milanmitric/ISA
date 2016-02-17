package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Local;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;

@Local
public interface MenadzerDaoLocal extends GenericDaoLocal<Menadzer,Integer>{
	
	public Menadzer findMenadzerByIdAndPass(String korisnickoIme, String lozinka);

}
