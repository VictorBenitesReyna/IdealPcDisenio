package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.RecomendacionParte;

@Repository
public interface IRecomendacionParteRepository extends JpaRepository<RecomendacionParte, Integer>{
	@Query("select count(t.nombreRecomendacionParte) from RecomendacionParte t where t.nombreRecomendacionParte=:name")
	public int buscarRecomendacionParte(@Param("name") String nombre);
	
	List<RecomendacionParte>findBynombreRecomendacionParte(String name);
}
