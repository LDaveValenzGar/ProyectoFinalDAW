package controlador;

import datos.AsistenteDAO;
import datos.DAOExceptions;
import modelo.AsistenteJB;
import java.util.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AsistenteServlet")
public class AsistenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String opcion = request.getParameter("opcion");
	    switch(opcion) {		
			case "Editar":
				try {
					this.editarAsistente(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} 			
				break;
			case "Eliminar":		
				try {
					this.eliminarAsistente(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} 				
				break;
			default:
				try {
					this.buscarAsistente(request, response);
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
					insertarAsistente(request, response);
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				}

				break;
			case "Modificar":

				try {
					modificarAsistente(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			
				break;
			case "Eliminar":
				try {
					this.eliminarAsistente(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} 
				
			default:
				try {
					this.buscarAsistente(request, response);
				} catch (DAOExceptions e) {
					e.printStackTrace();
				}
    	}
	}
	
	private void editarAsistente(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException {
		
		String idAsistente = request.getParameter("idAsistente");
		AsistenteJB asistente = new AsistenteDAO().buscar(idAsistente);
		request.setAttribute("asistente", asistente);
		String editJSP = "/WEB-INF/vista/asistente/editAsistente.jsp";
		request.getRequestDispatcher(editJSP).forward(request, response);
		this.buscarAsistente(request, response);
		
	}

	private void eliminarAsistente(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException {
		
		String idAsistente = request.getParameter("idAsistente");
		AsistenteJB asistente = new AsistenteJB(idAsistente);
		AsistenteDAO regMod = new AsistenteDAO();
		regMod.eliminar(asistente);
		this.buscarAsistente(request, response);
				
	}
	
	private void insertarAsistente(HttpServletRequest request, HttpServletResponse response) throws ParseException, DAOExceptions {
		DateFormat form = new SimpleDateFormat("YYYY-MM-DD");
		
		String idAsistente = request.getParameter("idAsistente");
		String name = request.getParameter("nombre");
		String paterno = request.getParameter("paterno");
		String materno = request.getParameter("materno");
		int edad = Integer.parseInt(request.getParameter("edad"));
		String fch = request.getParameter("fecha");
		Date fecha = form.parse(fch);
		String educacion = request.getParameter("educacion");
		String telefono = request.getParameter("telefono");
		
		AsistenteJB asistente = new AsistenteJB(idAsistente, name, paterno, materno, edad, fecha, educacion, telefono);
		AsistenteDAO regMod = new AsistenteDAO();
		regMod.insertdata(asistente);
		
	}
	
	private void modificarAsistente(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException, ParseException {
		
		DateFormat form = new SimpleDateFormat("YYYY-MM-DD");
		String fch = request.getParameter("fecha");
	
		String name = request.getParameter("nombre");
		String paterno = request.getParameter("paterno");
		String materno = request.getParameter("materno");
		int edad = Integer.parseInt(request.getParameter("edad"));
		Date fecha = form.parse(fch);
		String educacion = request.getParameter("educacion");
		String telefono = request.getParameter("telefono");
		
		AsistenteJB asistente = new AsistenteJB(name, paterno, materno, edad, fecha, educacion, telefono);
		AsistenteDAO regMod = new AsistenteDAO();
		regMod.modificar(asistente);
		this.buscarAsistente(request, response);
		
	}
	
	private void buscarAsistente(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, IOException {
		List<AsistenteJB> asistentes = new AsistenteDAO().showdata();
		System.out.println("Asistentes" + asistentes);
//		HttpSession sesion=request.getSession();
		request.setAttribute("Asistentes", asistentes);
		response.sendRedirect("asistente.jsp");
		
	}
	
}
