package modelo;

import java.io.Serializable;

public class VacunasJB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int idvacuna;
	private String nombre_vacuna;
	private String vacuna_tipo;
	private double precio_vacuna;
	private String caducidad;
	private String lote;
	private int stock;
	private int proveedor;
	
	public VacunasJB() {
		
	}

	public int getIdvacuna() {
		return idvacuna;
	}

	public void setIdvacuna(int idvacuna) {
		this.idvacuna = idvacuna;
	}

	public String getNombre_vacuna() {
		return nombre_vacuna;
	}

	public void setNombre_vacuna(String nombre_vacuna) {
		this.nombre_vacuna = nombre_vacuna;
	}

	public String getVacuna_tipo() {
		return vacuna_tipo;
	}

	public void setVacuna_tipo(String vacuna_tipo) {
		this.vacuna_tipo = vacuna_tipo;
	}

	public double getPrecio_vacuna() {
		return precio_vacuna;
	}

	public void setPrecio_vacuna(double precio_vacuna) {
		this.precio_vacuna = precio_vacuna;
	}

	public String getCaducidad() {
		return caducidad;
	}

	public void setCaducidad(String caducidad) {
		this.caducidad = caducidad;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getProveedor() {
		return proveedor;
	}

	public void setProveedor(int proveedor) {
		this.proveedor = proveedor;
	}

	@Override
	public String toString() {
		return "VacunasJB [idvacuna=" + idvacuna + ", nombre_vacuna=" + nombre_vacuna + ", vacuna_tipo=" + vacuna_tipo
				+ ", precio_vacuna=" + precio_vacuna + ", caducidad=" + caducidad + ", lote=" + lote + ", stock="
				+ stock + ", proveedor=" + proveedor + "]";
	}
	
	
}
