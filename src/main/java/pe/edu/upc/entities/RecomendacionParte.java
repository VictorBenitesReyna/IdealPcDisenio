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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "RecomendacionParte")
public class RecomendacionParte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRecomendPart;
	
	@Pattern(regexp = "[^!\"#%'()*+,-./:;<=>?@^`{|}~]+", message = "El nombre de la Nombre de Recomendacion no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El Nombre de la Recomendacion no puede contener un número")
	@NotNull(message = "El Nombre de Recomendacion no puede estar vacio")
	@Column(name = "nombreRecomendacionParte", nullable = false)
	private String nombreRecomendacionParte;
	
	@ManyToOne
	@JoinColumn(name = "idHardware",nullable = false)
	private Hardware hardware;
	
	@ManyToOne
	@JoinColumn(name = "idRecomendacion",nullable = false)
	private Recomendacion recomendacion;
	
	@NotNull(message = "Debes poner un numero de contacto")
	@Positive(message = "El telefono no puede ser negativo")
	@Min(value = 1, message = "El numero debe ser mayor igual a 1")
	@Column(name = "cantidadReco",nullable = false)
	private int cantidadReco;
	
	private Date fechaRegistro;

	public RecomendacionParte() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public RecomendacionParte(int idRecomendPart, String nombreRecomendacionParte, Hardware hardware,
			Recomendacion recomendacion, int cantidadReco, Date fechaRegistro) {
		super();
		this.idRecomendPart = idRecomendPart;
		this.nombreRecomendacionParte = nombreRecomendacionParte;
		this.hardware = hardware;
		this.recomendacion = recomendacion;
		this.cantidadReco = cantidadReco;
		this.fechaRegistro = fechaRegistro;
	}

	public String getNombreRecomendacionParte() {
		return nombreRecomendacionParte;
	}


	public void setNombreRecomendacionParte(String nombreRecomendacionParte) {
		this.nombreRecomendacionParte = nombreRecomendacionParte;
	}


	public int getIdRecomendPart() {
		return idRecomendPart;
	}

	public void setIdRecomendPart(int idRecomendPart) {
		this.idRecomendPart = idRecomendPart;
	}

	public Hardware getHardware() {
		return hardware;
	}

	public void setHardware(Hardware hardware) {
		this.hardware = hardware;
	}

	public Recomendacion getRecomendacion() {
		return recomendacion;
	}

	public void setRecomendacion(Recomendacion recomendacion) {
		this.recomendacion = recomendacion;
	}

	public int getCantidadReco() {
		return cantidadReco;
	}

	public void setCantidadReco(int cantidadReco) {
		this.cantidadReco = cantidadReco;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	
	
}
