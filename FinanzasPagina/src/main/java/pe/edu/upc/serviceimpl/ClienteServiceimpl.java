package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Cliente;
import pe.edu.upc.repository.IClienteRepository;
import pe.edu.upc.service.IClienteService;


@Service
public class ClienteServiceimpl implements IClienteService {
	
	@Autowired
	private IClienteRepository cCliente;

	@Override
	@Transactional
	public boolean insertar(Cliente cliente) {
		Cliente objCliente = cCliente.save(cliente);
		if (objCliente == null)
			return false;
		else
			return true;
	}

	
	@Override
	@Transactional
	public void eliminar(int idcliente) {
		cCliente.deleteById(idcliente);
		
	}
	
	@Override
	@Transactional
	public List<Cliente> listar() {
		return cCliente.findAll();
	}

	
	@Transactional	
	public boolean modificar(Cliente cliente) {
		boolean flag = false;
		try {
			cCliente.save(cliente);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}


	@Override
	public Optional<Cliente> listarId(int idCredito) {

		return cCliente.findById(idCredito);		
	}
	
}
