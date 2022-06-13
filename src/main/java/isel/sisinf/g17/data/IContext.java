package isel.sisinf.g17.data;

import isel.sisinf.g17.data.repos.interfaces.*;
import isel.sisinf.g17.domain.ClienteParticular;
import isel.sisinf.g17.domain.Veiculo;
import isel.sisinf.g17.domain.ZonaVerde;

public interface IContext extends AutoCloseable {
    void beginTransaction();

    void commit();

    void flush();

    IRepoClientesParticulares getRepoClientesParticulares();

    IRepoClientesInstitucionais getRepoClientesInstitucionais();

    IRepoAlarmes getRepoAlarmes();

    IRepoVeiculos getRepoVeiculos();

    IRepoFrotas getRepoFrotas();
    IRepoRegistosNaoProcessados getRepoRegistosNaoProcessados();

    long contarAlarmes(int year, String matricula);

    void processarRegistos();

    void processarRegistosOptimisticLocking();

    ClienteParticular inserirClienteParticular(int nif, String nome, String morada, int telefone, int cc);

    Veiculo criarVeiculo(String matricula, String nomeCondutor, int telefoneCondutor, int nifCliente, ZonaVerde zonaVerde);

    void apagarRegistosInvalidos();
}
