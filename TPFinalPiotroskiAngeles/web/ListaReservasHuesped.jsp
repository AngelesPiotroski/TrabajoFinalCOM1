<%-- 
    Document   : ListaReservasHuespedes
    Created on : 08/08/2021, 15:47:25
    Author     : piotr
--%>

<%@page import="Logica.Huesped"%>
<%@page import="Logica.Reserva"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Lista de Reservas del Huesped </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/utilList.css">
        <link rel="stylesheet" type="text/css" href="css/mainList.css">
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <!--===============================================================================================-->
    </head>
    <body>
        <%
            HttpSession misesion = request.getSession();
            String usu = (String) misesion.getAttribute("usuario");
            if (usu == null) {
                response.sendRedirect("Login.jsp");
            } else {
        %>

        <div class="limiter">
            <div class="container-table100">

                <%
                    Controladora control = new Controladora();
                    Reserva res = (Reserva) misesion.getAttribute("reserva");
                    Huesped hue = (Huesped) misesion.getAttribute("huesped");
                    String nombreHuesped = hue.getApellido() + " " + hue.getNombre();
                    List<Reserva> listaReservasHuesped = control.buscarReservasHuespedFecha(res.getCheckIn(), res.getCheckOut(), hue.getIdPersona());
                    
                %>
                <H3  >Lista de todas las Reservas del huesped <%=nombreHuesped%></H3>
                <div class="wrap-table100">
                    <div class="table">     
                        <div class="row header">
                            <div class="cell">
                                Fecha de Creacion
                            </div>
                            <div class="cell">
                                Check in
                            </div>
                            <div class="cell">
                                Check out
                            </div>
                            <div class="cell">
                                Habitacion
                            </div>
                        </div>
                        <%for (Reserva resHues : listaReservasHuesped) {%>
                        <div class="row">
                            <% String fechaCreacion = control.convertirDateToString(resHues.getFechaCreacion());%>
                            <div class="cell" data-title="Fecha de Creacion">
                                <%=fechaCreacion%>
                            </div>

                            <% String checkin = control.convertirDateToString(resHues.getCheckIn());%>
                            <div class="cell" data-title="Check in">
                                <%=checkin%>
                            </div>

                            <% String checkout = control.convertirDateToString(resHues.getCheckOut());%>
                            <div class="cell" data-title="Check out">
                                <%=checkout%>
                            </div>

                            <% String nombreHabi = resHues.getHabitacion().getTipo().getNombre();%>
                            <div class="cell" data-title="Habitacion">
                                <%=nombreHabi%>
                            </div>
                        </div>
                        <% }%>
                    </div>
                    <form action="SvVolverAPrincipal" method="post" >
                        <button class="login100-form-btn" type="submit" name="enviar_formulario" id="enviar"><p>Volver</p></button>
                    </form>
                </div>
            </div>
        </div>
        <!--===============================================================================================-->	
        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/select2/select2.min.js"></script>
        <!--===============================================================================================-->
        <script src="js/mainList.js"></script>

        <%
            }%>

    </body>

</html>
