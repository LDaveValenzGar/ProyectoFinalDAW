package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConClassToBD {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	
	private String user = "postgres";
	private String pass = "12345";
	private String url = "jdbc:postgresql://localhost:5433/ProyectoFinalDAW";
	private String driver = "org.postgresql.Driver";
	
	public ConClassToBD() {
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
	
	public static void close(ResultSet res) {
		try {
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement st) {
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
