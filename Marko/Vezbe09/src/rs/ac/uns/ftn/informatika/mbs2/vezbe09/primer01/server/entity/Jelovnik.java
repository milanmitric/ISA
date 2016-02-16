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
@Table(name = "jelovnik")
public class Jelovnik implements Serializable{

	private static final long serialVersionUID = -2441237196648230347L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "jelovnik_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "jelovnik_naziv", unique = false, nullable = false)
	private String nazivJelovnika;
	
	@ManyToOne
	@JoinColumn(name = "menadzer_id", referencedColumnName = "menadzer_id", nullable = true)
	private Menadzer menadzer;

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "jelovnik")
	private Set<Restoran> restorani = new HashSet<Restoran>();
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "jelovnik")
	private Set<StavkaJelovnika> stavke = new HashSet<StavkaJelovnika>();

	public void addRestoran(Restoran r) {
		
		if (r.getJelovnik() != null)
			r.getJelovnik().getRestorani().remove(r);
		r.setJelovnik(this);
		restorani.add(r);
		
	}
	
	public void addStavku(StavkaJelovnika s){
		if (s.getJelovnik() != null)
			s.getJelovnik().getStavke().remove(s);
		s.setJelovnik(this);
		stavke.add(s);
	}

	public void removeRestoran(Restoran r) {
		r.setJelovnik(null);
		restorani.remove(r);
	}
	
	public void removeStavku(StavkaJelovnika s){
		s.setJelovnik(null);
		stavke.remove(s);
	}
	
	public Integer getId() {
		return id;
	}
	
	public Set<StavkaJelovnika> getStavke() {
		return stavke;
	}

	public void setStavke(Set<StavkaJelovnika> stavke) {
		this.stavke = stavke;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazivJelovnika() {
		return nazivJelovnika;
	}

	public void setNazivJelovnika(String nazivJelovnika) {
		this.nazivJelovnika = nazivJelovnika;
	}

	public Set<Restoran> getRestorani() {
		return restorani;
	}

	public void setRestorani(Set<Restoran> restorani) {
		this.restorani = restorani;
	}
	
	public Menadzer getMenadzer() {
		return menadzer;
	}

	public void setMenadzer(Menadzer menadzer) {
		this.menadzer = menadzer;
	}

	public Jelovnik() {
		super();
	}

	public Jelovnik(String nazivJelovnika, Set<Restoran> restorani, Set<StavkaJelovnika> stavke, Menadzer menadzer) {
		super();
		this.nazivJelovnika = nazivJelovnika;
		this.restorani = restorani;
		this.stavke = stavke;
		this.menadzer = menadzer;
		
	}

	

}
