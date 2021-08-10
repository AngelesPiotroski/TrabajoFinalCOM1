
package Logica;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 *
 * @author piotr
 */
@Entity
public class Habitacion implements Serializable {
    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private int nroHabitacion;
    @Basic
    private String nombre;
    private int piso;
    private float precioNoche;
    @OneToMany
    private List<Reserva> reserva = new ArrayList<>();
    @ManyToOne
    private TipoHabitacion tipo;
    

    public Habitacion() {
    }

    public Habitacion(int nroHabitacion, String nombre, int piso, float precioNoche, TipoHabitacion tipo) {
        this.nroHabitacion = nroHabitacion;
        this.nombre = nombre;
        this.piso = piso;
        this.precioNoche = precioNoche;
        this.tipo = tipo;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    

    public List<Reserva> getReserva() {
        return reserva;
    }

    public void setReserva(List<Reserva> reserva) {
        this.reserva = reserva;
    }

    
    public int getNroHabitacion() {
        return nroHabitacion;
    }

    public void setNroHabitacion(int nroHabitacion) {
        this.nroHabitacion = nroHabitacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public float getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(float precioNoche) {
        this.precioNoche = precioNoche;
    }
    
    public void addReserva(Reserva reserv){
        this.reserva.add(reserv);
    }
    
}
