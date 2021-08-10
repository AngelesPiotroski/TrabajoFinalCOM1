<%-- 
    Document   : crearReserva
    Created on : 08/08/2021, 21:07:06
    Author     : piotr
--%>

<%@page import="Logica.Huesped"%>
<%@page import="Logica.TipoHabitacion"%>
<%@page import="Logica.Habitacion"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Empleado"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Crear Reserva</title>
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
                Empleado emp = control.buscarEmpleadoUsuario(usu, contra);
                String nombreEmp = emp.getApellido() + " " + emp.getNombre();
                Huesped hue = (Huesped) misesion.getAttribute("hue");
        %>
        <div class="contact_form">
            <div class="formulario">      
                <h1>Crear Reserva</h1>

                <form action="SvCrearReserva" method="post">  

                    <p>
                        <label for="checkin" class="colocar_checkin">Check-in
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="date" name="introducir_checkin" id="checkin" required="obligatorio" placeholder="Checkin">
                    </p>
                    <p>
                        <label for="checkin" class="colocar_checkout">Check-out
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="date" name="introducir_checkout" id="checkout" required="obligatorio" placeholder="Checkout">
                    </p>
                    <p>
                        <label for="checkin" class="colocar_cantidad">Cantidad de personas 
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="number" name="introducir_cantidad" id="cantidad" required="obligatorio" placeholder="Cantidad de personas">
                    </p>
                    <p>
                        <label for="tipo" class="colocar_tipo">Tipo Habitacion
                            <span class="obligatorio">*</span>
                        </label>
                        <select name="selectTipo" class="select-css">
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
                        <input type="hidden" name="idHuesped" value="<%=hue.getIdPersona()%>"></input>
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
