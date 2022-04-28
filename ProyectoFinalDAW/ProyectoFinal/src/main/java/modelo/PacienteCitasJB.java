package modelo;

import java.io.Serializable;

public class PacienteCitasJB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int idpacienetecita;
	private int id_paciente;
	private String fecha;
	private String hora;
	private int id_doctor;
	private int id_asistente;
	private int id_cita;
	private int id_tipoconsulta;
	
	public PacienteCitasJB() {
	}
	
	public PacienteCitasJB(int idpacienetecita) {
		this.idpacienetecita = idpacienetecita;
	}

	public PacienteCitasJB(int id_paciente, String fecha, String hora, int id_doctor, int id_asistente, int id_cita,
			int id_tipoconsulta) {
		super();
		this.id_paciente = id_paciente;
		this.fecha = fecha;
		this.hora = hora;
		this.id_doctor = id_doctor;
		this.id_asistente = id_asistente;
		this.id_cita = id_cita;
		this.id_tipoconsulta = id_tipoconsulta;
	}

	public int getIdpacienetecita() {
		return idpacienetecita;
	}

	public void setIdpacienetecita(int idpacienetecita) {
		this.idpacienetecita = idpacienetecita;
	}

	public int getId_paciente() {
		return id_paciente;
	}

	public void setId_paciente(int id_paciente) {
		this.id_paciente = id_paciente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getId_doctor() {
		return id_doctor;
	}

	public void setId_doctor(int id_doctor) {
		this.id_doctor = id_doctor;
	}

	public int getId_asistente() {
		return id_asistente;
	}

	public void setId_asistente(int id_asistente) {
		this.id_asistente = id_asistente;
	}

	public int getId_cita() {
		return id_cita;
	}

	public void setId_cita(int id_cita) {
		this.id_cita = id_cita;
	}

	public int getId_tipoconsulta() {
		return id_tipoconsulta;
	}

	public void setId_tipoconsulta(int id_tipoconsulta) {
		this.id_tipoconsulta = id_tipoconsulta;
	}

	@Override
	public String toString() {
		return "PacienteCitasJB [idpacienetecita=" + idpacienetecita + ", id_paciente=" + id_paciente + ", fecha="
				+ fecha + ", hora=" + hora + ", id_doctor=" + id_doctor + ", id_asistente=" + id_asistente
				+ ", id_cita=" + id_cita + ", id_tipoconsulta=" + id_tipoconsulta + "]";
	}
	
	
}
