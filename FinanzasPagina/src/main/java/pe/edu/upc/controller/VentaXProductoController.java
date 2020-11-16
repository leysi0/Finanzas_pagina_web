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

import pe.edu.upc.model.Venta;
import pe.edu.upc.model.VentaXProducto;
import pe.edu.upc.service.IVentaXProductoService;

@Controller
@RequestMapping("/ventaproducto")
public class VentaXProductoController {

	@Autowired
	private IVentaXProductoService vPService;

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid VentaXProducto objVPenta, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listVentaProducto",vPService.listar());
			return "VentaProducto";
		}
		
		else {
			
			boolean flag = vPService.insertar(objVPenta);
			if (flag) {
				return "redirect:/ventaproducto/listar";
			}
			else {
				model.addAttribute("mensaje", "Sucedio un error");
				return "redirect:/ventaproducto/irRegistrarVenta";
			}
		}
		}

	@RequestMapping("/irRegistrarVenta")
	public String irRegistrar(Model model) {
		model.addAttribute("ventaProducto", new Venta());
		return "registro";
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				vPService.eliminar(id);
				model.put("listVentas", vPService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listVentasXProductos", vPService.listar());
		}
		return "listVenta";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
	   	model.put("ventaXproducto", new Venta());
 		return "listVenta";
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir){
		
		Optional<VentaXProducto> cliente = vPService.listarId(id);
		
		if (cliente == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un rochesin");
			return "redirect:/ventaproducto/listar";
		}
		else {
			model.addAttribute("venta", cliente.get());
			return "Modificar_ventaproducto";
		}
	}
	
	@RequestMapping("/guardar")
	public String guardar(@ModelAttribute @Valid VentaXProducto objVenta, BindingResult binRes, Model model) throws ParseException
	{
		
			boolean flag = vPService.modificar(objVenta);
			if (flag) {
				return "redirect:/venta/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "ModificarVenta";
			}
		}
	
}
