/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
import Logica.Huesped;
import Logica.Reserva;
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

/**
 *
 * @author piotr
 */
@WebServlet(name = "SvListaReservasDia", urlPatterns = {"/SvListaReservasDia"})
public class SvListaReservasDia extends HttpServlet{
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
       
        Controladora control = new Controladora();
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaJSP = request.getParameter("introducir_fecha");
       
        Date fecha = new Date();
      
        try {
            fecha = formato.parse(fechaJSP);
        } catch (ParseException ex) {
            Logger.getLogger(SvListaReservasDia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        request.getSession().setAttribute("fecha", fecha);
      
        response.sendRedirect("ListaReservasDia.jsp");
    }

}
