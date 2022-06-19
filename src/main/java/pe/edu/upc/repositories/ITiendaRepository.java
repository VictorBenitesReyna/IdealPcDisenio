package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Tienda;

@Repository
public interface ITiendaRepository extends JpaRepository<Tienda, Integer> {
	@Query("select count(t.nombreTienda) from Tienda t where t.nombreTienda=:name")
	public int buscarTienda(@Param("name") String nombre);

	//@Query(value="select * from Tienda t where t.id_distrito = (select idDistrito from Distrito where nombreDistrito =:ndistrito)", nativeQuery = true)
	
	List<Tienda> findBynombreTienda(String name);
	

}
