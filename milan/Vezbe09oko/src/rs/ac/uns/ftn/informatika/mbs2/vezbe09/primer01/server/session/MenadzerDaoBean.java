package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Menadzer;

@Stateless
public class MenadzerDaoBean extends GenericDaoBean<Menadzer, Integer> implements MenadzerDaoLocal{

	@Override
	public Menadzer findMenadzerByIdAndPass(String korisnickoIme, String lozinka) {		
		Query q = em.createNamedQuery("findMenadzerByIdAndPass").setParameter("korisnickoIme", korisnickoIme).setParameter("lozinka", lozinka);
		Menadzer m = (Menadzer) q.getSingleResult();
		return m;
	}
	public boolean checkIfExists(String korisnickoIme){
		Query q = em.createQuery("SELECT m from Menadzer m WHERE m.korisnickoIme like :korisnickoIme");
		q.setParameter("korisnickoIme", korisnickoIme);
		System.out.println(korisnickoIme +" IZ MENADZERDAO");
		try{
			Menadzer m = (Menadzer)q.getSingleResult();
			if (m != null) {
				System.out.println("POSTOJI");
				return true;
			}
		} catch (Exception e){
			System.out.println("NE POSTOJI");
			return false;
		}
		return false;
	}

}
