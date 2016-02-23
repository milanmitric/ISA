package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.RezervisaniSto;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Sto;

@Stateless
public class RezervisaniStoDaoBean extends GenericDaoBean<RezervisaniSto,Integer> implements RezervisaniStoDaoLocal{
	
	public  List<Sto> getStoByRestoranAndRezervacija(Integer restoranId, ArrayList<Integer> rezervacijaId){
		
		Query q = em.createQuery("SELECT s.sto FROM RezervisaniSto s WHERE s.sto.restoran.id like :restoranId and "
								 + " s.rezervacija.id in (SELECT r.id FROM Rezervacija r where r.datum like  :datum "
								 + "and r.sat between :pocinje and :zavrsava "
								 + "and r.sat + r.trajanje between :pocinje and :zavrsava)");
		q.setParameter("restoranId", restoranId);
		q.setParameter("rezervacijaId", rezervacijaId);
		
		List<RezervisaniSto> ret = (List<RezervisaniSto>) q.getResultList();
		
		return null;
		
	}

}
