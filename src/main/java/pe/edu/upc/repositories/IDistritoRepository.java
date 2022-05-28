package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Distrito;

@Repository
public interface IDistritoRepository extends JpaRepository<Distrito, Integer>{

	@Query("select count(d.nombreDistrito) from Distrito d where d.nombreDistrito=:name")
	public int distritosExistentes(@Param("name")String nombre);
	
	List<Distrito>findBynombreDistrito(String name);
}
