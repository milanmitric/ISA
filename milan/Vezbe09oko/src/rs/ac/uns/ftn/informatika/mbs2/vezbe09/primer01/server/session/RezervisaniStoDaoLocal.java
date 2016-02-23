package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.RezervisaniSto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;

@Local
public interface RezervisaniStoDaoLocal extends GenericDaoLocal<RezervisaniSto, Integer>{
	
	public  List<Sto> getStoByRestoranAndRezervacija(Integer restoranId, ArrayList<Integer> rezervacijaId);

}
