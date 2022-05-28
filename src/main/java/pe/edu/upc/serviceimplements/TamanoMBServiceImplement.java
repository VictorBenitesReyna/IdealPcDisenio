package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.TamanoMB;
import pe.edu.upc.repositories.ITamanoMBRepository;
import pe.edu.upc.serviceinterfaces.ITamanoMBService;

@Service
public class TamanoMBServiceImplement implements ITamanoMBService{

	@Autowired
	private ITamanoMBRepository tmbR;

	@Override
	public boolean insert(TamanoMB tamanomb) {
		TamanoMB rpta=tmbR.save(tamanomb);
		if(rpta==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<TamanoMB> list() {
		return tmbR.findAll();
	}
	
	@Override

	@Transactional(readOnly=true)
	public TamanoMB listarId(int idTamanoMB) {
		Optional<TamanoMB>op=tmbR.findById(idTamanoMB);
		return op.isPresent()?op.get():new TamanoMB();
	}
	@Override
	public void delete(int idTamanoMB) {
		// TODO Auto-generated method stub
		tmbR.deleteById(idTamanoMB);
	}
	
	
	
}
