<%-- 
    Document   : ListaEmpleados
    Created on : 07/08/2021, 16:06:44
    Author     : piotr
--%>

<%@page import="Logica.Persona"%>

<%@page import="Logica.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Lista Empleados</title>
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
                <h3>Lista de todos los Empleados del sistema </h3>
                <div class="wrap-table100">
                    <div class="table">

                        <div class="row header">
                            <div class="cell">
                                Empleados
                            </div>
                            <div class="cell">
                                DNI
                            </div>
                            <div class="cell">
                                Direccion
                            </div>
                            <div class="cell">
                                Cargo
                            </div>
                            <div class="cell">
                                Ver Reservas
                            </div>
                            <div class="cell">
                                Modificar
                            </div>
                            <div class="cell">
                                Eliminar
                            </div>
                        </div>
                        <%
                            Controladora control = new Controladora();
                            List<Empleado> listaEmp = control.listaEmpleados();
                            for (Empleado emp : listaEmp) {
                        %>
                        <div class="row">
                            <div class="cell" data-title="Empleados">
                                <% String nombreEmp = emp.getApellido() + " " + emp.getNombre();%>
                                <%=nombreEmp%>
                            </div>
                            <div class="cell" data-title="DNI">
                                <% String dni = emp.getDni().toString();%>
                                <%=dni%>
                            </div>
                            <div class="cell" data-title="Direccion">
                                <% String direccion = emp.getDireccion();%>
                                <%=direccion%>
                            </div>
                            <div class="cell" data-title="Cargo">
                                <% String cargo = emp.getCargo();%>
                                <%=cargo%>
                            </div>
                            <%Long id = emp.getIdPersona();%>
                            <div class="cell" data-title="Ver Reservas">
                                <form action="SvReservasEmpleado" method="POST">
                                    <input type="hidden" name="id" value="<%=id%>"></input>
                                    <button type="submit" >Ver reservas </button>
                                </form>
                            </div>
                            <div class="cell" data-title="Modificar">
                                <form action="SvModificarEmpleado" method="POST">
                                    <input type="hidden" name="id" value="<%=id%>"></input>
                                    <button type="submit" >Modificar </button>
                                </form>
                            </div>
                            <div class="cell" data-title="Eliminar">
                                <form action="SvEliminarEmpleado" method="POST">
                                    <input type="hidden" name="nombreUsu" value="<%=usu%>"></input>
                                    <input type="hidden" name="id" value="<%=id%>"></input>
                                    <button type="submit" >Eliminar </button>
                                </form>
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