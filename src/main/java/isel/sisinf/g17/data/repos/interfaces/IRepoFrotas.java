package isel.sisinf.g17.data.repos.interfaces;

import isel.sisinf.g17.domain.Frota;

public interface IRepoFrotas extends IRepo<Long, Frota> {
    Frota findByNif(int nif);
}
