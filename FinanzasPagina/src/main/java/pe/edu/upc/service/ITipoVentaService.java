package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.TipoVenta;


public interface ITipoVentaService {
	public boolean insertar(TipoVenta Tipoventa);
	public void eliminar(int idtipoventa);
	List<TipoVenta> listar();
	boolean modificar(TipoVenta Tipoventa);
	public Optional<TipoVenta> listarId(int id);
	
}
