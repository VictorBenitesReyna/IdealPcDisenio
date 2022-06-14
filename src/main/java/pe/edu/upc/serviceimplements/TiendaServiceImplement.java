package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Tienda;
import pe.edu.upc.repositories.ITiendaRepository;
import pe.edu.upc.serviceinterfaces.ITiendaService;

@Service
public class TiendaServiceImplement implements ITiendaService{

	@Autowired
	private ITiendaRepository tR;
	@Override
	public boolean insert(Tienda tienda) {
		if(tienda.getWebTienda().endsWith("com")) {
			Tienda rpta=tR.save(tienda);
			if(rpta==null) {
				return false;
			}else {
				return true;
			}
		}else {
			return false;
		}
		
	}
	@Override
	public List<Tienda>list(){
		return tR.findAll();
	}
	@Override
	//@Transactional(readOnly=true)

	@Transactional(readOnly=true)
	public Tienda listarId(int idTienda) {
		Optional<Tienda>op=tR.findById(idTienda);
		return op.isPresent()?op.get():new Tienda();
	}
	@Override
	public void delete(int idTienda) {
		// TODO Auto-generated method stub
		tR.deleteById(idTienda);
	}
	@Override
	public List<Tienda> findBynombreTienda(String name) {
		// TODO Auto-generated method stub
		return tR.findBynombreTienda(name);
	}
	@Override
	public List<Tienda> findByDistritoNombreDistrito(String distrito) {
		
		return tR.findByDistritoNombreDistrito(distrito);
	}
	@Override
	public List<String[]> quantityStores() {
		// TODO Auto-generated method stub
		return tR.quantityStores();
	}
}
