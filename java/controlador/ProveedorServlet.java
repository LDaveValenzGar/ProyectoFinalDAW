package controlador;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.ProveedorDAO;
import datos.DAOExceptions;
import modelo.ProveedorJB;

@WebServlet("/ProveedorServlet")
public class ProveedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String opcion = request.getParameter("opcion");
	    switch(opcion) {		
			case "Editar":
				try {
					this.editarProveedor(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} 			
				break;
			case "Eliminar":		
				try {
					this.eliminarProveedor(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} 				
				break;
			default:
				try {
					this.buscarProveedor(request, response);
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
					insertarProveedor(request, response);
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				}

				break;
			case "Modificar":

				try {
					modificarProveedor(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			
				break;
			case "Eliminar":
				try {
					this.eliminarProveedor(request, response);
				} catch (DAOExceptions e1) {
					e1.printStackTrace();
				} 
				
			default:
				try {
					this.buscarProveedor(request, response);
				} catch (DAOExceptions e) {
					e.printStackTrace();
				}
    	}
	}
	
	private void editarProveedor(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException {
		
		String idProveedor = request.getParameter("idProveedor");
		ProveedorJB proveedor = new ProveedorDAO().buscar(idProveedor);
		request.setAttribute("proveedor", proveedor);
		String editJSP = "/WEB-INF/vista/proveedor/editProveedor.jsp";
		request.getRequestDispatcher(editJSP).forward(request, response);
		this.buscarProveedor(request, response);
		
	}

	private void eliminarProveedor(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException {
		
		String idProveedor = request.getParameter("idProveedor");
		ProveedorJB provedor = new ProveedorJB(idProveedor);
		ProveedorDAO regMod = new ProveedorDAO();
		regMod.eliminar(provedor);
		this.buscarProveedor(request, response);
				
	}
	
	private void insertarProveedor(HttpServletRequest request, HttpServletResponse response) throws ParseException, DAOExceptions {
		
		String idProveedor = request.getParameter("idAsistente");
		String name = request.getParameter("nombre");
		String direccion = request.getParameter("direccion");
		String telefono = request.getParameter("telefono");
		
		ProveedorJB proveedor = new ProveedorJB(idProveedor, name, direccion, telefono);
		ProveedorDAO regMod = new ProveedorDAO();
		regMod.insertdata(proveedor);
		
	}
	
	private void modificarProveedor(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, ServletException, IOException, ParseException {

		String name = request.getParameter("nombre");
		String direccion = request.getParameter("direccion");
		String telefono = request.getParameter("telefono");
		
		ProveedorJB proveedor = new ProveedorJB(name, direccion, telefono);
		ProveedorDAO regMod = new ProveedorDAO();
		regMod.modificar(proveedor);
		this.buscarProveedor(request, response);
		
	}
	
	private void buscarProveedor(HttpServletRequest request, HttpServletResponse response) throws DAOExceptions, IOException {
		List<ProveedorJB> proveedores = new ProveedorDAO().showdata();
		System.out.println("Proveedores" + proveedores);
//		HttpSession sesion=request.getSession();
		request.setAttribute("Porveedores", proveedores);
		response.sendRedirect("proveedor.jsp");
		
	}

}
