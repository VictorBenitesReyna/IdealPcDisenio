package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Typeua;

public interface ITipouaService {
	public Integer insert(Typeua tipoua);

	List<Typeua> list();

	Typeua listarId(int idTipoua);

	public void delete(int idTipoua);
}
