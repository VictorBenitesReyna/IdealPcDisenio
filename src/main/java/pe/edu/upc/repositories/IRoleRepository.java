package pe.edu.upc.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import pe.edu.upc.entities.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer>{

	@Query("select count(d.rol) from Role d where d.rol=:name")
	public int rolesExistentes(@Param("name")String nombre);
	
	List<Role>findByrol(String name);
}
