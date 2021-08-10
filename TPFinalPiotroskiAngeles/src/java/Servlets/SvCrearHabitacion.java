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
@WebServlet(name = "SvCrearHabitacion", urlPatterns = {"/SvCrearHabitacion"})
public class SvCrearHabitacion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombre = request.getParameter("introducir_nombre");
        int piso = Integer.valueOf(request.getParameter("introducir_piso"));
        float precio = Float.valueOf(request.getParameter("introducir_precio"));
        int nro=0;
         
        try {
            
                nro =Integer.valueOf( request.getParameter("selectTipo"));
                Controladora control = new Controladora();
                TipoHabitacion tipo = control.buscarUnTipoHabitacionId(nro);
                try {
                    
                        control.crearHabitacion(nombre, piso, precio, tipo);
                        request.getSession().setAttribute("listaHab", control.listaHabitaciones());
                        response.sendRedirect("ListaHabitaciones.jsp");
                    
                } catch (Exception ex) {
                    HttpSession misesion = request.getSession();
                    misesion.setAttribute("mensaje", ex.getMessage());
                    response.sendRedirect("mostrarMensajeError.jsp");
                }
            
        } catch (Exception ex) {
            String mensaje = "no se puede crear una habitacion sin tipo habitacion.";
            HttpSession misesion = request.getSession();
            misesion.setAttribute("mensaje", mensaje);
            response.sendRedirect("mostrarMensajeError.jsp");
        }

    }
}
