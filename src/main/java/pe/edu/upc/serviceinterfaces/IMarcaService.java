package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Companies;

public interface IMarcaService {
	
	public boolean insert(Companies marca);
	
	List<Companies> list();
	
	Companies listarId(int idMarca);
	
	public void delete(int idMarca);
	
	List<Companies> findBynMarca(String name);
}
