package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.AsistenteJB;

public class AsistenteDAO {
	ConexionBD conec = new ConexionBD();
	final String ADDUSER = "INSERT INTO assistant (idassistant,assistant_name,assist_age,assist_birth,education,assist_phone) VALUES (?,row(?,?,?),?,?,?,?)";
	final String GETALL = "SELECT idassistant,(assistant_name).name,(assistant_name).last_father,(assistant_name).last_mother,assist_age,assist_birth,education,assist_phone FROM assistant";
	final String UPDATEUSER = "UPDATE assistant SET assistant_name.name = ?,assistant_name.last_father = ?,assistant_name.last_mother = ?,assist_age = ?,assist_birth = ?,education = ?,assist_phone = ? WHERE idassistant =?";
	final String DELETEUSER = "DELETE FROM assistant WHERE idassistant = ?";
	final String GETUSER = "SELECT idassistant,(assistant_name).name,(assistant_name).last_father,(assistant_name).last_mother,assist_age,assist_birth,education,assist_phone FROM assistant WHERE idassistant = ?";
	
	//Para insertar datos en la tabla asistente
	public void insertdata(AsistenteJB asistente) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = conec.getCon();
			pst = con.prepareStatement(ADDUSER);
			
			pst.setString(1,asistente.getIdasistente());
			pst.setString(2,asistente.getAsist_name());
			pst.setString(3,asistente.getAsist_paterno());
			pst.setString(4,asistente.getAsist_materno());
			pst.setInt(5,asistente.getAsist_edad());
			pst.setDate(6, new Date(asistente.getAsist_f_nac().getTime()));
			pst.setString(7,asistente.getEducacion());
			pst.setString(8,asistente.getTelefono_asist());
			
			if(pst.executeUpdate() == 0) {
				throw new DAOExceptions("No se pudo guardar.");
			}else {		
				System.out.println("Agregado con exito");
			}
			
			ConexionBD.close(pst);
			ConexionBD.close(con);		
		}catch(SQLException e) {
			throw new DAOExceptions("Error en SQL",e);
		}
	}
	
	//Para modificar datos de la tabla asistente
	public void modificar(AsistenteJB asistente) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = conec.getCon();
			pst = con.prepareStatement(UPDATEUSER);
			
			pst.setString(1, asistente.getAsist_name());
			pst.setString(2,asistente.getAsist_paterno());
			pst.setString(3,asistente.getAsist_materno());
			pst.setInt(4,asistente.getAsist_edad());
			pst.setDate(5, new Date(asistente.getAsist_f_nac().getTime()));
			pst.setString(6,asistente.getEducacion());
			pst.setString(7,asistente.getTelefono_asist());
			pst.setString(8, asistente.getIdasistente());
			
			if(pst.executeUpdate() == 0) {
				throw new DAOExceptions("No se pudo actualizar.");
			}else {		
				System.out.println("Actualizado con exito");
			}
			
			ConexionBD.close(pst);
			ConexionBD.close(con);
		}catch(SQLException e){
			throw new DAOExceptions("Error en SQL", e);
		}
		
	}
	
	//Para eliminar un dato de la tabla asistente
	public void eliminar(AsistenteJB asistente) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = conec.getCon();
			pst = con.prepareStatement(DELETEUSER);
			
			pst.setString(1, asistente.getIdasistente());
			
			if(pst.executeUpdate() == 0) {
				throw new DAOExceptions("No se pudo eliminar.");
			}else {		
				System.out.println("Eliminado con exito");
			}
			
			ConexionBD.close(pst);
			ConexionBD.close(con);
		}catch(SQLException e) {
			throw new DAOExceptions("Error en SQL", e);
		}
	}
	
	public AsistenteJB transformar(ResultSet rs) throws SQLException{
		String name = rs.getString("name");
		String paterno = rs.getString("last_father");
		String materno = rs.getString("last_mother");
		int edad = rs.getInt("assist_age");
		Date fecha_nac = rs.getDate("assist_birth");
		String educacion = rs.getString("education");
		String telefono_asist = rs.getString("assist_phone");
		
		AsistenteJB asistente = new AsistenteJB(name,paterno,materno,edad,fecha_nac,educacion,telefono_asist);
		asistente.setIdasistente(rs.getString("idassistant"));
		return asistente;
	}
	
	//Para mostrar todos los datos de la tabla asistente
	public List<AsistenteJB> showdata() throws DAOExceptions{
		Connection con = null;
		PreparedStatement st = null;
		ResultSet res = null;
		
		List<AsistenteJB> asistente = new ArrayList<>();
		try {
			con = conec.getCon();
			st=con.prepareStatement(GETALL);
			res=st.executeQuery();
			
			while(res.next()) {
				asistente.add(transformar(res));
			}	
			ConexionBD.close(res);
			ConexionBD.close(st);
			ConexionBD.close(con);
				
			for (AsistenteJB asistant: asistente) {
				System.out.println("ID: " + asistant.getIdasistente());
				System.out.println("Nombre Completo: " + asistant.getAsist_name() + " " + asistant.getAsist_paterno() + " " + asistant.getAsist_materno());
				System.out.println("Edad: " + asistant.getAsist_edad());
				System.out.println("Fecha de Nacimiento: " + asistant.getAsist_f_nac());
				System.out.println("Educacion: " + asistant.getEducacion());
				System.out.println("Telefono: " + asistant.getTelefono_asist());
				System.out.print("\n");
			}
		}catch (SQLException e) {
			throw new DAOExceptions("Error en SQL",e);
		}
		return asistente;
	}
	
	//Para buscar un dato en la tabla asistente
	public AsistenteJB buscar(String id) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		AsistenteJB asistente = null;
		
		try {
			con = conec.getCon();
			pst = con.prepareStatement(GETUSER);
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
