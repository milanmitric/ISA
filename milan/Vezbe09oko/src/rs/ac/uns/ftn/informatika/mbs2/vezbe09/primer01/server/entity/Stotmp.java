package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class Stotmp implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer redniBroj;
	
	private Integer red;
	
	private Integer kolona;

	@ManyToOne
	@JoinColumn(name = "restoran_fk")
	private RestoranTmp restoran;
	
	
	public Stotmp(){
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getRedniBroj() {
		return redniBroj;
	}


	public void setRedniBroj(Integer redniBroj) {
		this.redniBroj = redniBroj;
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


	public RestoranTmp getRestoran() {
		return restoran;
	}


	public void setRestoran(RestoranTmp restoran) {
		this.restoran = restoran;
	}
	
	
	
	
	
	
}
