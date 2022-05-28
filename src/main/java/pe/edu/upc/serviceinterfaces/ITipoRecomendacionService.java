package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.TypeRecomendation;

public interface ITipoRecomendacionService {
	public boolean insert(TypeRecomendation tiporecomendacion);

	List<TypeRecomendation> list();

	TypeRecomendation listarId(int idTipoRecomendacion);

	public void delete(int idTipoRecomendacion);
}
