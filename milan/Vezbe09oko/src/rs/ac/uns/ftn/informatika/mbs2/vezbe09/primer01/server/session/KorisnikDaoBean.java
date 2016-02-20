package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

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
	
	public boolean checkIfExists(String korisnickoIme){
		Query q = em.createQuery("SELECT k from Korisnik k WHERE k.korisnickoImeKorisnika like :korisnickoIme");
		q.setParameter("korisnickoIme", korisnickoIme);
		
		try{
			Korisnik k = (Korisnik)q.getSingleResult();
			if (k != null) {
				return true;
			}
		} catch (Exception e){
			return false;
		}
		return false;
	}

}
