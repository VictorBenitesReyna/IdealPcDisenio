package pe.edu.upc.controllers;


import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Users;
import pe.edu.upc.serviceinterfaces.IDistritoService;
import pe.edu.upc.serviceinterfaces.ITipoUsuarioService;

@Controller
@RequestMapping
public class LoginController {
	

	@Autowired
	private IDistritoService dService;
	@Autowired
	private ITipoUsuarioService tService;
	

	Date date = new Date();
	java.sql.Date date2;

	@GetMapping(value = { "/login", "/" })
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {

		if (principal != null) {
			return "redirect:/welcome/bienvenido";
		}

		if (error != null) {
			model.addAttribute("error",
					"Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
		}

		if (logout != null) {
			model.addAttribute("success", "Ha cerrado sesión con éxito!");
		}

		return "login";
	}
	
	@GetMapping("/createAccount")
	public String createAccount(Model model) {
		model.addAttribute("listaDistritos", dService.list());
		model.addAttribute("listaTipos", tService.list());
		Users newuser = new Users();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formatdate = formatter.format(date);
		date2 =  java.sql.Date.valueOf(formatdate);
		newuser.setRegistrationdate(date2);
		model.addAttribute("users", newuser);
		return "usuario/createAccount";
	}
}

