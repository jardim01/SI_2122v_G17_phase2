package isel.sisinf.g17.data.repos;

import isel.sisinf.g17.data.Generic;
import isel.sisinf.g17.data.repos.interfaces.IRepoVeiculos;
import isel.sisinf.g17.domain.Veiculo;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RepoVeiculos implements IRepoVeiculos {
    private final EntityManager em;

    public RepoVeiculos(EntityManager em) {
        super();

        this.em = em;
    }

    @Override
    public Veiculo findByKey(String key) {
        return em.createNamedQuery("Veiculo.findByKey", Veiculo.class)
                .setParameter("key", key)
                .getSingleResult();
    }

    @Override
    public void add(Veiculo entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Veiculo entity) {
        em.remove(entity);
    }

    @Override
    public <R> List<R> find(String jpql, Object... params) {
        return Generic.query(em, jpql, params);
    }
}
