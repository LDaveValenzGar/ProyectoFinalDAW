package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.DoctorJB;

public class DoctorDAO {
	ConexionBD conec = new ConexionBD();
	final String ADDUSER = "INSERT INTO doctor (iddoctor,doctor_name,dr_age,dr_birth,dr_phone,specialty,schedule,position) VALUES (?,row(?,?,?),?,?,?,?,?,?)";
	final String GETALL = "SELECT iddoctor,(doctor_name).name,(doctor_name).last_father,(doctor_name).last_mother,dr_age,dr_birth,dr_phone,specialty,position FROM doctor";
	final String UPDATEUSER = "UPDATE doctor SET doctor_name.name = ?,doctor_name.last_father = ?,doctor_name.last_mother = ?,dr_age = ?,dr_birth = ?,dr_phone = ?,specialty = ?,position = ? WHERE iddoctor = ?";
	final String DELETEUSER = "DELETE FROM doctor WHERE iddoctor = ?";
	final String GETUSER = "SELECT iddoctor,(doctor_name).name,(doctor_name).last_father,(doctor_name).last_mother,dr_age,dr_birth,dr_phone,specialty,position FROM doctor WHERE iddoctor = ?";
	
	Connection con = null;
	PreparedStatement pst = null;
	Statement cst = null;
	ResultSet res = null;
	
	
	//Para insertar datos en la tabla doctor
	public void insertdata(DoctorJB doctor) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = ConexionBD.getCon();
			pst = con.prepareStatement(ADDUSER);
			
			pst.setString(1,doctor.getIddoctor());
			pst.setString(2,doctor.getDoc_name());
			pst.setString(3,doctor.getDoc_paterno());
			pst.setString(4,doctor.getDoc_materno());
			pst.setInt(5,doctor.getDr_edad());
			pst.setDate(6, new Date(doctor.getFecha_nacdoc().getTime()));
			pst.setString(7,doctor.getNum_doctor());
			pst.setString(8,doctor.getEspecialidad());
			pst.setString(9,doctor.getPass());
			pst.setString(10,doctor.getCargo());
			
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
	
	//Login del usuario asistente
		public boolean docLogin(DoctorJB doctor) throws SQLException{
			String LOGUSER = "SELECT iddoctor,(doctor_name).name,(doctor_name).last_father,(doctor_name).last_mother,dr_age,dr_birth,education,dr_phone,specialty,pass,job_name FROM doctor INNER JOIN job ON djobid = idjob WHERE iddoctor = ? AND pass = ? ";
			con = ConexionBD.getCon();
			
			pst = con.prepareStatement(LOGUSER);
			pst.setString(1, doctor.getIddoctor());
			pst.setString(2, doctor.getPass());
			res = pst.executeQuery();
			if(res.next()) {
				System.out.println("nombre "+doctor.getDoc_name()+" Sesion iniciada");
				return true;
				
			}
			
			
			return false;
			
		}
	
