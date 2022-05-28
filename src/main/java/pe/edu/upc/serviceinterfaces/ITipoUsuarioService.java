package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Role;



public interface ITipoUsuarioService {
	public boolean insert(Role tipousuario);
	List<Role> list();
	Role listarId(Long idTipousuario);
	
	Role listarRol(String rol);

	public void delete(Long idTipousuario);

}
