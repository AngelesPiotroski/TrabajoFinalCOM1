<%-- 
    Document   : ModificarHabitacion
    Created on : 08/08/2021, 18:16:16
    Author     : piotr
--%>

<%@page import="Logica.Empleado"%>
<%@page import="Logica.TipoHabitacion"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page import="Logica.Habitacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Modificar Habitacion</title>
        <link rel="stylesheet" type="text/css" href="css/style_1.css">
    </head>
    <body>  
        <%
            HttpSession misesion = request.getSession();
            String usu = (String) misesion.getAttribute("usuario");
            String contra = (String) misesion.getAttribute("contra");
            if (usu == null) {
                response.sendRedirect("Login.jsp");
            } else {
                Controladora control = new Controladora();
                Empleado emple= control.buscarEmpleadoUsuario(usu, contra);
                String nombreEmp = emple.getApellido() + " " + emple.getNombre();
        %>
        <div class="contact_form">
            <div class="formulario">      
                <h1>Modificar Habitacion</h1>

                <form action="SvModificarHabitacion" method="get">      
                    <%
                        Habitacion hab = (Habitacion) misesion.getAttribute("habitacion");

                        {%>

                    <p>
                        <label for="nombre" class="colocar_nombre">Nombre
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_nombre" id="nombre" required="obligatorio" placeholder="Nombre" value="<%=hab.getNombre()%>"> </input>
                    </p>
                    <p>
                        <label for="dni" class="colocar_piso">Piso
                            <span class="obligatorio">*</span>
                        </label>
                        <%String piso = String.valueOf(hab.getPiso());%>
                        <input type="text" name="introducir_piso" id="piso" required="obligatorio" placeholder="Piso" value="<%=piso%>"></input>
                    </p> 
                    <p>
                        <label for="direccion" class="colocar_precio">Precio
                            <span class="obligatorio">*</span>
                        </label>
                        <%String precio = String.valueOf(hab.getPrecioNoche());%>
                        <input type="text" name="introducir_precio" id="precio" required="obligatorio" placeholder="Precio" value="<%=precio%>"></input>
                    </p>
                    <p>
                        <label for="tipo" class="colocar_tipo">Tipo Habitacion
                            <span class="obligatorio">*</span>
                        </label>
                        <select name="selectTipo">
                            <%
                                List<TipoHabitacion> tipos = control.listaTipoHabitaciones();
                                for (TipoHabitacion tipo : tipos) {
                            %>
                            <option value="<%=tipo.getNroTipo()%>"><%=tipo.getNombre()%></option>

                            <%}%>
                        </select>
                    </p>
                    <p>
                        <input type="hidden" name="id" id="id" value="<%=hab.getNroHabitacion()%>"> </input> 
                         <input type="hidden" name="idEmpleado" value="<%=emple.getIdPersona()%>"></input>
                        <span class="obligatorio"> Usuario: <%=nombreEmp%>  </span> 
                    </p>
                    <button type="submit" name="enviar_formulario" id="enviar"><p>Modificar</p></button>
                    
                    <%}%>
                </form>
                <form action="SvVolverAPrincipal" method="post">

                    <button type="submit" name="enviar_formulario" id="enviar"><p>Volver</p></button>
                </form>
            </div>  
        </div>
        <%
            }
        %>
    </body>
</html>

