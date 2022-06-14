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

import datos.DAOExceptions;
import datos.VacunasDAO;
import modelo.VacunasJB;

@WebServlet("/VacunasServlet")
public class VacunasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		switch(opcion) {
		case "Editar":
			try {
				this.editarVacunas(request, response);
			} catch (DAOExceptions e1) {
				e1.printStackTrace();
			} 			
			break;
		case "Eliminar":		
			try {
				this.eliminarVacunas(request, response);
			} catch (DAOExceptions e1) {
				e1.printStackTrace();
			} 				
			break;
		default:
			try {
				this.buscarVacuna(request, response);
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
					insertarVacunas(request, response);
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				}

				break;
			case "Modificar":

				try {
					modificarVacunas(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			
				break;
			case "Eliminar":
				try {
					this.eliminarVacunas(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} 
				
			default:
				try {
					this.buscarVacuna(request, response);
				} catch (DAOExceptions e) {
					e.printStackTrace();
				}
    	}
	}
	
private void editarVacunas(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException {
		
		String idVacuna = request.getParameter("idVacuna");
		VacunasJB vacuna = new VacunasDAO().buscar(idVacuna);
		request.setAttribute("vacuna", vacuna);
		String editJSP = "/WEB-INF/vista/vacunas/editVacunas.jsp";
		request.getRequestDispatcher(editJSP).forward(request, response);
		this.buscarVacuna(request, response);
		
	}

	private void eliminarVacunas(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException {
		
		int idVacuna = Integer.parseInt(request.getParameter("idVacuna"));
		VacunasJB vacuna = new VacunasJB(idVacuna);
		VacunasDAO regMod = new VacunasDAO();
		regMod.eliminar(vacuna);
		this.buscarVacuna(request, response);
				
	}

	private void insertarVacunas(HttpServletRequest request, HttpServletResponse response) throws ParseException, DAOExceptions {
		DateFormat form = new SimpleDateFormat("YYYY-MM-DD");
		
		int idVacuna = Integer.parseInt(request.getParameter("idVacuna"));
		String name = request.getParameter("nombre");
		String tipoV = request.getParameter("tipo");
		double precio = Double.parseDouble(request.getParameter("precio"));
		String fech = request.getParameter("caducidad");
		Date caducidad = form.parse(fech);
		String lote = request.getParameter("lote");
		int stock = Integer.parseInt(request.getParameter("stock"));
		String proveedor = request.getParameter("proveedor");
		
		VacunasJB vacuna = new VacunasJB(idVacuna, name, tipoV, precio, caducidad, lote, stock, proveedor);
		VacunasDAO regMod = new VacunasDAO();
		regMod.insertdata(vacuna);
		
		
	}
	
	private void modificarVacunas(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException, ParseException {
		DateFormat form = new SimpleDateFormat("YYYY-MM-DD");
		
		String name = request.getParameter("nombre");
		String tipoV = request.getParameter("tipo");
		double precio = Double.parseDouble(request.getParameter("precio"));
		String fech = request.getParameter("caducidad");
		Date caducidad = form.parse(fech);
		String lote = request.getParameter("lote");
		int stock = Integer.parseInt(request.getParameter("stock"));
		String proveedor = request.getParameter("proveedor");
		
		VacunasJB vacuna = new VacunasJB(name, tipoV, precio, caducidad, lote, stock, proveedor);
		VacunasDAO regMod = new VacunasDAO();
		regMod.modificar(vacuna);
		this.buscarVacuna(request, response);
		
	}
	
	private void buscarVacuna(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, IOException {
		List<VacunasJB> vacunas = new VacunasDAO().showdata();
		System.out.println("Vacunas" + vacunas);
//		HttpSession sesion=request.getSession();
		request.setAttribute("Vacunas", vacunas);
		response.sendRedirect("vacunas.jsp");
		
	}

	

}
