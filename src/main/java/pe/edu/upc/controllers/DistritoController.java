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

import pe.edu.upc.entities.Distrito;
import pe.edu.upc.serviceinterfaces.IDistritoService;

@Controller
@RequestMapping("/distritos")
public class DistritoController {
	@Autowired
	private IDistritoService dService;
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newDistrito(Model model) {
		model.addAttribute("distrito", new Distrito());
		return "distrito/distrito";
	}
	@Secured("ROLE_ADMIN")
	@GetMapping("/list")
	public String listDistritos(Model model) {
		try {
			model.addAttribute("distrito", new Distrito());
			model.addAttribute("listaDistritos", dService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			// TODO: handle exception
		}
		return "distrito/listDistrito";
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String saveDistrito(@Validated Distrito distrito, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "distrito/distrito";
		} else {
			int rpta = dService.insert(distrito);
			if (rpta > 0) {
				model.addAttribute("mensaje", "ya existe");
				return "distrito/distrito";
			} else {
				model.addAttribute("mensaje","Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("distrito",new Distrito());
		return "redirect:/distritos/list";
	}
	@RequestMapping("/listarId")
	public String listarId(Map<String,Object>model,@ModelAttribute Distrito dis) {
		dService.listarId(dis.getIdDistrito());
		return "distrito/listDistrito";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id,Model model, RedirectAttributes objRedir) {
		Distrito objDistrito=dService.listarId(id);
		if(objDistrito==null) {
			objRedir.addFlashAttribute("mensaje", "ocurrió un error");
			return "redirect:/distritos/list";
		}else {
			model.addAttribute("distrito",objDistrito);
			return "distrito/distrito";
		}
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteDistrito(Model model, @RequestParam(value="id")Integer id) {
		
		dService.delete(id);
		model.addAttribute("listaDistritos", dService.list());
		model.addAttribute("distrito", new Distrito());
		return "distrito/listDistrito";
	}
	@RequestMapping("/search")
	public String findDistrito(@ModelAttribute Distrito distri,Model model) {
		List<Distrito> listaDistritos;
		listaDistritos=dService.findBynombreDistrito(distri.getNombreDistrito());
		model.addAttribute("listaDistritos",listaDistritos);
		return "distrito/listDistrito";
	}
}
