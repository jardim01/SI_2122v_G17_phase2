package isel.sisinf.g17.data;

import isel.sisinf.g17.data.repos.*;
import isel.sisinf.g17.data.repos.interfaces.IRepoAlarmes;
import isel.sisinf.g17.data.repos.interfaces.IRepoClientesParticulares;
import isel.sisinf.g17.data.repos.interfaces.IRepoVeiculos;
import isel.sisinf.g17.domain.ClienteParticular;
import isel.sisinf.g17.domain.Veiculo;
import isel.sisinf.g17.domain.ZonaVerde;
import jakarta.persistence.*;

public class JPAContext implements IContext {
    private final EntityManagerFactory emf;
    private final EntityManager em;
    private EntityTransaction tx;
    private int txCount;

    private final IRepoClientesParticulares repoClientesParticulares;
    private final IRepoAlarmes repoAlarmes;
    private final IRepoVeiculos repoVeiculos;

    public JPAContext() {
        this("isel-sisinf-g17");
    }

    public JPAContext(String persistenceUnitName) {
        super();

        this.emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        this.em = emf.createEntityManager();
        this.repoClientesParticulares = new RepoClientesParticulares(this.em);
        this.repoAlarmes = new RepoAlarmes(this.em);
        this.repoVeiculos = new RepoVeiculos(this.em);
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
    public IRepoVeiculos getRepoVeiculos() {
        return this.repoVeiculos;
    }

    @Override
    public long contarAlarmes(int year, String matricula) {
        if (matricula == null || matricula.isEmpty()) {
            return (long) repoAlarmes.find(
                    "SELECT count(a) " +
                            "FROM Alarme a " +
                            "WHERE SQL('EXTRACT (YEAR FROM ?)', a.marcaTemporal) = ?1",
                    year
            ).get(0);
        } else {
            return (long) repoAlarmes.find(
                    "SELECT count(a) " +
                            "FROM Alarme a " +
                            "WHERE a.veiculo.matricula = ?1 " +
                            "AND SQL('EXTRACT (YEAR FROM ?)', a.marcaTemporal) = ?2",
                    matricula,
                    year
            ).get(0);
        }
    }

    @Override
    public void processarRegistos() {
        beginTransaction();
        em.createNativeQuery("call processarRegistos()").executeUpdate();
        commit();
    }

    @Override
    public ClienteParticular inserirCliente(int nif, String nome, String morada, int telefone, int cc) {
        beginTransaction();
        Query q = em.createNativeQuery("call inserirCliente(?1, ?2, ?3, ?4, null, 'P', null, ?5)");
        q.setParameter(1, nif);
        q.setParameter(2, nome);
        q.setParameter(3, morada);
        q.setParameter(4, telefone);
        q.setParameter(5, cc);
        q.executeUpdate();
        commit();
        return repoClientesParticulares.findByKey(nif);
    }

    @Override
    public Veiculo criarVeiculo(
            String matricula,
            String nomeCondutor,
            int telefoneCondutor,
            int nifCliente,
            ZonaVerde zonaVerde
    ) {
        beginTransaction();
        Query q;
        if (zonaVerde == null) {
            q = em.createNativeQuery("call criarVeiculo(?1, ?2, ?3, ?4, null, null, null)");
        } else {
            q = em.createNativeQuery("call criarVeiculo(?1, ?2, ?3, ?4, ?5, ?6, ?7)");
            q.setParameter(5, zonaVerde.getLatitude());
            q.setParameter(6, zonaVerde.getLongitude());
            q.setParameter(7, zonaVerde.getRaio());
        }
        q.setParameter(1, matricula);
        q.setParameter(2, nomeCondutor);
        q.setParameter(3, telefoneCondutor);
        q.setParameter(4, nifCliente);
        q.executeUpdate();
        commit();
        return repoVeiculos.findByKey(matricula);
    }

    @Override
    public void close() {
        if (tx != null)
            tx.rollback();
        em.close();
        emf.close();
    }
}
