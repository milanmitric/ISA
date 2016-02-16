package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Gorivo;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Jelovnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menjac;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Vozilo;

@Stateless
@Remote(Init.class)
public class InitBean implements Init {

	@PersistenceContext(unitName = "Vezbe09")
	EntityManager em;
	
	public void init() {
		
		Korisnik korisnik = new Korisnik("Admin", "Admin", "admin", "admin");
		em.persist(korisnik);
		
		
		Jelovnik jelovnik = new Jelovnik();
		jelovnik.setNazivJelovnika("Jelovnik1");
		em.persist(jelovnik);
		
		Restoran restoran = new Restoran();
		restoran.setAdresaRestoran("Sremska 10");
		restoran.setNazivRestorana("Dva stapica");
		restoran.setMailRestoran("dvastapica@office.com");
		restoran.setTelefonRestoran("021453143");
		restoran.setUdaljenostRestoran(12);
		restoran.setJelovnik(jelovnik);
		
		HashSet<Sto> stolovi = new HashSet<Sto>();
		
		for(int i = 0; i < 10; i++){
			Sto tmp = new Sto();
			tmp.setRedniBroj(i);
			tmp.setRed(i % 5);
			tmp.setKolona(i / 5);
			tmp.setRestoran(restoran);
			stolovi.add(tmp);
		}
		
		restoran.setStolovi(stolovi);
		
		em.persist(restoran);
		
	}
}
