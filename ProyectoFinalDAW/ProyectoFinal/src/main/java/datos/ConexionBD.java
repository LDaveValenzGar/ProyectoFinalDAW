package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBD {
	Connection con = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	
	private String user = "postgres";
	private String pass = "12345";
	private String url = "jdbc:postgresql://localhost:5433/ProyectoFinalDAW";
	private String driver = "org.postgresql.Driver";
	
	public ConexionBD() {
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,pass);
			if(con != null) {
				System.out.println("Conexion a BD lista");		}
		} catch (Exception ex) {
			System.out.println("Error al conectar a la Base de Datos" + ex);
		}
		
	}
	
	public Connection getCon() {
		return con;
	}
	
	public static void close(ResultSet res) throws DAOExceptions{
		try {
			res.close();
		} catch (SQLException e) {
			new DAOExceptions("Error en SQL", e);
		}
	}
	
	public static void close(PreparedStatement st) throws DAOExceptions{
		try {
			st.close();
		} catch (SQLException e) {
			new DAOExceptions("Error en SQL", e);
		}
	}
	
	public static void close(Connection con) throws DAOExceptions{
		try {
			con.close();
		} catch (SQLException e) {
			new DAOExceptions("Error en SQL", e);
		}
	}
	
}
