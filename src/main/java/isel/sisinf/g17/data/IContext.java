package isel.sisinf.g17.data;

import isel.sisinf.g17.data.repos.IRepoAlarmes;
import isel.sisinf.g17.data.repos.IRepoClientesParticulares;

public interface IContext extends AutoCloseable {
    void beginTransaction();

    void commit();

    void flush();

    IRepoClientesParticulares getRepoClientesParticulares();
    IRepoAlarmes getRepoAlarmes();
}
