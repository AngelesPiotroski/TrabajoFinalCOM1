package Logica;

import Logica.Reserva;
import Logica.TipoHabitacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-09T19:00:39")
@StaticMetamodel(Habitacion.class)
public class Habitacion_ { 

    public static volatile SingularAttribute<Habitacion, Integer> piso;
    public static volatile SingularAttribute<Habitacion, TipoHabitacion> tipo;
    public static volatile SingularAttribute<Habitacion, Float> precioNoche;
    public static volatile ListAttribute<Habitacion, Reserva> reserva;
    public static volatile SingularAttribute<Habitacion, Integer> nroHabitacion;
    public static volatile SingularAttribute<Habitacion, String> nombre;

}