package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import javax.ejb.Local;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Prijatelj;

@Local
public interface PrijateljDaoLocal extends GenericDaoLocal<Prijatelj, Integer> {
	
	public List<Prijatelj> findFriend(Integer id);
	
	public Integer findFriendshipByFriends(Integer userId, Integer friendId);

}
