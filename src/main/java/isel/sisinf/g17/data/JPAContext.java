package isel.sisinf.g17.data;

import isel.sisinf.g17.data.repos.IRepoAlarmes;
import isel.sisinf.g17.data.repos.IRepoClientesParticulares;
import isel.sisinf.g17.data.repos.RepoAlarmes;
import isel.sisinf.g17.data.repos.RepoClientesParticulares;
import jakarta.persistence.*;

public class JPAContext implements IContext {
    private final EntityManagerFactory emf;
    private final EntityManager em;
    private EntityTransaction tx;
    private int txCount;

    private final IRepoClientesParticulares repoClientesParticulares;
    private final IRepoAlarmes repoAlarmes;

    public JPAContext() {
        this("isel-sisinf-g17");
    }

    public JPAContext(String persistenceUnitName) {
        super();

        this.emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        this.em = emf.createEntityManager();
        this.repoClientesParticulares = new RepoClientesParticulares(this.em);
        this.repoAlarmes = new RepoAlarmes(this.em);
    }

    @Override
    public void beginTransaction() {
        if (tx == null) {
            tx = em.getTransaction();
            tx.begin();
            txCount = 0;
        }
        ++txCount;
    }

    @Override
    public void commit() {
        --txCount;
        if (txCount == 0 && tx != null) {
            tx.commit();
            tx = null;
        }
    }

    @Override
    public void flush() {
        em.flush();
    }

    @Override
    public IRepoClientesParticulares getRepoClientesParticulares() {
        return this.repoClientesParticulares;
    }

    @Override
    public IRepoAlarmes getRepoAlarmes() {
        return this.repoAlarmes;
    }

    @Override
    public void close() {
        if (tx != null)
            tx.rollback();
        em.close();
        emf.close();
    }
}
