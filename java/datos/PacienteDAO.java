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
			con = ConexionBD.getCon();
			pst = con.prepareStatement(ADDUSER);
				
			pst.setString(1,paciente.getIdpaciente());
			pst.setString(2,paciente.getNombreP());
			pst.setString(3,paciente.getPaterno());
			pst.setString(4,paciente.getMaterno());
			pst.setInt(5,paciente.getEdad());
			pst.setString(6,paciente.getSexo());
			pst.setDate(7, new Date(paciente.getFecha_nac().getTime()));
			pst.setString(8,paciente.getMadre());
			pst.setString(9,paciente.getPadre());
			pst.setString(10,paciente.getNum_telefono());
			pst.setInt(11,paciente.getTipo());
			pst.setDouble(12, paciente.getPeso());
			pst.setInt(13,paciente.getAltura());
			pst.setDouble(14, paciente.getTemperatura());
			
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
			con = ConexionBD.getCon();
			pst = con.prepareStatement(UPDATEUSER);
				
			pst.setString(1,paciente.getNombreP());
			pst.setString(2,paciente.getPaterno());
			pst.setString(3,paciente.getMaterno());
			pst.setInt(4,paciente.getEdad());
			pst.setString(5,paciente.getSexo());
			pst.setDate(6, new Date(paciente.getFecha_nac().getTime()));
			pst.setString(7,paciente.getMadre());
			pst.setString(8,paciente.getPadre());
			pst.setString(9,paciente.getNum_telefono());
			pst.setInt(10,paciente.getTipo());
			pst.setDouble(11, paciente.getPeso());
			pst.setInt(12,paciente.getAltura());
			pst.setDouble(13, paciente.getTemperatura());
				
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
			con = ConexionBD.getCon();
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
		String sexo = rs.getString("sexo");
		Date fecha_nac = rs.getDate("datebirth");
		String madre = rs.getString("mother_name");
		String padre = rs.getString("father_name");
		String telefono = rs.getString("phone_number");
		int tipo = rs.getInt("pacient_t");
		double peso = rs.getDouble("weight");
		int altura = rs.getInt("height");
		double temp = rs.getDouble("temp");
		
		PacienteJB paciente = new PacienteJB(name,paterno,materno,edad,sexo,fecha_nac,madre,padre,telefono,tipo,peso,altura,temp);
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
			con = ConexionBD.getCon();
			st=con.prepareStatement(GETALL);
			res=st.executeQuery();
			
			while(res.next()) {
				paciente.add(transformar(res));
			}	
			ConexionBD.close(res);
			ConexionBD.close(st);
			ConexionBD.close(con);
			
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
			con = ConexionBD.getCon();
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
				
		}catch(SQLException e){
			throw new DAOExceptions("Error en SQL", e);
		}
		return paciente;
	}
}
