package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.ProveedorJB;

public class ProveedorDAO {
	ConexionBD conec = new ConexionBD();
	final String ADDUSER = "INSERT INTO vaccine_supplier (idsupplier,supplier_name,address,supplier_phone) VALUES (?,?,?,?)";
	final String GETALL = "SELECT idsupplier,supplier_name,address,supplier_phone FROM vaccine_supplier";
	final String UPDATEUSER = "UPDATE vaccine_supplier SET supplier_name = ?,address = ?,supplier_phone = ? WHERE idsupplier = ?";
	final String DELETEUSER = "DELETE FROM vaccine_supplier WHERE idsupplier = ?";
	final String GETUSER = "SELECT idsupplier,supplier_name,address,supplier_phone FROM vaccine_supplier WHERE idsupplier = ?";

	//Para insertar datos en la tabla proveedores
	public void insertdata(ProveedorJB proveedor) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
			
		try {
			con = conec.getCon();
			pst = con.prepareStatement(ADDUSER);
			
			pst.setString(1, proveedor.getIdproveedor());
			pst.setString(2, proveedor.getNombre_proveedor());
			pst.setString(3, proveedor.getDireccion());
			pst.setString(4, proveedor.getTelefono_sup());
				
			if(pst.executeUpdate() == 0) {
				throw new DAOExceptions("No se pudo guardar.");
			}else {		
				System.out.println("Agegado con exito");
			}
			
			ConexionBD.close(pst);
			ConexionBD.close(con);		
		}catch(SQLException e) {
			throw new DAOExceptions("Error en SQL",e);
		}
	}
	
	//Para modificar datos de la tabla proveedor
	public void modificar(ProveedorJB proveedor) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
				
		try {
			con = conec.getCon();
			pst = con.prepareStatement(UPDATEUSER);
					
			pst.setString(1, proveedor.getNombre_proveedor());
			pst.setString(2, proveedor.getDireccion());
			pst.setString(3, proveedor.getTelefono_sup());
					
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
		
	//Para eliminar un dato de la tabla proveedor
	public void eliminar(ProveedorJB proveedor) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
			
		try {
			con = conec.getCon();
			pst = con.prepareStatement(DELETEUSER);
					
			pst.setString(1, proveedor.getIdproveedor());;
					
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
		
	public ProveedorJB transformar(ResultSet rs) throws SQLException{
		String namesup = rs.getString("supplier_name");
		String direccion = rs.getString("address");
		String telefono = rs.getString("supplier_phone");
				
		ProveedorJB proveedor = new ProveedorJB(namesup,direccion,telefono);
		proveedor.setIdproveedor(rs.getString("idsupplier"));
		return proveedor;
	}
		
	//Para mostrar todos los datos de la tabla proveedor
	public List<ProveedorJB> showdata() throws DAOExceptions{
		Connection con = null;
		PreparedStatement st = null;
		ResultSet res = null;
				
		List<ProveedorJB> proveedor = new ArrayList<>();
		try {
			con = conec.getCon();
			st=con.prepareStatement(GETALL);
			res=st.executeQuery();
					
			while(res.next()) {
				proveedor.add(transformar(res));
			}	
			ConexionBD.close(res);
			ConexionBD.close(st);
			ConexionBD.close(con);
					
			for (ProveedorJB supp: proveedor) {
				System.out.println("ID: " + supp.getIdproveedor());
				System.out.println("Nombre de Proveedor: " + supp.getNombre_proveedor());
				System.out.println("Direccion: " + supp.getDireccion());
				System.out.println("Telefono: " + supp.getTelefono_sup());
				System.out.print("\n");
			}
		}catch (SQLException e) {
			throw new DAOExceptions("Error en SQL",e);
		}
		return proveedor;
	}
		
	//Para buscar un dato en la tabla proveedor
	public ProveedorJB buscar(String id) throws DAOExceptions{
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ProveedorJB proveedor = null;
			
		try {
			con = conec.getCon();
			pst = con.prepareStatement(GETUSER);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				proveedor = transformar(rs);
			}else {
				throw new DAOExceptions("No se encontro al usuario.");
			}
			
			ConexionBD.close(rs);
			ConexionBD.close(pst);
			ConexionBD.close(con);
				
			System.out.println("Nombre de Proveedor: " + proveedor.getNombre_proveedor());
			System.out.println("Direccion: " + proveedor.getDireccion());
			System.out.println("Telefono: " + proveedor.getTelefono_sup());
		}catch(SQLException e){
			throw new DAOExceptions("Error en SQL", e);
		}
		return proveedor;
	}
		
	
}
