package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.ArrayList;

import javax.ejb.Local;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;

@Local
public interface NapraviRezervacijuDaoLocal {
	
	public void dodajRezervaciju(ArrayList<Sto> stolovi,Korisnik korisnik,String datum,Integer vrijeme,Integer trajanje,ArrayList<Korisnik> prijatelji );

}
