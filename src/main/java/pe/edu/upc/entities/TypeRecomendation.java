package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "typeRecomendation")
public class TypeRecomendation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_recomendation;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El Tipo de Recomendación no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El Tipo de Recomendación no puede contener un número")
	@Column(name = "type", nullable = false, length = 100)
	private String type;

	public TypeRecomendation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeRecomendation(int id_recomendation, String type) {
		super();
		this.id_recomendation = id_recomendation;
		this.type = type;
	}

	


	public int getId_recomendation() {
		return id_recomendation;
	}

	public void setId_recomendation(int id_recomendation) {
		this.id_recomendation = id_recomendation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
