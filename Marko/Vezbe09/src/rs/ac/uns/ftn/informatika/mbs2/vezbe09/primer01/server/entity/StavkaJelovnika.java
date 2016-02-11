package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stavka")
public class StavkaJelovnika implements Serializable{


	private static final long serialVersionUID = 1L;
	
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
	
	public StavkaJelovnika() {
		super();
	}

	public StavkaJelovnika(String nazivStavke, String tipStavke, String opisStavke) {
		super();
		this.nazivStavke = nazivStavke;
		this.tipStavke = tipStavke;
		this.opisStavke = opisStavke;
	}

}
