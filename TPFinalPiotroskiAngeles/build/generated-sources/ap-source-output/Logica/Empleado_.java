package Logica;

import Logica.Reserva;
import Logica.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-09T19:00:39")
@StaticMetamodel(Empleado.class)
public class Empleado_ extends Persona_ {

    public static volatile ListAttribute<Empleado, Reserva> reservas;
    public static volatile SingularAttribute<Empleado, Usuario> usuario;
    public static volatile SingularAttribute<Empleado, String> cargo;

}