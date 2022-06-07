package isel.sisinf.g17.data;

public class Validation {
    private static final int MAX_NAME_LENGTH = 60;
    private static final int MAX_ADDRESS_LENGTH = 60;
    private static final int MAX_ESTADO_LENGTH = 20;

    public static boolean matriculaValida(String matricula) {
        // 00-00-AA
        if (matricula.matches("\\d{2}-\\d{2}-[A-Z]{2}"))
            return true;
        // 00-AA-00
        if (matricula.matches("\\d{2}-[A-Z]{2}-\\d{2}"))
            return true;
        // AA-00-00
        if (matricula.matches("[A-Z]{2}-\\d{2}-\\d{2}"))
            return true;
        // AA-00-AA
        if (matricula.matches("[A-Z]{2}-\\d{2}-[A-Z]{2}"))
            return true;

        return false;
    }

    public static boolean nifValido(int nif) {
        return nif >= 0 && nif <= 999999999;
    }

    public static boolean telefoneValido(int telefone) {
        return telefone >= 0 && telefone <= 999999999;
    }

    public static boolean ccValido(int cc) {
        return cc >= 0 && cc <= 99999999;
    }

    public static boolean nomeValido(String nome) {
        return nome.length() <= MAX_NAME_LENGTH;
    }

    public static boolean moradaValida(String morada) {
        return morada.length() <= MAX_ADDRESS_LENGTH;
    }

    public static boolean estadoValido(String estado) {
        return estado.length() <= MAX_ESTADO_LENGTH;
    }

    public static boolean latitudeValida(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }

    public static boolean longitudeValida(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }
}
