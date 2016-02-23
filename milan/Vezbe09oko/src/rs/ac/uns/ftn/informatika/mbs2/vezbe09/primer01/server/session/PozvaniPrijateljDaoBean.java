package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Stateless;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.PozvaniPrijatelj;

@Stateless
public class PozvaniPrijateljDaoBean extends GenericDaoBean<PozvaniPrijatelj, Integer> implements PozvaniPrijateljDaoLocal{

}
