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
	private Date fecha_nac;
	private String nom_padre;
	private String pat_padre;
	private String mat_padre;
	private String num_telefono;
	private int tipo;
	
	public PacienteJB() {
	}
	
	//DELETEUSER
	public PacienteJB(String idpaciente) {
		this.idpaciente = idpaciente;
	}
	
	//GETUSER y UPDATEUSER
	public PacienteJB(String nombreP, String paterno, String materno, int edad, Date fecha_nac, String nom_padre,
			String pat_padre, String mat_padre, String num_telefono, int tipo) {
		this.nombreP = nombreP;
		this.paterno = paterno;
		this.materno = materno;
		this.edad = edad;
		this.fecha_nac = fecha_nac;
		this.nom_padre = nom_padre;
		this.pat_padre = pat_padre;
		this.mat_padre = mat_padre;
		this.num_telefono = num_telefono;
		this.tipo = tipo;
	}
	
	//ADDUSER
	public PacienteJB(String idpaciente, String nombreP, String paterno, String materno, int edad, Date fecha_nac,
			String nom_padre, String pat_padre, String mat_padre, String num_telefono, int tipo) {
		this.idpaciente = idpaciente;
		this.nombreP = nombreP;
		this.paterno = paterno;
		this.materno = materno;
		this.edad = edad;
		this.fecha_nac = fecha_nac;
		this.nom_padre = nom_padre;
		this.pat_padre = pat_padre;
		this.mat_padre = mat_padre;
		this.num_telefono = num_telefono;
		this.tipo = tipo;
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

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public String getNom_padre() {
		return nom_padre;
	}

	public void setNom_padre(String nom_padre) {
		this.nom_padre = nom_padre;
	}

	public String getPat_padre() {
		return pat_padre;
	}

	public void setPat_padre(String pat_padre) {
		this.pat_padre = pat_padre;
	}

	public String getMat_padre() {
		return mat_padre;
	}

	public void setMat_padre(String mat_padre) {
		this.mat_padre = mat_padre;
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

	@Override
	public String toString() {
		return "PacienteJB [idpaciente=" + idpaciente + ", nombreP=" + nombreP + ", paterno=" + paterno + ", materno="
				+ materno + ", edad=" + edad + ", fecha_nac=" + fecha_nac + ", nom_padre=" + nom_padre + ", pat_padre="
				+ pat_padre + ", mat_padre=" + mat_padre + ", num_telefono=" + num_telefono + ", tipo=" + tipo + "]";
	}
	
	
}
