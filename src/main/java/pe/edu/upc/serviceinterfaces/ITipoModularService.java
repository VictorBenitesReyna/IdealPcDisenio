package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.TypeModular;

public interface ITipoModularService {
	public boolean insert(TypeModular tipomodular);
	List<TypeModular> list();
	TypeModular listarId(int idTipoModular);
	public void delete (int idTipoModular);
}
