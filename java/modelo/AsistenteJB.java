package modelo;

import java.io.Serializable;
import java.util.Date;

public class AsistenteJB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String idasistente;
	private String asist_name;
	private String asist_paterno;
	private String asist_materno;
	private int asist_edad;
	private Date asist_f_nac;
	private String educacion;
	private String telefono_asist;
	private String pass;
	private String trabajo;
	
	public AsistenteJB() {
		this.asist_name = "";
		this.asist_paterno = "";
		this.asist_materno = "";
		this.asist_edad = 0;
		this.asist_f_nac = null;
		this.educacion = "";
		this.telefono_asist = "";
		this.pass = "";
		this.trabajo = "";
	}
	
	//LOG
	public AsistenteJB(String idasistente, String pass) {
		this.idasistente = idasistente;
		this.pass = pass;
	}

	//UPDATEUSER
	public AsistenteJB(String asist_name, String asist_paterno, String asist_materno, int asist_edad, Date asist_f_nac,
			String educacion, String telefono_asist, String trabajo) {
		this.asist_name = asist_name;
		this.asist_paterno = asist_paterno;
		this.asist_materno = asist_materno;
		this.asist_edad = asist_edad;
		this.asist_f_nac = asist_f_nac;
		this.educacion = educacion;
		this.telefono_asist = telefono_asist;
		this.trabajo = trabajo;
	}

	//ADDUSER & VIEWUSER
	public AsistenteJB(String idasistente, String asist_name, String asist_paterno, String asist_materno, int asist_edad,
			Date asist_f_nac, String educacion, String telefono_asist, String pass, String trabajo) {
		this.idasistente = idasistente;
		this.asist_name = asist_name;
		this.asist_paterno = asist_paterno;
		this.asist_materno = asist_materno;
		this.asist_edad = asist_edad;
		this.asist_f_nac = asist_f_nac;
		this.educacion = educacion;
		this.telefono_asist = telefono_asist;
		this.pass = pass;
		this.trabajo = trabajo;

	}
	
	public String getIdasistente() {
		return idasistente;
	}

	public void setIdasistente(String idasistente) {
		this.idasistente = idasistente;
	}

	public String getAsist_name() {
		return asist_name;
	}

	public void setAsist_name(String asist_name) {
		this.asist_name = asist_name;
	}

	public String getAsist_paterno() {
		return asist_paterno;
	}

	public void setAsist_paterno(String asist_paterno) {
		this.asist_paterno = asist_paterno;
	}

	public String getAsist_materno() {
		return asist_materno;
	}

	public void setAsist_materno(String asist_materno) {
		this.asist_materno = asist_materno;
	}

	public int getAsist_edad() {
		return asist_edad;
	}

	public void setAsist_edad(int asist_edad) {
		this.asist_edad = asist_edad;
	}

	public Date getAsist_f_nac() {
		return asist_f_nac;
	}

	public void setAsist_f_nac(Date asist_f_nac) {
		this.asist_f_nac = asist_f_nac;
	}

	public String getEducacion() {
		return educacion;
	}

	public void setEducacion(String educacion) {
		this.educacion = educacion;
	}

	public String getTelefono_asist() {
		return telefono_asist;
	}

	public void setTelefono_asist(String telefono_asist) {
		this.telefono_asist = telefono_asist;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(String trabajo) {
		this.trabajo = trabajo;
	}

	@Override
	public String toString() {
		return "AsistenteJB [idasistente=" + idasistente + ", asist_name=" + asist_name + ", asist_paterno="
				+ asist_paterno + ", asist_materno=" + asist_materno + ", asist_edad=" + asist_edad + ", asist_f_nac="
				+ asist_f_nac + ", educacion=" + educacion + ", telefono_asist=" + telefono_asist + ", pass=" + pass
				+ ", trabajo=" + trabajo + "]";
	}

}
