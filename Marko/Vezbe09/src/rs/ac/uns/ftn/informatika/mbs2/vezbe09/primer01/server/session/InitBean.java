package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.Date;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Gorivo;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Jelovnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menjac;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.OstvarenaPrijateljstva;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Restoran;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Vozilo;

@Stateless
@Remote(Init.class)
public class InitBean implements Init {

	@PersistenceContext(unitName = "Vezbe09")
	EntityManager em;
	
	public void init() {
		Korisnik korisnik = new Korisnik("kupac", "kupac", "kupac", "kupac");
		em.persist(korisnik);
		
		Korisnik korisnik2 = new Korisnik("marko", "marko", "marko", "marko");
		em.persist(korisnik2);
		
		Korisnik korisnik3 = new Korisnik("stefan", "stefan", "stefan", "stefan");
		em.persist(korisnik3);
		
		OstvarenaPrijateljstva prijatelji = new OstvarenaPrijateljstva(korisnik, korisnik2);
		em.persist(prijatelji);
		
		OstvarenaPrijateljstva prijatelji2 = new OstvarenaPrijateljstva(korisnik2, korisnik3);
		em.persist(prijatelji2);

		OstvarenaPrijateljstva prijatelji3 = new OstvarenaPrijateljstva(korisnik2, korisnik);
		em.persist(prijatelji3);
		
		Gorivo dizel = new Gorivo();
		dizel.setTipGoriva("Dizel");
		em.persist(dizel);
		
		Gorivo benzin = new Gorivo();
		benzin.setTipGoriva("Benzin");
		em.persist(benzin);
		
		Menjac automatski = new Menjac();
		automatski.setTipMenjaca("Automatski");
		em.persist(automatski);
		
		Menjac rucni = new Menjac();
		rucni.setTipMenjaca("Manuelni");
		em.persist(rucni);
		
		Long a= (long) 4;
		Date date = new Date();
		Vozilo voz = new Vozilo(4,4,4,4,date,4,dizel,4,rucni,"vozilo1",a,1,1,true);
		em.persist(voz);
		
		Jelovnik jelovnik = new Jelovnik();
		jelovnik.setNazivJelovnika("Jelovnik1");
		
		
		Restoran restoran = new Restoran("Restoran1","Adresa1",
				"restoran@gmail.com",0601234321,200, jelovnik, null, "Domaca kuhinja" , null, null);
		em.persist(restoran);
		
		Menadzer menadzerSistema = new Menadzer("Pera","Peric","pera","pera", null, null, true);
		Menadzer menadzerRestorana1 = new Menadzer("Sima", "Simic", "sima", "sima", restoran, null, false );
		
		em.persist(menadzerSistema);
		em.persist(menadzerRestorana1);
		
		jelovnik.setMenadzer(menadzerRestorana1);
		em.persist(jelovnik);
		
		
	}
}
