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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "menadzer")
@NamedQuery(name = "findMenadzerSaKorisnickimImenomILozinkom", query = "SELECT k FROM Menadzer k WHERE k.korisnickoImeMenadzera like :korisnickoIme AND k.lozinkaMenadzera LIKE :lozinka")
public class Menadzer implements Serializable{

	private static final long serialVersionUID = 8990486932360026306L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "menadzer_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "menadzer_ime", unique = false, nullable = false)
	private String imeMenadzera;

	@Column(name = "menadzer_prezime", unique = false, nullable = false)
	private String prezimeMenadzera;

	@Column(name = "menadzer_korisnicko_ime", unique = true, nullable = false)
	private String korisnickoImeMenadzera;

	@Column(name = "menadzer_lozinka", unique = false, nullable = false)
	private String lozinkaMenadzera;
	
	@Column(name ="menadzer_sistema", unique = false, nullable = false)
	private boolean menadzerSistema;
	
	@ManyToOne
	@JoinColumn(name = "restoran_id", referencedColumnName = "restoran_id", nullable = true)
	private Restoran restoran;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "menadzer")
	private Set<Jelovnik> jelovnici = new HashSet<Jelovnik>();
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImeMenadzera() {
		return imeMenadzera;
	}

	public void setImeMenadzera(String imeMenadzera) {
		this.imeMenadzera = imeMenadzera;
	}

	public String getPrezimeMenadzera() {
		return prezimeMenadzera;
	}

	public void setPrezimeMenadzera(String prezimeMenadzera) {
		this.prezimeMenadzera = prezimeMenadzera;
	}

	public String getKorisnickoImeMenadzera() {
		return korisnickoImeMenadzera;
	}

	public void setKorisnickoImeMenadzera(String korisnickoImeMenadzera) {
		this.korisnickoImeMenadzera = korisnickoImeMenadzera;
	}

	public String getLozinkaMenadzera() {
		return lozinkaMenadzera;
	}

	public void setLozinkaMenadzera(String lozinkaMenadzera) {
		this.lozinkaMenadzera = lozinkaMenadzera;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Set<Jelovnik> getJelovnici() {
		return jelovnici;
	}

	public void setJelovnici(Set<Jelovnik> jelovnici) {
		this.jelovnici = jelovnici;
	}

	public boolean isMenadzerSistema() {
		return menadzerSistema;
	}

	public void setMenadzerSistema(boolean menadzerSistema) {
		this.menadzerSistema = menadzerSistema;
	}

	public Menadzer() {
		super();
	}

	public Menadzer(String imeMenadzera, String prezimeMenadzera,
			String korisnickoImeMenadzera, String lozinkaMenadzera,
			Restoran restoran, HashSet<Jelovnik> jelovnici, boolean menadzerSistema) {
		super();
		this.imeMenadzera = imeMenadzera;
		this.prezimeMenadzera = prezimeMenadzera;
		this.korisnickoImeMenadzera = korisnickoImeMenadzera;
		this.lozinkaMenadzera = lozinkaMenadzera;
		this.restoran = restoran;
		this.jelovnici = jelovnici;
		this.menadzerSistema = menadzerSistema;
	}
	
	

}
