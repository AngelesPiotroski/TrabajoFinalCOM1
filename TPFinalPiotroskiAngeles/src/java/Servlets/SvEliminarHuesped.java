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
@WebServlet(name = "SvEliminarHuesped", urlPatterns = {"/SvEliminarHuesped"})
public class SvEliminarHuesped extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id= Long.valueOf(request.getParameter("id"));
        Controladora control= new Controladora();
        control.borrarHuesped(id);
    
    request.getSession().setAttribute("listaHues", control.listaHuespedes());
    response.sendRedirect("ListaHuespedes.jsp");
    }

}
