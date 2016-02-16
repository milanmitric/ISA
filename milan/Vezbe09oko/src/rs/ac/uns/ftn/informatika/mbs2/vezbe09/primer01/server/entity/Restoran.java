package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restoran")
public class Restoran implements Serializable{


	private static final long serialVersionUID = 4815934546041729723L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column( unique = false, nullable = false)
	private String nazivRestorana;

	@Column(unique = false, nullable = false)
	private String adresaRestoran;
	
	@Column( unique = false, nullable = false)
	private String mailRestoran;
	
	@Column(unique = false, nullable = false)
	private String telefonRestoran;
	
	@Column(unique = false, nullable = false)
	private Integer udaljenostRestoran;
	
	@ManyToOne
	@JoinColumn(name = "jelovnik_fk", nullable = false)
	private Jelovnik jelovnik;

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "restoran")
	private Set<Sto> stolovi = new  HashSet<>();
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazivRestorana() {
		return nazivRestorana;
	}

	public void setNazivRestorana(String nazivRestorana) {
		this.nazivRestorana = nazivRestorana;
	}

	public String getAdresaRestoran() {
		return adresaRestoran;
	}

	public void setAdresaRestoran(String adresaRestoran) {
		this.adresaRestoran = adresaRestoran;
	}

	public String getMailRestoran() {
		return mailRestoran;
	}

	public void setMailRestoran(String mailRestoran) {
		this.mailRestoran = mailRestoran;
	}

	public String getTelefonRestoran() {
		return telefonRestoran;
	}

	public void setTelefonRestoran(String telefonRestoran) {
		this.telefonRestoran = telefonRestoran;
	}

	public Integer getUdaljenostRestoran() {
		return udaljenostRestoran;
	}

	public void setUdaljenostRestoran(Integer udaljenostRestoran) {
		this.udaljenostRestoran = udaljenostRestoran;
	}

	public Jelovnik getJelovnik() {
		return jelovnik;
	}

	public void setJelovnik(Jelovnik jelovnik) {
		this.jelovnik = jelovnik;
	}

	public Restoran() {
		super();
	}


	public Set<Sto> getStolovi() {
		return stolovi;
	}

	public void setStolovi(Set<Sto> stolovi) {
		this.stolovi = stolovi;
	}
}
