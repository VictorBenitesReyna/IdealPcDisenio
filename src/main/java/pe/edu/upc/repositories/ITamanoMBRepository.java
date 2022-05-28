package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.TamanoMB;

@Repository
public interface ITamanoMBRepository extends JpaRepository<TamanoMB, Integer>{
	
	@Query("select count (m.nTamanoMB) from TamanoMB m where m.nTamanoMB=:name")
	public int TamanosMBExistentes(@Param("name")String nombre);
}
