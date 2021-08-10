<%-- 
    Document   : crearEmpleado
    Created on : 06/08/2021, 20:24:13
    Author     : piotr
--%>

<%@page import="Logica.Empleado"%>
<%@page import="Logica.TipoHabitacion"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Crear Tipo Habitacion</title>
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
                Empleado emp= control.buscarEmpleadoUsuario(usu, contra);
                String nombreEmp = emp.getApellido() + " " + emp.getNombre();
        %>
        <div class="contact_form">
            <div class="formulario">      
                <h1>Crear Nuevo Tipo Habitacion</h1>

                <form action="SvCrearTipoHabitacion" method="POST">   
                    <p>
                        <label for="nombre" class="colocar_nombre">Nombre
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_nombre" id="nombre" required="obligatorio" placeholder="Nombre">
                    </p>
                    <p>
                        <label for="dni" class="colocar_descripcion">Descripcion
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_descripcion" id="descripcion" required="obligatorio" placeholder="Descripcion">
                    </p> 
                    <p>
                        <label for="direccion" class="colocar_cantidad">Cantidad de Personas
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_cantidad" id="cantidad" required="obligatorio" placeholder="Cantidad de Personas">
                    </p>
                    <p class="aviso">
                        <span class="obligatorio"> * </span>los campos son obligatorios.
                        <input type="hidden" name="idEmpleado" value="<%=emp.getIdPersona()%>"></input>
                        <span class="obligatorio"> Creado por: <%=nombreEmp%>  </span> 
                    </p> 
                    <button type="submit" name="enviar_formulario" id="enviar"><p>Crear</p></button>



                </form>
                <form action="SvVolverAPrincipal" method="post">

                    <button type="submit" name="enviar_formulario" id="enviar"><p>Volver</p></button>
                </form>
            </div>  
        </div>
        <% }%>
    </body>
</html>
