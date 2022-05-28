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

import pe.edu.upc.entities.Role;
import pe.edu.upc.serviceinterfaces.IRoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {
	@Autowired
	private IRoleService rService;
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newRole(Model model) {
		try {
			model.addAttribute("role", new Role());
			model.addAttribute("listaRoles", rService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			// TODO: handle exception
		}
		return "role/listRoles";
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String saveRole(@Validated Role role, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "role/role";
		} else {
			int rpta = rService.insert(role);
			if (rpta > 0) {
				model.addAttribute("mensaje", "ya existe");
				return "role/role";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("role", new Role());
		return "redirect:/roles/list";
	}
	@RequestMapping("/listarId")
	public String listarId(Map<String,Object>model,@ModelAttribute Role role) {
		rService.listId(role.getId_role().intValue());
		return "role/listRole";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id,Model model, RedirectAttributes objRedir) {
		Role objRole=rService.listId(id);
		if(objRole==null) {
			objRedir.addFlashAttribute("mensaje", "ocurrió un error");
			return "redirect:/roles/list";
		}else {
			model.addAttribute("role",objRole);
			return "role/role";
		}
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteRole(Model model, @RequestParam(value="id")Integer id) {
		
		rService.delete(id);
		model.addAttribute("listaRoles", rService.list());
		//model.addAttribute("role",new Role());
		return "role/listRole";
	}
	@RequestMapping("/search")
	public String findRole(@ModelAttribute Role role,Model model) {
		List<Role> listaRoles;
		listaRoles=rService.findByrol(role.getRol());
		model.addAttribute("listaRoles",listaRoles);
		return "role/listRole";
	}
}
