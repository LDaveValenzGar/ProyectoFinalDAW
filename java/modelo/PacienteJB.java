package modelo;

import java.io.Serializable;
import java.util.Date;

public class PacienteJB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String idpaciente;
	private String nombreP;
	private String paterno;
	private String materno;
	private int edad;
	private String sexo;
	private Date fecha_nac;
	private String madre;
	private String padre;
	private String num_telefono;
	private int tipo;
	private double peso;
	private int altura;
	private double temperatura;
	
	public PacienteJB() {
	}

	public PacienteJB(String idpaciente) {
		this.idpaciente = idpaciente;
	}

	public PacienteJB(String idpaciente, String nombreP, String paterno, String materno, int edad, String sexo,
			Date fecha_nac, String madre, String padre, String num_telefono, int tipo, double peso, int altura,
			double temperatura) {
		this.idpaciente = idpaciente;
		this.nombreP = nombreP;
		this.paterno = paterno;
		this.materno = materno;
		this.edad = edad;
		this.sexo = sexo;
		this.fecha_nac = fecha_nac;
		this.madre = madre;
		this.padre = padre;
		this.num_telefono = num_telefono;
		this.tipo = tipo;
		this.peso = peso;
		this.altura = altura;
		this.temperatura = temperatura;
	}

	public PacienteJB(String nombreP, String paterno, String materno, int edad, String sexo, Date fecha_nac,
			String madre, String padre, String num_telefono, int tipo, double peso, int altura, double temperatura) {
		this.nombreP = nombreP;
		this.paterno = paterno;
		this.materno = materno;
		this.edad = edad;
		this.sexo = sexo;
		this.fecha_nac = fecha_nac;
		this.madre = madre;
		this.padre = padre;
		this.num_telefono = num_telefono;
		this.tipo = tipo;
		this.peso = peso;
		this.altura = altura;
		this.temperatura = temperatura;
	}

	public String getIdpaciente() {
		return idpaciente;
	}

	public void setIdpaciente(String idpaciente) {
		this.idpaciente = idpaciente;
	}

	public String getNombreP() {
		return nombreP;
	}

	public void setNombreP(String nombreP) {
		this.nombreP = nombreP;
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public String getMadre() {
		return madre;
	}

	public void setMadre(String madre) {
		this.madre = madre;
	}

	public String getPadre() {
		return padre;
	}

	public void setPadre(String padre) {
		this.padre = padre;
	}

	public String getNum_telefono() {
		return num_telefono;
	}

	public void setNum_telefono(String num_telefono) {
		this.num_telefono = num_telefono;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	@Override
	public String toString() {
		return "PacienteJB [idpaciente=" + idpaciente + ", nombreP=" + nombreP + ", paterno=" + paterno + ", materno="
				+ materno + ", edad=" + edad + ", sexo=" + sexo + ", fecha_nac=" + fecha_nac + ", madre=" + madre
				+ ", padre=" + padre + ", num_telefono=" + num_telefono + ", tipo=" + tipo + ", peso=" + peso
				+ ", altura=" + altura + ", temperatura=" + temperatura + "]";
	}
		
}
