package Servlets;

import Logica.Controladora;
import Logica.Empleado;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author piotr
 */
@WebServlet(name = "SvUsuario", urlPatterns = {"/SvUsuario"})
public class SvUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("email");
        String contrasena = request.getParameter("pass");
        Controladora control = new Controladora();
        boolean autorizado = control.verificarUsuario(usuario, contrasena);

        if (autorizado == true) {
            HttpSession misesion = request.getSession(true);
            misesion.setAttribute("usuario", usuario);
            misesion.setAttribute("contrasena", contrasena);

            Empleado emp = control.buscarEmpleadoUsuario(usuario, contrasena);
            if (emp == null) {
                String mensaje="Error: para iniciar sesion el empleado debe tener asignado un usuario.";
                misesion.setAttribute("mensaje", mensaje);
                response.sendRedirect("mostrarMensajeErrorLogin.jsp");
            } else {
                response.sendRedirect("Principal.jsp");
            }

        } else {
            response.sendRedirect("Login.jsp");
        }
    }
}
