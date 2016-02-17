package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;

public interface MenadzerDaoLocal extends GenericDaoLocal<Menadzer, Integer> {

	public Menadzer findMenadzerSaKorisnickimImenomILozinkom(
			String korisnickoIme, String lozinka);
	
	public Menadzer findMenadzerSaKorisnickimImenom(
			String korisnickoIme);

}
