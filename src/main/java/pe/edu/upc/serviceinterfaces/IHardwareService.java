package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Hardware;

public interface IHardwareService {

	public boolean insert(Hardware hardware);
	List<Hardware>list();
	Hardware listarId(int idHardware);
	public void delete(int idHardware);
	List<Hardware> findBynombreHardware(String name);
	
	public List<String[]> reportMarca();
	public List<String[]>reportCantHardware();
	public List<String[]>reportValueCantHardware();
}
