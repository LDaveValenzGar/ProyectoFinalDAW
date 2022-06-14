package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBD {
	
	PreparedStatement st = null;
	ResultSet rs = null;

	public static Connection getCon() {
		Connection con = null;
		String user = "postgres";
		String pass = "12345";
		String url = "jdbc:postgresql://localhost:5433/ProyectoFinalDAW";
		String driver = "org.postgresql.Driver";
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,pass);
			if(con != null) {
				System.out.println("Conexion a BD lista");		}
		} catch (Exception ex) {
			System.out.println("Error al conectar a la Base de Datos" + ex);
		}
		
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
