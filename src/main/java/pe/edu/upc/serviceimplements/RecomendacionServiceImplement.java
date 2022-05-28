package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Recomendacion;
import pe.edu.upc.repositories.IRecomendacionRepository;
import pe.edu.upc.serviceinterfaces.IRecomendacionService;

@Service
public class RecomendacionServiceImplement implements IRecomendacionService {
	@Autowired
	private IRecomendacionRepository rR;

	@Override
	public boolean insert(Recomendacion reco) {
		Recomendacion rpta = rR.save(reco);
		if (rpta == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<Recomendacion> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Recomendacion listarId(int idRecomendacion) {
		Optional<Recomendacion> op = rR.findById(idRecomendacion);
		return op.isPresent() ? op.get() : new Recomendacion();
	}

	@Override
	public void delete(int idRecomendacion) {
		// TODO Auto-generated method stub
		rR.deleteById(idRecomendacion);
	}

	@Override
	public List<Recomendacion> findBynombreRecomendacion(String name) {
		// TODO Auto-generated method stub
		return rR.findBynombreRecomendacion(name);
	}
/*
	@Override
	public List<Recomendacion> findByTypeRecomendationIdrecomendation(int reco) {
		// TODO Auto-generated method stub
		return rR.findByTypeRecomendationIdRecomendation(reco);
	}

	@Override
	public List<Recomendacion> findByUsersUsername(String username) {
		// TODO Auto-generated method stub
		return rR.findByUsersUsername(username);
	}
*/
	

}
