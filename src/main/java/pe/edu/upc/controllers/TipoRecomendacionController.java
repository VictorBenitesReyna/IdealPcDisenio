package pe.edu.upc.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.TypeRecomendation;
import pe.edu.upc.serviceinterfaces.ITipoRecomendacionService;

@Controller
@RequestMapping("/tipoderecomendaciones")
public class TipoRecomendacionController {

	@Autowired
	private ITipoRecomendacionService trService;

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newTipoRecomendacion(Model model) {
		model.addAttribute("typeRecomendacion", new TypeRecomendation());
		model.addAttribute("listaTipoRecomendacion", trService.list());
		model.addAttribute("typeRecomendacion", new TypeRecomendation());
		return "tipoRecomendacion/tipoRecomendacion";
	}

	@GetMapping("/list")
	public String listTipoRecomendaciones(Model model) {
		try {
			model.addAttribute("typeRecomendation", new TypeRecomendation());
			model.addAttribute("listaTipoRecomendacion", trService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "tipoRecomendacion/listTipoRecomendacion";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String saveTipoRecomendacion(@ModelAttribute("typeRecomendacion") @Valid TypeRecomendation tiporecomendacion, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "tipoRecomendacion/tipoRecomendacion";
		} else {
			boolean rpta = trService.insert(tiporecomendacion);
			if (rpta ) {
				model.addAttribute("mensaje", "ya existe");
				return "tipoRecomendacion/tipoRecomendacion";
			} else {
				model.addAttribute("mensaje","Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("typeRecomendation", new TypeRecomendation());
		return "redirect:/tipoderecomendaciones/list";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute TypeRecomendation tiporecomendacion) {
		trService.listarId(tiporecomendacion.getId_recomendation());
		return "tipoRecomendacion/listTipoRecomendacion";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		TypeRecomendation objTipoRecomendacion = trService.listarId(id);
		if (objTipoRecomendacion == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrió un error");
			return "redirect:/tipoderecomendaciones/list";
		} else {
			model.addAttribute("tipoRecomendacion", objTipoRecomendacion);
			return "tipoRecomendacion/tipoRecomendacion";
		}
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteMarca(Model model, @RequestParam(value = "id") Integer id) {
		trService.delete(id);
		model.addAttribute("listaTipoRecomendacion", trService.list());
		return "tiporecomendacion/listTipoRecomendacion";
	}
}
