package isel.sisinf.g17.data.repos;

import isel.sisinf.g17.data.Generic;
import isel.sisinf.g17.data.repos.interfaces.IRepoRegistosNaoProcessados;
import isel.sisinf.g17.data.repos.interfaces.IRepoRegistosProcessados;
import isel.sisinf.g17.domain.RegistoNaoProcessado;
import isel.sisinf.g17.domain.RegistoProcessado;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RepoRegistosProcessados implements IRepoRegistosProcessados {
    private final EntityManager em;

    public RepoRegistosProcessados(EntityManager em) {
        super();

        this.em = em;
    }

    @Override
    public RegistoProcessado findByKey(Long key) {
        return em.createNamedQuery("RegistoProcessado.findByKey", RegistoProcessado.class)
                .setParameter("key", key)
                .getSingleResult();
    }

    @Override
    public void add(RegistoProcessado entity) {
        em.merge(entity);
    }

    @Override
    public void remove(RegistoProcessado entity) {
        em.remove(entity);
    }

    @Override
    public <R> List<R> find(String jpql, Object... params) {
        return Generic.query(this.em, jpql, params);
    }
}
