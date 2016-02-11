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

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "jelovnik")
	private Set<Restoran> restorani = new HashSet<Restoran>();

	public void add(Restoran r) {
		
		if (r.getJelovnik() != null)
			r.getJelovnik().getRestorani().remove(r);
		r.setJelovnik(this);
		restorani.add(r);
		
	}

	public void remove(Restoran r) {
		r.setJelovnik(null);
		restorani.remove(r);
	}
	
	public Integer getId() {
		return id;
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

	public Jelovnik() {
		super();
	}

	public Jelovnik(String nazivJelovnika, Set<Restoran> restorani) {
		super();
		this.nazivJelovnika = nazivJelovnika;
		this.restorani = restorani;
	}

	

}
