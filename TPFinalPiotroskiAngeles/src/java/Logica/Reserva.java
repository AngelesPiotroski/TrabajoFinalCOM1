package Logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author piotr
 */
@Entity
public class Reserva implements Serializable {

    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private int nroReserva;
    @Basic
    @ManyToOne
    private Empleado empleado;
    @ManyToOne
    private Huesped huesped;
    @ManyToOne
    private Habitacion habitacion;
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Temporal(TemporalType.DATE)
    private Date checkIn, checkOut;

    public Reserva() {
    }

    public Reserva(int nroReserva, Empleado empleado, Huesped huesped, Habitacion habitacion, Date fechaCreacion, Date checkIn, Date checkOut) {
        this.nroReserva = nroReserva;
        this.empleado = empleado;
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.fechaCreacion = fechaCreacion;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public int getNroReserva() {
        return nroReserva;
    }

    public void setNroReserva(int nroReserva) {
        this.nroReserva = nroReserva;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    

}
