package isel.sisinf.g17.domain.interfaces;

import isel.sisinf.g17.domain.Estado;

public interface IEquipamento {
    long getId();

    Estado getEstado();

    void setEstado(Estado estado);
}
