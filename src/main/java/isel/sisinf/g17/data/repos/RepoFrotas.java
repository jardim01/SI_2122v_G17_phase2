package isel.sisinf.g17.data.repos;

import isel.sisinf.g17.data.Generic;
import isel.sisinf.g17.data.repos.interfaces.IRepoFrotas;
import isel.sisinf.g17.domain.Frota;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RepoFrotas implements IRepoFrotas {
    private final EntityManager em;

    public RepoFrotas(EntityManager em) {
        super();

        this.em = em;
    }

    @Override
    public Frota findByKey(Long key) {
        return em.createNamedQuery("Frota.findByKey", Frota.class)
                .setParameter("key", key)
                .getSingleResult();
    }

    @Override
    public void add(Frota entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Frota entity) {
        em.remove(entity);
    }

    @Override
    public <R> List<R> find(String jpql, Object... params) {
        return Generic.query(this.em, jpql, params);
    }

    @Override
    public Frota findByNif(int nif) {
        return em.createNamedQuery("Frota.findByNif", Frota.class)
                .setParameter("nif", nif)
                .getSingleResult();
    }
}
