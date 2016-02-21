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
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menjac;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Prijatelj;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Vozilo;

@Stateless
@Remote(Init.class)
public class InitBean implements Init {

	@PersistenceContext(unitName = "Vezbe09")
	EntityManager em;
	
	public void init() {
		
		Menadzer menadzer = new Menadzer();
		menadzer.setIme("Admin");
		menadzer.setPrezime("Admin");
		menadzer.setKorisnickoIme("admin");
		menadzer.setLozinka("admin");
		menadzer.setSistemMenadzer(true);
		em.persist(menadzer);
		
		
		
		Korisnik korisnik = new Korisnik();
		korisnik.setImeKorisnika("Pera");
		korisnik.setPrezimeKorisnika("Peric");
		korisnik.setKorisnickoImeKorisnika("pera");
		korisnik.setLozinkaKorisnika("pera");
		em.persist(korisnik);
		
		
		Korisnik korisnik2 = new Korisnik();
		korisnik2.setImeKorisnika("Mika");
		korisnik2.setPrezimeKorisnika("Mikic");
		korisnik2.setKorisnickoImeKorisnika("mika");
		korisnik2.setLozinkaKorisnika("mika");
		em.persist(korisnik2);
		
		Prijatelj prijatelj = new Prijatelj();
		prijatelj.setKorisnik(korisnik);
		prijatelj.setPrijatelj(korisnik2);
		em.persist(prijatelj);
		
		
		Restoran restoran = new Restoran();
		restoran.setAdresaRestorana("Sremska 10");
		restoran.setNazivRestorana("Dva stapica");
		restoran.setMailRestorana("dvastapica@office.com");
		restoran.setTelefonRestorana("021453143");
		restoran.setUdaljenostRestorana(12);

		/*
		HashSet<Sto> stolovi = new HashSet<Sto>();
		
		for(int i = 0; i < 10; i++){
			Sto tmp = new Sto();
			tmp.setRed(i % 5);
			tmp.setKolona(i / 5);
			tmp.setRestoran(restoran);
			stolovi.add(tmp);
		}
		
		restoran.setStolovi(stolovi);*/
		
		em.persist(restoran);
		
		
		Menadzer m = new Menadzer();
		m.setKorisnickoIme("zika");
		m.setLozinka("zika");
		m.setRestoran(restoran);
		em.persist(m);
	}
}
