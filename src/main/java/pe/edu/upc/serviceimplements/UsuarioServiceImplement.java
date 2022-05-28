package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Users;
import pe.edu.upc.repositories.IUsuarioRepository;
import pe.edu.upc.serviceinterfaces.IUsuarioService;

@Service
public class UsuarioServiceImplement implements IUsuarioService {

	@Autowired
	private IUsuarioRepository uR;

	@Override
	public boolean insert(Users usuario) {

		Users rpta = uR.save(usuario);
		if (rpta == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<Users> list() {
		return uR.findAll();
	}

	@Override
	// @Transactional(readOnly=true)

	@Transactional(readOnly = true)
	public Users listarId(int idUsuario) {
		Optional<Users> op = uR.findById(idUsuario);
		return op.isPresent() ? op.get() : new Users();
	}

	@Override
	public List<Users> findBynombreUsuario(String name) {
		// TODO Auto-generated method stub
		return uR.findByUsername(name);
	}

	@Override
	public void delete(int idUsuario) {
		// TODO Auto-generated method stub
		uR.deleteById(idUsuario);
	}

	@Override
	public List<String[]> quantityUsers() {
		// TODO Auto-generated method stub
		return uR.quantityUsers();
	}
}
