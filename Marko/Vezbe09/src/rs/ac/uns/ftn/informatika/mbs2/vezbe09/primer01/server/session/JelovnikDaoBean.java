package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Jelovnik;

@Stateless
@Local(JelovnikDaoLocal.class)
public class JelovnikDaoBean extends GenericDaoBean<Jelovnik, Integer> 
	implements JelovnikDaoLocal{

}
