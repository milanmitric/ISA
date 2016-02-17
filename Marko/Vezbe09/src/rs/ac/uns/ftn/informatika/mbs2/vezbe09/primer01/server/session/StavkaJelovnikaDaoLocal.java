package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.StavkaJelovnika;

public interface StavkaJelovnikaDaoLocal extends GenericDaoLocal<StavkaJelovnika, Integer> {

	public List<StavkaJelovnika> findStavkeSaIDJelovnika(Integer id);
}
