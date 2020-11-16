package pe.edu.upc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="producto")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nombreProducto", length=50, nullable=false)
	private String nombreProducto;
	
	@Column(name = "precioProducto", nullable =false)
	private double precioProducto;

	@Column(name="cantidadDisponible", nullable=false)
	private int cantidadDisponible;
	
	
	@OneToMany(mappedBy="producto", cascade= CascadeType.ALL)
	private Set<VentaXProducto> ventaXProductos= new HashSet<>();

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}

	public int getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public Set<VentaXProducto> getVentaXProductos() {
		return ventaXProductos;
	}

	public void setVentaXProductos(Set<VentaXProducto> ventaXProductos) {
		this.ventaXProductos = ventaXProductos;
	}
	


		
}
