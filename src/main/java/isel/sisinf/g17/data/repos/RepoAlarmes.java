package isel.sisinf.g17.data.repos;

import isel.sisinf.g17.data.Generic;
import isel.sisinf.g17.data.repos.interfaces.IRepoAlarmes;
import isel.sisinf.g17.domain.Alarme;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RepoAlarmes implements IRepoAlarmes {
    private final EntityManager em;

    public RepoAlarmes(EntityManager em) {
        super();

        this.em = em;
    }

    @Override
    public Alarme findByKey(Long key) {
        return em.createNamedQuery("Alarme.findByKey", Alarme.class)
                .setParameter("key", key)
                .getSingleResult();
    }

    @Override
    public void add(Alarme entity) {
        // TODO: review this
        em.merge(entity);
    }

    @Override
    public void remove(Alarme entity) {
        em.remove(entity);
    }

    @Override
    public <R> List<R> find(String jpql, Object... params) {
        return Generic.query(em, jpql, params);
    }
}
