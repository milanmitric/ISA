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
	private String adresaRestorana;
	
	@Column( unique = false, nullable = false)
	private String mailRestorana;
	
	@Column(unique = false, nullable = false)
	private String telefonRestorana;
	
	@Column(unique = false, nullable = false)
	private Integer udaljenostRestorana;
	
	@Column(unique = false, nullable = false)
	private Integer kapacitet;
	
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

	public String getAdresaRestorana() {
		return adresaRestorana;
	}

	public void setAdresaRestorana(String adresaRestorana) {
		this.adresaRestorana = adresaRestorana;
	}

	public String getMailRestorana() {
		return mailRestorana;
	}

	public void setMailRestorana(String mailRestorana) {
		this.mailRestorana = mailRestorana;
	}

	public String getTelefonRestorana() {
		return telefonRestorana;
	}

	public void setTelefonRestorana(String telefonRestorana) {
		this.telefonRestorana = telefonRestorana;
	}

	public Integer getUdaljenostRestorana() {
		return udaljenostRestorana;
	}

	public void setUdaljenostRestorana(Integer udaljenostRestorana) {
		this.udaljenostRestorana = udaljenostRestorana;
	}

	public Jelovnik getJelovnik() {
		return jelovnik;
	}

	public void setJelovnik(Jelovnik jelovnik) {
		this.jelovnik = jelovnik;
	}

	public Set<Sto> getStolovi() {
		return stolovi;
	}

	public void setStolovi(Set<Sto> stolovi) {
		this.stolovi = stolovi;
	}

	public Integer getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(Integer kapacitet) {
		this.kapacitet = kapacitet;
	}
	
	
	
	
	
}
