/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
import Logica.TipoHabitacion;
import Logica.Usuario;
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
@WebServlet(name = "SvModificarUsuario", urlPatterns = {"/SvModificarUsuario"})
public class SvModificarUsuario extends HttpServlet{
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        Controladora control = new Controladora();
        Usuario usu = control.buscarUnUsuarioId(id);
        HttpSession misesion = request.getSession();
        misesion.setAttribute("usua", usu);

        response.sendRedirect("modificarUsuario.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        String usuario = request.getParameter("introducir_usuario");
        String contra = request.getParameter("introducir_contraseña");

        Controladora control = new Controladora();
       
        Usuario usu = control.buscarUnUsuarioId(id);
        usu.setNombreUsuario(usuario);
        
        usu.setContrasena(contra);
       
        control.modificarUsuario(usu);

        request.getSession().setAttribute("listaUsus", control.listaUsuarios());
        response.sendRedirect("ListaUsuarios.jsp");
    }
    
}
