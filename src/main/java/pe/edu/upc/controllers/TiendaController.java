package pe.edu.upc.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Tienda;
import pe.edu.upc.serviceinterfaces.ISubirFotoService;
import pe.edu.upc.serviceinterfaces.ITiendaService;

@Controller
@RequestMapping("/store")
public class TiendaController {
	@Autowired
	private ITiendaService tService;
	@Autowired
	private ISubirFotoService subirarchivoService;
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newTienda(Model model) {
		model.addAttribute("tienda", new Tienda());
		model.addAttribute("tienda", new Tienda());
		return "tienda/tienda";
	}

	@GetMapping("/list")
	public String listTienda(Model model) {
		try {
			model.addAttribute("tienda", new Tienda());
			model.addAttribute("listaTiendas", tService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			// TODO: handle exception
		}
		return "tienda/listTienda";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/save")
	public String insertProduct(@ModelAttribute @Valid Tienda objTiend, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {
			return "tienda/tienda";
		} else {
			if (!foto.isEmpty()) {
				System.out.println("Foto: "+foto.getName());


				if (objTiend.getIdTienda() > 0 && objTiend.getFotoTienda() != null
						&& objTiend.getFotoTienda().length() > 0) {

					subirarchivoService.delete(objTiend.getFotoTienda());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = subirarchivoService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				objTiend.setFotoTienda(uniqueFilename);
			}else {
				String def="def.png";
				objTiend.setFotoTienda(def);
			}
			boolean flag = tService.insert(objTiend);
			if (flag) {
				return "redirect:/store/list";
			} else {
				model.addAttribute("mensaje", "Foto no válida");
				return "redirect:/store/new";
			}
		}
	}

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		Resource recurso = null;
		try {
			recurso = subirarchivoService.load(filename);
		} catch (MalformedURLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {
		Tienda tienda = tService.listarId(id);
		if (tienda == null) {
			flash.addFlashAttribute("error", "La tienda no existe en la base de datos");
			return "tienda/listTienda";
		}
		model.put("tienda", tienda);
		model.put("titulo", "Detalle de tienda: " + tienda.getNombreTienda());
		return "tienda/ver";
	}

	@RequestMapping("/list")
	public String listTiendas(Map<String, Object> model) {
		model.put("listaTiendas", tService.list());
		return "tienda/listTienda";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Tienda tiend) {
		tService.listarId(tiend.getIdTienda());
		return "tienda/listTienda";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Tienda objTienda = tService.listarId(id);
		if (objTienda == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/store/list";
		} else {
			model.addAttribute("tienda", objTienda);
			return "tienda/tienda";
		}
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteTienda(Model model, @RequestParam(value = "id") Integer id) {
		tService.delete(id);
		model.addAttribute("tienda", new Tienda());
		model.addAttribute("listaTiendas", tService.list());
		return "tienda/listTienda";
	}

	@RequestMapping("/search")
	public String findTienda(@ModelAttribute Tienda tienda, Model model) {
		List<Tienda> listaTiendas;
		listaTiendas = tService.findBynombreTienda(tienda.getNombreTienda());
		model.addAttribute("listaTiendas", listaTiendas);
		return "tienda/listTienda";
	}
	

}
