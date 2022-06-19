package pe.edu.upc.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del Usuario no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del Usuario no puede contener un númeroo")
	@NotNull(message = "El nombre de la Usuario no puede estar vacio")
	@Column(length = 30, unique = true)
	private String username;

	@Column(length = 200)
	private String password;

	@Size(min = 9, message = "Teléfono debe tener solo 9 dígitos")
	@Size(max = 9, message = "Teléfono debe tener solo 9 dígitos")
	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "email", nullable = false)
	@Email(message = "Ingresar un email valido.")
	private String email;

	@Column(name = "photo", nullable = true)
	private String photo;

	private Boolean enabled;

	@Column(name = "idDistrito", nullable = false)
	private String distrito;

	@ManyToOne
	@JoinColumn(name = "id_role", nullable = false)
	private Role roles;

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(int id,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del Usuario no puede contener caracteres especiales") @Pattern(regexp = "[^0-9]+", message = "El nombre del Usuario no puede contener un númeroo") @NotNull(message = "El nombre de la Usuario no puede estar vacio") String username,
			String password,
			@Size(min = 9, message = "Teléfono debe tener solo 9 dígitos") @Size(max = 9, message = "Teléfono debe tener solo 9 dígitos") String phone,
			@Email(message = "Ingresar un email valido.") String email, String photo, Boolean enabled, String distrito, Role roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.photo = photo;
		this.enabled = enabled;
		this.distrito = distrito;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
