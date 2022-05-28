package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Hardware;
import pe.edu.upc.repositories.IHardwareRepository;
import pe.edu.upc.serviceinterfaces.IHardwareService;

@Service
public class HardwareServiceImplement implements IHardwareService{

	@Autowired
	private IHardwareRepository hR;
	@Override
	public boolean insert(Hardware hardware) {
		// TODO Auto-generated method stub
		Hardware rpta=hR.save(hardware);
		if(rpta==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<Hardware> list() {
		// TODO Auto-generated method stub
		return hR.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Hardware listarId(int idHardware) {
		// TODO Auto-generated method stub
		Optional<Hardware>op=hR.findById(idHardware);
		return op.isPresent()?op.get():new Hardware();
	}

	@Override
	public void delete(int idHardware) {
		// TODO Auto-generated method stub
		hR.deleteById(idHardware);
	}

	@Override
	public List<Hardware> findBynombreHardware(String name){
	return hR.findBynombreHardware(name);	
	}
	
	@Override
	public List<String[]> reportMarca() {
		// TODO Auto-generated method stub
		return hR.reportMarca();
	}

	@Override
	public List<String[]> reportCantHardware() {
		// TODO Auto-generated method stub
		return hR.reportCantHardware();
	}

	@Override
	public List<String[]> reportValueCantHardware() {
		// TODO Auto-generated method stub
		return hR.reportValueCantHardware();
	}
	
}
