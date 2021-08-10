/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
import Logica.Empleado;
import Logica.Huesped;
import java.io.IOException;
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
@WebServlet(name = "SvReservasDelHuesped", urlPatterns = {"/SvReservasDelHuesped"})
public class SvReservasDelHuesped extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        Controladora control = new Controladora();
        Huesped hue = control.buscarUnHuespedId(id);

        HttpSession misesion = request.getSession();
        misesion.setAttribute("huesped", hue);
        response.sendRedirect("ListaReservasDelHuesped.jsp");
    }
    
}
