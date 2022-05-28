package pe.edu.upc.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Role;
import pe.edu.upc.serviceinterfaces.ITipoUsuarioService;

@Controller
@RequestMapping("/tipousuarios")
public class TipoUsuarioController {
	@Autowired
	private ITipoUsuarioService tuService;
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newTipoUsuario(Model model) {
		model.addAttribute("tipousuario", new Role());
		return "tipousuario/tipousuario";
	}

	@GetMapping("/list")
	public String listTipoUsuario(Model model) {
		try {
			model.addAttribute("tipousuario", new Role());
			model.addAttribute("listaTipoUsuario", tuService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			// TODO: handle exception
		}
		return "tipousuario/listTipoUsuario";
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String saveTipoUsuario(@Validated Role tipousuario, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {			
			return "tipoUsuario/tipoUsuario";
		} else {
			boolean flag = tuService.insert(tipousuario);
			if (flag) {
				return "redirect:/tipousuarios/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/tipousuarios/new";
			}
		}
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Role tipousuario) {
		tuService.listarId(tipousuario.getId_role());
		return "tipoUsuario/listTipoUsuario";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model, RedirectAttributes objRedir) {
		Role objTipoUsuario = tuService.listarId(id);
		if (objTipoUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrió un error");
			return "redirect:/tipousuarios/list";
		} else {
			model.addAttribute("tipousuario", objTipoUsuario);
			return "tipoUsuario/tipoUsuario";
		}
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String delete(Model model, @RequestParam(value = "id") Long id) {
		tuService.delete(id);
		model.addAttribute("listaTipoUsuario", tuService.list());
		return "tipoUsuario/listTipoUsuario";
	}
}
