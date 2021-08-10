<%-- 
    Document   : BuscarReservasDia
    Created on : 09/08/2021, 07:42:12
    Author     : piotr
--%>

<%@page import="Logica.Empleado"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Buscar Reservas de un dia</title>
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
                Empleado emple = control.buscarEmpleadoUsuario(usu, contra);
                String nombreEmp = emple.getApellido() + " " + emple.getNombre();
        %>
        <div class="contact_form">
            <div class="formulario">                 
                <h1>Buscar Reservas de un dia </h1>
                <form action="SvListaReservasDia" method="post">       
                    <p>
                        <label for="fecha" class="colocar_fecha"> Fecha
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_fecha" id="fecha" required="obligatorio" placeholder="Fecha">
                    </p>
                    <p>
                        <input type="hidden" name="idEmpleado" value="<%=emple.getIdPersona()%>"></input> 
                    </p>

                    <button type="submit" name="enviar_formulario" id="enviar"><p>Buscar</p></button>
                    <p class="aviso">
                        <span class="obligatorio"> * </span>los campos son obligatorios.
                        <span class="obligatorio"> Usuario: <%=nombreEmp%>  </span> 
                    </p>          
                </form>
                    <form action="SvVolverAPrincipal" method="post">

                    <button type="submit" name="enviar_formulario" id="enviar"><p>Volver</p></button>
                </form>
            </div>  
        </div>
        <% }%>
    </body>
</html>

