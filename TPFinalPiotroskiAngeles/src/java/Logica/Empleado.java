
package Logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author piotr
 */
@Entity
public class Empleado extends Persona implements Serializable{
    
    @Basic
    private String cargo;
    @OneToOne
    private Usuario usuario;
    @OneToMany
    private List<Reserva> reservas = new ArrayList<>();
    
    public Empleado() {
    }

    public Empleado(Usuario usuario, String cargo, Long idPersona, Long dni, String nombre, String apellido, String direccion, Date fechaNac) {
        super(idPersona, dni, nombre, apellido, direccion, fechaNac);
        this.usuario = usuario;
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
