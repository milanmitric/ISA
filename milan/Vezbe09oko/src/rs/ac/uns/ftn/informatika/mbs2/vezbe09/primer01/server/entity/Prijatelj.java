package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
public class Prijatelj implements Serializable{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "korisnik_fk")
	private Korisnik korisnik;
	
	@JoinColumn(name = "prijatelj_fk")
	@ManyToOne
	private Korisnik prijatelj;
	
	public Prijatelj(){
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Korisnik getPrijatelj() {
		return prijatelj;
	}

	public void setPrijatelj(Korisnik prijatelj) {
		this.prijatelj = prijatelj;
	}
	
	

}
