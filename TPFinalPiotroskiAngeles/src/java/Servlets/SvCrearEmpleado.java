/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
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
@WebServlet(name = "SvCrearEmpleado", urlPatterns = {"/SvCrearEmpleado"})
public class SvCrearEmpleado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long dni = Long.valueOf(request.getParameter("introducir_dni"));
        String nombre = request.getParameter("introducir_nombre");
        String apellido = request.getParameter("introducir_apellido");
        String direccion = request.getParameter("introducir_direccion");
        String cargo = request.getParameter("introducir_cargo");
        String usuario = request.getParameter("introducir_usuario");
        String contrasena = request.getParameter("introducir_contrasena");

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaNac = request.getParameter("introducir_fechanac");
        Date fechaNacimiento = new Date();
        try {
            fechaNacimiento = formato.parse(fechaNac);
        } catch (ParseException ex) {
            String mensaje = "Ocurrio un error al aplicar formato a las fechas";
            HttpSession misesion = request.getSession();
            misesion.setAttribute("mensaje", mensaje);
            response.sendRedirect("mostrarMensajeError.jsp");
        }

        Controladora control = new Controladora();
        try {
            control.crearEmpleado(usuario, contrasena, dni, nombre, apellido, direccion, fechaNacimiento, cargo);

            request.getSession().setAttribute("listaEmp", control.listaEmpleados());
            response.sendRedirect("ListaEmpleados.jsp");
        } catch (Exception ex) {
            HttpSession misesion = request.getSession();
            misesion.setAttribute("mensaje", ex.getMessage());
            response.sendRedirect("mostrarMensajeError.jsp");
        }
    }
}
