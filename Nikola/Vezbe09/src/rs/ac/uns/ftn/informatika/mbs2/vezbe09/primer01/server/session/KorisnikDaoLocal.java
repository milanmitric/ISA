package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.Set;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;

public interface KorisnikDaoLocal extends GenericDaoLocal<Korisnik, Integer> {

	public Korisnik findKorisnikSaKorisnickimImenomILozinkom(
			String korisnickoIme, String lozinka);
	
	public Set<Korisnik> findPrijatelje(Integer id);

}
