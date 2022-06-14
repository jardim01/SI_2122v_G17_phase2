package isel.sisinf.g17.data.repos;

import isel.sisinf.g17.data.Generic;
import isel.sisinf.g17.data.repos.interfaces.IRepoRegistosNaoProcessadosOpt;
import isel.sisinf.g17.domain.RegistoNaoProcessadoOpt;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RepoRegistosNaoProcessadosOpt implements IRepoRegistosNaoProcessadosOpt {
    private final EntityManager em;

    public RepoRegistosNaoProcessadosOpt(EntityManager em) {
        super();

        this.em = em;
    }

    @Override
    public RegistoNaoProcessadoOpt findByKey(Long key) {
        return em.createNamedQuery("RegistoNaoProcessadoOpt.findByKey", RegistoNaoProcessadoOpt.class)
                .setParameter("key", key)
                .getSingleResult();
    }

    @Override
    public void add(RegistoNaoProcessadoOpt entity) {
        em.merge(entity);
    }

    @Override
    public void remove(RegistoNaoProcessadoOpt entity) {
        em.remove(entity);
    }

    @Override
    public <R> List<R> find(String jpql, Object... params) {
        return Generic.query(this.em, jpql, params);
    }
}
