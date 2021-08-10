package Persistencia;

import Logica.Empleado;
import Logica.Habitacion;
import Logica.Huesped;
import Logica.Persona;
import Logica.Reserva;
import Logica.TipoHabitacion;
import Logica.Usuario;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author piotr
 */
public class ControladoraPersistencia {

    EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();
    TipoHabitacionJpaController tipoHabiJPA = new TipoHabitacionJpaController();
    HabitacionJpaController habitacionJPA = new HabitacionJpaController();
    HuespedJpaController huespedJPA = new HuespedJpaController();
    PersonaJpaController personaJPA = new PersonaJpaController();
    ReservaJpaController reservaJPA = new ReservaJpaController();
    UsuarioJpaController usuarioJPA = new UsuarioJpaController();

    /**
     * Metodos crear
     */
    
    
    public void crearUsuario(Usuario usuario) throws Exception{
        try {
        usuarioJPA.create(usuario);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void crearEmpleado(Empleado empleado)throws Exception {
        try {
            empleadoJPA.create(empleado);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void crearHuesped(Huesped huesped) throws Exception{
        try {
            huespedJPA.create(huesped);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void crearHabitacion(Habitacion habitacion) throws Exception{
        try {
            habitacionJPA.create(habitacion);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void crearReserva(Reserva reserva)throws Exception {
        try {
            reservaJPA.create(reserva);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void crearTipoHabitacion(TipoHabitacion tipoHabitacion) throws Exception{
        try {
            tipoHabiJPA.create(tipoHabitacion);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void crearPersona(Persona persona) throws Exception{
        try {
            personaJPA.create(persona);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * Metodos Listar
     *
     * @return lista de objetos de cada clase
     */
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = usuarioJPA.findUsuarioEntities();
        return usuarios;
    }

    public List<Empleado> getEmpleados() {

        return empleadoJPA.findEmpleadoEntities();
    }

    public List<Huesped> getHuespedes() {
        List<Huesped> huespedes = huespedJPA.findHuespedEntities();
        return huespedes;
    }

    public List<Habitacion> getHabitaciones() {
        List<Habitacion> habitaciones = habitacionJPA.findHabitacionEntities();
        return habitaciones;
    }

    public List<Reserva> getReservas() {
        List<Reserva> reservas = reservaJPA.findReservaEntities();
        return reservas;
    }

    public List<TipoHabitacion> getTipoHabitaciones() {
        List<TipoHabitacion> tipoHabitaciones = tipoHabiJPA.findTipoHabitacionEntities();
        return tipoHabitaciones;
    }

    public List<Persona> getPersonas() {
        List<Persona> personas = personaJPA.findPersonaEntities();
        return personas;
    }

    /**
     * Metodos Borrar
     */
    public void borrarUsuario(int id) {
        try {
            usuarioJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarEmpleado(Long id) {
        try {
            empleadoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarHuesped(Long id) {
        try {
            huespedJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarHabitacion(int id) {
        try {
            habitacionJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarReserva(int id) {
        try {
            reservaJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarTipoHabitacion(int id) {
        try {
            tipoHabiJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarPersona(Long id) {
        try {
            personaJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodos Buscar
     */
    public Usuario buscarUsuario(int nroUsuario) {
        return usuarioJPA.findUsuario(nroUsuario);
    }

    public Empleado buscarEmpleado(Long id) {
        return empleadoJPA.findEmpleado(id);
    }

    public Huesped buscarHuesped(Long id) {
        return huespedJPA.findHuesped(id);
    }

    public Habitacion buscarHabitacion(int nroHabitacion) {
        return habitacionJPA.findHabitacion(nroHabitacion);
    }

    public Reserva buscarReserva(int nroReserva) {
        return reservaJPA.findReserva(nroReserva);
    }

    public TipoHabitacion buscarTipoHabitacion(int nroTipo) {
        return tipoHabiJPA.findTipoHabitacion(nroTipo);
    }

    public Persona buscarPersona(Long id) {
        return personaJPA.findPersona(id);
    }

    /**
     * Metodos Modificar
     */
    public void modificarUsuario(Usuario usuario) {
        try {
            usuarioJPA.edit(usuario);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarEmpleado(Empleado empleado) {
        try {
            empleadoJPA.edit(empleado);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarHuesped(Huesped huesped) {
        try {
            huespedJPA.edit(huesped);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarHabitacion(Habitacion habitacion) {
        try {
            habitacionJPA.edit(habitacion);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarReserva(Reserva reserva) {
        try {
            reservaJPA.edit(reserva);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarTipoHabitacion(TipoHabitacion tipoHabitacion) {
        try {
            tipoHabiJPA.edit(tipoHabitacion);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarPersona(Persona persona) {
        try {
            personaJPA.edit(persona);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
