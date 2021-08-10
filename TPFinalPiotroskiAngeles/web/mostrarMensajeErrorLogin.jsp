<%-- 
    Document   : mostrarMensajeError
    Created on : 09/08/2021, 08:29:02
    Author     : piotr
--%>

<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Mensaje de error</title>
        <link rel="stylesheet" type="text/css" href="css/style_1.css">
    </head>
    <body>  
        <%
            HttpSession misesion = request.getSession();
            String usu = (String) misesion.getAttribute("usuario");

            if (usu == null) {
                response.sendRedirect("Login.jsp");
            } else {

                String mensaje = (String) misesion.getAttribute("mensaje");

        %>
        <div class="contact_form">
            <div class="formulario">      
                <h1>Ocurrio un error!</h1>

                <form action="SvErrorLogin" method="get">   

                    <p class="aviso">

                        <span class="obligatorio">  <%=mensaje%>  </span> 
                    </p> 
                    
                </form>
                <form action="SvVolverALogin" method="post">

                    <button type="submit" name="enviar_formulario" id="enviar"><p>Volver</p></button>
                </form>
            </div>  
        </div>
        <% }%>
    </body>
</html>

