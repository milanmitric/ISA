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
import javax.persistence.OneToMany;
import javax.persistence.Table;



public class Jelovnik implements Serializable{

	private static final long serialVersionUID = -2441237196648230347L;

	
	
	
	private Integer id;

	
	private String nazivJelovnika;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazivJelovnika() {
		return nazivJelovnika;
	}

	public void setNazivJelovnika(String nazivJelovnika) {
		this.nazivJelovnika = nazivJelovnika;
	}


	public Jelovnik() {
		super();
	}

	

	

}
