package isel.sisinf.g17.domain.interfaces;

import isel.sisinf.g17.domain.ClienteParticular;

public interface ICliente {
    int MAX_NAME_LENGTH = 60;
    int MAX_ADDRESS_LENGTH = 60;

    int getNif();

    String getNome();

    String getMorada();

    int getTelefone();

    char getTipo();

    ClienteParticular getRefCliente();

    boolean getRemovido();

    void setNif(int nif);

    void setNome(String nome);

    void setMorada(String morada);

    void setTelefone(int telefone);

    void setRefCliente(ClienteParticular refCliente);

    void setRemovido(boolean removido);
}
