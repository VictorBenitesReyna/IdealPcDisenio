package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.RecomendacionParte;

public interface IRecomendacionParteService {
	public boolean insert(RecomendacionParte reco);

	List<RecomendacionParte> list();

	RecomendacionParte listarId(int idRecoPart);

	public void delete(int idRecoPart);

	List<RecomendacionParte> findBynombreRecomendacionParte(String name);
	
}
