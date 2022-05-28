package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Typeua;



@Repository
public interface ITipouaRepository extends JpaRepository<Typeua, Integer>{
	@Query("select count(l.type) from Typeua l where l.type=:type")
	public int buscarTipoua(@Param("type") String type);
}