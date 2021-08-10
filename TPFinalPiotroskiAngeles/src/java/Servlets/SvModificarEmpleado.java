/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
import Logica.Empleado;
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
@WebServlet(name = "SvModificarEmpleado", urlPatterns = {"/SvModificarEmpleado"})
public class SvModificarEmpleado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = Long.valueOf(request.getParameter("id"));
        Controladora control = new Controladora();
        Empleado emp = control.buscarUnEmpleadoID(id);

        HttpSession misesion = request.getSession();
        misesion.setAttribute("empleado", emp);

        response.sendRedirect("modificarEmpleado.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        Long Nuevodni = Long.valueOf(request.getParameter("introducir_dni"));
        String Nuevonombre = request.getParameter("introducir_nombre");
        String Nuevoapellido = request.getParameter("introducir_apellido");
        String Nuevodireccion = request.getParameter("introducir_direccion");
        String Nuevocargo = request.getParameter("introducir_cargo");

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaNac = request.getParameter("introducir_fechanac");
        Date NuevofechaNacimiento = new Date();
        try {
            NuevofechaNacimiento = formato.parse(fechaNac);

        } catch (ParseException ex) {
            Logger.getLogger(SvCrearEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        Controladora control = new Controladora();
        Empleado emp = control.buscarUnEmpleadoID(id);

        emp.setApellido(Nuevoapellido);
        emp.setCargo(Nuevocargo);
        emp.setDireccion(Nuevodireccion);
        emp.setDni(Nuevodni);
        emp.setFechaNac(NuevofechaNacimiento);
        emp.setNombre(Nuevonombre);

        control.modificarEmpleado(emp);

        request.getSession().setAttribute("listaEmp", control.listaEmpleados());
        response.sendRedirect("ListaEmpleados.jsp");
    }

}
