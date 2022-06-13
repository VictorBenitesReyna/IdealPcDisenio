package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "tipopanel")
public class TipoPanel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoPanel;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El tipo de panel no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El tipo de panel no puede contener un número")
	@Column(name = "nTipoPanel", nullable = false, length = 20)
	private String nTipoPanel;

	@URL
	@Column(name = "urlTipoPanel", length = 300, nullable = false)
	private String urlTipoPanel;

	public TipoPanel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdTipoPanel() {
		return idTipoPanel;
	}

	public void setIdTipoPanel(int idTipoPanel) {
		this.idTipoPanel = idTipoPanel;
	}

	public String getnTipoPanel() {
		return nTipoPanel;
	}

	public void setnTipoPanel(String nTipoPanel) {
		this.nTipoPanel = nTipoPanel;
	}

	public String getUrlTipoPanel() {
		return urlTipoPanel;
	}

	public void setUrlTipoPanel(String urlTipoPanel) {
		this.urlTipoPanel = urlTipoPanel;
	}

	public TipoPanel(int idTipoPanel,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El tipo de panel no puede contener caracteres especiales") @Pattern(regexp = "[^0-9]+", message = "El tipo de panel no puede contener un número") String nTipoPanel,
			String urlTipoPanel) {
		super();
		this.idTipoPanel = idTipoPanel;
		this.nTipoPanel = nTipoPanel;
		this.urlTipoPanel = urlTipoPanel;
	}

}
