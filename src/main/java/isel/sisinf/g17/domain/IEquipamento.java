package isel.sisinf.g17.domain;

public interface IEquipamento {
    long getId();

    Estado getEstado();

    void setId(long id);

    void setEstado(Estado estado);
}
