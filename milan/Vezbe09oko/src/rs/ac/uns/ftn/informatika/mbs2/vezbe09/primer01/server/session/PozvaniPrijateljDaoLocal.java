package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Local;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.PozvaniPrijatelj;

@Local
public interface PozvaniPrijateljDaoLocal extends GenericDaoLocal<PozvaniPrijatelj,Integer>{

}
