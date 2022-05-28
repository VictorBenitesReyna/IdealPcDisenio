package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.TypeRecomendation;
import pe.edu.upc.repositories.ITipoRecomendacionRepository;
import pe.edu.upc.serviceinterfaces.ITipoRecomendacionService;

@Service
public class TipoRecomendacionServiceImplement implements ITipoRecomendacionService {

	@Autowired
	private ITipoRecomendacionRepository trR;

	@Override
	public boolean insert(TypeRecomendation tiporeco) {
		TypeRecomendation rpta=trR.save(tiporeco);
		if(rpta==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<TypeRecomendation> list() {
		// TODO Auto-generated method stub
		return trR.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public TypeRecomendation listarId(int idTipoRecomendacion) {
		Optional<TypeRecomendation> op = trR.findById(idTipoRecomendacion);
		return op.isPresent() ? op.get() : new TypeRecomendation();
	}

	@Override
	public void delete(int idTipoRecomendacion) {
		// TODO Auto-generated method stub
		trR.deleteById(idTipoRecomendacion);
	}

}
