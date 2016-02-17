package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;

@Stateless
@Local(MenadzerDaoLocal.class)
public class MenadzerDaoBean extends GenericDaoBean<Menadzer, Integer>
	implements MenadzerDaoLocal{

	public Menadzer findMenadzerSaKorisnickimImenomILozinkom(
			String korisnickoIme, String lozinka) {
		
		Query q = em
				.createNamedQuery("findMenadzerSaKorisnickimImenomILozinkom");
		q.setParameter("korisnickoIme", korisnickoIme);
		q.setParameter("lozinka", lozinka);
		Menadzer result = (Menadzer) q.getSingleResult();
		return result;
	}
	
	public Menadzer findMenadzerSaKorisnickimImenom(String korisnickoIme){
		Query q = em.createQuery("SELECT k FROM Menadzer k WHERE k.korisnickoImeMenadzera like :korisnickoIme");
		q.setParameter("korisnickoIme", korisnickoIme);
		Menadzer result = (Menadzer) q.getSingleResult();
		return result;
	}
	

}
