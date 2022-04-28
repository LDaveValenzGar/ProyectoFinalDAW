package modelo;

import java.io.Serializable;

public class CitasJB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int idcitas;
	private double costo_cita;
	private int tipo_consulta;
	
	public CitasJB() {
		
	}

	public CitasJB(int idcitas, double costo_cita, int tipo_consulta) {
		this.idcitas = idcitas;
		this.costo_cita = costo_cita;
		this.tipo_consulta = tipo_consulta;
	}

	public int getIdcitas() {
		return idcitas;
	}

	public void setIdcitas(int idcitas) {
		this.idcitas = idcitas;
	}

	public double getCosto_cita() {
		return costo_cita;
	}

	public void setCosto_cita(double costo_cita) {
		this.costo_cita = costo_cita;
	}

	public int getTipo_consulta() {
		return tipo_consulta;
	}

	public void setTipo_consulta(int tipo_consulta) {
		this.tipo_consulta = tipo_consulta;
	}

	@Override
	public String toString() {
		return "CitasJB [idcitas=" + idcitas + ", costo_cita=" + costo_cita + ", tipo_consulta=" + tipo_consulta + "]";
	}
	
	
}
