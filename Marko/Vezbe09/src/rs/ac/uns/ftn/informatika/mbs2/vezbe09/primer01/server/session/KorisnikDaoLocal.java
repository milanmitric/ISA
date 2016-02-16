package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;

public interface KorisnikDaoLocal extends GenericDaoLocal<Korisnik, Integer> {

	public Korisnik findKorisnikSaKorisnickimImenomILozinkom(
			String korisnickoIme, String lozinka);
	
	public Korisnik findKorisnikSaKorisnickimImenom(String korisnickoIme);
	
	public List<Korisnik> findKorisnikSaImenom(String ime, Integer id);
	public List<Korisnik> findKorisnikSaPrezimenom(String prezime, Integer id);
	public List<Korisnik> findKorisnikSaImenomIprezimenom(String ime, String prezime, Integer id);
	

}
