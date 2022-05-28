package pe.edu.upc.controllers;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

import pe.edu.upc.entities.Hardware;
import pe.edu.upc.serviceinterfaces.IHardwareService;
import pe.edu.upc.serviceinterfaces.IMarcaService;
import pe.edu.upc.serviceinterfaces.ISubirFotoService;

@Controller
@RequestMapping("/hardware")
public class HardwareController {
	@Autowired
	private IHardwareService hService;
	@Autowired
	private IMarcaService mService;
	@Autowired
	private ISubirFotoService subirarchivoService;

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newHardware(Model model) {
		model.addAttribute("hardware", new Hardware());
		model.addAttribute("listaMarcas", mService.list());
		model.addAttribute("hardware", new Hardware());
		return "hardware/hardware";

	}

	@GetMapping("/list")
	public String listHardware(Model model) {
		try {
			model.addAttribute("hardware", new Hardware());
			model.addAttribute("listaHardwares", hService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			// TODO: handle exception
		}
		return "hardware/listHardware";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/save")
	public String insertHardware(@ModelAttribute @Valid Hardware objHard, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaMarcas", mService.list());
			return "hardware/hardware";
		} else {

			if (!foto.isEmpty()) {
				if (objHard.getIdHardware() > 0 && objHard.getFotoHardware() != null
						&& objHard.getFotoHardware().length() > 0) {
					subirarchivoService.delete(objHard.getFotoHardware());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = subirarchivoService.copy(foto);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				objHard.setFotoHardware(uniqueFilename);
			}
			boolean flag = hService.insert(objHard);
			if (flag) {
				return "redirect:/hardware/list";
			} else {
				return "redirect:/hardware/new";
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
		Hardware hardware = hService.listarId(id);
		if (hardware == null) {
			flash.addFlashAttribute("error", "El Hardware no existe en la base de datos");
			return "hardware/listHardware";
		}
		model.put("hardware", hardware);
		model.put("titulo", "Detalle de Hardware: " + hardware.getNombreHardware());
		return "hardware/ver";
	}

	@RequestMapping("/list")
	public String listHardwares(Map<String, Object> model) {
		model.put("listaHardwares", hService.list());
		return "hardware/listHardware";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Hardware hard) {
		hService.listarId(hard.getIdHardware());
		return "hardware/listHardware";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Hardware objHard = hService.listarId(id);
		if (objHard == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/hardware/list";
		} else {
			model.addAttribute("listaMarcas", mService.list());
			model.addAttribute("hardware", objHard);
			return "hardware/hardware";
		}
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteHardware(Model model, @RequestParam(value = "id") Integer id) {
		hService.delete(id);
		model.addAttribute("listaHardwares", hService.list());
		model.addAttribute("hardware", new Hardware());
		return "hardware/listHardware";
	}

	@RequestMapping("/search")
	public String findCategory(@ModelAttribute Hardware hardware, Model model) {
		List<Hardware> listaHardwares;
		listaHardwares = hService.findBynombreHardware(hardware.getNombreHardware());
		model.addAttribute("listaHardwares", listaHardwares);
		return "hardware/listHardware";
	}

	@RequestMapping("/reporte4")
	public String quantityUsers(Map<String, Object> model) {
		model.put("listaMarcas", hService.reportMarca());
		model.put("nmarca", hService.reportMarca().get(0)[7]);
		model.put("cantidad", hService.reportMarca().size());
		return "reports/reportMarca";
	}

	@GetMapping("/reportes")
	public String listReports(Model model) {

		return "/reports/reports";
	}

	@RequestMapping("/reporte1")
	public String reportCantHardware(Map<String, Object> model) {
		model.put("listaHardwares", hService.reportCantHardware());
		// System.out.println("cant usu: " + hService.reportCantHardware().size());
		String[] val = hService.reportValueCantHardware().get(0);
	
		//int suma = 0;
		//try {
		//	for (int i = 0; i < val.length; i++) {
		//		
		//		suma += (int) val[i];
		//	}
		//	/*for (int x = 0; x < val.length; x++) {
		//		suma = suma + val[x];
		//	}*/
		//} catch (Exception e) {
		//	System.out.println("ERROR DEL FOR");
		//}

		//String asw = Integer.toString(suma);
		//System.out.println("Object value: "+val[0]);
		//System.out.println("cant vecess: "+ val);
		model.put("cantidad", val[0]);
		return "reports/reportCantHard";
	}

}
