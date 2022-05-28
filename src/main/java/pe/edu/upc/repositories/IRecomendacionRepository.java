package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Recomendacion;

@Repository
public interface IRecomendacionRepository extends JpaRepository<Recomendacion,Integer>{
	@Query("select count(t.nombreRecomendacion) from Recomendacion t where t.nombreRecomendacion=:name")
	public int buscarRecomendacion(@Param("name") String nombre);
	
	List<Recomendacion> findBynombreRecomendacion(String name);
}
