package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.RecomendacionParte;
import pe.edu.upc.repositories.IRecomendacionParteRepository;
import pe.edu.upc.serviceinterfaces.IRecomendacionParteService;

@Service
public class RecomendacionParteServiceImplement implements IRecomendacionParteService{

	@Autowired
	private IRecomendacionParteRepository rpR;
	@Override
	public boolean insert(RecomendacionParte reco) {
		RecomendacionParte rpta=rpR.save(reco);
		if(rpta==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<RecomendacionParte> list() {
		// TODO Auto-generated method stub
		return rpR.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public RecomendacionParte listarId(int idRecoPart) {
		Optional<RecomendacionParte>op=rpR.findById(idRecoPart);
		return op.isPresent()?op.get():new RecomendacionParte();
	}

	@Override
	public void delete(int idRecoPart) {
		// TODO Auto-generated method stub
		rpR.deleteById(idRecoPart);
		
	}

	@Override
	public List<RecomendacionParte> findBynombreRecomendacionParte(String name) {
		// TODO Auto-generated method stub
		return rpR.findBynombreRecomendacionParte(name);
	}

}
