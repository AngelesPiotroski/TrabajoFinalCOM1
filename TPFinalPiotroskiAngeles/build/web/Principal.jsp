<%-- 
    Document   : Principal
    Created on : 06/08/2021, 09:42:51
    Author     : piotr
--%>

<%@page import="Logica.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
        <link rel="stylesheet" href="fonts/icomoon/style.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- Style -->
        <link rel="stylesheet" href="css/style.css">
        <title>Menu Hotel</title>
    </head>
    <body>
        <%
            HttpSession misesion = request.getSession();
            String usu = (String) misesion.getAttribute("usuario");
            String contra = (String) misesion.getAttribute("contrasena");
            if (usu == null) {
                response.sendRedirect("Login.jsp");
            } else {
        %>
        <div class="site-mobile-menu site-navbar-target">
            <div class="site-mobile-menu-header">
                <div class="site-mobile-menu-close mt-3">
                    <span class="icon-close2 js-menu-toggle"></span>
                </div>
            </div>
            <div class="site-mobile-menu-body"></div>
        </div>
        <header class="site-navbar site-navbar-target py-4" role="banner">
            <div class="container">
                <div class="row align-items-center position-relative">
                    
                    
                    <div class="col-3 ml-auto text-right order-2">
                        <%
                            Controladora control = new Controladora();
                            List<Empleado> listaEmp = control.listaEmpleados();
                            Empleado emp = control.buscarEmpleadoUsuario(usu, contra);
                            String nombre = emp.getApellido()+ " "+ emp.getNombre();
                        %>
                        <div class="site-logo">
                            <a  class="font-weight-bold text-white">Bienvenid@ <%=nombre%></a>
                        </div>
                    </div>
                    
                    
                    <div class="col-9 order-1 text-left mr-auto">
                        <span class="d-inline-block d-lg-block"><a href="#" class="text-black site-menu-toggle js-menu-toggle py-5"><span class="icon-menu h3 text-white"></span></a></span>
                        <nav class="site-navigation text-right ml-auto d-none d-lg-none" role="navigation">
                            <ul class="site-menu main-menu js-clone-nav ml-auto " id="menu">
                                <li class="active"><a href="index.html" class="nav-link">Reservas</a></li>
                                <ul>
                                    <li><a  href="ListaHuespedes.jsp" class="nav-link">Crear Reserva</a></li>
                                    <li><a  href="ListaReservas.jsp" class="nav-link">Lista de Reservas</a></li>
                                    <li><a  href="BuscarReservasDia.jsp" class="nav-link">Buscar reservas por dia</a></li>
                                </ul>
                                <li><a href="about.html" class="nav-link">Huespedes</a></li>
                                <ul>
                                    <li><a  href="crearHuesped.jsp" class="nav-link">Crear Huesped</a></li>
                                    <li><a  href="ListaHuespedes.jsp" class="nav-link">Lista de Huespedes</a></li>
                                </ul>
                                <li><a  class="nav-link">Empleados</a></li>
                                <ul>
                                    <li><a  href="crearEmpleado.jsp" class="nav-link">Crear Empleado</a></li>
                                    <li><a  href="ListaEmpleados.jsp" class="nav-link">Lista de Empleados</a></li>
                                </ul>
                                <li><a href="blog.html" class="nav-link">Habitaciones</a></li>
                                <ul>
                                    <li><a  href="crearHabitacion.jsp" class="nav-link">Crear Habitacion</a></li>
                                    <li><a  href="ListaHabitaciones.jsp" class="nav-link">Lista de Habitaciones</a></li>
                                </ul>
                                <li><a href="contact.html" class="nav-link">Tipo de Habitaciones</a></li>
                                <ul>
                                    <li><a  href="crearTipoHabitacion.jsp" class="nav-link">Crear Tipo Habitacion</a></li>
                                    <li><a  href="ListaTipoHabitaciones.jsp" class="nav-link">Lista de Tipos de Habitaciones</a></li>
                                </ul>
                                <li><a href="contact.html" class="nav-link">Usuarios</a></li>
                                <ul>
                                    <li><a  href="crearEmpleado.jsp" class="nav-link">Crear Usuario</a></li>
                                    <li><a  href="ListaUsuarios.jsp" class="nav-link">Lista de Usuarios</a></li>
                                </ul>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </header>
        <div class="hero" style="background-image: url('images/hotel.jpg');"></div>

        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.sticky.js"></script>
        <script src="js/mainMenu.js"></script>

        <% }%>
    </body>
</html>