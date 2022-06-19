package pe.edu.upc.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import pe.edu.upc.entities.Users;
import pe.edu.upc.serviceinterfaces.ISubirFotoService;
import pe.edu.upc.serviceinterfaces.ITipoUsuarioService;
import pe.edu.upc.serviceinterfaces.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IUsuarioService uService;
	@Autowired
	private ISubirFotoService subirarchivoService;
	@Autowired
	private ITipoUsuarioService tService;
	
	boolean update= false;
	
	Date date = new Date();
	java.sql.Date date2;

	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/new")
	public String newUsuario(Model model) {
		model.addAttribute("listaTipos", tService.list());
		Users newuser = new Users();
		model.addAttribute("users", newuser);
		return "usuario/usuario";
	}

	@GetMapping("/list")
	public String listUsuarios(Model model) {
		try {
			model.addAttribute("users", new Users());
			model.addAttribute("listaUsuarios", uService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "usuario/listUsuario";
	}

	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping("/save")
	public String saveUsuario(@ModelAttribute @Valid Users usuario, BindingResult result, Model model,
			@RequestParam("file") MultipartFile photo, RedirectAttributes flash, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaTipos", tService.list());
			return "usuario/usuario";
		} else {
			if (!photo.isEmpty()) {
				if (Math.toIntExact(usuario.getId()) > 0 && usuario.getPhoto() != null
						&& usuario.getPhoto().length() > 0) {
					subirarchivoService.delete(usuario.getPhoto());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = subirarchivoService.copy(photo);

				} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				usuario.setPhoto(uniqueFilename);
			}
			String bcryptPassword = passwordEncoder.encode(usuario.getPassword());
			usuario.setPassword(bcryptPassword);

			//boolean flag = uService.insert(usuario);
			//System.out.println(usuario.getRegistrationdate());
			if (uService.findBynombreUsuario(usuario.getUsername()).isEmpty() || update) {
				uService.insert(usuario);
				update = false;
				return "redirect:/usuario/list";
			} else {
			    //System.out.println("Aqui");
				//model.addAttribute("mensaje", "Ocurrió un error");
				flash.addFlashAttribute("error", "Ya existe un usuario con el username ingresado");
				return "redirect:/usuario/new";
			}
		}
	}

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		Resource recurso = null;
		try {
			recurso = subirarchivoService.load(filename);
		} catch (MalformedURLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {
		Users usuario = uService.listarId(id);
		if (usuario == null) {
			flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
			return "usuario/listUsuario";
		}
		model.put("usuario", usuario);
		model.put("titulo", "Detalle de usuario: " + usuario.getUsername());
		return "usuario/ver";
	}

	@RequestMapping("/list")
	public String listUsuarios(Map<String, Object> model) {
		model.put("listaUsuario", uService.list());
		return "usuario/listUsuario";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Users usuario) {
		uService.listarId(usuario.getId());
		return "usuario/listUsuario";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Users objUsuario = uService.listarId(id);
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/usuario/list";
		} else {
			model.addAttribute("listaTipos", tService.list());
			model.addAttribute("users", objUsuario);
			update = true;
			return "usuario/usuario";
		}
	}


	@RequestMapping("/delete")
	public String deleteUsuario(Model model, @RequestParam(value = "id") int id) {
		uService.delete(id);
		model.addAttribute("usuario", new Users());
		model.addAttribute("listaUsuarios", uService.list());
		model.addAttribute("users", new Users());
		return "usuario/listUsuario";
	}
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping("/search")
	public String findUsuario(@ModelAttribute Users usuario, Model model) {
		List<Users> listaUsuarios;
		listaUsuarios = uService.findBynombreUsuario(usuario.getUsername());
		model.addAttribute("listaUsuarios", listaUsuarios);
		return "usuario/listUsuario";
	}
	
	@GetMapping("/createAccount")
	public String createAccount(Model model) {
		Users newuser = new Users();
		model.addAttribute("users", newuser);
		return "usuario/createAccount";
	}
	
	@RequestMapping("/saveAccount")
	public String saveAccount(@ModelAttribute @Valid Users usuario, BindingResult result, Model model,
			@RequestParam("file") MultipartFile photo, RedirectAttributes flash, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "usuario/createAccount";
		} else {
			if (!photo.isEmpty()) {
				if (Math.toIntExact(usuario.getId()) > 0 && usuario.getPhoto() != null
						&& usuario.getPhoto().length() > 0) {
					subirarchivoService.delete(usuario.getPhoto());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = subirarchivoService.copy(photo);

				} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				usuario.setPhoto(uniqueFilename);
			}
			String bcryptPassword = passwordEncoder.encode(usuario.getPassword());
			usuario.setPassword(bcryptPassword);
			

			usuario.setEnabled(true);
			System.out.println("Rol: "+tService.listarRol("ROLE_USER").getRol());
			usuario.setRoles(tService.listarRol("ROLE_USER"));

			//boolean flag = uService.insert(usuario);
			//System.out.println(usuario.getRegistrationdate());
			if (uService.findBynombreUsuario(usuario.getUsername()).isEmpty()) {
				uService.insert(usuario);
				return "redirect:/usuario/list";
			} else {
			    //System.out.println("Aqui");
				//model.addAttribute("mensaje", "Ocurrió un error");
				flash.addFlashAttribute("error", "Ya existe un usuario con el username ingresado");
				return "redirect:/usuario/createAccount";
			}
		}
	}
	
	
	@GetMapping("/reportes")
	public String listReports(Model model) {

		return "/reports/reports";
	}
	
	@RequestMapping("/reporte3")
	public String quantityUsers(Map<String, Object> model) {
		model.put("listaUsuarios", uService.quantityUsers());
		System.out.println("cant usu: " + uService.quantityUsers().size());
		model.put("cantidad", uService.quantityUsers().size());
		return "reports/quantityUsers";
	}
}
