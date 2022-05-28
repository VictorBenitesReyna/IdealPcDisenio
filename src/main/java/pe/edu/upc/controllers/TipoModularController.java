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

import pe.edu.upc.entities.TypeModular;
import pe.edu.upc.serviceinterfaces.ITipoModularService;

@Controller
@RequestMapping("/tipomodulares")
public class TipoModularController {

	@Autowired
	private ITipoModularService tmService;
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newTipoModular(Model model) {
		model.addAttribute("tipomodular", new TypeModular());
		return "tipoModular/tipoModular";
	}

	@GetMapping("/list")
	public String listTipoModulares(Model model) {
		try {
			model.addAttribute("typeModular", new TypeModular());
			model.addAttribute("listaTipoModular", tmService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			// TODO: handle exceptionSS
		}
		return "tipoModular/listTipoModular";
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String saveTipoModular(@ModelAttribute("tipomodular")@Valid TypeModular tipomodular, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
            return "tipoModular/tipoModular";
        } else {
            boolean rpta = tmService.insert(tipomodular);

            if (rpta ) {
                model.addAttribute("mensaje", "ya existe");
                return "tipoModular/tipoModular";

            } else {
                model.addAttribute("mensaje","Se guardó correctamente");
                status.setComplete();
            }
        }
        model.addAttribute("tipomodular", new TypeModular());
        return "redirect:/tipomodulares/list";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute TypeModular tipomodular) {
		tmService.listarId(tipomodular.getId_modular());
		return "tipoModular/listTipoModular";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		TypeModular objTipoModular = tmService.listarId(id);
		if (objTipoModular == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrió un error");
			return "redirect:/tipomodulares/list";
		} else {
			model.addAttribute("tipomodular", objTipoModular);
			return "tipoModular/tipoModular";
		}
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String delete(Model model, @RequestParam(value = "id") Integer id) {
		tmService.delete(id);
		model.addAttribute("listaTipoModular", tmService.list());
		return "tipoModular/listTipoModular";
	}
}
