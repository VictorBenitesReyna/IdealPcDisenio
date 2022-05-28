package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "typeua")
public class Typeua {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_ua;
	
	@Column(name = "type", nullable = false, length = 20)
	private String type;

	public Typeua() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Typeua(int id_ua, String nameTipoua) {
		super();
		this.id_ua = id_ua;
		this.type = nameTipoua;
	}



	public int getId_ua() {
		return id_ua;
	}

	public void setId_ua(int id_ua) {
		this.id_ua = id_ua;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
