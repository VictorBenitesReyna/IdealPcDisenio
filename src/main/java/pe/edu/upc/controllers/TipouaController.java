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

import pe.edu.upc.entities.Typeua;
import pe.edu.upc.serviceinterfaces.ITipouaService;

@Controller
@RequestMapping("/tipouas")
public class TipouaController {
	@Autowired
	private ITipouaService cService;
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newTipoua(Model model) {
		model.addAttribute("tipoua", new Typeua());
		model.addAttribute("listaTipouas", cService.list());
		return "tipoua/tipoua";
	}

	@GetMapping("/list")
	public String listTipouas(Model model) {
		try {
			model.addAttribute("typeua", new Typeua());
			model.addAttribute("listaTipouas", cService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "tipoua/listTipouas";
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String saveMarca(@Valid Typeua tipoua, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "tipoua/tipoua";
		} else {
			int rpta = cService.insert(tipoua);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "tipoua/tipoua";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("tipoua", new Typeua());
		return "redirect:/tipouas/list";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Typeua tipo) {
		cService.listarId(tipo.getId_ua());
		return "tipoua/listTipouas";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Typeua objTipoua = cService.listarId(id);
		if (objTipoua == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrió un error");
			return "redirect:/tipouas/list";
		} else {
			model.addAttribute("tipoua", objTipoua);
			return "tipoua/tipoua";
		}
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteTipoua(Model model, @RequestParam(value = "id") Integer id) {
		cService.delete(id);
		model.addAttribute("listaTipouas", cService.list());
		return "tipoua/listTipouas";
	}
}
