package isel.sisinf.g17.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public class ClienteInstitucional extends Cliente {
    private String nomeContacto;

    public String getNomeContacto() {
        return nomeContacto;
    }

    public void setNomeContacto(String nomeContacto) {
        this.nomeContacto = nomeContacto;
    }
}
