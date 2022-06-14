package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.AsistenteJB;


public class AsistenteDAO {

	final String ADDUSER = "INSERT INTO assistant (idassistant,assistant_name,assist_age,assist_birth,education,assist_phone,pass,ajobid) VALUES (?,row(?,?,?),?,?,?,?,?,?)";
	final String GETALL = "SELECT idassistant,(assistant_name).name,(assistant_name).last_father,(assistant_name).last_mother,assist_age,assist_birth,education,assist_phone FROM assistant";
	final String UPDATEUSER = "UPDATE assistant SET assistant_name.name = ?,assistant_name.last_father = ?,assistant_name.last_mother = ?,assist_age = ?,assist_birth = ?,education = ?,assist_phone = ?, pass = ? WHERE idassistant = ?";
	final String DELETEUSER = "DELETE FROM assistant WHERE idassistant = ?";
	final String VERUSER = "SELECT idassistant,(assistant_name).name,(assistant_name).last_father,(assistant_name).last_mother,assist_age,assist_birth,education,assist_phone,pass FROM assistant WHERE idassistant = ? AND (assistant_name).name = ?";

	
	Connection con = null;
	PreparedStatement pst = null;
	Statement cst = null;
	ResultSet res = null;
	
	
	
	//Para insertar datos en la tabla asistente
	public int insertdata(AsistenteJB asistente) throws SQLException{
		int result = 0;
		con = ConexionBD.getCon();
		
		pst = con.prepareStatement(ADDUSER);
		pst.setString(1,asistente.getIdasistente());
		pst.setString(2,asistente.getAsist_name());
		pst.setString(3,asistente.getAsist_paterno());
		pst.setString(4,asistente.getAsist_materno());
		pst.setInt(5,asistente.getAsist_edad());
		pst.setDate(6, new Date(asistente.getAsist_f_nac().getTime()));
		pst.setString(7,asistente.getEducacion());
		pst.setString(8,asistente.getTelefono_asist());
		pst.setString(9, asistente.getPass());
		pst.setObject(10, asistente.getTrabajo());
		
		result = pst.executeUpdate();
		
		con.close();
		return result;
	
	}
	//Login del usuario asistente
	public boolean asistLogin(AsistenteJB asistente) throws SQLException{
		String LOGUSER = "SELECT idassistant,(assistant_name).name,(assistant_name).last_father,(assistant_name).last_mother,assist_age,assist_birth,education,assist_phone,pass,job_name FROM assistant INNER JOIN job ON ajobid = idjob WHERE idassistant = ? AND pass = ? ";
		con = ConexionBD.getCon();
		
		pst = con.prepareStatement(LOGUSER);
		pst.setString(1, asistente.getIdasistente());
		pst.setString(2, asistente.getPass());
		res = pst.executeQuery();
		if(res.next()) {
			System.out.println("nombre "+asistente.getAsist_name()+" Sesion iniciada");
			return true;
			
		}
		
		
		return false;
		
	}
	
