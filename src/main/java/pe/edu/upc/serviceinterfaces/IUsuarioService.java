package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Users;


public interface IUsuarioService {

	public boolean insert(Users usuario);

	List<Users> list();

	Users listarId(int idUsuario);
	
	public void delete(int idUsuarioidUsuario);

	List<Users> findBynombreUsuario(String name);

	public List<String[]> quantityUsers();

}
