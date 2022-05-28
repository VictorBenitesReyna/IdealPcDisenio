package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Role;
import pe.edu.upc.repositories.ITipoUsuarioRepository;
import pe.edu.upc.serviceinterfaces.ITipoUsuarioService;

@Service
public class TipoUsuarioServiceImplement implements ITipoUsuarioService {

	@Autowired
	private ITipoUsuarioRepository tuR;

	@Override
	public boolean insert(Role tipousuario) {
		Role rpta=tuR.save(tipousuario);
		if(rpta==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<Role> list() {
		// TODO Auto-generated method stub
		return tuR.findAll();
	}

	@Override
	public Role listarId(Long idTipousuario) {
		// TODO Auto-generated method stub
		Optional<Role>op=tuR.findById(idTipousuario);

		return op.isPresent() ? op.get() : new Role();
	}
	
	@Override
	public Role listarRol(String rol) {
		// TODO Auto-generated method stub
		Role eop=tuR.findByRol(rol).get(0);

		return eop;
	}

	@Override
	public void delete(Long idTipousuario) {
		// TODO Auto-generated method stub
		tuR.deleteById(idTipousuario);
	}

}