		public DoctorJB verAsist(String iddoc, String pass){
			con = ConexionBD.getCon();
			DoctorJB doctor=null;
			String sqlVer = "SELECT iddoctor,(doctor_name).name,(doctor_name).last_father,(doctor_name).last_mother,dr_age,dr_birth,education,dr_phone,specialty,pass,job_name FROM doctor INNER JOIN job ON djobid = idjob WHERE iddoctor = ? AND pass = ? ";
			
			try {
				pst=con.prepareStatement(sqlVer);
				pst.setString(1, iddoc);
				pst.setString(2, pass);
				res = pst.executeQuery();
				while(res.next()) {
					doctor = transformar(res);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			System.out.println("Nombre completo: " + doctor.getDoc_name() + " " + doctor.getDoc_paterno() + " " + doctor.getDoc_materno());
			return doctor;
		}		
		
	//Para modificar datos de la tabla doctor
	public void modificar(DoctorJB doctor) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = ConexionBD.getCon();
			pst = con.prepareStatement(UPDATEUSER);
			
			pst.setString(1,doctor.getDoc_name());
			pst.setString(2,doctor.getDoc_paterno());
			pst.setString(3,doctor.getDoc_materno());
			pst.setInt(4,doctor.getDr_edad());
			pst.setDate(5, new Date(doctor.getFecha_nacdoc().getTime()));
			pst.setString(6,doctor.getNum_doctor());
			pst.setString(7,doctor.getEspecialidad());
			pst.setString(8,doctor.getPass());
			pst.setString(9,doctor.getCargo());
			
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
	public void eliminar(DoctorJB doctor) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = ConexionBD.getCon();
			pst = con.prepareStatement(DELETEUSER);
			
			pst.setString(1, doctor.getIddoctor());
			
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
	
	public DoctorJB transformar(ResultSet rs) throws SQLException{
		String drname = rs.getString("name");
		String drpaterno = rs.getString("last_father");
		String drmaterno = rs.getString("last_mother");
		int dredad = rs.getInt("dr_age");
		Date drfecha_nac = rs.getDate("dr_birth");
		String drtelefono = rs.getString("dr_phone");
		String especialidad = rs.getString("specialty");
		String horario = rs.getString("schedule");
		String posicion = rs.getString("position");
		
		DoctorJB doctor = new DoctorJB(drname,drpaterno,drmaterno,dredad,drfecha_nac,drtelefono,especialidad,horario,posicion);
		doctor.setIddoctor(rs.getString("iddoctor"));
		return doctor;
	}
	
	//Para mostrar todos los datos de la tabla doctor
	public List<DoctorJB> showdata() throws DAOExceptions{
		Connection con = null;
		PreparedStatement st = null;
		ResultSet res = null;
		
		List<DoctorJB> doctor = new ArrayList<>();
		try {
			con = ConexionBD.getCon();
			st=con.prepareStatement(GETALL);
			res=st.executeQuery();
			
			while(res.next()) {
				doctor.add(transformar(res));
			}	
			ConexionBD.close(res);
			ConexionBD.close(st);
			ConexionBD.close(con);
				
			for (DoctorJB doc: doctor) {
				System.out.println("ID: " + doc.getIddoctor());
				System.out.println("Nombre Completo: " + doc.getDoc_name() + " " + doc.getDoc_paterno() + " " + doc.getDoc_materno());
				System.out.println("Edad: " + doc.getDr_edad());
				System.out.println("Fecha de Nacimiento: " + doc.getFecha_nacdoc());
				System.out.println("Telefono: " + doc.getNum_doctor());
				System.out.println("Especialidad: " + doc.getEspecialidad());
				System.out.println("Horario: " + doc.getPass());
				System.out.println("Posicion: " + doc.getCargo());
				System.out.print("\n");
			}
		}catch (SQLException e) {
			throw new DAOExceptions("Error en SQL",e);
		}
		return doctor;
	}
	
	//Para buscar un dato en la tabla doctor
	public DoctorJB buscar(String id) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		DoctorJB doctor = null;
		
		try {
			con = ConexionBD.getCon();
			pst = con.prepareStatement(GETUSER);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				doctor = transformar(rs);
			}else {
				throw new DAOExceptions("No se encontro al usuario.");
			}
			
			ConexionBD.close(rs);
			ConexionBD.close(pst);
			ConexionBD.close(con);
			
			System.out.println("Nombre Completo: " + doctor.getDoc_name() + " " + doctor.getDoc_paterno() + " " + doctor.getDoc_materno());
			System.out.println("Edad: " + doctor.getDr_edad());
			System.out.println("Fecha de Nacimiento: " + doctor.getFecha_nacdoc());
			System.out.println("Telefono: " + doctor.getNum_doctor());
			System.out.println("Especialidad: " + doctor.getEspecialidad());
			System.out.println("Horario: " + doctor.getPass());
			System.out.println("Posicion: " + doctor.getCargo());

		}catch(SQLException e){
			throw new DAOExceptions("Error en SQL", e);
		}
		return doctor;
	}
}
