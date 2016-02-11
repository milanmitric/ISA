package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.StavkaJelovnika;

@Stateless
@Local(StavkaJelovnikaDaoLocal.class)
public class StavkaJelovnikaDaoBean extends GenericDaoBean<StavkaJelovnika, Integer>
	implements StavkaJelovnikaDaoLocal {

}
