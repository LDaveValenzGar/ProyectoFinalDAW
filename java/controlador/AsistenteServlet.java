package controlador;

import datos.AsistenteDAO;
import modelo.AsistenteJB;

import java.io.IOException;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AsistenteServlet", urlPatterns=("/AsistenteServlet"))
public class AsistenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
    	String accion = request.getParameter("accion");
    	
	    if (accion != null) {
	    	switch(accion) {
			    case "login":
					logAsistente(request, response);
					
			    	break;
			    case "datos":
			    	verAsistente(request, response);
			    	break;
			    case "modificar":
					try {
						modAsistente(request, response);
					} catch (ParseException e) {
						e.printStackTrace();
					}
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void logAsistente(HttpServletRequest request, HttpServletResponse response){
		AsistenteJB asistente = new AsistenteJB();
		String idasist = null;
		String pass = null;
		AsistenteDAO asist = new AsistenteDAO();
		HttpSession session=request.getSession();
		idasist = request.getParameter("idasist");
		pass = request.getParameter("pass");
		asistente=asist.verAsist(idasist,pass);
		try {
			if(asist.asistLogin(asistente)) {
				session.setAttribute("idasist", asistente.getIdasistente());
				session.setAttribute("nombre", asistente.getAsist_name());
				session.setAttribute("pass", asistente.getPass());
				RequestDispatcher rd= request.getRequestDispatcher("vistas/asistente/menuAsistente.jsp");
				rd.forward(request, response);
			}else {
				response.sendRedirect("asistenteLogin.jsp");
				session.setAttribute("mensaje", "Fallida");
			}
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
				
	}
	
	private void verAsistente(HttpServletRequest request, HttpServletResponse response) {

		String idasist = null;
		String pass = null;
		AsistenteJB asistente = new AsistenteJB();
		AsistenteDAO asist = new AsistenteDAO();
		
		HttpSession session=request.getSession();
		idasist = (String)session.getAttribute("idasist");
		pass = (String) session.getAttribute("pass");
		
		asistente=asist.verAsist(idasist, pass);
		try {
			if(asist.asistLogin(asistente)) {
			
			session.setAttribute("idasist", asistente.getIdasistente());
			session.setAttribute("nombre", asistente.getAsist_name());
			session.setAttribute("paterno", asistente.getAsist_paterno());
			session.setAttribute("materno", asistente.getAsist_materno());
			session.setAttribute("edad", asistente.getAsist_edad());
			session.setAttribute("fecha", asistente.getAsist_f_nac());
			session.setAttribute("educacion", asistente.getEducacion());
			session.setAttribute("telefono", asistente.getTelefono_asist());
			session.setAttribute("pass", asistente.getPass());
			session.setAttribute("trabajo", asistente.getTrabajo());	
			
			RequestDispatcher rd = request.getRequestDispatcher("vistas/asistente/asistenteDatos.jsp");
			rd.forward(request, response);
			}else {
				response.sendRedirect("asistenteLogin.jsp");
				session.setAttribute("mensaje", "Fallida");
			}
		} catch (SQLException | ServletException | IOException e1) {
			e1.printStackTrace();
		} 
				
	}
	
	private void modAsistente(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		DateFormat form = new SimpleDateFormat("YYYY-MM-DD");
		int result = 0;			
		AsistenteJB asistente = new AsistenteJB();
		AsistenteDAO asist = new AsistenteDAO();	
		HttpSession session=request.getSession();
		
		String idasist = (String)session.getAttribute("idasist");
		String pass = (String) session.getAttribute("pass");
		asistente.setAsist_name(request.getParameter("nombre"));
		asistente.setAsist_paterno(request.getParameter("paterno"));
		asistente.setAsist_materno(request.getParameter("materno"));
		asistente.setAsist_edad(Integer.parseInt(request.getParameter("edad")));
		asistente.setAsist_f_nac(form.parse(request.getParameter("fecha")));
		asistente.setTelefono_asist(request.getParameter("telefono"));
		asistente.setEducacion(request.getParameter("educacion"));
		asistente.setTrabajo(request.getParameter("trabajo"));
		asistente.setPass(request.getParameter("pass"));
		asistente.setIdasistente(request.getParameter("idasist"));
		
		
		try {
			result=asist.modificar(asistente);
			if(result!=0) {
				asistente=asist.verAsist(idasist, pass);
				
				asistente.setIdasistente((String)session.getAttribute("idassist"));
				asistente.setAsist_name((String)session.getAttribute("nombre"));
				asistente.setAsist_paterno((String)session.getAttribute("paterno"));
				asistente.setAsist_materno((String)session.getAttribute("materno"));
				asistente.setAsist_edad(Integer.parseInt((String)session.getAttribute("edad")));
				asistente.setAsist_f_nac(form.parse((String)session.getAttribute("fecha")));
				asistente.setTelefono_asist((String)session.getAttribute("telefono"));
				asistente.setEducacion((String)session.getAttribute("educacion"));
				asistente.setPass((String)session.getAttribute("pass"));
				asistente.setTrabajo((String)session.getAttribute("trabajo"));
				
				session = request.getSession();
				session.setAttribute("idasist", asistente.getIdasistente());
				session.setAttribute("nombre", asistente.getAsist_name());
				session.setAttribute("paterno", asistente.getAsist_paterno());
				session.setAttribute("materno", asistente.getAsist_materno());
				session.setAttribute("edad", asistente.getAsist_edad());
				session.setAttribute("fecha", asistente.getAsist_f_nac());
				session.setAttribute("educacion", asistente.getEducacion());
				session.setAttribute("telefono", asistente.getTelefono_asist());
				session.setAttribute("pass", asistente.getPass());
				
				RequestDispatcher rd = request.getRequestDispatcher("vistas/asistente/asistenteDatos.jsp");
				rd.forward(request, response);
				
			}else {
				PrintWriter out = response.getWriter();
				out.println("Error al modificar");
			}
			
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
			
	}
//Este debe estar en el servlet de doctor y ser remplazado por un registro de pacientes
	/*private void regAsistente(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
		DateFormat form = new SimpleDateFormat("YYYY-MM-DD");
		AsistenteJB asistente = new AsistenteJB();
		AsistenteDAO asist = new AsistenteDAO();
		int result = 0;
		
		asistente.setIdasistente(request.getParameter("idasistente"));
		asistente.setAsist_name(request.getParameter("nombre"));
		asistente.setAsist_paterno(request.getParameter("paterno"));
		asistente.setAsist_materno(request.getParameter("materno"));
		asistente.setAsist_edad(Integer.parseInt(request.getParameter("edad")));
		String fch = (request.getParameter("fecha"));
		asistente.setAsist_f_nac(form.parse(fch));
		asistente.setEducacion(request.getParameter("educacion"));
		asistente.setTelefono_asist(request.getParameter("telefono"));
		asistente.setPass(request.getParameter("pass"));
		
		try {
			if(asist.verAsist(asistente)) {
				result = asist.insertdata(asistente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (result != 0) {
			RequestDispatcher rd = request.getRequestDispatcher("asistenteLog.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect("webapp/index.jsp");
		}
			
	}*/
	
	
	private void salirAsist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute("idasist",null);
		session.invalidate();
		response.sendRedirect("asistenteLog.jsp");
							
	}
	
	private void atras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher rd= request.getRequestDispatcher("vistas/asistente/menuAsistente.jsp");
		rd.forward(request, response);
	}
	
}
