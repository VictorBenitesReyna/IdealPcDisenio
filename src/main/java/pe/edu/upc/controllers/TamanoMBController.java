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
import pe.edu.upc.serviceinterfaces.ITamanoMBService;

@Controller
@RequestMapping("/tamanomb")
public class TamanoMBController {
	@Autowired
	private ITamanoMBService mService;
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newTamanoMB(Model model) {
		model.addAttribute("tamanomb", new TamanoMB());
		return "tamanomb/tamanomb";
	}
	
	@GetMapping("/list")
	public String listTamanosMB(Model model) {
		try {
			model.addAttribute("tamanomb", new TamanoMB());
			model.addAttribute("listaTamanosMB", mService.list());
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "tamanomb/listTamanoMB";
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String saveTamanoMB(@Validated TamanoMB tamanomb, BindingResult result, Model model, SessionStatus status)
	throws Exception{
		if(result.hasErrors()) {
			model.addAttribute("listaDistritos", mService.list());
			return "tamanomb/tamanomb";
		}else {
			boolean flag = mService.insert(tamanomb);
			if (flag) {
				return "redirect:/tamanomb/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/tamanomb/new";
			}
		}
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String,Object>model,@ModelAttribute TamanoMB tmb) {
		mService.listarId(tmb.getIdTamanoMB());
		return "tamanomb/listTamanoMB";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id,Model model, RedirectAttributes objRedir) {
		TamanoMB objTamanoMB=mService.listarId(id);
		if(objTamanoMB==null) {
			objRedir.addFlashAttribute("mensaje", "ocurrió un error");
			return "redirect:/tamanomb/list";
		}else {
			model.addAttribute("tamanomb",objTamanoMB);
			return "tamanomb/tamanomb";
		}
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteTamanoMB(Model model, @RequestParam(value="id")Integer id) {
		mService.delete(id);
		model.addAttribute("listaTamanosMB", mService.list());
		model.addAttribute("tamanomb", new TamanoMB());
		return "tamanomb/listTamanoMB";
	}
	
	
}
