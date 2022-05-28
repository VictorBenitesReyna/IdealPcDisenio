package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Tienda;

public interface ITiendaService {
	public boolean insert(Tienda tienda);

	List<Tienda> list();

	Tienda listarId(int idTienda);

	public void delete(int idTienda);

	List<Tienda> findBynombreTienda(String name);
	
	List<Tienda> findByDistritoNombreDistrito(String distrito);

	public List<String[]> quantityStores();

}
