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
        <title>Crear Habitacion</title>
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
                <h1>Crear Nueva Habitacion</h1>

                <form action="SvCrearHabitacion" method="POST">   
                    <p>
                        <label for="nombre" class="colocar_nombre">Nombre
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_nombre" id="nombre" required="obligatorio" placeholder="Nombre">
                    </p>
                    <p>
                        <label for="dni" class="colocar_piso">Piso
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_piso" id="piso" required="obligatorio" placeholder="Piso">
                    </p> 
                    <p>
                        <label for="direccion" class="colocar_precio">Precio por noche
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_precio" id="precio" required="obligatorio" placeholder="Precio">
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
