/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Habitacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.TipoHabitacion;
import Logica.Reserva;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author piotr
 */
public class HabitacionJpaController implements Serializable {

    public HabitacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    HabitacionJpaController() {
        emf = Persistence.createEntityManagerFactory("TPFinalPiotroskiAngeles_PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Habitacion habitacion) {
        if (habitacion.getReserva() == null) {
            habitacion.setReserva(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoHabitacion tipo = habitacion.getTipo();
            if (tipo != null) {
                tipo = em.getReference(tipo.getClass(), tipo.getNroTipo());
                habitacion.setTipo(tipo);
            }
            List<Reserva> attachedReserva = new ArrayList<Reserva>();
            for (Reserva reservaReservaToAttach : habitacion.getReserva()) {
                reservaReservaToAttach = em.getReference(reservaReservaToAttach.getClass(), reservaReservaToAttach.getNroReserva());
                attachedReserva.add(reservaReservaToAttach);
            }
            habitacion.setReserva(attachedReserva);
            em.persist(habitacion);
            if (tipo != null) {
                tipo.getHabitaciones().add(habitacion);
                tipo = em.merge(tipo);
            }
            for (Reserva reservaReserva : habitacion.getReserva()) {
                Habitacion oldHabitacionOfReservaReserva = reservaReserva.getHabitacion();
                reservaReserva.setHabitacion(habitacion);
                reservaReserva = em.merge(reservaReserva);
                if (oldHabitacionOfReservaReserva != null) {
                    oldHabitacionOfReservaReserva.getReserva().remove(reservaReserva);
                    oldHabitacionOfReservaReserva = em.merge(oldHabitacionOfReservaReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Habitacion habitacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habitacion persistentHabitacion = em.find(Habitacion.class, habitacion.getNroHabitacion());
            TipoHabitacion tipoOld = persistentHabitacion.getTipo();
            TipoHabitacion tipoNew = habitacion.getTipo();
            List<Reserva> reservaOld = persistentHabitacion.getReserva();
            List<Reserva> reservaNew = habitacion.getReserva();
            if (tipoNew != null) {
                tipoNew = em.getReference(tipoNew.getClass(), tipoNew.getNroTipo());
                habitacion.setTipo(tipoNew);
            }
            List<Reserva> attachedReservaNew = new ArrayList<Reserva>();
            for (Reserva reservaNewReservaToAttach : reservaNew) {
                reservaNewReservaToAttach = em.getReference(reservaNewReservaToAttach.getClass(), reservaNewReservaToAttach.getNroReserva());
                attachedReservaNew.add(reservaNewReservaToAttach);
            }
            reservaNew = attachedReservaNew;
            habitacion.setReserva(reservaNew);
            habitacion = em.merge(habitacion);
            if (tipoOld != null && !tipoOld.equals(tipoNew)) {
                tipoOld.getHabitaciones().remove(habitacion);
                tipoOld = em.merge(tipoOld);
            }
            if (tipoNew != null && !tipoNew.equals(tipoOld)) {
                tipoNew.getHabitaciones().add(habitacion);
                tipoNew = em.merge(tipoNew);
            }
            for (Reserva reservaOldReserva : reservaOld) {
                if (!reservaNew.contains(reservaOldReserva)) {
                    reservaOldReserva.setHabitacion(null);
                    reservaOldReserva = em.merge(reservaOldReserva);
                }
            }
            for (Reserva reservaNewReserva : reservaNew) {
                if (!reservaOld.contains(reservaNewReserva)) {
                    Habitacion oldHabitacionOfReservaNewReserva = reservaNewReserva.getHabitacion();
                    reservaNewReserva.setHabitacion(habitacion);
                    reservaNewReserva = em.merge(reservaNewReserva);
                    if (oldHabitacionOfReservaNewReserva != null && !oldHabitacionOfReservaNewReserva.equals(habitacion)) {
                        oldHabitacionOfReservaNewReserva.getReserva().remove(reservaNewReserva);
                        oldHabitacionOfReservaNewReserva = em.merge(oldHabitacionOfReservaNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = habitacion.getNroHabitacion();
                if (findHabitacion(id) == null) {
                    throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.");
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
            Habitacion habitacion;
            try {
                habitacion = em.getReference(Habitacion.class, id);
                habitacion.getNroHabitacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.", enfe);
            }
            TipoHabitacion tipo = habitacion.getTipo();
            if (tipo != null) {
                tipo.getHabitaciones().remove(habitacion);
                tipo = em.merge(tipo);
            }
            List<Reserva> reserva = habitacion.getReserva();
            for (Reserva reservaReserva : reserva) {
                reservaReserva.setHabitacion(null);
                reservaReserva = em.merge(reservaReserva);
            }
            em.remove(habitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Habitacion> findHabitacionEntities() {
        return findHabitacionEntities(true, -1, -1);
    }

    public List<Habitacion> findHabitacionEntities(int maxResults, int firstResult) {
        return findHabitacionEntities(false, maxResults, firstResult);
    }

    private List<Habitacion> findHabitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Habitacion.class));
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

    public Habitacion findHabitacion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Habitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHabitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Habitacion> rt = cq.from(Habitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
