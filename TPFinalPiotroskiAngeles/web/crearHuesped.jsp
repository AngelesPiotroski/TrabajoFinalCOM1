<%-- 
    Document   : crearEmpleado
    Created on : 06/08/2021, 20:24:13
    Author     : piotr
--%>

<%@page import="Logica.Empleado"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Crear Huesped</title>
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
                <h1>Crear Nuevo Huesped</h1>

                <form action="SvCrearHuesped" method="POST">       
                    <p>
                        <label for="apellido" class="colocar_nombre">Apellido
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_apellido" id="nombre" required="obligatorio" placeholder="Apellido">
                    </p>
                    <p>
                    <p>
                        <label for="nombre" class="colocar_nombre">Nombre
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_nombre" id="nombre" required="obligatorio" placeholder="Nombre">
                    </p>
                    <p>
                        <label for="dni" class="colocar_dni">dni
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_dni" id="dni" required="obligatorio" placeholder="DNI">
                    </p> 
                    <p>
                        <label for="direccion" class="colocar_direccion">Direccion
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_direccion" id="direccion" required="obligatorio" placeholder="Direccion">
                    </p>
                    <p>
                        <label for="fechanac" class="colocar_fechanac">Fecha Nacimiento
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="date" name="introducir_fechanac" id="fechanac" required="obligatorio" placeholder="Fecha Nacimiento">
                    </p>
                    <p>
                        <label for="cargo" class="colocar_profesion">Profesion
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_profesion" id="profesion" required="obligatorio" placeholder="Profesion">
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
