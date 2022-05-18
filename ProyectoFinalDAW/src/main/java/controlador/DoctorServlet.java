package controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DoctorDAO;
import datos.DAOExceptions;
import modelo.DoctorJB;

@WebServlet("/DoctorServlet")
public class DoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
    	switch(opcion) {		
			case "Editar":
				try {
					this.editarDoctor(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} 			
				break;
			case "Eliminar":		
				try {
					this.eliminarDoctor(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} 				
				break;
			default:
				try {
					this.buscarDoctor(request, response);
				} catch (DAOExceptions e) {
					e.printStackTrace();
				}
    	}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
    	switch(opcion) {		
			case "Insertar":
				
				try {
					insertarDoctor(request, response);
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				}

				break;
			case "Modificar":

				try {
					modificarDoctor(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			
				break;
			case "Eliminar":
				try {
					this.eliminarDoctor(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} 
				
			default:
				try {
					this.buscarDoctor(request, response);
				} catch (DAOExceptions e) {
					e.printStackTrace();
				}
    	}
	}
	
	private void editarDoctor(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException {
		
		String idDoctor = request.getParameter("idDoctor");
		DoctorJB doctor = new DoctorDAO().buscar(idDoctor);
		request.setAttribute("doctor", doctor);
		String editJSP = "/WEB-INF/vista/doctor/editDoctor.jsp";
		request.getRequestDispatcher(editJSP).forward(request, response);
		this.buscarDoctor(request, response);
		
	}

	private void eliminarDoctor(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException {
		
		String idDoctor = request.getParameter("idDoctor");
		DoctorJB doctor = new DoctorJB(idDoctor);
		DoctorDAO regMod = new DoctorDAO();
		regMod.eliminar(doctor);
		this.buscarDoctor(request, response);
				
	}
	
	private void insertarDoctor(HttpServletRequest request, HttpServletResponse response) throws ParseException, DAOExceptions {
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
		
	}
	
	private void modificarDoctor(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException, ParseException {
		
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
		
	}
	
	private void buscarDoctor(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, IOException {
		List<DoctorJB> doctores = new DoctorDAO().showdata();
		System.out.println("Doctores" + doctores);
//		HttpSession sesion=request.getSession();
		request.setAttribute("Doctores", doctores);
		response.sendRedirect("doctor.jsp");
		
	}

}
