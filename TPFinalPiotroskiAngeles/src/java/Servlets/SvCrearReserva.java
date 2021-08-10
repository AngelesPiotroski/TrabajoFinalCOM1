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
@WebServlet(name = "SvCrearReserva", urlPatterns = {"/SvCrearReserva"})
public class SvCrearReserva extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String checkinJSP = request.getParameter("introducir_checkin");
        String checkoutJSP = request.getParameter("introducir_checkout");
        Long idEmpleado = Long.valueOf(request.getParameter("idEmpleado"));
        Long idHuesped = Long.valueOf(request.getParameter("idHuesped"));
        int nroTipo = Integer.valueOf(request.getParameter("selectTipo"));
        int cantPersonas = Integer.valueOf(request.getParameter("introducir_cantidad"));

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date checkin = new Date();
        Date checkout = new Date();
        try {
            checkin = formato.parse(checkinJSP);
            checkout = formato.parse(checkoutJSP);
        } catch (ParseException ex) {
            Logger.getLogger(SvCrearEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        Controladora control = new Controladora();
        Empleado empleado = control.buscarUnEmpleadoID(idEmpleado);
        Huesped huesped = control.buscarUnHuespedId(idHuesped);
        TipoHabitacion tipo = control.buscarUnTipoHabitacionId(nroTipo);
        String nombreTipo = tipo.getNombre();
        Habitacion habitacion = control.buscarHabitacionDisponible(nombreTipo, checkin, checkin);

        if (habitacion != null) {
            if (cantPersonas!= 0) {
                if (habitacion.getTipo().getCantidadPersonas() >= cantPersonas) {
                    try {
                        control.crearReserva(empleado, huesped, habitacion, checkin, checkout);
                        request.getSession().setAttribute("listaRes", control.listaReservas());
                        response.sendRedirect("ListaReservas.jsp");
                    } catch (Exception ex) {
                        HttpSession misesion = request.getSession();
                        misesion.setAttribute("mensaje", ex.getMessage());
                        response.sendRedirect("mostrarMensajeError.jsp");
                    }
                } else {
                    String mensaje = "La habitacion seleccionada no acepta la cantidad de personas ingresadas.";
                    HttpSession misesion = request.getSession();
                    misesion.setAttribute("mensaje", mensaje);
                    response.sendRedirect("mostrarMensajeError.jsp");
                }
            } else {
                String mensaje = "La habitacion seleccionada no acepta la cantidad de personas ingresadas.";
                HttpSession misesion = request.getSession();
                misesion.setAttribute("mensaje", mensaje);
                response.sendRedirect("mostrarMensajeError.jsp");
            }
        } else {
            String mensaje = "No existen habitaciones disponibles en la fecha ingresada.";
            HttpSession misesion = request.getSession();
            misesion.setAttribute("mensaje", mensaje);
            response.sendRedirect("mostrarMensajeError.jsp");
        }
    }

}
