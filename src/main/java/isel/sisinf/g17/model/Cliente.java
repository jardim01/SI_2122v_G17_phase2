package isel.sisinf.g17.model;

public class Cliente {
    private int nif;
    private String nome;
    private String morada;
    private int telefone;
    private Character tipo;
    private ClienteParticular refCliente;

    private static final int MAX_NAME_LENGTH = 60;
    private static final int MAX_ADDRESS_LENGTH = 60;

    // Getters

    public int getNif() {
        return nif;
    }

    public String getNome() {
        return nome;
    }

    public String getMorada() {
        return morada;
    }

    public int getTelefone() {
        return telefone;
    }

    public Character getTipo() {
        return tipo;
    }

    public ClienteParticular getRefCliente() {
        return refCliente;
    }

    // Setters

    public void setNif(int nif) {
        if (nif < 0 || nif > 999999999)
            throw new IllegalArgumentException("Invalid nif");
        this.nif = nif;
    }

    public void setNome(String nome) {
        if (nome.length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException("The given name is too long");
        this.nome = nome;
    }

    public void setMorada(String morada) {
        if (morada.length() > MAX_ADDRESS_LENGTH)
            throw new IllegalArgumentException("The given address is too long");
        this.morada = morada;
    }

    public void setTelefone(int telefone) {
        if (telefone < 0 || telefone > 999999999)
            throw new IllegalArgumentException("Invalid phone number");
        this.telefone = telefone;
    }

    public void setTipo(Character tipo) {
        if (tipo != 'I' && tipo != 'P')
            throw new IllegalArgumentException("Invalid client type");
        this.tipo = tipo;
    }

    public void setRefCliente(ClienteParticular refCliente) {
        this.refCliente = refCliente;
    }
}
