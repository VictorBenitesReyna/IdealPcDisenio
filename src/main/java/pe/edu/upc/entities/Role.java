package pe.edu.upc.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "roles", uniqueConstraints = { @UniqueConstraint(columnNames = { "rol" }) })
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_role;

	private String rol;



	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Long id_role, String rol) {
		super();
		this.id_role = id_role;
		this.rol = rol;
	}



	public String getRol() {
		return rol;
	}

	public Long getId_role() {
		return id_role;
	}

	public void setId_role(Long id_role) {
		this.id_role = id_role;
	}



	public void setRol(String rol) {
		this.rol = rol;
	}


	//@JoinColumn(name = "user_id", nullable = false)
	//private Users user;

	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/


	/*public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
*/
}
