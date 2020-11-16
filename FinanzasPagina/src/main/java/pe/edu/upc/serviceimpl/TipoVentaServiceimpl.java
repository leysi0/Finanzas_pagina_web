package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.TipoVenta;
import pe.edu.upc.model.Venta;
import pe.edu.upc.repository.ITipoVentaRepository;
import pe.edu.upc.repository.IVentaRepository;
import pe.edu.upc.service.ITipoVentaService;
import pe.edu.upc.service.IVentaService;


@Service
public class TipoVentaServiceimpl implements ITipoVentaService {
	
	@Autowired
	private ITipoVentaRepository vTVenta;

	@Override
	@Transactional
	public boolean insertar(TipoVenta Tipoventa) {
		TipoVenta objTipoVenta = vTVenta.save(Tipoventa);
		if (objTipoVenta == null)
			return false;
		else
			return true;
	}

	
	@Override
	@Transactional
	public void eliminar(int idTipoVenta) {
		vTVenta.deleteById(idTipoVenta);
		
	}
	
	@Override
	@Transactional
	public List<TipoVenta> listar() {
		return vTVenta.findAll();
	}

	
	@Transactional	
	public boolean modificar(TipoVenta Tipoventa) {
		boolean flag = false;
		try {
			vTVenta.save(Tipoventa);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}


	@Override
	public Optional<TipoVenta> listarId(int idVenta) {

		return vTVenta.findById(idVenta);		
	}
	
}
