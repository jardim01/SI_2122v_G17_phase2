package isel.sisinf.g17.data.validation;

import java.util.regex.Pattern;

public class Validation {

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
}
