package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.CitasJB;

public class CitasDAO {
	ConexionBD conec = new ConexionBD();
	final String ADDUSER = "INSERT INTO appointments (idappoint,appoint_cost,cunsult_tp) VALUES (?,?,?)";
	final String GETALL = "SELECT idappoint,appoint_cost,consult_tp FROM appointments";
	final String UPDATEUSER = "UPDATE appointments SET appoint_cost = ?,consult_tp = ? WHERE idappoint = ?";
	final String DELETEUSER = "DELETE FROM appointments WHERE idappoint = ?";
	final String GETUSER = "SELECT idappoint,appoint_cost,consult_tp FROM appointments WHERE idappoint = ?";
	
	//Para insertar datos en la tabla citas
	public void insertdata(CitasJB citas) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
			
		try {
			con = conec.getCon();
			pst = con.prepareStatement(ADDUSER);
			
			pst.setInt(1, citas.getIdcitas());
			pst.setDouble(2, citas.getCosto_cita());
			pst.setInt(3, citas.getTipo_consulta());;
				
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
	
	//Para modificar datos de la tabla citas
	public void modificar(CitasJB citas) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
			
		try {
			con = conec.getCon();
			pst = con.prepareStatement(UPDATEUSER);
				
			pst.setDouble(1, citas.getCosto_cita());
			pst.setInt(2, citas.getTipo_consulta());
				
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
	
	//Para eliminar un dato de la tabla citas
	public void eliminar(CitasJB citas) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
			
		try {
			con = conec.getCon();
			pst = con.prepareStatement(DELETEUSER);
				
			pst.setInt(1, citas.getIdcitas());
				
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
	
	public CitasJB transformar(ResultSet rs) throws SQLException{
		double costo = rs.getDouble("appoint_cost");
		int tipo = rs.getInt("consult_tp");
			
		CitasJB citas = new CitasJB(costo,tipo);
		citas.setIdcitas(rs.getInt("idappoint"));
		return citas;
	}
	
	//Para mostrar todos los datos de la tabla citas
	public List<CitasJB> showdata() throws DAOExceptions{
		Connection con = null;
		PreparedStatement st = null;
		ResultSet res = null;
			
		List<CitasJB> citas = new ArrayList<>();
		try {
			con = conec.getCon();
			st=con.prepareStatement(GETALL);
			res=st.executeQuery();
				
			while(res.next()) {
				citas.add(transformar(res));
			}	
			ConexionBD.close(res);
			ConexionBD.close(st);
			ConexionBD.close(con);
					
			for (CitasJB appoint: citas) {
				System.out.println("ID: " + appoint.getIdcitas());
				System.out.println("Precio: " + appoint.getCosto_cita());
				System.out.println("Tipo: " + appoint.getTipo_consulta());
				System.out.print("\n");
			}
		}catch (SQLException e) {
			throw new DAOExceptions("Error en SQL",e);
		}
		return citas;
	}
	
	//Para buscar un dato en la tabla citas
	public CitasJB buscar(String id) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		CitasJB citas = null;
			
		try {
			con = conec.getCon();
			pst = con.prepareStatement(GETUSER);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				citas = transformar(rs);
			}else {
				throw new DAOExceptions("No se encontro al usuario.");
			}
			
			ConexionBD.close(rs);
			ConexionBD.close(pst);
			ConexionBD.close(con);
				
			System.out.println("Precio: " + citas.getCosto_cita());
			System.out.println("Tipo: " + citas.getTipo_consulta());

		}catch(SQLException e){
			throw new DAOExceptions("Error en SQL", e);
		}
		return citas;
	}
	
}
