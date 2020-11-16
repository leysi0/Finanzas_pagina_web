package pe.edu.upc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="venta")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVenta;
	
	@Column(name="fecha_venta", length=60, nullable=false)
	private Date fecha_venta;
	
	@ManyToOne
	@JoinColumn(name = "idCliente", nullable =false)
	private Cliente cliente;
	
	
	@Column(name="delivery", nullable=true)
	private double precioDelivery;

	@OneToOne
	@JoinColumn(name = "idtipoVenta", nullable =false)
	private TipoVenta tipoventa;
	
	@OneToMany(mappedBy="venta", cascade= CascadeType.ALL)
	private Set<VentaXProducto> ventaXProductos= new HashSet<>();

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public Date getFecha_venta() {
		return fecha_venta;
	}

	public void setFecha_venta(Date fecha_venta) {
		this.fecha_venta = fecha_venta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getPrecioDelivery() {
		return precioDelivery;
	}

	public void setPrecioDelivery(double precioDelivery) {
		this.precioDelivery = precioDelivery;
	}

	public TipoVenta getTipoventa() {
		return tipoventa;
	}

	public void setTipoventa(TipoVenta tipoventa) {
		this.tipoventa = tipoventa;
	}

	public Set<VentaXProducto> getVentaXProductos() {
		return ventaXProductos;
	}

	public void setVentaXProductos(Set<VentaXProducto> ventaXProductos) {
		this.ventaXProductos = ventaXProductos;
	}

	
		
}
