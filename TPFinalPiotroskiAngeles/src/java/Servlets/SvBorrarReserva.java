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
import Logica.Reserva;
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
@WebServlet(name = "SvBorrarReserva", urlPatterns = {"/SvBorrarReserva"})
public class SvBorrarReserva extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        
        Controladora control = new Controladora();
        Reserva resBorrar = control.buscarUnaReservaId(id);

        //busco al empleado y le borro la reserva
        Empleado emp = resBorrar.getEmpleado();
        emp.getReservas().remove(resBorrar);
        control.modificarEmpleado(emp);

        //busco al huesped y le borro la reserva
        Huesped hue = resBorrar.getHuesped();
        hue.getReservas().remove(resBorrar);
        control.modificarHuesped(hue);
        
        //busco la habitacion y le borro la reserva
        Habitacion hab = resBorrar.getHabitacion();
        hab.getReserva().remove(resBorrar);
        control.modificarHuesped(hue);

        //borro la reserva
        control.borrarReserva(id);
        
        request.getSession().setAttribute("listaEmp", control.listaEmpleados());
        response.sendRedirect("ListaEmpleados.jsp");

    }

}
