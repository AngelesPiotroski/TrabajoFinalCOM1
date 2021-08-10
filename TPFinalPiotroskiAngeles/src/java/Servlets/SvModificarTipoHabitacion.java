/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
import Logica.Empleado;
import Logica.Habitacion;
import Logica.Huesped;
import Logica.TipoHabitacion;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "SvModificarTipoHabitacion", urlPatterns = {"/SvModificarTipoHabitacion"})
public class SvModificarTipoHabitacion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        Controladora control = new Controladora();
        TipoHabitacion tipohab = control.buscarUnTipoHabitacionId(id);
        HttpSession misesion = request.getSession();
        misesion.setAttribute("tipohabitacion", tipohab);

        response.sendRedirect("modificarTipoHabitacion.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        String nombre = request.getParameter("introducir_nombre");
        String descripcion = request.getParameter("introducir_descripcion");
        int cantidad = Integer.valueOf(request.getParameter("introducir_cantidad"));

        Controladora control = new Controladora();
       
        TipoHabitacion tipo = control.buscarUnTipoHabitacionId(id);
        tipo.setNombre(nombre);
        tipo.setCantidadPersonas(cantidad);
        tipo.setDescripcion(descripcion);
       
        control.modificarTipoHabitacion(tipo);

        request.getSession().setAttribute("listaTipoHab", control.listaTipoHabitaciones());
        response.sendRedirect("ListaTipoHabitaciones.jsp");
    }
}
