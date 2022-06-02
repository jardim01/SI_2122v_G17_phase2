package isel.sisinf.g17.domain;

interface IClienteInstitucional extends ICliente {

    int getNif();

    String getNome();

    String getMorada();

    int getTelefone();

    char getTipo();

    ClienteParticular getRefCliente();

    String getNomeContacto();

    void setNif(int nif);

    void setNome(String nome);

    void setMorada(String morada);

    void setTelefone(int telefone);

    void setRefCliente(ClienteParticular refCliente);

    void setNomeContacto(String nomeContacto);
}
