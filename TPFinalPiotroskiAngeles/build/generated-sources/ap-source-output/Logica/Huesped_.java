package Logica;

import Logica.Reserva;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-09T19:00:39")
@StaticMetamodel(Huesped.class)
public class Huesped_ extends Persona_ {

    public static volatile ListAttribute<Huesped, Reserva> reservas;
    public static volatile SingularAttribute<Huesped, String> profesion;

}