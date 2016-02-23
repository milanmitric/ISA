package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.sun.org.apache.xpath.internal.operations.Equals;

@Entity
public class Rezervacija implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;
	
	@Column(unique = false, nullable = false)
	private String datum;
	
	@Column(unique = false, nullable = false)
	private Integer sat;
	
	@Column(unique = false, nullable = false)
	private Integer trajanje;
	
	@ManyToOne
	@JoinColumn(name="korisnik_fk", nullable =false)
	private Korisnik korisnik;
	
	@ManyToOne
	@JoinColumn(name="restoran_fk", nullable = false)
	private Restoran restoran;
	
	@Transient
	private Integer ocena = -1;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public Integer getSat() {
		return sat;
	}

	public void setSat(Integer sat) {
		this.sat = sat;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	public Rezervacija(){
		super();
	}

	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Rezervacija)){
			return false;
		} else {
			Rezervacija rez = (Rezervacija)obj;
			if (this.getId().equals(rez.getId())){
				return true;
			} else {
				return false;
			}			
		}
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Integer getOcena() {
		return ocena;
	}

	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}
	
	

}
