package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Producto;


public interface IProductoService {
	public boolean insertar(Producto producto);
	public void eliminar(int idcliente);
	List<Producto> listar();
	boolean modificar(Producto producto);
	public Optional<Producto> listarId(int id);
	
}
