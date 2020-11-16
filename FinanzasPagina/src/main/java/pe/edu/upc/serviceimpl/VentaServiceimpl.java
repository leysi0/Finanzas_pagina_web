package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Venta;
import pe.edu.upc.repository.IVentaRepository;
import pe.edu.upc.service.IVentaService;


@Service
public class VentaServiceimpl implements IVentaService {
	
	@Autowired
	private IVentaRepository vVenta;

	@Override
	@Transactional
	public boolean insertar(Venta venta) {
		Venta objVenta = vVenta.save(venta);
		if (objVenta == null)
			return false;
		else
			return true;
	}

	
	@Override
	@Transactional
	public void eliminar(int idVenta) {
		vVenta.deleteById(idVenta);
		
	}
	
	@Override
	@Transactional
	public List<Venta> listar() {
		return vVenta.findAll();
	}

	
	@Transactional	
	public boolean modificar(Venta venta) {
		boolean flag = false;
		try {
			vVenta.save(venta);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}


	@Override
	public Optional<Venta> listarId(int idVenta) {

		return vVenta.findById(idVenta);		
	}
	
}