	public AsistenteJB verAsist(String idasist, String pass){
		con = ConexionBD.getCon();
		AsistenteJB asistente=null;
		String sqlVer = "SELECT idassistant,(assistant_name).name,(assistant_name).last_father,(assistant_name).last_mother,assist_age,assist_birth,education,assist_phone,pass,job_name FROM assistant INNER JOIN job ON ajobid = idjob WHERE idassistant = ? AND pass = ? ";
		
		try {
			pst=con.prepareStatement(sqlVer);
			pst.setString(1, idasist);
			pst.setString(2, pass);
			res = pst.executeQuery();
			while(res.next()) {
				asistente = transformar(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println("Nombre completo: " + asistente.getAsist_name() + " " + asistente.getAsist_paterno() + " " + asistente.getAsist_materno());
		return asistente;
	}	

	//Para modificar datos de asistente
	public int modificar(AsistenteJB asistente) throws SQLException{
		int result = 0;
		String update = "UPDATE assistant SET assistant_name.name = ?,assistant_name.last_father = ?,assistant_name.last_mother = ?,assist_age = ?,assist_birth = ?,education = ?,assist_phone = ?, pass = ? WHERE idassistant = ?";

		
		con = ConexionBD.getCon();
		
		pst = con.prepareStatement(update);		
		pst.setString(1, asistente.getAsist_name());
		pst.setString(2,asistente.getAsist_paterno());
		pst.setString(3,asistente.getAsist_materno());
		pst.setInt(4,asistente.getAsist_edad());
		pst.setDate(5, new Date(asistente.getAsist_f_nac().getTime()));
		pst.setString(6,asistente.getEducacion());
		pst.setString(7,asistente.getTelefono_asist());
		pst.setString(8, asistente.getPass());
		pst.setString(9, asistente.getIdasistente());
			
		result = pst.executeUpdate();
		
		con.close();
		return result;
	}
	
	//Para eliminar un asistente(Solo el doctor puede usar esta opcion)
	public int eliminar(AsistenteJB asistente) throws SQLException{
		int res = 0;
		con = ConexionBD.getCon();

		pst = con.prepareStatement(DELETEUSER);
		pst.setString(1, asistente.getIdasistente());
			
		res = pst.executeUpdate();
		
		con.close();
		return res;
		
	}
	
	public AsistenteJB transformar(ResultSet rs) throws SQLException{
		String name = rs.getString("name");
		String paterno = rs.getString("last_father");
		String materno = rs.getString("last_mother");
		int edad = rs.getInt("assist_age");
		Date fecha_nac = rs.getDate("assist_birth");
		String educacion = rs.getString("education");
		String telefono_asist = rs.getString("assist_phone");
		//String password = rs.getString("pass");
		String trabajo= rs.getString("job_name");
		
		AsistenteJB asistente = new AsistenteJB(name,paterno,materno,edad,fecha_nac,educacion,telefono_asist,trabajo);
		asistente.setIdasistente(rs.getString("idassistant"));
		asistente.setPass(rs.getString("pass"));
		return asistente;
	}
	
	//Para mostrar todos los datos de los asistentes(Solo el doctor puede usar esta opcion)
	public List<AsistenteJB> showdata() throws SQLException{
		//Connection con = null;
		//PreparedStatement st = null;
		//ResultSet res = null;
		String VERUSER = "SELECT idassistant,(assistant_name).name,(assistant_name).last_father,(assistant_name).last_mother,assist_age,assist_birth,education,assist_phone,pass,job_name FROM assistant INNER JOIN job ON ajobid = idjob WHERE idassistant = ? AND pass = ?";
		
		List<AsistenteJB> asistente = new ArrayList<>();
		//try {
			con = ConexionBD.getCon();
			pst=con.prepareStatement(VERUSER);
			res=pst.executeQuery();
			
			while(res.next()) {
				asistente.add(transformar(res));
			}
			res.close();
			pst.close();
			con.close();
			/*ConexionBD.close(res);
			ConexionBD.close(pst);
			ConexionBD.close(con);*/
				
			for (AsistenteJB asistant: asistente) {
				System.out.println("ID: " + asistant.getIdasistente());
				System.out.println("Nombre Completo: " + asistant.getAsist_name() + " " + asistant.getAsist_paterno() + " " + asistant.getAsist_materno());
				System.out.println("Edad: " + asistant.getAsist_edad());
				System.out.println("Fecha de Nacimiento: " + asistant.getAsist_f_nac());
				System.out.println("Educacion: " + asistant.getEducacion());
				System.out.println("Telefono: " + asistant.getTelefono_asist());
				System.out.println("Contraseña: " + asistant.getPass());
				System.out.println("Trabajo: " + asistant.getTrabajo());
				System.out.print("\n");
			}
		/*}catch (SQLException e) {
			throw new DAOExceptions("Error en SQL",e);
		}*/
		return asistente;
	}
	
	//Para buscar un dato en la tabla asistente(Solo el doctor puede usar esta opcion)
	public AsistenteJB buscar(String id) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		AsistenteJB asistente = null;
		String VERUSER = "SELECT idassistant,(assistant_name).name,(assistant_name).last_father,(assistant_name).last_mother,assist_age,assist_birth,education,assist_phone,pass,job_name FROM assistant INNER JOIN job ON ajobid = idjob WHERE idassistant = ? AND pass = ?";
		
		try {
			con = ConexionBD.getCon();
			pst = con.prepareStatement(VERUSER);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				asistente = transformar(rs);
			}else {
				throw new DAOExceptions("No se encontro al usuario.");
			}
			
			ConexionBD.close(rs);
			ConexionBD.close(pst);
			ConexionBD.close(con);
			
			System.out.println("Nombre completo: " + asistente.getAsist_name() + " " + asistente.getAsist_paterno() + " " + asistente.getAsist_materno());
			System.out.println("Edad: " + asistente.getAsist_edad());
			System.out.println("Fecha de Nacimiento: " + asistente.getAsist_f_nac());
			System.out.println("Educacion: " + asistente.getEducacion());
			System.out.println("Telefono: " + asistente.getTelefono_asist());

		}catch(SQLException e){
			throw new DAOExceptions("Error en SQL", e);
		}
		return asistente;
	}
}
