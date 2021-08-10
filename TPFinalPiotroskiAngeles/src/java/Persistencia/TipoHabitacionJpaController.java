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
import Logica.Habitacion;
import Logica.TipoHabitacion;
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
public class TipoHabitacionJpaController implements Serializable {

    public TipoHabitacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    TipoHabitacionJpaController() {
        emf = Persistence.createEntityManagerFactory("TPFinalPiotroskiAngeles_PU");
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion.getHabitaciones() == null) {
            tipoHabitacion.setHabitaciones(new ArrayList<Habitacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Habitacion> attachedHabitaciones = new ArrayList<Habitacion>();
            for (Habitacion habitacionesHabitacionToAttach : tipoHabitacion.getHabitaciones()) {
                habitacionesHabitacionToAttach = em.getReference(habitacionesHabitacionToAttach.getClass(), habitacionesHabitacionToAttach.getNroHabitacion());
                attachedHabitaciones.add(habitacionesHabitacionToAttach);
            }
            tipoHabitacion.setHabitaciones(attachedHabitaciones);
            em.persist(tipoHabitacion);
            for (Habitacion habitacionesHabitacion : tipoHabitacion.getHabitaciones()) {
                TipoHabitacion oldTipoOfHabitacionesHabitacion = habitacionesHabitacion.getTipo();
                habitacionesHabitacion.setTipo(tipoHabitacion);
                habitacionesHabitacion = em.merge(habitacionesHabitacion);
                if (oldTipoOfHabitacionesHabitacion != null) {
                    oldTipoOfHabitacionesHabitacion.getHabitaciones().remove(habitacionesHabitacion);
                    oldTipoOfHabitacionesHabitacion = em.merge(oldTipoOfHabitacionesHabitacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoHabitacion tipoHabitacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoHabitacion persistentTipoHabitacion = em.find(TipoHabitacion.class, tipoHabitacion.getNroTipo());
            List<Habitacion> habitacionesOld = persistentTipoHabitacion.getHabitaciones();
            List<Habitacion> habitacionesNew = tipoHabitacion.getHabitaciones();
            List<Habitacion> attachedHabitacionesNew = new ArrayList<Habitacion>();
            for (Habitacion habitacionesNewHabitacionToAttach : habitacionesNew) {
                habitacionesNewHabitacionToAttach = em.getReference(habitacionesNewHabitacionToAttach.getClass(), habitacionesNewHabitacionToAttach.getNroHabitacion());
                attachedHabitacionesNew.add(habitacionesNewHabitacionToAttach);
            }
            habitacionesNew = attachedHabitacionesNew;
            tipoHabitacion.setHabitaciones(habitacionesNew);
            tipoHabitacion = em.merge(tipoHabitacion);
            for (Habitacion habitacionesOldHabitacion : habitacionesOld) {
                if (!habitacionesNew.contains(habitacionesOldHabitacion)) {
                    habitacionesOldHabitacion.setTipo(null);
                    habitacionesOldHabitacion = em.merge(habitacionesOldHabitacion);
                }
            }
            for (Habitacion habitacionesNewHabitacion : habitacionesNew) {
                if (!habitacionesOld.contains(habitacionesNewHabitacion)) {
                    TipoHabitacion oldTipoOfHabitacionesNewHabitacion = habitacionesNewHabitacion.getTipo();
                    habitacionesNewHabitacion.setTipo(tipoHabitacion);
                    habitacionesNewHabitacion = em.merge(habitacionesNewHabitacion);
                    if (oldTipoOfHabitacionesNewHabitacion != null && !oldTipoOfHabitacionesNewHabitacion.equals(tipoHabitacion)) {
                        oldTipoOfHabitacionesNewHabitacion.getHabitaciones().remove(habitacionesNewHabitacion);
                        oldTipoOfHabitacionesNewHabitacion = em.merge(oldTipoOfHabitacionesNewHabitacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tipoHabitacion.getNroTipo();
                if (findTipoHabitacion(id) == null) {
                    throw new NonexistentEntityException("The tipoHabitacion with id " + id + " no longer exists.");
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
            TipoHabitacion tipoHabitacion;
            try {
                tipoHabitacion = em.getReference(TipoHabitacion.class, id);
                tipoHabitacion.getNroTipo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoHabitacion with id " + id + " no longer exists.", enfe);
            }
            List<Habitacion> habitaciones = tipoHabitacion.getHabitaciones();
            for (Habitacion habitacionesHabitacion : habitaciones) {
                habitacionesHabitacion.setTipo(null);
                habitacionesHabitacion = em.merge(habitacionesHabitacion);
            }
            em.remove(tipoHabitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoHabitacion> findTipoHabitacionEntities() {
        return findTipoHabitacionEntities(true, -1, -1);
    }

    public List<TipoHabitacion> findTipoHabitacionEntities(int maxResults, int firstResult) {
        return findTipoHabitacionEntities(false, maxResults, firstResult);
    }

    private List<TipoHabitacion> findTipoHabitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoHabitacion.class));
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

    public TipoHabitacion findTipoHabitacion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoHabitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoHabitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoHabitacion> rt = cq.from(TipoHabitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
