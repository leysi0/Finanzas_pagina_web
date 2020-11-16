package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Producto;
import pe.edu.upc.repository.IProductoRepository;
import pe.edu.upc.service.IProductoService;


@Service
public class ProductoServiceimpl implements IProductoService {
	
	@Autowired
	private IProductoRepository pProducto;

	@Override
	@Transactional
	public boolean insertar(Producto producto) {
		Producto objProducto = pProducto.save(producto);
		if (objProducto == null)
			return false;
		else
			return true;
	}

	
	@Override
	@Transactional
	public void eliminar(int idcliente) {
		pProducto.deleteById(idcliente);
		
	}
	
	@Override
	@Transactional
	public List<Producto> listar() {
		return pProducto.findAll();
	}

	
	@Transactional	
	public boolean modificar(Producto producto) {
		boolean flag = false;
		try {
			pProducto.save(producto);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}


	@Override
	public Optional<Producto> listarId(int idProducto) {

		return pProducto.findById(idProducto);		
	}
	
}
