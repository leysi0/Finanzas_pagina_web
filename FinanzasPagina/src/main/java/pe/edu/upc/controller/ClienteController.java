package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.Date;
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
import pe.edu.upc.service.IClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private IClienteService cService;
	
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Cliente objCliente, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listcredito",cService.listar());
			return "cliente";
		}
		else {
			Date requestday=new Date();
			objCliente.setFecha_creación(requestday);;
			
			boolean flag = cService.insertar(objCliente);
			if (flag) {
				return "redirect:/cliente/listar";
			}
			else {
				model.addAttribute("mensaje", "Sucedio un error");
				return "redirect:/credito/irRegistrar";
			}
		}
		}

	@RequestMapping("/irRegistrarCliente")
	public String irRegistrar(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "registro";
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
		
    	model.put("cliente", new Cliente());
 		return "listCredito";
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir){
		
		Optional<Cliente> cliente = cService.listarId(id);
		
		if (cliente == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un rochesin");
			return "redirect:/credito/listar";
		}
		else {
			model.addAttribute("credito", cliente.get());
			return "Modificar_Credito";
		}
	}
	
	@RequestMapping("/guardar")
	public String guardar(@ModelAttribute @Valid Cliente objUser, BindingResult binRes, Model model) throws ParseException
	{
			Date requestday = new Date();
			objUser.setFecha_creación(requestday);;
			boolean flag = cService.modificar(objUser);
			if (flag) {
				return "redirect:/cliente/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "ModificarCliente";
			}
		}
	
}
