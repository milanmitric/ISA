package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
@NamedQuery(name ="findMenadzerByIdAndPass", query = "SELECT m FROM Menadzer m WHERE m.korisnickoIme like :korisnickoIme AND m.lozinka LIKE :lozinka")
public class Menadzer implements Serializable{
	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = true)
	private String ime;
	
	@Column(nullable = true)
	private String prezime;
	
	@Column(nullable = false, unique = true)
	private String korisnickoIme;
	
	@Column(nullable = false, unique = false)
	private String lozinka;
	
	/*
	 * Da li je menazder sistema ili menadzer restorana?
	 */
	@Column(nullable = false, unique = false)
	private boolean sistemMenadzer;
	
	
	public Menadzer(){
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getKorisnickoIme() {
		return korisnickoIme;
	}


	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}


	public String getLozinka() {
		return lozinka;
	}


	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}


	public boolean isSistemMenadzer() {
		return sistemMenadzer;
	}


	public void setSistemMenadzer(boolean sistemMenadzer) {
		this.sistemMenadzer = sistemMenadzer;
	}
}
