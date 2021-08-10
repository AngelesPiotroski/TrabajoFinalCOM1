<%-- 
    Document   : modificarUsuario
    Created on : 08/08/2021, 20:37:31
    Author     : piotr
--%>

<%@page import="Logica.Empleado"%>
<%@page import="Logica.Controladora"%>
<%@page import="Logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Modificar Usuario</title>
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
                Empleado emple= control.buscarEmpleadoUsuario(usu, contra);
                String nombreEmp = emple.getApellido() + " " + emple.getNombre();
        %>
        <div class="contact_form">
            <div class="formulario">      
                <h1>Modificar  Usuario</h1>
                <form action="SvModificarUsuario" method="get">      
                    <%
                        Usuario usua = (Usuario) misesion.getAttribute("usua");
                        {%>
                    <p>
                        <label for="usuario" class="colocar_usuario">Usuario
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_usuario" id="usuario" required="obligatorio" placeholder="Usuario" value="<%=usua.getNombreUsuario() %>">
                    </p>
                    <p>
                        <label for="contraseña" class="colocar_contraseña">Contraseña
                            <span class="obligatorio">*</span>
                        </label>
                        <input type="text" name="introducir_contraseña" id="contraseña" required="obligatorio" placeholder="contraseña"value="<%=usua.getContrasena() %>">
                    </p> 
                    
                    <p>
                        <input type="hidden" name="id" id="id" value="<%=usua.getNroUsuario() %>"> </input>
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
