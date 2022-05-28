package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Typeua;
import pe.edu.upc.repositories.ITipouaRepository;
import pe.edu.upc.serviceinterfaces.ITipouaService;

@Service
public class TipouaServiceImpl implements ITipouaService {

	@Autowired
	private ITipouaRepository cR;

	@Override
	public Integer insert(Typeua tipoua) {
		int rpta = cR.buscarTipoua(tipoua.getType());
		if (rpta == 0) {
			cR.save(tipoua);
		}
		return rpta;
	}

	@Override
	public List<Typeua> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Typeua listarId(int idTipoua) {
		Optional<Typeua>op=cR.findById(idTipoua);
		return op.isPresent()?op.get():new Typeua();
	}

	@Override
	public void delete(int idTipoua) {
		// TODO Auto-generated method stub
		cR.deleteById(idTipoua);
	}

}
