package isel.sisinf.g17.domain.interfaces;

public interface IZonaVerde {
    long getId();

    double getLatitude();

    double getLongitude();

    int getRaio();

    String getMatricula();

    void setLatitude(double latitude);

    void setLongitude(double longitude);

    void setRaio(int raio);

    void setMatricula(String matricula);
}
