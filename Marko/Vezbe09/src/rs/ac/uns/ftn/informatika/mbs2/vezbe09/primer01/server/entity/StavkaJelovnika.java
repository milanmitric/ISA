package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "stavka")
@NamedQuery(name = "findStavkeSaIDJelovnika", query = "SELECT s FROM StavkaJelovnika s WHERE s.jelovnik.id like :id ORDER BY s.tipStavke ASC , s.nazivStavke ASC")
public class StavkaJelovnika implements Serializable{


	private static final long serialVersionUID = 8414414385139890385L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "stavka_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "naziv_stavke", unique = false, nullable = false)
	private String nazivStavke;
	
	@Column(name = "tip_stavke", unique = false, nullable = false)
	private String tipStavke;

	@Column(name = "opis", unique = false, nullable = true)
	private String opisStavke;
	
	@Column(name = "cena", unique = false, nullable = false)
	private Integer cenaStavke;

	@ManyToOne
	@JoinColumn(name = "jelovnik_id", referencedColumnName = "jelovnik_id", nullable = false)
	private Jelovnik jelovnik;

	public Jelovnik getJelovnik() {
		return jelovnik;
	}

	public void setJelovnik(Jelovnik jelovnik) {
		this.jelovnik = jelovnik;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazivStavke() {
		return nazivStavke;
	}

	public void setNazivStavke(String nazivStavke) {
		this.nazivStavke = nazivStavke;
	}

	public String getTipStavke() {
		return tipStavke;
	}

	public void setTipStavke(String tipStavke) {
		this.tipStavke = tipStavke;
	}

	public String getOpisStavke() {
		return opisStavke;
	}

	public void setOpisStavke(String opisStavke) {
		this.opisStavke = opisStavke;
	}
	
	public Integer getCenaStavke() {
		return cenaStavke;
	}

	public void setCenaStavke(Integer cenaStavke) {
		this.cenaStavke = cenaStavke;
	}
	
	public StavkaJelovnika() {
		super();
	}

	public StavkaJelovnika(String nazivStavke, String tipStavke, String opisStavke,
				Integer cena, Jelovnik jelovnik) {
		super();
		this.nazivStavke = nazivStavke;
		this.tipStavke = tipStavke;
		this.opisStavke = opisStavke;
		jelovnik.addStavku(this);
		this.cenaStavke = cena;
	}

}
