package isel.sisinf.g17.domain;

import isel.sisinf.g17.data.validation.Validation;
import isel.sisinf.g17.domain.interfaces.IZonaVerde;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ZonaVerde implements IZonaVerde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zona")
    private long id;
    private double latitude;
    private double longitude;
    private int raio;
    private String matricula;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public int getRaio() {
        return raio;
    }

    @Override
    public String getMatricula() {
        return matricula;
    }

    @Override
    public void setLatitude(double latitude) {
        // TODO: validate
        this.latitude = latitude;
    }

    @Override
    public void setLongitude(double longitude) {
        // TODO: validate
        this.longitude = longitude;
    }

    @Override
    public void setRaio(int raio) {
        this.raio = raio;
    }

    @Override
    public void setMatricula(String matricula) {
        if (!Validation.matriculaValida(matricula))
            throw new IllegalArgumentException("Invalid license plate format");
        this.matricula = matricula;
    }
}
