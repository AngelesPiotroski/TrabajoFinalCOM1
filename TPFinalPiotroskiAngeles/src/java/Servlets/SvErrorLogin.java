/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
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
@WebServlet(name = "SvErrorLogin", urlPatterns = {"/SvErrorLogin"})
public class SvErrorLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mensaje = request.getParameter("mensaje");

        request.getSession().setAttribute("mensaje", mensaje);
        response.sendRedirect("mostrarMensajeErrorLogin.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mensaje = request.getParameter("mensaje");
        request.getSession().setAttribute("mensaje", mensaje);
    }

}
