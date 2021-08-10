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
@WebServlet(name = "SvModificarHabitacion", urlPatterns = {"/SvModificarHabitacion"})
public class SvModificarHabitacion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.valueOf(request.getParameter("id"));
        Controladora control = new Controladora();
        Habitacion hab = control.buscarUnaHabitacionId(id);

        HttpSession misesion = request.getSession();
        misesion.setAttribute("habitacion", hab);

        response.sendRedirect("modificarHabitacion.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        String Nuevonombre = request.getParameter("introducir_nombre");
        int Nuevopiso = Integer.valueOf(request.getParameter("introducir_piso"));

        float Nuevoprecio = Float.valueOf(request.getParameter("introducir_precio"));
        int nroTipo = Integer.valueOf(request.getParameter("selectTipo"));

        Controladora control = new Controladora();
        TipoHabitacion tipo = control.buscarUnTipoHabitacionId(nroTipo);
        Habitacion hab = control.buscarUnaHabitacionId(id);

        hab.setNombre(Nuevonombre);
        hab.setPiso(Nuevopiso);
        hab.setPrecioNoche(Nuevoprecio);
        hab.setTipo(tipo);

        control.modificarHabitacion(hab);

        request.getSession().setAttribute("listaHab", control.listaHabitaciones());
        response.sendRedirect("ListaHabitaciones.jsp");
    }

}
