package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sto")
public class Sto implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;
	
	
	@Column(unique = false, nullable = false)
	private Integer red;
	
	@Column(unique = false, nullable = false)
	private Integer kolona;
	
	@ManyToOne
	@JoinColumn(name="restoran_fk", nullable = false)
	private Restoran restoran;
	
	@Transient
	private boolean slobodan;
		
	public Sto(){
		super();
	}


	public Integer getRed() {
		return red;
	}

	public void setRed(Integer red) {
		this.red = red;
	}

	public Integer getKolona() {
		return kolona;
	}

	public void setKolona(Integer kolona) {
		this.kolona = kolona;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public boolean isSlobodan() {
		return slobodan;
	}


	public void setSlobodan(boolean slobodan) {
		this.slobodan = slobodan;
	}



	
	

}
