/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
import Logica.Empleado;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author piotr
 */
@WebServlet(name = "SvListaReservasHuesped", urlPatterns = {"/SvListaReservasHuesped"})
public class SvListaReservasHuesped extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reserva res = new Reserva();
        Long idHuesped = Long.valueOf(request.getParameter("id"));
        Controladora control = new Controladora();
        Huesped huesped = control.buscarUnHuespedId(idHuesped);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String desdeJSP = request.getParameter("introducir_desde");
        String hastaJSP = request.getParameter("introducir_hasta");
        
        Date desde = new Date();
        Date hasta = new Date();
        
        try {
            desde = formato.parse(desdeJSP);
            hasta = formato.parse(hastaJSP);
        } catch (ParseException ex) {
            Logger.getLogger(SvListaReservasHuesped.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        res.setCheckIn(desde);
        res.setCheckOut(hasta);
        
        request.getSession().setAttribute("huesped", huesped);
        request.getSession().setAttribute("reserva", res);
        response.sendRedirect("ListaReservasHuesped.jsp");
    }

   
}
