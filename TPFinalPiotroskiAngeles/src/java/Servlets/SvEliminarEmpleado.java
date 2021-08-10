/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
import Logica.Empleado;
import Logica.Reserva;
import Logica.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author piotr
 */
@WebServlet(name = "SvEliminarEmpleado", urlPatterns = {"/SvEliminarEmpleado"})
public class SvEliminarEmpleado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id= Long.valueOf(request.getParameter("id"));
        String usu= request.getParameter("nombreUsu");
        Controladora control= new Controladora();
        Empleado empBorrar= control.buscarUnEmpleadoID(id);
        List<Reserva> reservasEmp = empBorrar.getReservas();
        
        //busco al administrador y le asigno las reservas
        Empleado admin = control.buscarEmpleadoPorUsuario("admin@admin.com");
        admin.setReservas(reservasEmp);
        control.modificarEmpleado(admin);
        
        //borro el empleado y su usuario
        control.borraEmpleado(id);
        Usuario usuario = control.buscarNombreUsuario(usu);
        control.borrarUsuario(usuario.getNroUsuario());                
    
    request.getSession().setAttribute("listaEmp", control.listaEmpleados());
    response.sendRedirect("ListaEmpleados.jsp");
    }

    
    
    
     
}
