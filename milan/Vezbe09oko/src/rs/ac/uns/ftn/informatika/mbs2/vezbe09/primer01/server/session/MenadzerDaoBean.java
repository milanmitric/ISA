package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;

@Stateless
public class MenadzerDaoBean extends GenericDaoBean<Menadzer, Integer> implements MenadzerDaoLocal{

	@Override
	public Menadzer findMenadzerByIdAndPass(String korisnickoIme, String lozinka) {
		System.out.println(korisnickoIme + lozinka);
		Query q = em.createNamedQuery("findMenadzerByIdAndPass").setParameter("korisnickoIme", korisnickoIme).setParameter("lozinka", lozinka);
		Menadzer m = (Menadzer) q.getSingleResult();
		return m;
	}
	

}
