package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.repositories.IRoleRepository;
import pe.edu.upc.serviceinterfaces.IRoleService;
import pe.edu.upc.entities.Role;

@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private IRoleRepository rR;

	@Override
	public Integer insert(Role role) {
		int rpta = rR.rolesExistentes(role.getRol());
		if (rpta == 0) {
			rR.save(role);
		}
		return rpta;
	}

	@Override
	public List<Role> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}

	@Override
	public Role listId(int id) {
		// TODO Auto-generated method stub
		Optional<Role> op = rR.findById(id);
		return op.isPresent() ? op.get() : new Role();
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
rR.deleteById(id);
	}

	@Override
	public List<Role> findByrol(String name) {
		// TODO Auto-generated method stub
		return rR.findByrol(name);
	}

}
