package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "tamanomb")
public class TamanoMB {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTamanoMB;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El tamaño de la motherboard no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El tamaño de la motherboard no puede contener un número")
	@Column(name = "nTamanoMB", nullable = false, length = 20)
	private String nTamanoMB;
	
	@Column(name = "urlTamanoMB", length = 300, nullable = false)
	private String urlTamanoMB;

	public TamanoMB(int idTamanoMB,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El tamaño de la motherboard no puede contener caracteres especiales") @Pattern(regexp = "[^0-9]+", message = "El tamaño de la motherboard no puede contener un número") String nTamanoMB,
			String urlTamanoMB) {
		super();
		this.idTamanoMB = idTamanoMB;
		this.nTamanoMB = nTamanoMB;
		this.urlTamanoMB = urlTamanoMB;
	}

	public TamanoMB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUrlTamanoMB() {
		return urlTamanoMB;
	}

	public void setUrlTamanoMB(String urlTamanoMB) {
		this.urlTamanoMB = urlTamanoMB;
	}

	public int getIdTamanoMB() {
		return idTamanoMB;
	}

	public void setIdTamanoMB(int idTamanoMB) {
		this.idTamanoMB = idTamanoMB;
	}

	public String getnTamanoMB() {
		return nTamanoMB;
	}

	public void setnTamanoMB(String nTamanoMB) {
		this.nTamanoMB = nTamanoMB;
	}
	
	
}
