package isel.sisinf.g17.data.repos;

import isel.sisinf.g17.data.Generic;
import isel.sisinf.g17.data.repos.interfaces.IRepoRegistosInvalidos;
import isel.sisinf.g17.data.repos.interfaces.IRepoRegistosProcessados;
import isel.sisinf.g17.domain.RegistoInvalido;
import isel.sisinf.g17.domain.RegistoProcessado;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RepoRegistosInvalidos implements IRepoRegistosInvalidos {
    private final EntityManager em;

    public RepoRegistosInvalidos(EntityManager em) {
        super();

        this.em = em;
    }

    @Override
    public RegistoInvalido findByKey(Long key) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void add(RegistoInvalido entity) {
        em.merge(entity);
    }

    @Override
    public void remove(RegistoInvalido entity) {
        em.remove(entity);
    }

    @Override
    public <R> List<R> find(String jpql, Object... params) {
        return Generic.query(this.em, jpql, params);
    }
}
