package pe.edu.upc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="credito")
public class Credito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCredito;
	
	@Column(name = "monto_credito", nullable =false)
	private double monto_credito;
	
	@Column(name = "interes", nullable =false)
	private double interes;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="saldo_usado", nullable=false)
	private double saldo_usado;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="saldo_disponible", nullable=false)
	private double saldo_disponible;

	@OneToOne
	@JoinColumn(name = "idCliente", nullable =false)
	private Cliente cliente;

	
	public int getIdCredito() {
		return idCredito;
	}

	public void setIdCredito(int idCredito) {
		this.idCredito = idCredito;
	}

	public double getMonto_credito() {
		return monto_credito;
	}

	public void setMonto_credito(double monto_credito) {
		this.monto_credito = monto_credito;
	}

	public double getInteres() {
		return interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public double getSaldo_usado() {
		return saldo_usado;
	}

	public void setSaldo_usado(double saldo_usado) {
		this.saldo_usado = saldo_usado;
	}

	public double getSaldo_disponible() {
		return saldo_disponible;
	}

	public void setSaldo_disponible(double saldo_disponible) {
		this.saldo_disponible = saldo_disponible;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	



}
