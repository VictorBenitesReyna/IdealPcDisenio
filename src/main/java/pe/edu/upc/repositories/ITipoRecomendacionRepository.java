package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.TypeRecomendation;




@Repository
public interface ITipoRecomendacionRepository extends JpaRepository<TypeRecomendation, Integer>{

	@Query("select count (t.type) from TypeRecomendation t where t.type=:type")
	public int TipoRecomendacionExistentes(@Param("type") String type);

	
}
