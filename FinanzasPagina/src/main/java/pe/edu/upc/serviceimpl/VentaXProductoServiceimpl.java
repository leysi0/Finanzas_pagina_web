package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Venta;
import pe.edu.upc.model.VentaXProducto;
import pe.edu.upc.repository.IVentaRepository;
import pe.edu.upc.repository.IVentaXProductoRepository;
import pe.edu.upc.service.IVentaService;
import pe.edu.upc.service.IVentaXProductoService;


@Service
public class VentaXProductoServiceimpl implements IVentaXProductoService {
	
	@Autowired
	private IVentaXProductoRepository vPVenta;

	@Override
	@Transactional
	public boolean insertar(VentaXProducto VentaXProducto) {
		VentaXProducto objVenta = vPVenta.save(VentaXProducto);
		if (objVenta == null)
			return false;
		else
			return true;
	}

	
	@Override
	@Transactional
	public void eliminar(int idVenta) {
		vPVenta.deleteById(idVenta);
		
	}
	
	@Override
	@Transactional
	public List<VentaXProducto> listar() {
		return vPVenta.findAll();
	}

	
	@Transactional	
	public boolean modificar(VentaXProducto VentaXProducto) {
		boolean flag = false;
		try {
			vPVenta.save(VentaXProducto);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}


	@Override
	public Optional<VentaXProducto> listarId(int idVenta) {

		return vPVenta.findById(idVenta);		
	}
	
}
