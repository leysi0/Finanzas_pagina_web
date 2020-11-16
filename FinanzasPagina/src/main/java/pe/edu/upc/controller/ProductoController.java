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
import pe.edu.upc.model.Producto;
import pe.edu.upc.service.IProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	private IProductoService pService;
	
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Producto objProducto, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listproducto",pService.listar());
			return "producto";
		}
		else {
			boolean flag = pService.insertar(objProducto);
			if (flag) {
				return "redirect:/producto/listar";
			}
			else {
				model.addAttribute("mensaje", "Sucedio un error");
				return "redirect:/producto/irRegistrar";
			}
		}
		}

	@RequestMapping("/irRegistrarProducto")
	public String irRegistrar(Model model) {
		model.addAttribute("producto", new Producto());
		return "Añadir_producto";
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				pService.eliminar(id);
				model.put("listproductos", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listproductos", pService.listar());
		}
		return "Lista_productos";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		/*Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDetails  userDetail = (UserDetails) auth.getPrincipal();
        cuenta2 = this.uService.getAccount(userDetail.getUsername()); //username=correo*/
		
    	model.put("listproductos", pService.listar());
 		return "Lista_productos";
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir){
		
		Optional<Producto> producto = pService.listarId(id);
		
		if (producto == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un rochesin");
			return "redirect:/producto/listar";
		}
		else {
			model.addAttribute("producto", producto.get());
			return "Modificar_Producto";
		}
	}
	
	@RequestMapping("/guardar")
	public String guardar(@ModelAttribute @Valid Producto objProducto, BindingResult binRes, Model model) throws ParseException
	{
			
			boolean flag = pService.modificar(objProducto);
			if (flag) {
				return "redirect:/Producto/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "ModificarProducto";
			}
		}
	
}
