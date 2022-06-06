package isel.sisinf.g17.ui;

import isel.sisinf.g17.data.Validation;

import java.util.List;
import java.util.Scanner;

public class ConsoleUtils {
    public static void clear() {
        // 80 lines is probably enough
        for (int i = 0; i < 80; i++)
            System.out.println();
    }

    public static String readLine(String prompt) {
        System.out.print(prompt);
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    public static int readInt(String prompt) {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String line = s.nextLine();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                // ignore
            }
        }
    }

    public static Character readChar(String prompt, List<Character> valid) {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String line = s.nextLine();
            try {
                Character ch = line.charAt(0);
                if (valid.contains(ch)) return ch;
            } catch (IllegalArgumentException e) {
                // ignore
            }
        }
    }

    public static int readTelefone(String prompt) {
        while (true) {
            int tel = readInt(prompt);
            if (Validation.telefoneValido(tel))
                return tel;
        }
    }

    public static String readMatricula(String prompt) {
        while (true) {
            String matricula = readLine(prompt);
            if (Validation.matriculaValida(matricula))
                return matricula;
        }
    }

    public static int readNif(String prompt) {
        while (true) {
            int nif = readInt(prompt);
            if (Validation.nifValido(nif))
                return nif;
        }
    }

    public static String readNome(String prompt) {
        while (true) {
            String nome = readLine(prompt);
            if (Validation.nomeValido(nome))
                return nome;
        }
    }

    public static String readMorada(String prompt) {
        while (true) {
            String morada = readLine(prompt);
            if (Validation.moradaValida(morada))
                return morada;
        }
    }

    public static int readCc(String prompt) {
        while (true) {
            int cc = readInt(prompt);
            if (Validation.ccValido(cc))
                return cc;
        }
    }
}
