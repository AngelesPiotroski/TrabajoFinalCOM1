<%-- 
    Document   : ListaReservas
    Created on : 09/08/2021, 18:28:11
    Author     : piotr
--%>

<%@page import="java.util.Date"%>
<%@page import="Logica.Reserva"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Lista Reservas</title>
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
                <h3  >Lista de todas las reservas del sistema </h3>
                <div class="wrap-table100">
                    <div class="table">

                        <div class="row header">
                            <div class="cell">
                                Fecha Creacion
                            </div>
                            <div class="cell">
                                Checkin
                            </div>
                            <div class="cell">
                                Checkout
                            </div>
                            <div class="cell">
                                Habitacion
                            </div>
                            <div class="cell">
                                Empleado
                            </div>
                            <div class="cell">
                                Huesped
                            </div>
                            <div class="cell">
                                Precio total
                            </div>
                            <!-- <div class="cell">
                                 Modificar
                             </div>
                             <div class="cell">
                                 Borrar
                             </div> 
                            -->
                        </div>
                        <%
                            Controladora control = new Controladora();
                            List<Reserva> listaRes = control.listaReservas();
                            for (Reserva res : listaRes) {
                        %>
                        <div class="row">
                            <div class="cell" data-title="Fecha Creacion">
                                <% String fecha = control.convertirDateToString(res.getFechaCreacion());%>
                                <%=fecha%>
                            </div>
                            <div class="cell" data-title="Checkin">
                                <% String Checkin = control.convertirDateToString(res.getCheckIn());%>
                                <%=Checkin%>
                            </div>
                            <div class="cell" data-title="Checkout">
                                <% String Checkout = control.convertirDateToString(res.getCheckOut());%>
                                <%=Checkout%>
                            </div>
                            <div class="cell" data-title="Habitacion">
                                <% String habitacion = res.getHabitacion().getTipo().getNombre();%>
                                <%=habitacion%>
                            </div>
                            <div class="cell" data-title="Empleado">
                                <% String empleado = res.getEmpleado().getApellido() + " " + res.getEmpleado().getNombre();%>
                                <%=empleado%>
                            </div>
                            <div class="cell" data-title="Huesped">
                                <% String huesped = res.getHuesped().getApellido() + " " + res.getHuesped().getNombre();%>
                                <%=huesped%>
                            </div>
                            <div class="cell" data-title="Precio total">
                                <% float precioNoche = res.getHabitacion().getPrecioNoche();
                                    Date ini = res.getCheckIn();
                                    Date fin =res.getCheckOut();
                                    float precio = (control.calcularTotalPrecio(ini, fin, precioNoche));
                                    String precioString= String.valueOf(precio);
                                %>
                                <%=precioString%>
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
