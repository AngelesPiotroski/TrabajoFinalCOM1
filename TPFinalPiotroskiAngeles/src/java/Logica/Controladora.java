package Logica;

import Persistencia.ControladoraPersistencia;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author piotr
 */
public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    /**
     * Metodo Verificar Usuario
     *
     * @param usuario
     * @param contra
     * @return
     */
    public boolean verificarUsuario(String usuario, String contra) {
        List<Usuario> listaUsuarios = controlPersis.getUsuarios();
        if (listaUsuarios != null) {
            for (Usuario usu : listaUsuarios) {
                if (usu.getNombreUsuario().equals(usuario) && usu.getContrasena().equals(contra)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodos Crear
     */
    public void crearUsuario(String user, String contrasena) throws Exception {
        Usuario usu = new Usuario();
        usu.setNombreUsuario(user);
        usu.setContrasena(contrasena);
        Usuario usuExistente = this.buscarNombreUsuario(user);
        if (usuExistente == null) {
            try {
                controlPersis.crearUsuario(usu);
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        } else {
            throw new Exception("El usuario que intenta crear ya existe");
        }
    }

    public void crearEmpleado(String usuario, String contrasena, Long dni, String nombre, String apellido, String direccion, Date fechaNac, String cargo) throws Exception {
        Empleado emp = new Empleado();
        emp.setApellido(apellido);
        emp.setDireccion(direccion);
        emp.setDni(dni);
        emp.setCargo(cargo);
        emp.setFechaNac(fechaNac);
        emp.setNombre(nombre);

        this.crearUsuario(usuario, contrasena);
        Usuario usu = this.buscarUnUsuario(usuario, contrasena);
        emp.setUsuario(usu);
        int nroUsuario = usu.getNroUsuario();
        Empleado empCreado = this.buscarUnEmpleadoDni(dni);
        if (empCreado == null) {
            try {
                controlPersis.crearEmpleado(emp);
            } catch (Exception ex) {
                this.borrarUsuario(nroUsuario);
                throw new Exception(ex.getMessage());
            }
        } else {
            this.borrarUsuario(nroUsuario);
            throw new Exception("El empleado que intenta cargar ya existe");
        }
    }

    public void crearHuesped(Long dni, String nombre, String apellido, String direccion, Date fechaNac, String profesion) throws Exception {

        Huesped hue = new Huesped();
        hue.setApellido(apellido);
        hue.setDireccion(direccion);
        hue.setDni(dni);
        hue.setFechaNac(fechaNac);
        hue.setNombre(nombre);
        hue.setProfesion(profesion);
        Huesped hueCreado = this.buscarUnHuespedDni(dni);
        if (hueCreado == null) {
            try {
                controlPersis.crearHuesped(hue);
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        } else {
            throw new Exception("El huesped que intenta cargar ya existe.");
        }
    }

    public void crearHabitacion(String nombre, int piso, float precioNoche, TipoHabitacion tipoHabi) throws Exception {
        Habitacion habitacion = new Habitacion();
        habitacion.setNombre(nombre);
        habitacion.setPiso(piso);
        habitacion.setPrecioNoche(precioNoche);
        habitacion.setTipo(tipoHabi);
        Habitacion hab = this.buscarUnaHabitacion(nombre);
        if (hab == null) {
            try {
                controlPersis.crearHabitacion(habitacion);
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        } else {
            throw new Exception("La habitacion que intenta cargar ya existe.");
        }
    }

    public void crearReserva(Empleado empleado, Huesped huesped, Habitacion habitacion, Date checkIn, Date checkOut) throws Exception {
        Reserva reserva = new Reserva();
        Boolean bandera = true;
        reserva.setCheckIn(checkIn);
        reserva.setCheckOut(checkOut);
        reserva.setEmpleado(empleado);
        reserva.setHuesped(huesped);
        Date fechaCreacion = new Date();
        reserva.setFechaCreacion(fechaCreacion);
        reserva.setHabitacion(habitacion);
        habitacion.addReserva(reserva);
        List<Reserva> reservas = this.listaReservas();
        for (Reserva res : reservas) {
            if (res.getCheckIn().equals(checkIn) && res.getCheckOut().equals(checkOut)) {
                if (res.getHabitacion().getNroHabitacion() == habitacion.getNroHabitacion()) {
                    bandera = false;
                }
            }
        }
        if (bandera) {
            try {
                controlPersis.crearReserva(reserva);
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        } else {
            throw new Exception("La reserva que intenta crear ya existe.");
        }
    }

    public void crearTipoHabitacion(String nombre, String descripcion, int cantidadPersonas) throws Exception {
        TipoHabitacion tipoHabi = new TipoHabitacion();
        tipoHabi.setCantidadPersonas(cantidadPersonas);
        tipoHabi.setDescripcion(descripcion);
        tipoHabi.setNombre(nombre);
        TipoHabitacion tipoExistente = this.buscarUnTipoHabitacion(nombre);
        if (tipoExistente == null) {
            try {
                controlPersis.crearTipoHabitacion(tipoHabi);
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        } else {
            throw new Exception("El tipo habitacion que intenta crear ya existe.");
        }
    }

    /**
     * Metodos Borrar
     */
    public void borraEmpleado(Long id) {
        controlPersis.borrarEmpleado(id);
    }

    public void borrarHuesped(Long id) {
        controlPersis.borrarHuesped(id);
    }

    public void borrarHabitacion(int id) {
        controlPersis.borrarHabitacion(id);
    }

    public void borrarTipoHabitacion(int id) {
        controlPersis.borrarTipoHabitacion(id);
    }

    public void borrarReserva(int id) {
        controlPersis.borrarReserva(id);
    }

    public void borrarPersona(Long id) {
        controlPersis.borrarPersona(id);
    }

    public void borrarUsuario(int id) {
        controlPersis.borrarUsuario(id);
    }

    /**
     * Metodos Listar
     */
    public List<Empleado> listaEmpleados() {
        return controlPersis.getEmpleados();
    }

    public List<Huesped> listaHuespedes() {
        return controlPersis.getHuespedes();
    }

    public List<Habitacion> listaHabitaciones() {
        return controlPersis.getHabitaciones();
    }

    public List<Reserva> listaReservas() {
        return controlPersis.getReservas();
    }

    public List<TipoHabitacion> listaTipoHabitaciones() {
        return controlPersis.getTipoHabitaciones();
    }

    public List<Usuario> listaUsuarios() {
        return controlPersis.getUsuarios();
    }

    /**
     * Metodos Buscar
     */
    public Empleado buscarUnEmpleadoDni(Long dni) {
        List<Empleado> empleados = controlPersis.getEmpleados();
        Empleado empEncontrado = null;
        if (empleados != null) {
            for (Empleado emp : empleados) {
                if (emp.getDni().equals(dni)) {
                    empEncontrado = emp;
                }
            }
        }
        return empEncontrado;
    }

    public Usuario buscarUnUsuario(String usuario, String contra) {
        List<Usuario> usuarios = controlPersis.getUsuarios();
        Usuario usuEncontrado = null;
        if (usuarios != null) {
            for (Usuario usuar : usuarios) {
                if (usuar.getContrasena().equals(contra) && usuar.getNombreUsuario().equals(usuario)) {
                    usuEncontrado = usuar;
                }
            }
        }
        return usuEncontrado;
    }

    public Usuario buscarNombreUsuario(String usuario) {
        List<Usuario> usuarios = controlPersis.getUsuarios();
        Usuario usuEncontrado = null;
        if (usuarios != null) {
            for (Usuario usuar : usuarios) {
                if (usuar.getNombreUsuario().equals(usuario)) {
                    usuEncontrado = usuar;
                }
            }
        }
        return usuEncontrado;
    }

    public Huesped buscarUnHuespedDni(Long dni) {
        List<Huesped> huespedes = controlPersis.getHuespedes();
        Huesped hueEncontrado = null;
        if (huespedes != null) {
            for (Huesped huesp : huespedes) {
                if (huesp.getDni().equals(dni)) {
                    hueEncontrado = huesp;
                }
            }
        }
        return hueEncontrado;
    }

    public Persona buscarUnaPersona(Long dni) {
        List<Persona> personas = controlPersis.getPersonas();
        Persona perEncontrado = null;
        if (personas != null) {
            for (Persona per : personas) {
                if (per.getDni().equals(dni)) {
                    perEncontrado = per;
                }
            }
        }
        return perEncontrado;
    }

    public Habitacion buscarUnaHabitacion(String nombreHabi) {
        List<Habitacion> habitaciones = controlPersis.getHabitaciones();
        Habitacion habiEncontrado = null;
        if (habitaciones != null) {
            for (Habitacion hab : habitaciones) {
                if (hab.getNombre().equals(nombreHabi)) {
                    habiEncontrado = hab;
                }
            }
        }
        return habiEncontrado;
    }

    public Reserva buscarUnaReserva(int nroHabi, Date fechaCreacion) {
        List<Reserva> reservas = controlPersis.getReservas();
        Reserva resEncontrado = null;
        if (reservas != null) {
            for (Reserva res : reservas) {
                if (res.getFechaCreacion().equals(fechaCreacion) && res.getHabitacion().getNroHabitacion() == nroHabi) {
                    resEncontrado = res;
                }
            }
        }
        return resEncontrado;
    }

    public List<Reserva> buscarReservaFechas(Date inicio, Date fin) {
        List<Reserva> reservas = controlPersis.getReservas();
        List<Reserva> resEncontrados = null;
        if (reservas != null) {
            for (Reserva res : reservas) {
                if (res.getCheckIn().equals(inicio) && res.getCheckOut().equals(fin)) {
                    resEncontrados.add(res);
                }
            }
        }
        return resEncontrados;
    }

    public List<Reserva> buscarUnaReservaDia(Date fechaCreacion) {
        List<Reserva> reservas = controlPersis.getReservas();
        List<Reserva> resEncontrados = null;
        if (reservas != null) {
            for (Reserva res : reservas) {
                if (res.getFechaCreacion().equals(fechaCreacion)) {
                    resEncontrados.add(res);
                }
            }
        }
        return resEncontrados;
    }

    public List<Reserva> buscarReservasEmpleado(Long dni) {
        Empleado emp = this.buscarUnEmpleadoDni(dni);
        List<Reserva> reservas = controlPersis.getReservas();
        List<Reserva> resEncontrados = null;
        if (reservas != null) {
            for (Reserva res : reservas) {
                if (emp.getReservas().contains(res)) {
                    resEncontrados.add(res);
                }
            }
        }
        return resEncontrados;
    }

    public TipoHabitacion buscarUnTipoHabitacion(String tipoHab) {
        List<TipoHabitacion> tiposHabi = controlPersis.getTipoHabitaciones();
        TipoHabitacion tipoEncontrado = null;
        if (tiposHabi != null) {
            for (TipoHabitacion tipo : tiposHabi) {
                if (tipo.getNombre().equals(tipoHab)) {
                    tipoEncontrado = tipo;
                }
            }
        }
        return tipoEncontrado;
    }

    public Empleado buscarEmpleadoUsuario(String usu, String contra) {
        List<Empleado> empleados = this.listaEmpleados();
        Empleado empEncontrado = null;
        if (empleados != null) {
            for (Empleado emp : empleados) {
                if (emp.getUsuario().getContrasena().equals(contra) && emp.getUsuario().getNombreUsuario().equals(usu));
                empEncontrado = emp;
            }
        }
        return empEncontrado;
    }

    public Empleado buscarEmpleadoPorUsuario(String usua) {
        List<Empleado> empleados = this.listaEmpleados();
        Empleado empEncontrado = null;
        if (empleados != null) {
            for (Empleado emp : empleados) {
                if ( emp.getUsuario().getNombreUsuario().equals(usua));
                empEncontrado = emp;
            }
        }
        return empEncontrado;
    }
    
    /**
     * BUscar por ID
     *
     * @param id
     * @return
     */
    public TipoHabitacion buscarUnTipoHabitacionId(int nro) {
        TipoHabitacion tipoEncontrado = controlPersis.buscarTipoHabitacion(nro);
        return tipoEncontrado;
    }

    public Habitacion buscarUnaHabitacionId(int id) {
        Habitacion habEncontrado = controlPersis.buscarHabitacion(id);
        return habEncontrado;
    }

    public Huesped buscarUnHuespedId(Long id) {
        Huesped hueEncontrado = controlPersis.buscarHuesped(id);
        return hueEncontrado;
    }

    public Usuario buscarUnUsuarioId(int id) {
        Usuario usubEncontrado = controlPersis.buscarUsuario(id);
        return usubEncontrado;
    }

    public Reserva buscarUnaReservaId(int id) {
        Reserva resEncontrado = controlPersis.buscarReserva(id);
        return resEncontrado;
    }

    public Empleado buscarUnEmpleadoID(Long id) {
        Empleado empEncontrado = controlPersis.buscarEmpleado(id);
        return empEncontrado;
    }

    public Persona buscarUnaPersonaoID(Long id) {
        Persona perEncontrado = controlPersis.buscarPersona(id);
        return perEncontrado;
    }

    /**
     * Metodos Modificar
     */
    public void modificarEmpleado(Empleado emp) {
        controlPersis.modificarEmpleado(emp);
    }

    public void modificarHuesped(Huesped hue) {
        controlPersis.modificarHuesped(hue);
    }

    public void modificarPersona(Persona per) {
        controlPersis.modificarPersona(per);
    }

    public void modificarHabitacion(Habitacion hab) {
        controlPersis.modificarHabitacion(hab);
    }

    public void modificarTipoHabitacion(TipoHabitacion tipo) {
        controlPersis.modificarTipoHabitacion(tipo);
    }

    public void modificarReserva(Reserva res) {
        controlPersis.modificarReserva(res);
    }

    public void modificarUsuario(Usuario usu) {
        controlPersis.modificarUsuario(usu);
    }

    /**
     * Metodo Buscar todas las reservas del huesped por fecha
     *
     * @param desde
     * @param hasta
     * @param id
     * @return
     */
    public List<Reserva> buscarReservasHuespedFecha(Date desde, Date hasta, Long id) {
        List<Reserva> reservasHuesped = new ArrayList<>();
        Huesped huespedBuscar = controlPersis.buscarHuesped(id);
        for (Reserva res : huespedBuscar.getReservas()) {
            //if ((res.getCheckIn().after(desde) || res.getCheckIn().equals(desde))&& (res.getCheckOut().before(hasta)|| res.getCheckOut().equals(hasta))) {
            if ((res.getFechaCreacion().after(desde) || res.getFechaCreacion().equals(desde)) && (res.getFechaCreacion().before(hasta) || res.getFechaCreacion().equals(hasta))) {
                reservasHuesped.add(res);
            }
        }
        return reservasHuesped;
    }

    public List<Reserva> buscarReservasdia(Date dia) {
        List<Reserva> reservasDia = new ArrayList<>();
        List<Reserva> reservas = this.listaReservas();

        for (Reserva res : reservas) {
            if (res.getCheckIn().equals(dia)) {
                reservasDia.add(res);
            }
        }
        return reservasDia;
    }

    /**
     * Metodo para convertir fechas
     *
     * @param fecha
     * @return
     */
    public String convertirDateToString(Date fecha) {
        String patron = "yyyy/MM/dd";
        DateFormat df = new SimpleDateFormat(patron);
        String fechaConvertida = df.format(fecha);
        return fechaConvertida;

    }

    public Habitacion buscarHabitacionDisponible(String nombreTipo, Date desde, Date hasta) {
        Habitacion habitacionDisponible = null;
        Boolean disponible = true;
        List<Habitacion> habitaciones = this.listaHabitaciones();
        for (Habitacion hab : habitaciones) {
                if (hab.getTipo().getNombre().equals(nombreTipo)) {
                    //si existen reservas en esa habitacion...
                    if (hab.getReserva().size() > 0) {
                        for (Reserva res : hab.getReserva()) {
                            //si la fecha esta en el rango desde hasta entonces de alguna reserva
                            //if ((res.getCheckIn().after(desde) || res.getCheckIn().equals(desde)) && (res.getCheckOut().before(hasta) || res.getCheckOut().equals(hasta))) {
                                //si entre aca es xq alguna esta en ese rango... entonces no esta disponible.
                               // disponible = false;
                                
                                 if (res.getCheckIn().compareTo(desde) < 0 && res.getCheckOut().compareTo(hasta) >= 0) { 
                                     disponible = false; }
                                
                            
                        }
                        //una vez recorrida las reservas vemos si ninguna estaba en el rango de la solicitada y no hizo cambiar la bandera
                        if (disponible == true)
                        {
                            habitacionDisponible = hab;
                            break;
                        }
                    } else {
                        //si esta habitacion es del tipo que se busca y aún no tiene reservas hechas.... se la asignamos de una
                            habitacionDisponible = hab;
                            break;
                        }
                    }
                }
            return habitacionDisponible;
        
        
        /*Habitacion habitacionDisponible = null;
        Boolean disponible = true;
        List<Habitacion> habitaciones = this.listaHabitaciones();
        for (Habitacion hab : habitaciones) {
            if (habitacionDisponible == null) {
                if (hab.getTipo().getNombre().equals(nombreTipo)) {
                    //si existen reservas en esa habitacion...
                    if (hab.getReserva().size() > 0) {
                        for (Reserva res : hab.getReserva()) {
                            //si la fecha no esta en el rango desde hasta entonces es disponible
                            if ((res.getCheckIn().after(desde) || res.getCheckIn().equals(desde)) && (res.getCheckOut().before(hasta) || res.getCheckOut().equals(hasta))) {
                                disponible = false;
                                /**
                                 * if (res.getCheckIn().compareTo(desde) < 0 && res.getCheckOut().compareTo(hasta)
                                 * >= 0) { disponible = false; }
                                 
                            }
                        }
                    } else {
                        if (disponible == true) {
                            habitacionDisponible = hab;
                        }
                    }
                }
            }

        }
        return habitacionDisponible;*/
    }

    public float calcularTotalPrecio(Date desde, Date hasta, float precio) {
        float precioTotal = 0;
        long startTime = desde.getTime();
        long endTime = hasta.getTime();
        long diasDesde = (long) Math.floor(startTime / (1000 * 60 * 60 * 24));
        long diasHasta = (long) Math.floor(endTime / (1000 * 60 * 60 * 24));
        long dias = diasHasta - diasDesde;
        precioTotal = precio * dias;

        return precioTotal;
    }

}
