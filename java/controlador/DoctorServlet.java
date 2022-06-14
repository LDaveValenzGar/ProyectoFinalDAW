package controlador;

import java.io.IOException;
import java.sql.SQLException;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.DoctorDAO;

//import datos.DAOExceptions;

import modelo.DoctorJB;

@WebServlet("/DoctorServlet")
public class DoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String accion = request.getParameter("accion");
    	
	    if (accion != null) {
	    	switch(accion) {
			    case "login":
					logDoctor(request, response);
					
			    	break;
			    case "datos":
			    	verDoctor(request, response);
			    	break;
			    case "modificar":
					
					break;
				case "salir":
					salirAsist(request, response);
					break;
				case "atras":
					atras(request, response);
					break;
				default:
					response.sendRedirect("asistenteLog.jsp");
		    }
	    }else {
		   	response.sendRedirect("asistenteLog.jsp");
	    }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void logDoctor(HttpServletRequest request, HttpServletResponse response){
		DoctorJB doctor = new DoctorJB();
		String iddoc = null;
		String pass = null;
		DoctorDAO doc = new DoctorDAO();
		HttpSession session=request.getSession();
		iddoc = request.getParameter("iddoc");
		pass = request.getParameter("pass");
		doctor=doc.verAsist(iddoc,pass);
		try {
			if(doc.docLogin(doctor)) {
				session.setAttribute("iddoc", doctor.getIddoctor());
				session.setAttribute("nombre", doctor.getDoc_name());
				session.setAttribute("pass", doctor.getPass());
				RequestDispatcher rd= request.getRequestDispatcher("vistas/doctor/menuDoctor.jsp");
				rd.forward(request, response);
			}else {
				response.sendRedirect("logDoctor.jsp");
				session.setAttribute("mensaje", "Fallida");
			}
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
				
	}
	
	private void verDoctor(HttpServletRequest request, HttpServletResponse response) {

		String iddoc = null;
		String pass = null;
		DoctorJB doctor = new DoctorJB();
		DoctorDAO doc = new DoctorDAO();
		
		HttpSession session=request.getSession();
		iddoc = (String)session.getAttribute("iddoc");
		pass = (String) session.getAttribute("pass");
		
		doctor=doc.verAsist(iddoc, pass);
		try {
			if(doc.docLogin(doctor)) {
			
			session.setAttribute("idasist", doctor.getIddoctor());
			session.setAttribute("nombre", doctor.getDoc_name());
			session.setAttribute("paterno", doctor.getDoc_paterno());
			session.setAttribute("materno", doctor.getDoc_materno());
			session.setAttribute("edad", doctor.getDr_edad());
			session.setAttribute("fecha", doctor.getFecha_nacdoc());
			session.setAttribute("telefono", doctor.getNum_doctor());
			session.setAttribute("especialidad", doctor.getEspecialidad());
			session.setAttribute("pass", doctor.getPass());
			session.setAttribute("trabajo", doctor.getCargo());	
			
			RequestDispatcher rd = request.getRequestDispatcher("vistas/doctor/doctorDatos.jsp");
			rd.forward(request, response);
			}else {
				response.sendRedirect("logDoctor.jsp");
				session.setAttribute("mensaje", "Fallida");
			}
		} catch (SQLException | ServletException | IOException e1) {
			e1.printStackTrace();
		} 
				
	}
	
	/*private void editarDoctor(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException {
		
		String idDoctor = request.getParameter("idDoctor");
		DoctorJB doctor = new DoctorDAO().buscar(idDoctor);
		request.setAttribute("doctor", doctor);
		String editJSP = "/WEB-INF/vista/doctor/editDoctor.jsp";
		request.getRequestDispatcher(editJSP).forward(request, response);
		this.buscarDoctor(request, response);
		
	}/*

	/*private void eliminarDoctor(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException {
		
		String idDoctor = request.getParameter("idDoctor");
		DoctorJB doctor = new DoctorJB(idDoctor);
		DoctorDAO regMod = new DoctorDAO();
		regMod.eliminar(doctor);
		this.buscarDoctor(request, response);
				
	}*/
	
	/*private void insertarDoctor(HttpServletRequest request, HttpServletResponse response) throws ParseException, DAOExceptions {
		DateFormat form = new SimpleDateFormat("YYYY-MM-DD");
		String fch = request.getParameter("fecha");
		
		String idDoctor = request.getParameter("idDoctor");
		String name = request.getParameter("nombre");
		String paterno = request.getParameter("paterno");
		String materno = request.getParameter("materno");
		int edad = Integer.parseInt(request.getParameter("edad"));
		Date fecha = form.parse(fch);
		String telefono = request.getParameter("telefono");
		String especialidad = request.getParameter("especialidad");
		String horario = request.getParameter("horario");
		String cargo = request.getParameter("cargo");
		
		DoctorJB doctor = new DoctorJB(idDoctor, name, paterno, materno, edad, fecha, telefono, especialidad, horario, cargo);
		DoctorDAO regMod = new DoctorDAO();
		regMod.insertdata(doctor);
		
	}*/
	
	/*private void modificarDoctor(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException, ParseException {
		
		DateFormat form = new SimpleDateFormat("YYYY-MM-DD");
		String fch = request.getParameter("fecha");
	
		String name = request.getParameter("nombre");
		String paterno = request.getParameter("paterno");
		String materno = request.getParameter("materno");
		int edad = Integer.parseInt(request.getParameter("edad"));
		Date fecha = form.parse(fch);
		String telefono = request.getParameter("telefono");
		String especialidad = request.getParameter("especialidad");
		String horario = request.getParameter("horario");
		String cargo = request.getParameter("cargo");
		
		DoctorJB doctor = new DoctorJB(name, paterno, materno, edad, fecha, telefono, especialidad, horario, cargo);
		DoctorDAO regMod = new DoctorDAO();
		regMod.modificar(doctor);
		this.buscarDoctor(request, response);
		
	}*/
	/*
	private void buscarDoctor(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, IOException {
		List<DoctorJB> doctores = new DoctorDAO().showdata();
		System.out.println("Doctores" + doctores);
//		HttpSession sesion=request.getSession();
		request.setAttribute("Doctores", doctores);
		response.sendRedirect("doctor.jsp");
		
	}*/
	
	private void salirAsist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute("iddoc",null);
		session.invalidate();
		response.sendRedirect("logDoctor.jsp");
							
	}
	private void atras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	RequestDispatcher rd= request.getRequestDispatcher("vistas/doctor/menuDoctor.jsp");
	rd.forward(request, response);
}

}
