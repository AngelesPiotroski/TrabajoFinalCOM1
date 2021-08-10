/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
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
@WebServlet(name = "SvCrearHuesped", urlPatterns = {"/SvCrearHuesped"})
public class SvCrearHuesped extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long dni = Long.valueOf(request.getParameter("introducir_dni"));
        String nombre = request.getParameter("introducir_nombre");
        String apellido = request.getParameter("introducir_apellido");
        String direccion = request.getParameter("introducir_direccion");
        String profesion = request.getParameter("introducir_profesion");

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaNac = request.getParameter("introducir_fechanac");
        Date fechaNacimiento = new Date();
        try {
            fechaNacimiento = formato.parse(fechaNac);

        } catch (ParseException ex) {
            Logger.getLogger(SvCrearEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        Controladora control = new Controladora();

        try {
            control.crearHuesped(dni, nombre, apellido, direccion, fechaNacimiento, profesion);
            request.getSession().setAttribute("listaHues", control.listaHuespedes());
            response.sendRedirect("ListaHuespedes.jsp");
        } catch (Exception ex) {
            HttpSession misesion = request.getSession();
            misesion.setAttribute("mensaje", ex.getMessage());
            response.sendRedirect("mostrarMensajeError.jsp");
        }

    }
}
