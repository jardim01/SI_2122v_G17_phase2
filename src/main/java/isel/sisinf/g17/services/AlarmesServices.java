package isel.sisinf.g17.services;

import isel.sisinf.g17.data.repos.IRepoAlarmes;

public class AlarmesServices {
    public static long totalAlarmes(IRepoAlarmes repo, int year, String matricula) {
        if (matricula == null || matricula.isEmpty()) {
            return (long) repo.find(
                    "SELECT count(a) FROM Alarme a WHERE SQL('EXTRACT (YEAR FROM ?)', a.marcaTemporal) = ?1",
                    year
            ).get(0);
        } else {
            return (long) repo.find(
                    "SELECT count(a) FROM Alarme a WHERE a.veiculo.matricula = ?1 AND SQL('EXTRACT (YEAR FROM ?)', a.marcaTemporal) = ?2",
                    matricula,
                    year
            ).get(0);
        }
    }
}
