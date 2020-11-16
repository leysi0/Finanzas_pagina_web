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

import pe.edu.upc.model.TipoVenta;
import pe.edu.upc.model.Venta;
import pe.edu.upc.service.ITipoVentaService;
import pe.edu.upc.service.IVentaService;

@Controller
@RequestMapping("/tipoventa")
public class TipoVentaController {

	@Autowired
	private ITipoVentaService vtService;

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid TipoVenta objTVenta, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listtipoventa",vtService.listar());
			return "tipoventa";
		}
		
		else {
			boolean flag = vtService.insertar(objTVenta);
			if (flag) {
				return "redirect:/tipoventa/listar";
			}
			else {
				model.addAttribute("mensaje", "Sucedio un error");
				return "redirect:/tipoventa/irRegistrar";
			}
		}
		}

	@RequestMapping("/irRegistrarVenta")
	public String irRegistrar(Model model) {
		model.addAttribute("tipoventa", new Venta());
		return "registro_venta";
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				vtService.eliminar(id);
				model.put("listtiposventas", vtService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listtiposventas", vtService.listar());
		}
		return "listVentas";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
	   	model.put("tipoventa", new Venta());
 		return "listtipoVenta";
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir){
		
		Optional<TipoVenta> cliente = vtService.listarId(id);
		
		if (cliente == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un rochesin");
			return "redirect:/tipoventa/listar";
		}
		else {
			model.addAttribute("venta", cliente.get());
			return "Modificar_TipoVenta";
		}
	}
	
	@RequestMapping("/guardar")
	public String guardar(@ModelAttribute @Valid TipoVenta objVenta, BindingResult binRes, Model model) throws ParseException
	{
			boolean flag = vtService.modificar(objVenta);
			if (flag) {
				return "redirect:/tipoventa/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "ModificarTipoVenta";
			}
		}
	
}
