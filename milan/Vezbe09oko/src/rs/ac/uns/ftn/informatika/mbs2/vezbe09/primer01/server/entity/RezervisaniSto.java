package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RezervisaniSto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;
	
	
	
	@ManyToOne
	@JoinColumn(name="sto_fk",nullable = false)
	private Sto sto;
	
	@ManyToOne
	@JoinColumn(name="rezervacija_fk", nullable = false)
	private Rezervacija rezervacija;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Sto getSto() {
		return sto;
	}

	public void setSto(Sto sto) {
		this.sto = sto;
	}

	
	public RezervisaniSto() {
		super();
	}

	public Rezervacija getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}
	
	

}
