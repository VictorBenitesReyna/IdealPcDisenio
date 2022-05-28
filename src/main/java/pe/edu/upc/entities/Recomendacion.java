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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Recomendacion")
public class Recomendacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRecomendacion;


	@Pattern(regexp = "[^!\"#%'()*+,-./:;<=>?@^`{|}~]+", message = "El nombre de la Recomendacion no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El nombre de la Recomendacion no puede contener un número")
	@NotNull(message = "El nombre de la Recomendacion no puede estar vacio")
	@Column(name = "nombreRecomendacion", nullable = false)
	private String nombreRecomendacion;

	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	private Users usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_recomendation", nullable = false)
	private TypeRecomendation tipoRecomendation;
	
	private Date fechaRecomendacion;
	
	@Column(name = "fotoRecomendacion", nullable = true)
	private String fotoRecomendacion;

	public Recomendacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recomendacion(int idRecomendacion, String nombreRecomendacion, Users usuario,
			TypeRecomendation tipoRecomendation, Date fechaRecomendacion, String fotoRecomendacion) {
		super();
		this.idRecomendacion = idRecomendacion;
		this.nombreRecomendacion = nombreRecomendacion;
		this.usuario = usuario;
		this.tipoRecomendation = tipoRecomendation;
		this.fechaRecomendacion = fechaRecomendacion;
		this.fotoRecomendacion = fotoRecomendacion;
	}

	public int getIdRecomendacion() {
		return idRecomendacion;
	}

	public void setIdRecomendacion(int idRecomendacion) {
		this.idRecomendacion = idRecomendacion;
	}

	public String getNombreRecomendacion() {
		return nombreRecomendacion;
	}

	public void setNombreRecomendacion(String nombreRecomendacion) {
		this.nombreRecomendacion = nombreRecomendacion;
	}

	public Users getUsuario() {
		return usuario;
	}

	public void setUsuario(Users usuario) {
		this.usuario = usuario;
	}

	public TypeRecomendation getTipoRecomendation() {
		return tipoRecomendation;
	}

	public void setTipoRecomendation(TypeRecomendation tipoRecomendation) {
		this.tipoRecomendation = tipoRecomendation;
	}

	public Date getFechaRecomendacion() {
		return fechaRecomendacion;
	}

	public void setFechaRecomendacion(Date fechaRecomendacion) {
		this.fechaRecomendacion = fechaRecomendacion;
	}

	public String getFotoRecomendacion() {
		return fotoRecomendacion;
	}

	public void setFotoRecomendacion(String fotoRecomendacion) {
		this.fotoRecomendacion = fotoRecomendacion;
	}

	
}
