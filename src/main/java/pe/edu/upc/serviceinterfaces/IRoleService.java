package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Role;


public interface IRoleService {
	public Integer insert(Role role);
	List<Role> list();
	Role listId(int id);
	public void delete(int id);
	List<Role>findByrol(String name);
}
