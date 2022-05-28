package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "typeModular")
public class TypeModular  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_modular;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El Tipo Modular no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El Tipo Modular no puede contener un número")
	@Column(name = "type",nullable = false,length = 100)
	private String type;

	public TypeModular() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeModular(int id_modular, String type) {
		super();
		this.id_modular = id_modular;
		this.type = type;
	}

	public int getId_modular() {
		return id_modular;
	}

	public void setId_modular(int id_modular) {
		this.id_modular = id_modular;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}