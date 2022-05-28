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
//import org.springframework.security.access.annotation.Secured;
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

import pe.edu.upc.entities.Recomendacion;
import pe.edu.upc.serviceinterfaces.IRecomendacionService;
import pe.edu.upc.serviceinterfaces.ISubirFotoService;
import pe.edu.upc.serviceinterfaces.ITipoRecomendacionService;
import pe.edu.upc.serviceinterfaces.IUsuarioService;

@Controller
@RequestMapping("/recomendations")
public class RecomendacionController {
	@Autowired
	private IRecomendacionService rService;
	@Autowired
	private IUsuarioService uService;
	@Autowired
	private ITipoRecomendacionService trService;
	@Autowired
	private ISubirFotoService subirarchivoService;

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newRecomendation(Model model) {
		model.addAttribute("recomendacion", new Recomendacion());
		model.addAttribute("listaUsuarios", uService.list());
		model.addAttribute("listaTipoRecomendacion", trService.list());
		model.addAttribute("recomendacion", new Recomendacion());
		return "recomendacion/recomendacion";
	}

	@GetMapping("/list")
	public String listRecomendation(Model model) {
		try {
			model.addAttribute("recomendacion", new Recomendacion());
			model.addAttribute("listaRecomendaciones", rService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			// TODO: handle exception
		}
		return "recomendacion/listRecomendacion";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/save")
	public String insertRecomendacion(@ModelAttribute @Valid Recomendacion objReco, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.list());
			model.addAttribute("listaTipoRecomendacion", trService.list());
			return "recomendacion/recomendacion";
		} else {
			if (!foto.isEmpty()) {
				System.out.println("Foto: " + foto.getName());

				if (objReco.getIdRecomendacion() > 0 && objReco.getFotoRecomendacion() != null
						&& objReco.getFotoRecomendacion().length() > 0) {

					subirarchivoService.delete(objReco.getFotoRecomendacion());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = subirarchivoService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				objReco.setFotoRecomendacion(uniqueFilename);
			} else {
				String def = "def.png";
				objReco.setFotoRecomendacion(def);
			}
			boolean flag = rService.insert(objReco);
			if (flag) {
				return "redirect:/recomendations/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/recomendations/new";
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
		Recomendacion recomendacion = rService.listarId(id);
		if (recomendacion == null) {
			flash.addFlashAttribute("error", "La recomendacion no existe en la base de datos");
			return "recomendacion/listRecomendacion";
		}
		model.put("recomendacion", recomendacion);
		model.put("titulo", "Detalle de recomendacion: " + recomendacion.getNombreRecomendacion());
		return "recomendacion/ver";
	}

	@RequestMapping("/list")
	public String listRecomendacion(Map<String, Object> model) {
		model.put("listaRecomendacion", rService.list());
		return "recomendacion/listRecomendacion";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Recomendacion reco) {
		rService.listarId(reco.getIdRecomendacion());
		return "recomendacion/listRecomendacion";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Recomendacion objRecomendacion = rService.listarId(id);
		if (objRecomendacion == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/recomendations/list";
		} else {
			model.addAttribute("listaUsuarios", uService.list());
			model.addAttribute("listaTipoRecomendacion", trService.list());
			model.addAttribute("recomendacion", objRecomendacion);
			return "recomendacion/recomendacion";
		}
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteRecomendacion(Model model, @RequestParam(value = "id") Integer id) {
		rService.delete(id);
		model.addAttribute("recomendacion", new Recomendacion());
		model.addAttribute("listaRecomendaciones", rService.list());
		return "recomendacion/listRecomendacion";
	}

	@RequestMapping("/search")
	public String findRecomendacion(@ModelAttribute Recomendacion recomendacion, Model model) {
		List<Recomendacion> listarRecomendaciones;
		listarRecomendaciones = rService.findBynombreRecomendacion(recomendacion.getNombreRecomendacion());
		model.addAttribute("listaRecomendaciones", listarRecomendaciones);
		return "recomendacion/listRecomendacion";
	}

}
