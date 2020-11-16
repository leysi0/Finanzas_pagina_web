package pe.edu.upc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TipoVenta")
public class TipoVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idtipoVenta;
	
	@Column(name="nombretipoVenta", nullable=false)
	private String nombretipoVenta;

	public int getIdtipoVenta() {
		return idtipoVenta;
	}

	public void setIdtipoVenta(int idtipoVenta) {
		this.idtipoVenta = idtipoVenta;
	}

	public String getNombretipoVenta() {
		return nombretipoVenta;
	}

	public void setNombretipoVenta(String nombretipoVenta) {
		this.nombretipoVenta = nombretipoVenta;
	}
	
	
}
