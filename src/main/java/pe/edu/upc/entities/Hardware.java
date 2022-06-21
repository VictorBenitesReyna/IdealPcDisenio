package pe.edu.upc.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;


@Entity
@Table(name = "Hardware")
public class Hardware {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idHardware;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "Hardware no válido")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del HARDWARE no puede contener un número")
	@NotNull(message = "El nombre del HARDWARE no puede estar vacio")
	@Column(name = "nombreHardware", nullable = false)
	private String nombreHardware;

	@Pattern(regexp = "[^!\"$%&'()*+,./:;<=>?@^`{|}~]+", message = "Hardware no válido")
	// @Pattern(regexp = "[^0-9]+", message = "El nombre del MODELO no puede
	// contener un número")
	@NotNull(message = "El nombre del MODELO no puede estar vacio")
	@Column(name = "modeloHardware", nullable = false)
	private String modeloHardware;

	@NotNull
	@DecimalMin("1.00")
	@Positive
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "Precio estimado no válido")
	@Pattern(regexp = "[qwertyuiopasdfghjklñzxcvbnm]+", message = "Precio estimado no válido")
	@Pattern(regexp = "[QWERTYUIOPÑLKJHGFDSAZXCVBNM]+", message = "Precio estimado no válido")
	@Min(value = 1, message = "El precio no debe ser menor a 1")
	@Column(name = "precioHardware", nullable = false)
	private double precioHardware;

	@Column(name = "fotoHardware", nullable = true)
	private String fotoHardware;

	@ManyToOne
	@JoinColumn(name = "id_company", nullable = false)
	private Companies companies;

	private Date fecha;

	public Hardware() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hardware(int idHardware, String nombreHardware, String modeloHardware, Double precioHardware,
			String fotoHardware, Companies companies, Date fecha) {
		super();
		this.idHardware = idHardware;
		this.nombreHardware = nombreHardware;
		this.modeloHardware = modeloHardware;
		this.precioHardware = precioHardware;
		this.fotoHardware = fotoHardware;
		this.companies = companies;
		this.fecha = fecha;
	}

	public int getIdHardware() {
		return idHardware;
	}

	public void setIdHardware(int idHardware) {
		this.idHardware = idHardware;
	}

	public String getNombreHardware() {
		return nombreHardware;
	}

	public void setNombreHardware(String nombreHardware) {
		this.nombreHardware = nombreHardware;
	}

	public String getModeloHardware() {
		return modeloHardware;
	}

	public void setModeloHardware(String modeloHardware) {
		this.modeloHardware = modeloHardware;
	}

	public Double getPrecioHardware() {
		return precioHardware;
	}

	public void setPrecioHardware(Double precioHardware) {
		this.precioHardware = precioHardware;
	}

	public String getFotoHardware() {
		return fotoHardware;
	}

	public void setFotoHardware(String fotoHardware) {
		this.fotoHardware = fotoHardware;
	}

	public Companies getCompanies() {
		return companies;
	}

	public void setCompanies(Companies companies) {
		this.companies = companies;
	}

	public void setCompany(Companies companies) {
		this.companies = companies;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
