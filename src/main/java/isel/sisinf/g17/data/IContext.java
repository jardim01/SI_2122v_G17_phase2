package isel.sisinf.g17.data;

import isel.sisinf.g17.data.repos.interfaces.IRepoAlarmes;
import isel.sisinf.g17.data.repos.interfaces.IRepoClientesParticulares;
import isel.sisinf.g17.data.repos.interfaces.IRepoVeiculos;
import isel.sisinf.g17.domain.ClienteParticular;
import isel.sisinf.g17.domain.Veiculo;
import isel.sisinf.g17.domain.ZonaVerde;

public interface IContext extends AutoCloseable {
    void beginTransaction();

    void commit();

    void flush();

    IRepoClientesParticulares getRepoClientesParticulares();

    IRepoAlarmes getRepoAlarmes();

    IRepoVeiculos getRepoVeiculos();

    long contarAlarmes(int year, String matricula);

    void processarRegistos();

    ClienteParticular inserirCliente(int nif, String nome, String morada, int telefone, int cc);

    Veiculo criarVeiculo(String matricula, String nomeCondutor, int telefoneCondutor, int nifCliente, ZonaVerde zonaVerde);
}
