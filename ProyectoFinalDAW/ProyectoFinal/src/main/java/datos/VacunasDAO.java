package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.VacunasJB;

public class VacunasDAO {
	ConexionBD conec = new ConexionBD();
	final String ADDUSER = "INSERT INTO vaccines (idvaccine,vaccine_name,vaccine_type,vaccine_cost,exp,lot,stock,supplier) VALUES (?,?,?,?,?,?,?,?)";
	final String GETALL = "SELECT idvaccine,vaccine_name,vaccine_type,vaccine_cost,exp,lot,stock,supplier FROM vaccines";
	final String UPDATEUSER = "UPDATE vaccines SET vaccine_name = ?,vaccine_type = ?,vaccine_cost = ?,exp = ?,lot = ?,stock = ?,supplier = ? WHERE idvaccine = ?";
	final String DELETEUSER = "DELETE FROM vaccines WHERE idvaccine = ?";
	final String GETUSER = "SELECT idvaccine,vaccine_name,vaccine_type,vaccine_cost,exp,lot,stock,supplier FROM vaccines WHERE idvaccine = ?";
	
	//Para insertar datos en la tabla vacunas
	public void insertdata(VacunasJB vacuna) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
				
		try {
			con = conec.getCon();
			pst = con.prepareStatement(ADDUSER);
				
			pst.setInt(1, vacuna.getIdvacuna());
			pst.setString(2, vacuna.getNombre_vacuna());
			pst.setString(3, vacuna.getVacuna_tipo());
			pst.setDouble(4, vacuna.getPrecio_vacuna());
			pst.setDate(5, new Date(vacuna.getCaducidad().getTime()));
			pst.setString(6, vacuna.getLote());
			pst.setInt(7, vacuna.getStock());
			pst.setString(8, vacuna.getProveedor());
					
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
	
	//Para modificar datos de la tabla vacunas
	public void modificar(VacunasJB vacuna) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
				
		try {
			con = conec.getCon();
			pst = con.prepareStatement(UPDATEUSER);
					
			pst.setString(1, vacuna.getNombre_vacuna());
			pst.setString(2, vacuna.getVacuna_tipo());
			pst.setDouble(3, vacuna.getPrecio_vacuna());
			pst.setDate(4, new Date(vacuna.getCaducidad().getTime()));
			pst.setString(5, vacuna.getLote());
			pst.setInt(6, vacuna.getStock());
			pst.setString(7, vacuna.getProveedor());
					
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
	
	//Para eliminar un dato de la tabla vacunas
	public void eliminar(VacunasJB vacuna) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
			
		try {
			con = conec.getCon();
			pst = con.prepareStatement(DELETEUSER);
				
			pst.setInt(1, vacuna.getIdvacuna());
				
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
	
	public VacunasJB transformar(ResultSet rs) throws SQLException{
		String vacuname = rs.getString("vaccine_name");
		String vacutipo = rs.getString("vaccine_type");
		double vacuprecio = rs.getDouble("vaccine_cost");
		Date expira = rs.getDate("exp");
		String lote = rs.getString("lot");
		int stock = rs.getInt("stock");
		String supp = rs.getString("supplier");
			
		VacunasJB vacunas = new VacunasJB(vacuname,vacutipo,vacuprecio,expira,lote,stock,supp);
		vacunas.setIdvacuna(rs.getInt("idvaccine"));
		return vacunas;		
	}
		
	//Para mostrar todos los datos de la tabla vacunas
	public List<VacunasJB> showdata() throws DAOExceptions{
		Connection con = null;
		PreparedStatement st = null;
		ResultSet res = null;
		
		List<VacunasJB> vacuna = new ArrayList<>();
		try {
			con = conec.getCon();
			st=con.prepareStatement(GETALL);
			res=st.executeQuery();
			
			while(res.next()) {
				vacuna.add(transformar(res));
			}	
			ConexionBD.close(res);
			ConexionBD.close(st);
			ConexionBD.close(con);
				
			for (VacunasJB vaccine: vacuna) {
				System.out.println("ID: " + vaccine.getIdvacuna());
				System.out.println("Nombre de Vacuna: " + vaccine.getNombre_vacuna());
				System.out.println("Tipo de Vacuna: " + vaccine.getVacuna_tipo());
				System.out.println("Costo de Vacuna: " + vaccine.getPrecio_vacuna());
				System.out.println("Fecha de Caducidad: " + vaccine.getCaducidad());
				System.out.println("Numero de LOTE: " + vaccine.getLote());
				System.out.println("Stock Disponible: " + vaccine.getStock());
				System.out.println("Nombre de Proveedor: " + vaccine.getProveedor());
				System.out.print("\n");
			}
		}catch (SQLException e) {
			throw new DAOExceptions("Error en SQL",e);
		}
		return vacuna;
	}
	
	
	//Para buscar un dato en la tabla asistente
	public VacunasJB buscar(String id) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		VacunasJB vacuna = null;
			
		try {
			con = conec.getCon();
			pst = con.prepareStatement(GETUSER);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				vacuna = transformar(rs);
			}else {
				throw new DAOExceptions("No se encontro al usuario.");
			}
				
			ConexionBD.close(rs);
			ConexionBD.close(pst);
			ConexionBD.close(con);
				
			System.out.println("Nombre de Vacuna: " + vacuna.getNombre_vacuna());
			System.out.println("Tipo de Vacuna: " + vacuna.getVacuna_tipo());
			System.out.println("Costo de Vacuna: " + vacuna.getPrecio_vacuna());
			System.out.println("Fecha de Caducidad: " + vacuna.getCaducidad());
			System.out.println("Numero de LOTE: " + vacuna.getLote());
			System.out.println("Stock Disponible: " + vacuna.getStock());
			System.out.println("Nombre de Proveedor: " + vacuna.getProveedor());

		}catch(SQLException e){
			throw new DAOExceptions("Error en SQL", e);
		}
		return vacuna;
	}
	
	
}

