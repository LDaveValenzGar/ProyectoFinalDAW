package modelo;

import java.io.Serializable;

public class ProveedorJB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String idproveedor;
	private String nombre_proveedor;
	private String direccion;
	private String telefono_sup;
	
	public ProveedorJB() {
		
	}
		
	public ProveedorJB(String idproveedor) {
		this.idproveedor = idproveedor;
	}
	
	public ProveedorJB(String nombre_proveedor, String direccion, String telefono_sup) {
		this.nombre_proveedor = nombre_proveedor;
		this.direccion = direccion;
		this.telefono_sup = telefono_sup;
	}
	
	public ProveedorJB(String idproveedor, String nombre_proveedor, String direccion, String telefono_sup) {
		this.idproveedor = idproveedor;
		this.nombre_proveedor = nombre_proveedor;
		this.direccion = direccion;
		this.telefono_sup = telefono_sup;
	}

	public String getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(String idproveedor) {
		this.idproveedor = idproveedor;
	}

	public String getNombre_proveedor() {
		return nombre_proveedor;
	}

	public void setNombre_proveedor(String nombre_proveedor) {
		this.nombre_proveedor = nombre_proveedor;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono_sup() {
		return telefono_sup;
	}

	public void setTelefono_sup(String telefono_sup) {
		this.telefono_sup = telefono_sup;
	}

	@Override
	public String toString() {
		return "ProveedorJB [idproveedor=" + idproveedor + ", nombre_proveedor=" + nombre_proveedor + ", direccion="
				+ direccion + ", telefono_sup=" + telefono_sup + "]";
	}
	
	
}
