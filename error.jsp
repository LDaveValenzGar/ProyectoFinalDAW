<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import = "java.util.*"%> 
<%@ page import = "entidades.equipos"%> 
<%@ include file="/formularios/menu.jsp" %>
<!--DOCTYPE html-->
<html>
<head>
	<!--meta charset="utf-8"-->
	<title>Equipos</title>
</head>


<body>

<h1>Equipos</h1>
<a href="equiposServlet?accion=nuevo">Nuevo registro</a> <!--pendiente-->

<table border="1"width="80%">
<br>
<br>
	
		<tr>
			<th>idEquipo</th>	
			<th>Nombre equipo</th>	
						
			<th></th>	
			<th></th>		
			
		</tr>

	<%
  List<equipos> listaEquipos = (List<equipos>) request.getAttribute("equipo");
	if(listaEquipos!=null){
  for(equipos equipo: listaEquipos) {
				  
				  
   out.println("<tr>"+
   "<td>"+equipo.getIdEquipo()+"</td>"+
   "<td>"+equipo.getNombreEquipo()+"</td>"+
   
   "<td><a href=equiposServlet?accion=modificar&idEquipo="+equipo.getIdEquipo()+">Modificar</a></td>"+	//pendiente
   "<td><a href=equiposServlet?accion=eliminar&idEquipo="+equipo.getIdEquipo()+">Eliminar</a></td>"+	//pendiente
   "</tr>");
	}
}	
%>
</table>

</body>
</html>