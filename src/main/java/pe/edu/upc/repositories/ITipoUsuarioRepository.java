package pe.edu.upc.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Role;


@Repository
public interface ITipoUsuarioRepository extends JpaRepository<Role, Long>{

	@Query("select count (tu.rol) from Role tu where tu.rol=:nTipousuario")
	public int TipoUsuarioExistentes(@Param("nTipousuario") String nTipousuario);
	
	List<Role> findByRol(String rol);
}
