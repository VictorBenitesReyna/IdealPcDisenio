package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Distrito;
import pe.edu.upc.repositories.IDistritoRepository;
import pe.edu.upc.serviceinterfaces.IDistritoService;

@Service
public class DistritoServiceImplement implements IDistritoService {

	@Autowired
	private IDistritoRepository dR;

	@Override
	public Integer insert(Distrito dis) {
		int rpta = dR.distritosExistentes(dis.getNombreDistrito());
		if (rpta == 0) {
			dR.save(dis);
		}
		return rpta;
	}

	@Override
	public List<Distrito> list() {
		// TODO Auto-generated method stub
		return dR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Distrito listarId(int idDistrito) {
		Optional<Distrito> op = dR.findById(idDistrito);
		return op.isPresent() ? op.get() : new Distrito();
	}

	@Override
	public void delete(int idDistrito) {
		// TODO Auto-generated method stub
		dR.deleteById(idDistrito);
	}

	@Override
	public List<Distrito> findBynombreDistrito(String name) {
		// TODO Auto-generated method stub
		return dR.findBynombreDistrito(name);
	}

}
