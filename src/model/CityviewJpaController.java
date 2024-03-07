/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.exceptions.IllegalOrphanException;
import model.exceptions.NonexistentEntityException;
import model.exceptions.PreexistingEntityException;

/**
 *
 * @author anton
 */
public class CityviewJpaController implements Serializable {

    public CityviewJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cityview cityview) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        City cityOrphanCheck = cityview.getCity();
        if (cityOrphanCheck != null) {
            Cityview oldCityviewOfCity = cityOrphanCheck.getCityview();
            if (oldCityviewOfCity != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The City " + cityOrphanCheck + " already has an item of type Cityview whose city column cannot be null. Please make another selection for the city field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            City city = cityview.getCity();
            if (city != null) {
                city = em.getReference(city.getClass(), city.getAreaname());
                cityview.setCity(city);
            }
            em.persist(cityview);
            if (city != null) {
                city.setCityview(cityview);
                city = em.merge(city);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCityview(cityview.getCityAreaname()) != null) {
                throw new PreexistingEntityException("Cityview " + cityview + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cityview cityview) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cityview persistentCityview = em.find(Cityview.class, cityview.getCityAreaname());
            City cityOld = persistentCityview.getCity();
            City cityNew = cityview.getCity();
            List<String> illegalOrphanMessages = null;
            if (cityNew != null && !cityNew.equals(cityOld)) {
                Cityview oldCityviewOfCity = cityNew.getCityview();
                if (oldCityviewOfCity != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The City " + cityNew + " already has an item of type Cityview whose city column cannot be null. Please make another selection for the city field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cityNew != null) {
                cityNew = em.getReference(cityNew.getClass(), cityNew.getAreaname());
                cityview.setCity(cityNew);
            }
            cityview = em.merge(cityview);
            if (cityOld != null && !cityOld.equals(cityNew)) {
                cityOld.setCityview(null);
                cityOld = em.merge(cityOld);
            }
            if (cityNew != null && !cityNew.equals(cityOld)) {
                cityNew.setCityview(cityview);
                cityNew = em.merge(cityNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cityview.getCityAreaname();
                if (findCityview(id) == null) {
                    throw new NonexistentEntityException("The cityview with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cityview cityview;
            try {
                cityview = em.getReference(Cityview.class, id);
                cityview.getCityAreaname();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cityview with id " + id + " no longer exists.", enfe);
            }
            City city = cityview.getCity();
            if (city != null) {
                city.setCityview(null);
                city = em.merge(city);
            }
            em.remove(cityview);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cityview> findCityviewEntities() {
        return findCityviewEntities(true, -1, -1);
    }

    public List<Cityview> findCityviewEntities(int maxResults, int firstResult) {
        return findCityviewEntities(false, maxResults, firstResult);
    }

    private List<Cityview> findCityviewEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cityview.class));
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

    public Cityview findCityview(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cityview.class, id);
        } finally {
            em.close();
        }
    }

    public int getCityviewCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cityview> rt = cq.from(Cityview.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
