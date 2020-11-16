package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.model.Cliente;
import pe.edu.upc.model.Credito;
import pe.edu.upc.service.ICreditoService;

@Controller
@RequestMapping("/credito")
public class CreditoController {
	
	@Autowired
	private ICreditoService cService;
	
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Credito objCred, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listcredito",cService.listar());
			return "credito";
		}
		else {
		
			boolean flag = cService.insertar(objCred);
			if (flag) {
				return "redirect:/credito/listar";
			}
			else {
				model.addAttribute("mensaje", "Sucedio un error");
				return "redirect:/credito/irRegistrar";
			}
		}
		}
	
	@RequestMapping("/irRegistrarCredito")
	public String irRegistrar(Model model) {
		model.addAttribute("credito", new Credito());
		return "register";
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				cService.eliminar(id);
				model.put("listCreditos", cService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listCreditos", cService.listar());
		}
		return "listCredito";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		/*Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDetails  userDetail = (UserDetails) auth.getPrincipal();
        cuenta2 = this.uService.getAccount(userDetail.getUsername()); //username=correo*/
		
    	model.put("credito", new Credito());
 		return "listCredito";
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir){
		
		Optional<Credito> credito = cService.listarId(id);
		
		if (credito == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un rochesin");
			return "redirect:/credito/listar";
		}
		else {
			model.addAttribute("credito", credito.get());
			return "Modificar_Credito";
		}
	}
	
	@RequestMapping("/guardar")
	public String guardar(@ModelAttribute @Valid Credito objUser, BindingResult binRes, Model model) throws ParseException
	{
			boolean flag = cService.modificar(objUser);
			if (flag) {
				return "redirect:/credito/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "Modificar_Credito";
			}
		}
	
}
