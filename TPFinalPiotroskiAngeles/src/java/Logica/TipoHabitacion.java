
package Logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author piotr
 */
@Entity
public class TipoHabitacion implements Serializable {
    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private int nroTipo;
    @Basic
    private String nombre, descripcion;
    private int cantidadPersonas;
    @OneToMany
    private List<Habitacion> habitaciones = new ArrayList<>();
   
    public TipoHabitacion() {
    }

    public TipoHabitacion(int nroTipo, String nombre, String descripcion, int cantidadPersonas) {
        this.nroTipo = nroTipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadPersonas = cantidadPersonas;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getNroTipo() {
        return nroTipo;
    }

    public void setNroTipo(int nroTipo) {
        this.nroTipo = nroTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }
    
    
    
}
