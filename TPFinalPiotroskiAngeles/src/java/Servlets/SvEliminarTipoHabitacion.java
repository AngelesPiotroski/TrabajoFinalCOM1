/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
import Logica.Habitacion;
import Logica.TipoHabitacion;
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
@WebServlet(name = "SvEliminarTipoHabitacion", urlPatterns = {"/SvEliminarTipoHabitacion"})
public class SvEliminarTipoHabitacion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.valueOf(request.getParameter("id"));
        Controladora control= new Controladora();
        TipoHabitacion tipoBorrar= control.buscarUnTipoHabitacionId(id);
        List<Habitacion> habitacionesBorrar = tipoBorrar.getHabitaciones();
        control.borrarTipoHabitacion(id);
        for(Habitacion hab: habitacionesBorrar){
            control.borrarHabitacion(hab.getNroHabitacion());
        }
    
    request.getSession().setAttribute("listaTipoHab", control.listaTipoHabitaciones());
    response.sendRedirect("ListaTipoHabitaciones.jsp");
    }

}
