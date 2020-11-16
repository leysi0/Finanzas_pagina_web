package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.VentaXProducto;


@Repository
public interface IVentaXProductoRepository extends JpaRepository<VentaXProducto, Integer> {

}
