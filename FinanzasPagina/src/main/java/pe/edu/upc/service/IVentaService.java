package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Venta;


public interface IVentaService {
	public boolean insertar(Venta venta);
	public void eliminar(int idventa);
	List<Venta> listar();
	boolean modificar(Venta venta);
	public Optional<Venta> listarId(int id);
	
}
