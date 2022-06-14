package isel.sisinf.g17.data;

import isel.sisinf.g17.data.repos.*;
import isel.sisinf.g17.data.repos.interfaces.*;
import isel.sisinf.g17.domain.*;
import isel.sisinf.g17.domain.interfaces.IRegistoNaoProcessado;
import jakarta.persistence.*;

import java.util.List;
import java.util.Scanner;

public class JPAContext implements IContext {
    private final EntityManagerFactory emf;
    private final EntityManager em;
    private EntityTransaction tx;
    private int txCount;

    private final IRepoClientesParticulares repoClientesParticulares;
    private final IRepoClientesInstitucionais repoClientesInstitucionais;
    private final IRepoAlarmes repoAlarmes;
    private final IRepoVeiculos repoVeiculos;
    private final IRepoFrotas repoFrotas;
    private final IRepoRegistosNaoProcessados repoRegistosNaoProcessados;
    private final IRepoRegistosNaoProcessadosOpt repoRegistosNaoProcessadosOpt;
    private final IRepoRegistosProcessados repoRegistosProcessados;
    private final IRepoRegistosInvalidos repoRegistosInvalidos;

    public JPAContext() {
        this("isel-sisinf-g17");
    }

    public JPAContext(String persistenceUnitName) {
        super();

        this.emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        this.em = emf.createEntityManager();
        this.repoClientesParticulares = new RepoClientesParticulares(this.em);
        this.repoClientesInstitucionais = new RepoClientesInstitucionais(this.em);
        this.repoAlarmes = new RepoAlarmes(this.em);
        this.repoVeiculos = new RepoVeiculos(this.em);
        this.repoFrotas = new RepoFrotas(this.em);
        this.repoRegistosNaoProcessados = new RepoRegistosNaoProcessados(this.em);
        this.repoRegistosNaoProcessadosOpt = new RepoRegistosNaoProcessadosOpt(this.em);
        this.repoRegistosProcessados = new RepoRegistosProcessados(this.em);
        this.repoRegistosInvalidos = new RepoRegistosInvalidos(this.em);
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
            if (tx.getRollbackOnly())
                tx.rollback();
            else
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
    public IRepoClientesInstitucionais getRepoClientesInstitucionais() {
        return this.repoClientesInstitucionais;
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
    public IRepoFrotas getRepoFrotas() {
        return this.repoFrotas;
    }

    @Override
    public IRepoRegistosNaoProcessados getRepoRegistosNaoProcessados() {
        return this.repoRegistosNaoProcessados;
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
        try {
            em.createNativeQuery("call processarRegistos()").executeUpdate();
        } finally {
            commit();
        }
    }

    private boolean registoValido(IRegistoNaoProcessado r) {
        if (r.getMarcaTemporal() == null) return false;
        if (r.getLatitude() == null) return false;
        if (r.getLongitude() == null) return false;
        if (r.getEquipamento() == null) return false;
        return true;
    }

    private void processarRegistoValido(IRegistoNaoProcessado r) {
        RegistoProcessado rp = new RegistoProcessado();
        rp.setMarcaTemporal(r.getMarcaTemporal());
        rp.setLatitude(r.getLatitude());
        rp.setLongitude(r.getLongitude());
        rp.setEquipamento(r.getEquipamento());
        repoRegistosProcessados.add(rp);
    }

    private void processarRegistoInvalido(IRegistoNaoProcessado r) {
        RegistoInvalido ri = new RegistoInvalido();
        ri.setMarcaTemporal(r.getMarcaTemporal());
        Equipamento e = r.getEquipamento();
        if (e == null) ri.setIdEquip(null);
        else ri.setIdEquip(e.getId());
        ri.setLatitude(r.getLatitude());
        ri.setLongitude(r.getLongitude());
        repoRegistosInvalidos.add(ri);
    }

    @Override
    public void processarRegistosOptimisticLocking() {
        beginTransaction();
        try {
            List<RegistoNaoProcessadoOpt> registos = repoRegistosNaoProcessadosOpt.find(
                    "SELECT r FROM RegistoNaoProcessadoOpt r"
            );
            // ----- For testing purposes only -----
            System.out.println("Pressione ENTER após causar alterações conflituantes...");
            Scanner s = new Scanner(System.in);
            s.nextLine();
            // -------------------------------------
            for (RegistoNaoProcessadoOpt r : registos) {
                if (registoValido(r)) processarRegistoValido(r);
                else processarRegistoInvalido(r);
                repoRegistosNaoProcessadosOpt.remove(r);
            }
        } finally {
            commit();
        }
    }

    @Override
    public ClienteParticular inserirClienteParticular(int nif, String nome, String morada, int telefone, int cc) {
        beginTransaction();
        try {
            Query q = em.createNativeQuery("call inserirCliente(?1, ?2, ?3, ?4, null, 'P', null, ?5)");
            q.setParameter(1, nif);
            q.setParameter(2, nome);
            q.setParameter(3, morada);
            q.setParameter(4, telefone);
            q.setParameter(5, cc);
            q.executeUpdate();
        } finally {
            commit();
        }
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
        try {
            beginTransaction();
            Query q;
            if (zonaVerde == null) {
                q = em.createNativeQuery("call criarVeiculo(?1, ?2, ?3, ?4, null, null, null)");
            } else {
                q = em.createNativeQuery(
                        "call criarVeiculo(?1, ?2, ?3, ?4, cast(?5 as decimal), cast(?6 as decimal), ?7)"
                );
                q.setParameter(5, zonaVerde.getLatitude());
                q.setParameter(6, zonaVerde.getLongitude());
                q.setParameter(7, zonaVerde.getRaio());
            }
            q.setParameter(1, matricula);
            q.setParameter(2, nomeCondutor);
            q.setParameter(3, telefoneCondutor);
            q.setParameter(4, nifCliente);
            q.executeUpdate();
        } finally {
            commit();
        }
        return repoVeiculos.findByKey(matricula);
    }

    @Override
    public void apagarRegistosInvalidos() {
        beginTransaction();
        try {
            em.createNativeQuery("call removerRegistosInvalidos()").executeUpdate();
        } finally {
            commit();
        }
    }

    @Override
    public void close() {
        if (tx != null)
            tx.rollback();
        em.close();
        emf.close();
    }
}
