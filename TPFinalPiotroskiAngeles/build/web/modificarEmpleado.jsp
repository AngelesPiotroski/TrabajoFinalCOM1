<%-- 
    Document   : crearEmpleado
    Created on : 06/08/2021, 20:24:13
    Author     : piotr
--%>

<%@page import="java.util.Date"%>
<%@page import="Logica.Controladora"%>
<%@page import="Logica.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Modificar Empleado</title>
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
                <h1>Modificar Empleado</h1>

                <form action="SvModificarEmpleado" method="get">      
                    <%
                        Empleado emp = (Empleado) misesion.getAttribute("empleado");

                        {%>
                    <p>
                        <label for="apellido" class="colocar_nombre">Apellido
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_apellido" id="apellido" required="obligatorio" placeholder="Apellido" value="<%=emp.getApellido()%>"> 
                    </p>
                    <p>
                        <label for="nombre" class="colocar_nombre">Nombre
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_nombre" id="nombre" required="obligatorio" placeholder="Nombre" value="<%=emp.getNombre()%>"> </input>
                    </p>
                    <p>
                        <label for="dni" class="colocar_dni">dni
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_dni" id="dni" required="obligatorio" placeholder="DNI" value="<%=emp.getDni().toString()%>"></input>
                    </p> 
                    <p>
                        <label for="direccion" class="colocar_direccion">Direccion
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_direccion" id="direccion" required="obligatorio" placeholder="Direccion" value="<%=emp.getDireccion()%>"></input>
                    </p>
                    <p>
                        <%
                            Date fecha = emp.getFechaNac();
                            String fechaMostrar = control.convertirDateToString(fecha);
                        %>
                        <label for="fechanac" class="colocar_fechanac">Fecha Nacimiento
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_fechanac" id="fechanac" required="obligatorio" placeholder="Fecha Nacimiento" value="<%=fechaMostrar%>"> </input>
                    </p>
                    <p>
                        <label for="cargo" class="colocar_cargo">Cargo
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_cargo" id="cargo" required="obligatorio" placeholder="Cargo"value="<%=emp.getCargo()%>"> </input>
                    </p>
                    <p>
                        <input type="hidden" name="id" id="id" value="<%=emp.getIdPersona()%>"> </input>   
                        <input type="hidden" name="idEmpleado" value="<%=emple.getIdPersona()%>"></input>
                        <span class="obligatorio"> Usuario: <%=nombreEmp%>  </span> 
                    </p>
                    <button type="submit" name="enviar_formulario" id="enviar"><p>Modificar</p></button>
                    <%}%>
                </form>
                <form action="SvVolverAPrincipal" method="post">

                    <button type="submit" name="enviar_formulario" id="enviar"><p>Volver</p></button>
                </form>

            </div>  
        </div>
        <%
            }
        %>
    </body>
</html>
