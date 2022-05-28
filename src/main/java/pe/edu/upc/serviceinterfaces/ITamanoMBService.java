package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.TamanoMB;

public interface ITamanoMBService {

	public boolean insert (TamanoMB tamanomb);
	
	List<TamanoMB> list();
	TamanoMB listarId(int idTamanoMB);
	public void delete(int idTamanoMB);

}
