package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Credito;
import pe.edu.upc.repository.ICreditoRepository;
import pe.edu.upc.service.ICreditoService;


@Service
public class CreditoServiceimpl implements ICreditoService {
	
	@Autowired
	private ICreditoRepository cCredito;

	@Override
	@Transactional
	public boolean insertar(Credito credito) {
		Credito objCred = cCredito.save(credito);
		if (objCred == null)
			return false;
		else
			return true;
	}

	
	@Override
	@Transactional
	public void eliminar(int idcredito) {
		cCredito.deleteById(idcredito);
		
	}
	
	@Override
	@Transactional
	public List<Credito> listar() {
		return cCredito.findAll();
	}

	
	@Transactional	
	public boolean modificar(Credito credito) {
		boolean flag = false;
		try {
			cCredito.save(credito);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}


	@Override
	public Optional<Credito> listarId(int idCredito) {

		return cCredito.findById(idCredito);		
	}
	
}
