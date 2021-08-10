<%-- 
    Document   : BuscarReservasHuesped
    Created on : 08/08/2021, 15:54:13
    Author     : piotr
--%>

<%@page import="Logica.Empleado"%>
<%@page import="Logica.Controladora"%>
<%@page import="Logica.Huesped"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Buscar Reservas Huesped</title>
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
                <%
                    Huesped hue = (Huesped) misesion.getAttribute("huesped");
                    String nombre = hue.getApellido() + " " + hue.getNombre();
                %>
                <h1>Buscar Reservas del Huesped <%=nombre%></h1>

                <form action="SvListaReservasHuesped" method="post">       
                    <p>
                        <label for="desde" class="colocar_desde"> Desde
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_desde" id="desde" required="obligatorio" placeholder="Desde">
                    </p>
                    <p>
                    <p>
                        <label for="nombre" class="colocar_hasta">Hasta
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_hasta" id="hasta" required="obligatorio" placeholder="Hasta">
                    </p>     
                    <p>
                        <input type="hidden" name="id" id="id" value="<%=hue.getIdPersona()%>"> </input> 

                    </p>
                    <p class="aviso">
                        <span class="obligatorio"> * </span>los campos son obligatorios.
                        <input type="hidden" name="idEmpleado" value="<%=emple.getIdPersona()%>"></input>
                        <span class="obligatorio"> Usuario: <%=nombreEmp%>  </span> 
                    </p> 
                    <button type="submit" name="enviar_formulario" id="enviar"><p>Buscar</p></button>
                </form>
                <form action="SvVolverAPrincipal" method="post">

                    <button type="submit" name="enviar_formulario" id="enviar"><p>Volver</p></button>
                </form>
            </div>  
        </div>
        <% }%>
    </body>
</html>

