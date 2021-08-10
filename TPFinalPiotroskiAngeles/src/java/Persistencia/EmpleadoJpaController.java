/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Empleado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
EmpleadoJpaController() {
        emf = Persistence.createEntityManagerFactory("TPFinalPiotroskiAngeles_PU");
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        if (empleado.getReservas() == null) {
            empleado.setReservas(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedReservas = new ArrayList<Reserva>();
            for (Reserva reservasReservaToAttach : empleado.getReservas()) {
                reservasReservaToAttach = em.getReference(reservasReservaToAttach.getClass(), reservasReservaToAttach.getNroReserva());
                attachedReservas.add(reservasReservaToAttach);
            }
            empleado.setReservas(attachedReservas);
            em.persist(empleado);
            for (Reserva reservasReserva : empleado.getReservas()) {
                Empleado oldEmpleadoOfReservasReserva = reservasReserva.getEmpleado();
                reservasReserva.setEmpleado(empleado);
                reservasReserva = em.merge(reservasReserva);
                if (oldEmpleadoOfReservasReserva != null) {
                    oldEmpleadoOfReservasReserva.getReservas().remove(reservasReserva);
                    oldEmpleadoOfReservasReserva = em.merge(oldEmpleadoOfReservasReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getIdPersona());
            List<Reserva> reservasOld = persistentEmpleado.getReservas();
            List<Reserva> reservasNew = empleado.getReservas();
            List<Reserva> attachedReservasNew = new ArrayList<Reserva>();
            for (Reserva reservasNewReservaToAttach : reservasNew) {
                reservasNewReservaToAttach = em.getReference(reservasNewReservaToAttach.getClass(), reservasNewReservaToAttach.getNroReserva());
                attachedReservasNew.add(reservasNewReservaToAttach);
            }
            reservasNew = attachedReservasNew;
            empleado.setReservas(reservasNew);
            empleado = em.merge(empleado);
            for (Reserva reservasOldReserva : reservasOld) {
                if (!reservasNew.contains(reservasOldReserva)) {
                    reservasOldReserva.setEmpleado(null);
                    reservasOldReserva = em.merge(reservasOldReserva);
                }
            }
            for (Reserva reservasNewReserva : reservasNew) {
                if (!reservasOld.contains(reservasNewReserva)) {
                    Empleado oldEmpleadoOfReservasNewReserva = reservasNewReserva.getEmpleado();
                    reservasNewReserva.setEmpleado(empleado);
                    reservasNewReserva = em.merge(reservasNewReserva);
                    if (oldEmpleadoOfReservasNewReserva != null && !oldEmpleadoOfReservasNewReserva.equals(empleado)) {
                        oldEmpleadoOfReservasNewReserva.getReservas().remove(reservasNewReserva);
                        oldEmpleadoOfReservasNewReserva = em.merge(oldEmpleadoOfReservasNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = empleado.getIdPersona();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getIdPersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> reservas = empleado.getReservas();
            for (Reserva reservasReserva : reservas) {
                reservasReserva.setEmpleado(null);
                reservasReserva = em.merge(reservasReserva);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
