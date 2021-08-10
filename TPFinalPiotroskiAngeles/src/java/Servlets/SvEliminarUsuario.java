/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
import Logica.Empleado;
import Logica.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author piotr
 */
@WebServlet(name = "SvEliminarUsuario", urlPatterns = {"/SvEliminarUsuario"})
public class SvEliminarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.valueOf(request.getParameter("id"));
        Controladora control= new Controladora();
        Usuario usuBorrar = control.buscarUnUsuarioId(id);
        Empleado empBorrar=control.buscarEmpleadoPorUsuario(usuBorrar.getNombreUsuario());
        
        control.borrarUsuario(id);
        control.borraEmpleado(empBorrar.getIdPersona());
    
    request.getSession().setAttribute("listaUsus", control.listaUsuarios());
    response.sendRedirect("ListaUsuarios.jsp");
    }

}
