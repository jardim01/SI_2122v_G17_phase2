package isel.sisinf.g17.data.repos;

import isel.sisinf.g17.data.Generic;
import isel.sisinf.g17.data.repos.interfaces.IRepoRegistosNaoProcessados;
import isel.sisinf.g17.domain.RegistoNaoProcessado;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RepoRegistosNaoProcessados implements IRepoRegistosNaoProcessados {
    private final EntityManager em;

    public RepoRegistosNaoProcessados(EntityManager em) {
        super();

        this.em = em;
    }

    @Override
    public RegistoNaoProcessado findByKey(Long key) {
        return em.createNamedQuery("RegistoNaoProcessado.findByKey", RegistoNaoProcessado.class)
                .setParameter("key", key)
                .getSingleResult();
    }

    @Override
    public void add(RegistoNaoProcessado entity) {
        em.merge(entity);
    }

    @Override
    public void remove(RegistoNaoProcessado entity) {
        em.remove(entity);
    }

    @Override
    public <R> List<R> find(String jpql, Object... params) {
        return Generic.query(this.em, jpql, params);
    }
}
