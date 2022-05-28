package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.TipoPanel;

public interface ITipoPanelService {

		public boolean insert(TipoPanel tipopanel);
		
		List<TipoPanel> list();		
		TipoPanel listarId(int idTamanoMB);
		public void delete(int idTamanoMB);
}
