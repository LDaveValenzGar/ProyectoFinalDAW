package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.PacienteJB;

public class PacienteDAO {
	ConexionBD conec = new ConexionBD();
	final String ADDUSER = "INSERT INTO pacient (idpacient,pacient_name,age,datebirth,parent_name,phone_number,pacient_t) VALUES (?,row(?,?,?),?,?,row(?,?,?),?,?)";
	final String GETALL = "SELECT idpacient,(pacient_name).name,(pacient_name).last_father,(pacient_name).last_mother,age,datebirth,(parent_name).name,(parent_name).last_father,(parent_name).last_mother,phone_number,pacient_t FROM pacient";
	final String UPDATEUSER = "UPDATE pacient SET pacient_name.name = ?,pacient_name.last_father = ?,pacient_name.last_mother = ?,age = ?,datebirth = ?,parent_name.name = ?,parent_name.last_father = ?,parent_name.last_mother = ?,phone_number = ?,pacient_t = ? WHERE idpacient = ?";
	final String DELETEUSER = "DELETE FROM pacient WHERE idpacient = ?";
	final String GETUSER = "SELECT idpacient,(pacient_name).name,(pacient_name).last_father,(pacient_name).last_mother,age,datebirth,(parent_name).name,(parent_name).last_father,(parent_name).last_mother,phone_number,pacient_t FROM pacient WHERE idpacient = ?";

	//Para insertar datos en la tabla paciente
	public void insertdata(PacienteJB paciente) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
			
		try {
			con = conec.getCon();
			pst = con.prepareStatement(ADDUSER);
				
			pst.setString(1,paciente.getIdpaciente());
			pst.setString(2,paciente.getNombreP());
			pst.setString(3,paciente.getPaterno());
			pst.setString(4,paciente.getMaterno());
			pst.setInt(5,paciente.getEdad());
			pst.setDate(6, new Date(paciente.getFecha_nac().getTime()));
			pst.setString(7,paciente.getNom_padre());
			pst.setString(8,paciente.getPat_padre());
			pst.setString(9,paciente.getMat_padre());
			pst.setString(10,paciente.getNum_telefono());
			pst.setInt(11,paciente.getTipo());
			
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
	
	//Para modificar datos de la tabla paciente
	public void modificar(PacienteJB paciente) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
			
		try {
			con = conec.getCon();
			pst = con.prepareStatement(UPDATEUSER);
				
			pst.setString(1,paciente.getNombreP());
			pst.setString(2,paciente.getPaterno());
			pst.setString(3,paciente.getMaterno());
			pst.setInt(4,paciente.getEdad());
			pst.setDate(5, new Date(paciente.getFecha_nac().getTime()));
			pst.setString(6,paciente.getNom_padre());
			pst.setString(7,paciente.getPat_padre());
			pst.setString(8,paciente.getMat_padre());
			pst.setString(9,paciente.getNum_telefono());
			pst.setInt(10,paciente.getTipo());
				
			if(pst.executeUpdate() == 0) {
				throw new DAOExceptions("No se pudo modificar.");
			}else {		
				System.out.println("Actualizado con exito");
			}
				
			ConexionBD.close(pst);
			ConexionBD.close(con);
		}catch(SQLException e){
			throw new DAOExceptions("Error en SQL", e);
		}
			
	}
	
	//Para eliminar un dato de la tabla doctor
	public void eliminar(PacienteJB paciente) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = conec.getCon();
			pst = con.prepareStatement(DELETEUSER);
			
			pst.setString(1, paciente.getIdpaciente());
			
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
		
	public PacienteJB transformar(ResultSet rs) throws SQLException{
		String name = rs.getString("name");
		String paterno = rs.getString("last_father");
		String materno = rs.getString("last_mother");
		int edad = rs.getInt("age");
		Date fecha_nac = rs.getDate("datebirth");
		String pname = rs.getString("name");
		String ppaterno = rs.getString("last_father");
		String pmaterno = rs.getString("last_mother");
		String telefono = rs.getString("phone_number");
		int tipo = rs.getInt("pacient_t");
		
		PacienteJB paciente = new PacienteJB(name,paterno,materno,edad,fecha_nac,pname,ppaterno,pmaterno,telefono,tipo);
		paciente.setIdpaciente(rs.getString("idpacient"));
		return paciente;
	}
	
	//Para mostrar todos los datos de la tabla doctor
	public List<PacienteJB> showdata() throws DAOExceptions{
		Connection con = null;
		PreparedStatement st = null;
		ResultSet res = null;
		
		List<PacienteJB> paciente = new ArrayList<>();
		try {
			con = conec.getCon();
			st=con.prepareStatement(GETALL);
			res=st.executeQuery();
			
			while(res.next()) {
				paciente.add(transformar(res));
			}	
			ConexionBD.close(res);
			ConexionBD.close(st);
			ConexionBD.close(con);
					
			for (PacienteJB pacient: paciente) {
				System.out.println("ID: " + pacient.getIdpaciente());
				System.out.println("Nombre Completo de Paciente: " + pacient.getNombreP() + " " + pacient.getPaterno() + " " + pacient.getMaterno());
				System.out.println("Edad: " + pacient.getEdad());
				System.out.println("Fecha de Nacimiento: " + pacient.getFecha_nac());
				System.out.println("Nombre Completo de padre: " + pacient.getNom_padre() + " " + pacient.getPat_padre() + " " + pacient.getMat_padre());
				System.out.println("Telefono: " + pacient.getNum_telefono());
				System.out.println("Tipo de Paciente: " + pacient.getTipo());
				System.out.print("\n");
			}
		}catch (SQLException e) {
			throw new DAOExceptions("Error en SQL",e);
		}
		return paciente;
	}
	
	//Para buscar un dato en la tabla doctor
	public PacienteJB buscar(String id) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		PacienteJB paciente = null;
			
		try {
			con = conec.getCon();
			pst = con.prepareStatement(GETUSER);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				paciente = transformar(rs);
			}else {
				throw new DAOExceptions("No se encontro al usuario.");
			}
				
			ConexionBD.close(rs);
			ConexionBD.close(pst);
			ConexionBD.close(con);
				
			System.out.println("Nombre Completo de Paciente: " + paciente.getNombreP() + " " + paciente.getPaterno() + " " + paciente.getMaterno());
			System.out.println("Edad: " + paciente.getEdad());
			System.out.println("Fecha de Nacimiento: " + paciente.getFecha_nac());
			System.out.println("Nombre Completo de padre: " + paciente.getNom_padre() + " " + paciente.getPat_padre() + " " + paciente.getMat_padre());
			System.out.println("Telefono: " + paciente.getNum_telefono());
			System.out.println("Tipo de Paciente: " + paciente.getTipo());

		}catch(SQLException e){
			throw new DAOExceptions("Error en SQL", e);
		}
		return paciente;
	}
}
