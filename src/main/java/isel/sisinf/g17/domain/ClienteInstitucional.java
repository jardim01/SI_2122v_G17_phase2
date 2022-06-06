package isel.sisinf.g17.domain;

import isel.sisinf.g17.data.Validation;
import isel.sisinf.g17.domain.interfaces.IClienteInstitucional;
import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
@SecondaryTables({
        @SecondaryTable(
                name = "clientes_institucionais",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "nif_cliente", referencedColumnName = "nif")
        ),
        @SecondaryTable(
                name = "frotas_veiculos",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "nif_cliente", referencedColumnName = "nif")
        )
})
@NamedQuery(name = "ClienteInstitucional.findByKey", query = "SELECT c FROM ClienteInstitucional c WHERE c.nif =:key")
public class ClienteInstitucional implements IClienteInstitucional {
    @Id
    private int nif;
    private String nome;
    private String morada;
    private int telefone;
    private final char tipo = 'I';
    @Convert(converter = BooleanToBit.class)
    private boolean removido;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ref_cliente", referencedColumnName = "nif")
    private ClienteParticular refCliente;

    @Column(table = "clientes_institucionais", name = "nome_contacto")
    private String nomeContacto;

    public ClienteInstitucional() {

    }

    public ClienteInstitucional(int nif, String nome, String morada, int telefone, String nomeContacto) {
        super();

        setNif(nif);
        setNome(nome);
        setMorada(morada);
        setTelefone(telefone);
        setRemovido(false);
        setNomeContacto(nomeContacto);
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
    public String getNomeContacto() {
        return this.nomeContacto;
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
        if (!Validation.nomeValido(nome))
            throw new IllegalArgumentException("The given name is too long");
        this.nome = nome;
    }

    @Override
    public void setMorada(String morada) {
        if (Validation.moradaValida(morada))
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
    public void setNomeContacto(String nomeContacto) {
        if (!Validation.nomeValido(nomeContacto))
            throw new IllegalArgumentException("The given name is too long");
        this.nomeContacto = nomeContacto;
    }

    @Override
    public void setRemovido(boolean removido) {
        this.removido = removido;
    }

    @Override
    public String toString() {
        return String.format(
                "ClienteInstitucional(nif=%d, nome=%s, nomeContacto=%s, morada=%s, telefone=%d, refCliente=%d, removido=%b)",
                nif, nome, nomeContacto, morada, telefone, (refCliente == null) ? null : refCliente.getNif(), removido
        );
    }
}
