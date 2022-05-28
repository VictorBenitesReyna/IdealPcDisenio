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

import pe.edu.upc.entities.TamanoMB;
import pe.edu.upc.entities.TipoPanel;
import pe.edu.upc.serviceinterfaces.ITipoPanelService;

@Controller
@RequestMapping("/tipopanel")
public class TipoPanelController {
	@Autowired
	private ITipoPanelService tpService;
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newTipoPanel(Model model) {
		model.addAttribute("tipopanel", new TipoPanel());
		return "tipopanel/tipopanel";
	}
	
	@GetMapping("/list")
	public String listTiposPanel(Model model) {
		try {
			model.addAttribute("tipopanel", new TipoPanel());
			model.addAttribute("listaTiposPanel", tpService.list());
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "tipopanel/listTipoPanel";
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String saveTipoPanel(@Validated TipoPanel tipopanel, BindingResult result, Model model, SessionStatus status)
	throws Exception{
		if(result.hasErrors()) {
			model.addAttribute("listaDistritos", tpService.list());
			return "tipopanel/tipopanel";
		}else {
			boolean flag = tpService.insert(tipopanel);
			if (flag) {
				return "redirect:/tipopanel/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/tipopanel/new";
			}
		}
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String,Object>model,@ModelAttribute TamanoMB tmb) {
		tpService.listarId(tmb.getIdTamanoMB());
		return "tipopanel/listTipoPanel";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id,Model model, RedirectAttributes objRedir) {
		TipoPanel objTipoPanel=tpService.listarId(id);
		if(objTipoPanel==null) {
			objRedir.addFlashAttribute("mensaje", "ocurrió un error");
			return "redirect:/tipopanel/list";
		}else {
			model.addAttribute("tipopanel",objTipoPanel);
			return "tipopanel/tipopanel";
		}
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteTipoPanel(Model model, @RequestParam(value="id")Integer id) {
		tpService.delete(id);
		model.addAttribute("listaTiposPanel", tpService.list());
		return "tipopanel/listTipoPanel";
	}
	
}
