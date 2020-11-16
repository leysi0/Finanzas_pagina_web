package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Credito;

public interface ICreditoService {
	public boolean insertar(Credito credito);
	public void eliminar(int idcredito);
	public Optional<Credito> listarId(int idCredito);
	List<Credito> listar();
	boolean modificar(Credito credito);
	
}
