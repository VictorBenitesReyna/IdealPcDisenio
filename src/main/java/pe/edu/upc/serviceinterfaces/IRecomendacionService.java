package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Recomendacion;

public interface IRecomendacionService {
	public boolean insert(Recomendacion reco);
	List<Recomendacion>list();
	Recomendacion listarId(int idRecomendacion);
	public void delete(int idRecomendacion);
	List<Recomendacion> findBynombreRecomendacion(String name);
	//List<Recomendacion> findByUsersUsername(String username);
	//List<Recomendacion> findByTypeRecomendationIdrecomendation(int reco);
}
