package modelo;

import java.io.Serializable;

public class AsistenteJB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int idasistente;
	private String asist_name;
	private String asist_paterno;
	private String asist_materno;
	private int asist_edad;
	private String asist_f_nac;
	private String educacion;
	private String telefono_asist;
	
	public AsistenteJB() {
		
	}

	public int getIdasistente() {
		return idasistente;
	}

	public void setIdasistente(int idasistente) {
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

	public String getAsist_f_nac() {
		return asist_f_nac;
	}

	public void setAsist_f_nac(String asist_f_nac) {
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

	@Override
	public String toString() {
		return "AsistenteJB [idasistente=" + idasistente + ", asist_name=" + asist_name + ", asist_paterno="
				+ asist_paterno + ", asist_materno=" + asist_materno + ", asist_edad=" + asist_edad + ", asist_f_nac="
				+ asist_f_nac + ", educacion=" + educacion + ", telefono_asist=" + telefono_asist + "]";
	}
	
	
}
