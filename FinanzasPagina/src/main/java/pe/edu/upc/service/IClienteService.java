package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Cliente;


public interface IClienteService {
	public boolean insertar(Cliente cliente);
	public void eliminar(int idcliente);
	List<Cliente> listar();
	boolean modificar(Cliente cliente);
	public Optional<Cliente> listarId(int id);
	
}
