package isel.sisinf.g17.domain;

import isel.sisinf.g17.data.validation.Validation;
import isel.sisinf.g17.domain.interfaces.IClienteInstitucional;

public class ClienteInstitucional implements IClienteInstitucional {
    private int nif;
    private String nome;
    private String morada;
    private int telefone;
    private char tipo;
    private ClienteParticular refCliente;
    private boolean removido;
    private String nomeContacto;
    private Frota frota;

    @Override
    public String getNomeContacto() {
        return nomeContacto;
    }

    @Override
    public int getNif() {
        return nif;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getMorada() {
        return morada;
    }

    @Override
    public int getTelefone() {
        return telefone;
    }

    @Override
    public char getTipo() {
        return tipo;
    }

    @Override
    public ClienteParticular getRefCliente() {
        return refCliente;
    }

    @Override
    public boolean getRemovido() {
        return removido;
    }


    @Override
    public void setNif(int nif) {
        if (!Validation.nifValido(nif))
            throw new IllegalArgumentException("Invalid nif");
        this.nif = nif;
    }

    @Override
    public void setNome(String nome) {
        if (nome.length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException("The given name is too long");
        this.nome = nome;
    }

    @Override
    public void setMorada(String morada) {
        if (morada.length() > MAX_ADDRESS_LENGTH)
            throw new IllegalArgumentException("The given address is too long");
        this.morada = morada;
    }

    @Override
    public void setTelefone(int telefone) {
        if (!Validation.telefoneValido(telefone))
            throw new IllegalArgumentException("Invalid phone number");
        this.telefone = telefone;
    }

    @Override
    public void setRefCliente(ClienteParticular refCliente) {
        this.refCliente = refCliente;
    }

    @Override
    public void setRemovido(boolean removido) {
        this.removido = removido;
    }

    @Override
    public void setNomeContacto(String nomeContacto) {
        if (nomeContacto.length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException("The given name is too long");
        this.nomeContacto = nomeContacto;
    }

    @Override
    public String toString() {
        return String.format(
                "ClienteInstitucional(nif=%d, nome=%s, nomeContacto=%s, morada=%s, telefone=%d, refCliente=%d, removido=%b)",
                nif, nome, nomeContacto, morada, telefone, (refCliente == null) ? null : refCliente.getNif(), removido
        );
    }
}
