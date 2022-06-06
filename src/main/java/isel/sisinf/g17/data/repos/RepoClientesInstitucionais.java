package isel.sisinf.g17.data.repos;

import isel.sisinf.g17.data.Generic;
import isel.sisinf.g17.data.repos.interfaces.IRepoClientesInstitucionais;
import isel.sisinf.g17.domain.ClienteInstitucional;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RepoClientesInstitucionais implements IRepoClientesInstitucionais {
    private final EntityManager em;

    public RepoClientesInstitucionais(EntityManager em) {
        super();

        this.em = em;
    }

    @Override
    public ClienteInstitucional findByKey(Integer key) {
        return em.createNamedQuery("ClienteInstitucional.findByKey", ClienteInstitucional.class)
                .setParameter("key", key)
                .getSingleResult();
    }

    @Override
    public void add(ClienteInstitucional entity) {
        em.merge(entity);
    }

    @Override
    public void remove(ClienteInstitucional entity) {
        em.remove(entity);
    }

    @Override
    public <R> List<R> find(String jpql, Object... params) {
        return Generic.query(this.em, jpql, params);
    }
}
