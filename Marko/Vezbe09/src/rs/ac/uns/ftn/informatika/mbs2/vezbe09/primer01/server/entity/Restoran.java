package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "restoran")
public class Restoran implements Serializable{


	private static final long serialVersionUID = 4815934546041729723L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "restoran_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "restoran_naziv", unique = false, nullable = false)
	private String nazivRestorana;

	@Column(name = "restoran_adresa", unique = false, nullable = false)
	private String adresaRestoran;
	
	@Column(name = "restoran_mail", unique = false, nullable = false)
	private String mailRestoran;
	
	@Column(name = "restoran_telefon", unique = false, nullable = false)
	private Integer telefonRestoran;
	
	@Column(name = "restoran_udaljenost", unique = false, nullable = false)
	private Integer udaljenostRestoran;
	
	@ManyToOne
	@JoinColumn(name = "jelovnik_id", referencedColumnName = "jelovnik_id", nullable = false)
	private Jelovnik jelovnik;

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

	public Integer getTelefonRestoran() {
		return telefonRestoran;
	}

	public void setTelefonRestoran(Integer telefonRestoran) {
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

	public Restoran(String nazivRestorana, String adresaRestoran,
			String mailRestoran, Integer telefonRestoran,
			Integer udaljenostRestoran, Jelovnik jelovnik) {
		super();
		this.nazivRestorana = nazivRestorana;
		this.adresaRestoran = adresaRestoran;
		this.mailRestoran = mailRestoran;
		this.telefonRestoran = telefonRestoran;
		this.udaljenostRestoran = udaljenostRestoran;
		jelovnik.add(this);
	}
	
	
	
	
	

}
