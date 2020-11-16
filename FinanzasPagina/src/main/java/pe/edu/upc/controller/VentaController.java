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
import pe.edu.upc.service.IVentaService;

@Controller
@RequestMapping("/venta")
public class VentaController {

	@Autowired
	private IVentaService vService;

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Venta objVenta, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listcredito",vService.listar());
			return "cliente";
		}
		
		else {
			Date requestday=new Date();
			objVenta.setFecha_venta(requestday);
			
			boolean flag = vService.insertar(objVenta);
			if (flag) {
				return "redirect:/venta/listar";
			}
			else {
				model.addAttribute("mensaje", "Sucedio un error");
				return "redirect:/venta/irRegistrar";
			}
		}
		}

	@RequestMapping("/irRegistrarVenta")
	public String irRegistrar(Model model) {
		model.addAttribute("venta", new Venta());
		return "registro";
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				vService.eliminar(id);
				model.put("listVentas", vService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listVentas", vService.listar());
		}
		return "listVenta";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
	   	model.put("venta", new Venta());
 		return "listVenta";
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir){
		
		Optional<Venta> cliente = vService.listarId(id);
		
		if (cliente == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un rochesin");
			return "redirect:/venta/listar";
		}
		else {
			model.addAttribute("venta", cliente.get());
			return "Modificar_Venta";
		}
	}
	
	@RequestMapping("/guardar")
	public String guardar(@ModelAttribute @Valid Venta objVenta, BindingResult binRes, Model model) throws ParseException
	{
			Date requestday = new Date();
			objVenta.setFecha_venta(requestday);
			boolean flag = vService.modificar(objVenta);
			if (flag) {
				return "redirect:/venta/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "ModificarVenta";
			}
		}
	
}
