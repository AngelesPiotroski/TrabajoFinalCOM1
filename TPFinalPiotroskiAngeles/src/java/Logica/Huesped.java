
package Logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author piotr
 */
@Entity
public class Huesped extends Persona implements Serializable{
   
    @Basic
    private String  profesion;
    @OneToMany
    private List<Reserva> reservas = new ArrayList<>();
    
    public Huesped() {
    }

    public Huesped(String profesion, Long idPersona, Long dni, String nombre, String apellido, String direccion, Date fechaNac) {
        super(idPersona, dni, nombre, apellido, direccion, fechaNac);
        this.profesion = profesion;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

}
