package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Companies;
import pe.edu.upc.repositories.IMarcaRepository;
import pe.edu.upc.serviceinterfaces.IMarcaService;

@Service
public class MarcaServiceImplement implements IMarcaService {

	@Autowired
	private IMarcaRepository mR;

	@Override
	public boolean insert(Companies marca) {
		Companies rpta=mR.save(marca);
		if(rpta==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<Companies> list() {
		return mR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Companies listarId(int idMarca) {
		Optional<Companies> op = mR.findById(idMarca);
		return op.isPresent() ? op.get() : new Companies();
	}

	@Override
	public void delete(int idMarca) {
		// TODO Auto-generated method stub
		mR.deleteById(idMarca);
	}

	@Override
	public List<Companies> findBynMarca(String name) {
		// TODO Auto-generated method stub
		return mR.findBynameCompany(name);
	}

}
