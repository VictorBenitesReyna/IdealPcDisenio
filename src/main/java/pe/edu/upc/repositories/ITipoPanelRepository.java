package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.TipoPanel;

@Repository
public interface ITipoPanelRepository extends JpaRepository<TipoPanel, Integer> {

	@Query("select count (m.nTipoPanel) from TipoPanel m where m.nTipoPanel=:name")
public int TiposPanelExistentes(@Param("name") String nombre);
}
