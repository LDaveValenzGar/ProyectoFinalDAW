package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import datos.AsistenteDAO;
import datos.DAOExceptions;
import modelo.AsistenteJB;

public class Test {

	public static void main(String[] args) throws DAOExceptions, ParseException {
		
		DateFormat form = new SimpleDateFormat("YYYY-MM-DD");
		String entrada = "1963-12-17";
		Date fecha = form.parse(entrada);
		
		AsistenteDAO asist = new AsistenteDAO();			
		//AsistenteJB userasist = new AsistenteJB("ABC002","Alexa","Perez","Sanchez",35, new Date(1987,02,15),"Bachillerato","(229) 912-33-66");
		
		AsistenteJB olduser = new AsistenteJB();
		olduser.setAsist_name("Bertha");
		olduser.setAsist_paterno("Garcia");
		olduser.setAsist_materno("Sanchez");
		olduser.setAsist_edad(59);
		olduser.setAsist_f_nac(fecha);
		olduser.setEducacion("Bachillerato");
		olduser.setTelefono_asist("(229) 260-30-16");
		olduser.setIdasistente("ABC001");
		
		
		//AsistenteJB moduser = new AsistenteJB();
		
		//asist.insertdata(userasist);
		//asist.showdata();
		//asist.eliminar(olduser);
		asist.modificar(olduser);
		//asist.buscar("ABC001");
	}

}
