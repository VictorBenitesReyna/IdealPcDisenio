package pe.edu.upc.controllers;

import java.util.List;
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

import pe.edu.upc.entities.Companies;
import pe.edu.upc.serviceinterfaces.IMarcaService;

@Controller
@RequestMapping("/marca")
public class MarcaController {
	@Autowired
	private IMarcaService mService;
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newMarca(Model model) {
		model.addAttribute("marca", new Companies());
		return "marca/marca";
	}

	@GetMapping("/list")
	public String listMarcas(Model model) {
		try {
			model.addAttribute("companies", new Companies());
			model.addAttribute("listaMarcas", mService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "marca/listMarca";
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String saveMarca(@Validated @ModelAttribute("marca") Companies marca, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaMarcas", mService.list());
			return "marca/marca";
		} else {
			boolean flag = mService.insert(marca);
			if (flag) {
				return "redirect:/marca/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/marca/new";
			}
		}
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Companies marca) {
		mService.listarId(marca.getId_company());
		return "marca/listMarca";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Companies objMarca = mService.listarId(id);
		if (objMarca == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrió un error");
			return "redirect:/marca/list";
		} else {
			model.addAttribute("marca", objMarca);
			return "marca/marca";
		}
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteMarca(Model model, @RequestParam(value = "id") Integer id) {
		mService.delete(id);
		model.addAttribute("companies", new Companies());
		model.addAttribute("listaMarcas", mService.list());
		return "marca/listMarca";
	}
	
	@RequestMapping("/search")
	public String findMarca(@ModelAttribute Companies marca, Model model) {
		List<Companies> listaMarcas;
		listaMarcas=mService.findBynMarca(marca.getNameCompany());
		model.addAttribute("listaMarcas", listaMarcas);
		return "marca/listMarca";
	}
}
