package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;

@Stateless
@Local(KorisnikDaoLocal.class)
public class KorisnikDaoBean extends GenericDaoBean<Korisnik, Integer>
		implements KorisnikDaoLocal {

	public Korisnik findKorisnikSaKorisnickimImenomILozinkom(
			String korisnickoIme, String lozinka) {
		Query q = em
				.createNamedQuery("findKorisnikSaKorisnickimImenomILozinkom");
		q.setParameter("korisnickoIme", korisnickoIme);
		q.setParameter("lozinka", lozinka);
		Korisnik result = (Korisnik) q.getSingleResult();
		return result;
	}
	
	public Korisnik findKorisnikSaKorisnickimImenom(String korisnickoIme){
		Query q = em.createQuery("SELECT k FROM Korisnik k WHERE k.korisnickoImeKorisnika like :korisnickoIme");
		q.setParameter("korisnickoIme", korisnickoIme);
		Korisnik result = (Korisnik) q.getSingleResult();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Korisnik> findKorisnikSaImenom(String ime, Integer id) {
		Query q = em.createQuery("SELECT k FROM Korisnik k WHERE k.imeKorisnika like :ime AND k.id not like :id");
		q.setParameter("ime", ime);
		q.setParameter("id", id);
		List<Korisnik> result = (List<Korisnik>) q.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Korisnik> findKorisnikSaPrezimenom(String prezime, Integer id) {
		
		Query q = em.createQuery("SELECT k FROM Korisnik k WHERE k.prezimeKorisnika like :prezime AND k.id not like :id");
		q.setParameter("prezime", prezime);
		q.setParameter("id", id);
		List<Korisnik> result = (List<Korisnik>) q.getResultList();
		return result;
		
	}

	@SuppressWarnings("unchecked")
	public List<Korisnik> findKorisnikSaImenomIprezimenom(String ime,
			String prezime, Integer id) {
		Query q = em.createQuery("SELECT k FROM Korisnik k WHERE k.imeKorisnika like :ime AND k.prezimeKorisnika like :prezime AND k.id not like :id");
		q.setParameter("ime", ime);
		q.setParameter("prezime", prezime);
		q.setParameter("id", id);
		List<Korisnik> result = (List<Korisnik>) q.getResultList();
		return result;
	}

	

}
