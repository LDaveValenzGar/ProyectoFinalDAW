package modelo;

import java.io.Serializable;
import java.util.Date;

public class DoctorJB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String iddoctor;
	private String doc_name;
	private String doc_paterno;
	private String doc_materno;
	private int dr_edad;
	private Date fecha_nacdoc;
	private String num_doctor;
	private String especialidad;
	private String pass;
	private String cargo;
	
	public DoctorJB() {
		
	}

	//DELETEUSER
	public DoctorJB(String iddoctor) {
		this.iddoctor = iddoctor;
	}
	
	//GETUSER y UPDATEUSER
	public DoctorJB(String doc_name, String doc_paterno, String doc_materno, int dr_edad, Date fecha_nacdoc,
			String num_doctor, String especialidad, String pass, String cargo) {
		this.doc_name = doc_name;
		this.doc_paterno = doc_paterno;
		this.doc_materno = doc_materno;
		this.dr_edad = dr_edad;
		this.fecha_nacdoc = fecha_nacdoc;
		this.num_doctor = num_doctor;
		this.especialidad = especialidad;
		this.pass = pass;
		this.cargo = cargo;
	}
	
	//ADDUSER 
	public DoctorJB(String iddoctor, String doc_name, String doc_paterno, String doc_materno, int dr_edad,
			Date fecha_nacdoc, String num_doctor, String especialidad, String pass, String cargo) {
		this.iddoctor = iddoctor;
		this.doc_name = doc_name;
		this.doc_paterno = doc_paterno;
		this.doc_materno = doc_materno;
		this.dr_edad = dr_edad;
		this.fecha_nacdoc = fecha_nacdoc;
		this.num_doctor = num_doctor;
		this.especialidad = especialidad;
		this.pass = pass;
		this.cargo = cargo;
	}

	public String getIddoctor() {
		return iddoctor;
	}

	public void setIddoctor(String iddoctor) {
		this.iddoctor = iddoctor;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

	public String getDoc_paterno() {
		return doc_paterno;
	}

	public void setDoc_paterno(String doc_paterno) {
		this.doc_paterno = doc_paterno;
	}

	public String getDoc_materno() {
		return doc_materno;
	}

	public void setDoc_materno(String doc_materno) {
		this.doc_materno = doc_materno;
	}

	public int getDr_edad() {
		return dr_edad;
	}

	public void setDr_edad(int dr_edad) {
		this.dr_edad = dr_edad;
	}

	public Date getFecha_nacdoc() {
		return fecha_nacdoc;
	}

	public void setFecha_nacdoc(Date fecha_nacdoc) {
		this.fecha_nacdoc = fecha_nacdoc;
	}

	public String getNum_doctor() {
		return num_doctor;
	}

	public void setNum_doctor(String num_doctor) {
		this.num_doctor = num_doctor;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		return "DoctorJB [iddoctor=" + iddoctor + ", doc_name=" + doc_name + ", doc_paterno=" + doc_paterno
				+ ", doc_materno=" + doc_materno + ", dr_edad=" + dr_edad + ", fecha_nacdoc=" + fecha_nacdoc
				+ ", num_doctor=" + num_doctor + ", especialidad=" + especialidad + ", pass=" + pass + ", cargo="
				+ cargo + "]";
	}
	
	
}
