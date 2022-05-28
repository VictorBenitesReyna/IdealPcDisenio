package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "companies")
public class Companies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_company;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre de la Marca no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El nombre de la Marca no puede contener un número")
	@Column(name = "nameCompany", nullable = false, length = 20)
	private String nameCompany;

	@Column(name = "urlCompany", length = 300, nullable = false)
	private String urlCompany;

	public Companies() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Companies(int id_company,
			String nameCompany,
			String urlCompany) {
		super();
		this.id_company = id_company;
		this.nameCompany = nameCompany;
		this.urlCompany = urlCompany;
	}

	public int getId_company() {
		return id_company;
	}

	public void setId_company(int id_company) {
		this.id_company = id_company;
	}

	public String getNameCompany() {
		return nameCompany;
	}

	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}

	public String getUrlCompany() {
		return urlCompany;
	}

	public void setUrlCompany(String urlCompany) {
		this.urlCompany = urlCompany;
	}

	
	
	

}
