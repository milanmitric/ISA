package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Rezervacija;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;

@Local
public interface RezervacijaDaoLocal extends GenericDaoLocal<Rezervacija, Integer>{
	

	public ArrayList<Rezervacija> getByUser(Integer userId);
	public List<Sto> getStoByDateAndTime(String datum, Integer vrijeme, Integer trajanje, Integer restoranId);
}
