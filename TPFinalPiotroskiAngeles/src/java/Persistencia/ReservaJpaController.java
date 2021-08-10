/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Huesped;
import Logica.Habitacion;
import Logica.Empleado;
import Logica.Reserva;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author piotr
 */
public class ReservaJpaController implements Serializable {

    public ReservaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    ReservaJpaController() {
        emf = Persistence.createEntityManagerFactory("TPFinalPiotroskiAngeles_PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reserva reserva) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Huesped huesped = reserva.getHuesped();
            if (huesped != null) {
                huesped = em.getReference(huesped.getClass(), huesped.getIdPersona());
                reserva.setHuesped(huesped);
            }
            Habitacion habitacion = reserva.getHabitacion();
            if (habitacion != null) {
                habitacion = em.getReference(habitacion.getClass(), habitacion.getNroHabitacion());
                reserva.setHabitacion(habitacion);
            }
            Empleado empleado = reserva.getEmpleado();
            if (empleado != null) {
                empleado = em.getReference(empleado.getClass(), empleado.getIdPersona());
                reserva.setEmpleado(empleado);
            }
            em.persist(reserva);
            if (huesped != null) {
                huesped.getReservas().add(reserva);
                huesped = em.merge(huesped);
            }
            if (habitacion != null) {
                habitacion.getReserva().add(reserva);
                habitacion = em.merge(habitacion);
            }
            if (empleado != null) {
                empleado.getReservas().add(reserva);
                empleado = em.merge(empleado);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reserva reserva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva persistentReserva = em.find(Reserva.class, reserva.getNroReserva());
            Huesped huespedOld = persistentReserva.getHuesped();
            Huesped huespedNew = reserva.getHuesped();
            Habitacion habitacionOld = persistentReserva.getHabitacion();
            Habitacion habitacionNew = reserva.getHabitacion();
            Empleado empleadoOld = persistentReserva.getEmpleado();
            Empleado empleadoNew = reserva.getEmpleado();
            if (huespedNew != null) {
                huespedNew = em.getReference(huespedNew.getClass(), huespedNew.getIdPersona());
                reserva.setHuesped(huespedNew);
            }
            if (habitacionNew != null) {
                habitacionNew = em.getReference(habitacionNew.getClass(), habitacionNew.getNroHabitacion());
                reserva.setHabitacion(habitacionNew);
            }
            if (empleadoNew != null) {
                empleadoNew = em.getReference(empleadoNew.getClass(), empleadoNew.getIdPersona());
                reserva.setEmpleado(empleadoNew);
            }
            reserva = em.merge(reserva);
            if (huespedOld != null && !huespedOld.equals(huespedNew)) {
                huespedOld.getReservas().remove(reserva);
                huespedOld = em.merge(huespedOld);
            }
            if (huespedNew != null && !huespedNew.equals(huespedOld)) {
                huespedNew.getReservas().add(reserva);
                huespedNew = em.merge(huespedNew);
            }
            if (habitacionOld != null && !habitacionOld.equals(habitacionNew)) {
                habitacionOld.getReserva().remove(reserva);
                habitacionOld = em.merge(habitacionOld);
            }
            if (habitacionNew != null && !habitacionNew.equals(habitacionOld)) {
                habitacionNew.getReserva().add(reserva);
                habitacionNew = em.merge(habitacionNew);
            }
            if (empleadoOld != null && !empleadoOld.equals(empleadoNew)) {
                empleadoOld.getReservas().remove(reserva);
                empleadoOld = em.merge(empleadoOld);
            }
            if (empleadoNew != null && !empleadoNew.equals(empleadoOld)) {
                empleadoNew.getReservas().add(reserva);
                empleadoNew = em.merge(empleadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = reserva.getNroReserva();
                if (findReserva(id) == null) {
                    throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva reserva;
            try {
                reserva = em.getReference(Reserva.class, id);
                reserva.getNroReserva();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.", enfe);
            }
            Huesped huesped = reserva.getHuesped();
            if (huesped != null) {
                huesped.getReservas().remove(reserva);
                huesped = em.merge(huesped);
            }
            Habitacion habitacion = reserva.getHabitacion();
            if (habitacion != null) {
                habitacion.getReserva().remove(reserva);
                habitacion = em.merge(habitacion);
            }
            Empleado empleado = reserva.getEmpleado();
            if (empleado != null) {
                empleado.getReservas().remove(reserva);
                empleado = em.merge(empleado);
            }
            em.remove(reserva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reserva> findReservaEntities() {
        return findReservaEntities(true, -1, -1);
    }

    public List<Reserva> findReservaEntities(int maxResults, int firstResult) {
        return findReservaEntities(false, maxResults, firstResult);
    }

    private List<Reserva> findReservaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reserva.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Reserva findReserva(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reserva.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reserva> rt = cq.from(Reserva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
