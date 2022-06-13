package pe.edu.upc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Tienda")
public class Tienda implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTienda;

	//@NotNull(message = "Foto no válida")
	//@NotBlank(message = "Foto no válida")
	//@NotEmpty(message = "Foto no válida")
	@Column(name = "fotoTienda", nullable = false)
	private String fotoTienda;

	@Pattern(regexp = "[^!\"#$%&'()*+,-/:;<=>?@^_`{|}~]+", message = "Tienda no válida")
	// @Pattern(regexp = "[^0-9]+", message = "El nombre del producto no puede
	// contener un número")
	@NotNull(message = "Tienda no válida")
	@Column(name = "direccionTienda", nullable = false)
	private String direccionTienda;

	@Pattern(regexp = "[^!\"#%'()*+,-./:;<=>?@^`{|}~]+", message = "Tienda no válida")
	@Pattern(regexp = "[^0-9]+", message = "Tienda no válida")
	@NotNull(message = "Tienda no válida")
	@Column(name = "nombreTienda", nullable = false)
	private String nombreTienda;

	@NotNull(message = "Debes poner un numero de contacto")
	@Positive(message = "El telefono no puede ser negativo")
	@Min(value = 100000000, message = "El telefono debe tener 9 digitos")
	@Max(value = 999999999, message = "El telefono no debe ser mayor a 9 digitos")
	@Pattern(regexp = "[^!\"#$%&'()*+,-/:;<=>?@^_`{|}~]+", message = "Numero no válido")
	@Column(name = "telefonoTienda", nullable = false)
	private int telefonoTienda;

	@URL
	@Column(name = "webTienda", nullable = false)
	private String webTienda;

	//@NotNull(message = "Fecha no válida")
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@Column(name = "fechaRegistro", nullable = false)
	//@NotEmpty(message = "Fecha no válida")
	@NotNull(message = "Fecha no válida")
	@PastOrPresent(message = "Fecha no válida")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dateOfBirth")
	private Date fechaRegistro;

	@ManyToOne
	@JoinColumn(name = "idDistrito", nullable = false)
	private Distrito distrito;

	public Tienda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tienda(int idTienda, String fotoTienda, String direccionTienda, String nombreTienda, int telefonoTienda,
			String webTienda, Distrito distrito, Date fechaRegistro) {
		super();
		this.idTienda = idTienda;
		this.fotoTienda = fotoTienda;
		this.direccionTienda = direccionTienda;
		this.nombreTienda = nombreTienda;
		this.telefonoTienda = telefonoTienda;
		this.webTienda = webTienda;
		this.distrito = distrito;
		this.fechaRegistro = fechaRegistro;
	}

	public int getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(int idTienda) {
		this.idTienda = idTienda;
	}

	public String getFotoTienda() {
		return fotoTienda;
	}

	public void setFotoTienda(String fotoTienda) {
		this.fotoTienda = fotoTienda;
	}

	public String getDireccionTienda() {
		return direccionTienda;
	}

	public void setDireccionTienda(String direccionTienda) {
		this.direccionTienda = direccionTienda;
	}

	public String getNombreTienda() {
		return nombreTienda;
	}

	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}

	public int getTelefonoTienda() {
		return telefonoTienda;
	}

	public void setTelefonoTienda(int telefonoTienda) {
		this.telefonoTienda = telefonoTienda;
	}

	public String getWebTienda() {
		return webTienda;
	}

	public void setWebTienda(String webTienda) {
		this.webTienda = webTienda;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}
