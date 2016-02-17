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
@Table(name = "sto")
public class Sto implements Serializable{


	private static final long serialVersionUID = -6733426811460616781L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sto_id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "restoran_id", referencedColumnName = "restoran_id", nullable = true)
	private Restoran restoran;
	
	@Column(name = "red", unique = false, nullable = false)
	private Integer red;
	
	@Column(name = "kolona", unique = false, nullable = false)
	private Integer kolona;
	
	@Column(name = "aktivanSto", unique = false, nullable = false)
	private boolean aktivanSto;
	
	@Column(name = "slobodanSto", unique = false, nullable = false)
	private boolean slobodanSto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
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

	public boolean isAktivanSto() {
		return aktivanSto;
	}

	public void setAktivanSto(boolean aktivanSto) {
		this.aktivanSto = aktivanSto;
	}

	public boolean isSlobodanSto() {
		return slobodanSto;
	}

	public void setSlobodanSto(boolean slobodanSto) {
		this.slobodanSto = slobodanSto;
	}

	public Sto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sto(Restoran restoran, Integer red, Integer kolona,
			boolean aktivanSto, boolean slobodanSto) {
		super();
		this.restoran = restoran;
		this.red = red;
		this.kolona = kolona;
		this.aktivanSto = aktivanSto;
		this.slobodanSto = slobodanSto;
	}

	
	
	

}
