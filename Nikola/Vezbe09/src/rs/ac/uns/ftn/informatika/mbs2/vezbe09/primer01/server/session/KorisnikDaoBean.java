package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	
	public Set<Korisnik> findPrijatelje(Integer id) {
		Query q = em.createNativeQuery("SELECT k2.* FROM Korisnik k1 INNER JOIN Prijateljstva p ON k1.korisnik_id = p.prijatelj1 INNER JOIN Korisnik k2 ON p.prijatelj2 = k2.korisnik_id WHERE k1.korisnik_id = ?");
		q.setParameter(1, id);
		Set<Korisnik> result = new HashSet<Korisnik>(q.getResultList());
		return result;
	}

}
