package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.TipoPanel;
import pe.edu.upc.repositories.ITipoPanelRepository;
import pe.edu.upc.serviceinterfaces.ITipoPanelService;

@Service
public class TipoPanelServiceImplement implements ITipoPanelService {

	@Autowired
	private ITipoPanelRepository tpR;

	@Override
	public boolean insert(TipoPanel tipopanel) {
		if (tipopanel.getUrlTipoPanel().endsWith(".com")) {
			TipoPanel rpta = tpR.save(tipopanel);
			if (rpta == null) {
				return false;
			} else {
				return true;
			}
		}else {
			return false;
		}

	}

	@Override
	public List<TipoPanel> list() {
		return tpR.findAll();
	}

	@Override

	@Transactional(readOnly = true)
	public TipoPanel listarId(int idTamanoMB) {
		Optional<TipoPanel> op = tpR.findById(idTamanoMB);
		return op.isPresent() ? op.get() : new TipoPanel();
	}

	@Override
	public void delete(int idTamanoMB) {
		// TODO Auto-generated method stub
		tpR.deleteById(idTamanoMB);
	}

}
