package controlador;

import datos.DAOExceptions;
import datos.PacienteDAO;
import modelo.PacienteJB;
import java.util.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(name="PacienteServlet", urlPatterns="/PacienteServlet") 
public class PacienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
    	String opcion = request.getParameter("opcion");
	    switch(opcion) {		
			case "Editar":
				try {
					this.editarPaciente(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} 
				break;
			case "Eliminar":
				try {
					this.eliminarPaciente(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} 
					
				break;
			default:
				try {
					this.buscarPaciente(request, response);
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
					insertarPaciente(request, response);
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				}

				break;
			case "Modificar":

				try {
					modificarPaciente(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			
				break;
			case "Eliminar":
				try {
					this.eliminarPaciente(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} 
				
			default:
				try {
					this.buscarPaciente(request, response);
				} catch (DAOExceptions e) {
					e.printStackTrace();
				}
    	}
	}

	private void editarPaciente(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException {
		
		String idPaciente = request.getParameter("idPaciente");
		PacienteJB paciente = new PacienteDAO().buscar(idPaciente);
		request.setAttribute("paciente", paciente);
		String editJSP = "/WEB-INF/vista/paciente/editPaciente.jsp";
		request.getRequestDispatcher(editJSP).forward(request, response);
		this.buscarPaciente(request, response);
		
	}

	private void eliminarPaciente(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException {
		
		String idPaciente = request.getParameter("idPaciente");
		PacienteJB paciente = new PacienteJB(idPaciente);
		PacienteDAO regMod = new PacienteDAO();
		regMod.eliminar(paciente);
		this.buscarPaciente(request, response);
		
		
	}
	
	private void insertarPaciente(HttpServletRequest request, HttpServletResponse response) throws ParseException, DAOExceptions {
		DateFormat form = new SimpleDateFormat("YYYY-MM-DD");
		String fch = request.getParameter("fecha");
		
		String idPaciente = request.getParameter("idPaciente");
		String name = request.getParameter("nombre");
		String paterno = request.getParameter("paterno");
		String materno = request.getParameter("materno");
		int edad = Integer.parseInt(request.getParameter("edad"));
		Date fecha = form.parse(fch);
		String namePadre = request.getParameter("nombPadre");
		String patPadre = request.getParameter("patPadre");
		String matPadre = request.getParameter("matPadre");
		String telefono = request.getParameter("telefono");
		int tipo = Integer.parseInt(request.getParameter("tipo"));
		
		PacienteJB paciente = new PacienteJB(idPaciente, name, paterno, materno, edad, fecha, namePadre, patPadre, matPadre, telefono, tipo);
		PacienteDAO regMod = new PacienteDAO();
		regMod.insertdata(paciente);
		
	}

	private void modificarPaciente(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException, ParseException {	
		DateFormat form = new SimpleDateFormat("YYYY-MM-DD");
			
		String name = request.getParameter("nombre");
		String paterno = request.getParameter("paterno");
		String materno = request.getParameter("materno");
		int edad = Integer.parseInt(request.getParameter("edad"));
		String fch = request.getParameter("fecha");
		Date fecha = form.parse(fch);
		String namePadre = request.getParameter("nombPadre");
		String patPadre = request.getParameter("patPadre");
		String matPadre = request.getParameter("matPadre");
		String telefono = request.getParameter("telefono");
		int tipo = Integer.parseInt(request.getParameter("tipo"));
		
		PacienteJB paciente = new PacienteJB(name, paterno, materno, edad, fecha, namePadre, patPadre, matPadre, telefono, tipo);
		PacienteDAO regMod = new PacienteDAO();
		regMod.modificar(paciente);
		this.buscarPaciente(request, response);
		
	}
	private void buscarPaciente(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, IOException {
		List<PacienteJB> pacientes = new PacienteDAO().showdata();
		System.out.println("Pacientes" + pacientes);
//		HttpSession sesion=request.getSession();
		request.setAttribute("pacientes", pacientes);
		response.sendRedirect("/WEB-INF/vista/paciente/pacientes.jsp");
		
	}

}
