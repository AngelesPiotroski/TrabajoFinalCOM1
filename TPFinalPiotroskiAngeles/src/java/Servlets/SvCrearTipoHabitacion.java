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
@WebServlet(name = "SvCrearTipoHabitacion", urlPatterns = {"/SvCrearTipoHabitacion"})
public class SvCrearTipoHabitacion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombre = request.getParameter("introducir_nombre");
        String descripcion = request.getParameter("introducir_descripcion");
        int cantidad = Integer.valueOf(request.getParameter("introducir_cantidad"));

        Controladora control = new Controladora();
        TipoHabitacion tipo = new TipoHabitacion();
        tipo.setCantidadPersonas(cantidad);
        tipo.setDescripcion(descripcion);
        tipo.setNombre(nombre);
        try {
            control.crearTipoHabitacion(nombre, descripcion, cantidad);
            request.getSession().setAttribute("listaTipoHab", control.listaHabitaciones());
            response.sendRedirect("ListaTipoHabitaciones.jsp");
        } catch (Exception ex) {
            HttpSession misesion = request.getSession();
            misesion.setAttribute("mensaje", ex.getMessage());
            response.sendRedirect("mostrarMensajeError.jsp");
        }

    }
}
