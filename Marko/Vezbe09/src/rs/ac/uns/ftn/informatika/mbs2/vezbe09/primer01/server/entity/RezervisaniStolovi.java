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
@Table(name = "rezervisansto")
public class RezervisaniStolovi implements Serializable{

	private static final long serialVersionUID = -3549041720201033894L;


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "rezervacija_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "datum_rezervacije", unique = false, nullable = false)
	private String datumRezervacije;
	
	@Column(name = "vreme_rezervacije", unique = false, nullable = false)
	private Integer satRezervacije;
	
	@Column(name = "trajanje_rezervacije", unique = false, nullable = false)
	private Integer trajanjeRezervacije;
	
	@ManyToOne
	@JoinColumn(name = "sto_id", referencedColumnName = "sto_id", nullable = false)
	private Sto sto;
	
	@ManyToOne
	@JoinColumn(name = "korisnik_id", referencedColumnName = "korisnik_id", nullable = false)
	private Korisnik korisnik;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDatumRezervacije() {
		return datumRezervacije;
	}

	public void setDatumRezervacije(String datumRezervacije) {
		this.datumRezervacije = datumRezervacije;
	}

	public Integer getSatRezervacije() {
		return satRezervacije;
	}

	public void setSatRezervacije(Integer satRezervacije) {
		this.satRezervacije = satRezervacije;
	}

	public Integer getTrajanjeRezervacije() {
		return trajanjeRezervacije;
	}

	public void setTrajanjeRezervacije(Integer trajanjeRezervacije) {
		this.trajanjeRezervacije = trajanjeRezervacije;
	}

	public Sto getSto() {
		return sto;
	}

	public void setSto(Sto sto) {
		this.sto = sto;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public RezervisaniStolovi(String datumRezervacije, Integer satRezervacije,
			Integer trajanjeRezervacije, Sto sto, Korisnik korisnik) {
		super();
		this.datumRezervacije = datumRezervacije;
		this.satRezervacije = satRezervacije;
		this.trajanjeRezervacije = trajanjeRezervacije;
		this.sto = sto;
		this.korisnik = korisnik;
	}

	public RezervisaniStolovi() {
		super();
	}
	
	
}
