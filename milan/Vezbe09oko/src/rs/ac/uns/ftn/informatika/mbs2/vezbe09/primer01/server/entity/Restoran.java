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
import javax.persistence.Transient;

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
	@Column(unique = false, nullable = true)
	private String vrstaRestorana;
	
	@Column(unique = false, nullable = false)
	private String telefonRestorana;
	
	@Column(unique = false, nullable = false)
	private Integer udaljenostRestorana;
	@Column(nullable = true)
	private Integer brojRedovaRestorana;
	@Column(nullable = true)
	private Integer brojKolonaRestorana;
	
	@Transient
	private Double prosecnaOcenaRestorana =-1.0;
	
	@Transient
	private Double prosecnaOcenaPrijateljaRestorana = -1.0;

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


	public Set<Sto> getStolovi() {
		return stolovi;
	}

	public void setStolovi(Set<Sto> stolovi) {
		this.stolovi = stolovi;
	}

	public String getVrstaRestorana() {
		return vrstaRestorana;
	}

	public void setVrstaRestorana(String vrstaRestorana) {
		this.vrstaRestorana = vrstaRestorana;
	}

	public Integer getBrojRedovaRestorana() {
		return brojRedovaRestorana;
	}

	public void setBrojRedovaRestorana(Integer brojRedovaRestorana) {
		this.brojRedovaRestorana = brojRedovaRestorana;
	}

	public Integer getBrojKolonaRestorana() {
		return brojKolonaRestorana;
	}

	public void setBrojKolonaRestorana(Integer brojKolonaRestorana) {
		this.brojKolonaRestorana = brojKolonaRestorana;
	}

	public Double getProsecnaOcenaRestorana() {
		return prosecnaOcenaRestorana;
	}

	public void setProsecnaOcenaRestorana(Double prosecnaOcenaRestorana) {
		this.prosecnaOcenaRestorana = prosecnaOcenaRestorana;
	}

	public Double getProsecnaOcenaPrijateljaRestorana() {
		return prosecnaOcenaPrijateljaRestorana;
	}

	public void setProsecnaOcenaPrijateljaRestorana(Double prosecnaOcenaPrijateljaRestorana) {
		this.prosecnaOcenaPrijateljaRestorana = prosecnaOcenaPrijateljaRestorana;
	}

	
	
	
	
	
}
