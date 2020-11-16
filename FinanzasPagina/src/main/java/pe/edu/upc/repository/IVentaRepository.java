package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Venta;


@Repository
public interface IVentaRepository extends JpaRepository<Venta, Integer> {

}
