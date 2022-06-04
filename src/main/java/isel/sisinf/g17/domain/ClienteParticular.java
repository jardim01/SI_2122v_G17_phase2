package isel.sisinf.g17.domain;

import isel.sisinf.g17.data.validation.Validation;
import isel.sisinf.g17.domain.interfaces.IClienteParticular;
import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
@SecondaryTables({
        @SecondaryTable(
                name = "clientes_particulares",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "nif_cliente", referencedColumnName = "nif")
        ),
        @SecondaryTable(
                name = "frotas_veiculos",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "nif_cliente", referencedColumnName = "nif")
        )
})
@NamedQuery(name = "ClienteParticular.findByKey", query = "SELECT c FROM ClienteParticular c WHERE c.nif =:key")
public class ClienteParticular implements IClienteParticular {
    @Id
    private int nif;
    private String nome;
    private String morada;
    private int telefone;
    private final char tipo = 'P';
    @Convert(converter = BooleanToBit.class)
    private boolean removido;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ref_cliente", referencedColumnName = "nif")
    private ClienteParticular refCliente;

    @Column(table = "clientes_particulares")
    private int cc;

    public ClienteParticular() {

    }

    public ClienteParticular(int nif, String nome, String morada, int telefone, int cc) {
        super();

        setNif(nif);
        setNome(nome);
        setMorada(morada);
        setTelefone(telefone);
        setRemovido(false);
        setCc(cc);
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
    public int getCc() {
        return cc;
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
        if (!Validation.telefoneValido(nif))
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
    public void setCc(int cc) {
        if (!Validation.ccValido(cc))
            throw new IllegalArgumentException("Invalid cc");
        this.cc = cc;
    }

    @Override
    public String toString() {
        return String.format(
                "ClienteParticular(nif=%d, cc=%d, nome=%s, morada=%s, telefone=%d, refCliente=%d, removido=%b)",
                nif, cc, nome, morada, telefone, (refCliente == null) ? null : refCliente.getNif(), removido
        );
    }
}
