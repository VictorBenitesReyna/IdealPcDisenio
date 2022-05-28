package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Companies;


@Repository
public interface IMarcaRepository extends JpaRepository<Companies, Integer>{
	
	@Query("select count(co.nameCompany) from Companies co where co.nameCompany=:nCompany")
	public int CompanysExistentes(@Param("nCompany") String nCompany);
	
	List<Companies> findBynameCompany(String name);
}
