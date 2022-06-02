package isel.sisinf.g17.data.repos;

import isel.sisinf.g17.data.Utils;
import isel.sisinf.g17.domain.ClienteParticular;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RepoClientesParticulares implements IRepoClientesParticulares {
    private final EntityManager em;

    public RepoClientesParticulares(EntityManager em) {
        super();

        this.em = em;
    }

    @Override
    public ClienteParticular findByKey(Integer key) {
        return em.createNamedQuery("ClienteParticular.findByKey", ClienteParticular.class)
                .setParameter("key", key)
                .getSingleResult();
    }

    @Override
    public void add(ClienteParticular entity) {
        em.merge(entity);
    }

    @Override
    public void remove(ClienteParticular entity) {
        em.remove(entity);
    }

    @Override
    public <R> List<R> find(String jpql, Object... params) {
        return Utils.genericQuery(this.em, jpql, params);
    }
}
