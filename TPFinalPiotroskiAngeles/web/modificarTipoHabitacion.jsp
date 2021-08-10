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
        <title>Modificar Tipo Habitacion</title>
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
                <h1>Modificar  Tipo Habitacion</h1>
                <form action="SvModificarTipoHabitacion" method="get">      
                    <%
                        TipoHabitacion tipohab = (TipoHabitacion) misesion.getAttribute("tipohabitacion");
                        {%>
                    <p>
                        <label for="nombre" class="colocar_nombre">Nombre
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_nombre" id="nombre" required="obligatorio" placeholder="Nombre" value="<%=tipohab.getNombre() %>">
                    </p>
                    <p>
                        <label for="dni" class="colocar_descripcion">Descripcion
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_descripcion" id="descripcion" required="obligatorio" placeholder="Descripcion"value="<%=tipohab.getDescripcion() %>">
                    </p> 
                    <p>
                        <label for="direccion" class="colocar_cantidad">Cantidad de Personas
                            <span class="obligatorio">*</span>
                        </label>
                        <%String cantidad= String.valueOf(tipohab.getCantidadPersonas());%>
                        <input type="text" name="introducir_cantidad" id="cantidad" required="obligatorio" placeholder="Cantidad de Personas" value="<%=cantidad%>">
                    </p>
                    <p>
                        <input type="hidden" name="id" id="id" value="<%=tipohab.getNroTipo() %>"> </input>  
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

