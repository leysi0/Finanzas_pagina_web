package pe.edu.upc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Credito;


@Repository
public interface ICreditoRepository extends JpaRepository<Credito, Integer> {

}
