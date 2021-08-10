package Logica;

import Logica.Habitacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-09T19:00:39")
@StaticMetamodel(TipoHabitacion.class)
public class TipoHabitacion_ { 

    public static volatile SingularAttribute<TipoHabitacion, String> descripcion;
    public static volatile ListAttribute<TipoHabitacion, Habitacion> habitaciones;
    public static volatile SingularAttribute<TipoHabitacion, Integer> nroTipo;
    public static volatile SingularAttribute<TipoHabitacion, Integer> cantidadPersonas;
    public static volatile SingularAttribute<TipoHabitacion, String> nombre;

}