package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Hardware;


@Repository
public interface IHardwareRepository extends JpaRepository<Hardware, Integer>{
	@Query("select count(h.nombreHardware) from Hardware h where h.nombreHardware=:name")
	public int buscarHardware(@Param("name")String nombre);

	List<Hardware>findBynombreHardware(String name);
	
	@Query(value="select har.*, com.name_company from hardware har \r\n"
			+ "inner join companies com on har.id_company = com.id_company\r\n"
			+ "where har.id_company = (\r\n"
			+ "select id_company  from hardware\r\n"
			+ "group by id_company\r\n"
			+ "order by count(id_hardware) desc\r\n"
			+ "limit 1)",  nativeQuery = true)
	public List<String[]> reportMarca();
	
	
	
	@Query(value="select  rp.id_hardware,h.nombre_hardware, h.modelo_hardware, rp.cantidad_reco, h.precio_hardware from hardware h \r\n"
			+ "join recomendacion_parte rp on h.id_hardware=rp.id_hardware\r\n"
			+ "join recomendacion r on r.id_recomendacion = rp.id_recomendacion\r\n"
			+ "join users u on u.id = r.id where rp.fecha_registro <= (CURRENT_DATE ) and rp.fecha_registro >= (CURRENT_DATE -30) \r\n",  nativeQuery = true)
	public List<String[]> reportCantHardware();
	
	
	@Query(value="select  sum(rp.cantidad_reco) from hardware h \r\n"
			+ "join recomendacion_parte rp on h.id_hardware=rp.id_hardware\r\n"
			+ "join recomendacion r on r.id_recomendacion = rp.id_recomendacion\r\n"
			+ "join users u on u.id = r.id \r\n",  nativeQuery = true)
	public List<String[]> reportValueCantHardware();
	
	//@Query(value="SELECT ")
	//public List<String[]>mostSold();
}
