package pe.edu.upc.controllers;

import java.text.ParseException;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.support.SessionStatus;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.RecomendacionParte;
import pe.edu.upc.serviceinterfaces.IHardwareService;
import pe.edu.upc.serviceinterfaces.IRecomendacionParteService;
import pe.edu.upc.serviceinterfaces.IRecomendacionService;

@Controller
@RequestMapping("/recoparts")
public class RecomendacionParteController {
	@Autowired
	private IRecomendacionParteService rpService;
	@Autowired
	private IHardwareService hService;
	@Autowired
	private IRecomendacionService rService;
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newTienda(Model model) {
		model.addAttribute("recomendacionParte", new RecomendacionParte());
		model.addAttribute("listaRecomendaciones", rService.list());
		model.addAttribute("listaHardwares", hService.list());
		model.addAttribute("recomendacionParte", new RecomendacionParte());
		return "recomendParts/recomendParts";
	}

	@GetMapping("/list")
	public String listTienda(Model model) {
		try {
			model.addAttribute("recomendacionParte", new RecomendacionParte());
			model.addAttribute("listaRecomendacionPartes", rpService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			// TODO: handle exception
		}
		return "recomendParts/listRecomendParts";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/save")
	public String insertProduct(@ModelAttribute @Valid RecomendacionParte objRp, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaRecomendaciones", rService.list());
			model.addAttribute("listaHardwares", hService.list());
			return "recomendParts/recomendParts";
		} else {
			
			boolean flag = rpService.insert(objRp);
			if (flag) {
				return "redirect:/recoparts/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/recoparts/new";
			}
		}
	}

	

	@RequestMapping("/list")
	public String listTiendas(Map<String, Object> model) {
		model.put("listaRecomendacionPartes", rpService.list());
		return "recomendParts/listRecomendParts";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute RecomendacionParte rp) {
		rpService.listarId(rp.getIdRecomendPart());
		return "recomendParts/listRecomendParts";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		RecomendacionParte objRp = rpService.listarId(id);
		if (objRp == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/recoparts/list";
		} else {
			model.addAttribute("listaRecomendaciones", rService.list());
			model.addAttribute("listaHardwares", hService.list());
			model.addAttribute("recomendacionParte", objRp);
			return "recomendParts/recomendParts";
		}
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteTienda(Model model, @RequestParam(value = "id") Integer id) {
		rpService.delete(id);
		model.addAttribute("recomendacionParte", new RecomendacionParte());
		model.addAttribute("listaRecomendacionPartes", rpService.list());
		return "recomendParts/listRecomendParts";
	}

	@RequestMapping("/search")
	public String findTienda(@ModelAttribute RecomendacionParte rp, Model model) {
		List<RecomendacionParte> listaRecomendacionParte;
		listaRecomendacionParte = rpService.findBynombreRecomendacionParte(rp.getNombreRecomendacionParte());
		model.addAttribute("listaRecomendacionPartes", listaRecomendacionParte);
		return "recomendParts/listRecomendParts";
	}
}
