package modelo;

import java.io.Serializable;

public class CitasJB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int idcitas;
	private int rason;
	private String diagnostico;
	private String receta;
	
	public CitasJB() {
	}

	public CitasJB(int idcitas) {
		this.idcitas = idcitas;
	}

	public CitasJB(int idcitas, int rason, String diagnostico, String receta) {
		this.idcitas = idcitas;
		this.rason = rason;
		this.diagnostico = diagnostico;
		this.receta = receta;
	}

	public CitasJB(int rason, String diagnostico, String receta) {
		this.rason = rason;
		this.diagnostico = diagnostico;
		this.receta = receta;
	}

	public int getIdcitas() {
		return idcitas;
	}

	public void setIdcitas(int idcitas) {
		this.idcitas = idcitas;
	}

	public int getRason() {
		return rason;
	}

	public void setRason(int rason) {
		this.rason = rason;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getReceta() {
		return receta;
	}

	public void setReceta(String receta) {
		this.receta = receta;
	}

	@Override
	public String toString() {
		return "CitasJB [idcitas=" + idcitas + ", rason=" + rason + ", diagnostico=" + diagnostico + ", receta="
				+ receta + "]";
	}
	
	
	
}
