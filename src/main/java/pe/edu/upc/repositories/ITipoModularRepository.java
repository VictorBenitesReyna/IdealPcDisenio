package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.TypeModular;
@Repository
public interface ITipoModularRepository extends JpaRepository<TypeModular, Integer> {
  
	@Query("select count(tm.type) from TypeModular tm where tm.type=:type")
	public int TipoModularExistentes(@Param("type") String type);
}
