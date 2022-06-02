package isel.sisinf.g17.domain;

interface IClienteParticular extends ICliente {

    int getNif();

    String getNome();

    String getMorada();

    int getTelefone();

    char getTipo();

    ClienteParticular getRefCliente();

    int getCc();

    void setNif(int nif);

    void setNome(String nome);

    void setMorada(String morada);

    void setTelefone(int telefone);

    void setRefCliente(ClienteParticular refCliente);

    void setCc(int cc);
}
