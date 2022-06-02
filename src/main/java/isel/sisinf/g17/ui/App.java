package isel.sisinf.g17.ui;

import isel.sisinf.g17.data.JPAContext;
import isel.sisinf.g17.data.repos.IRepoAlarmes;
import isel.sisinf.g17.data.repos.IRepoClientesParticulares;
import isel.sisinf.g17.domain.Alarme;
import isel.sisinf.g17.domain.ClienteParticular;
import isel.sisinf.g17.services.AlarmesServices;

import java.util.List;

public class App {
    public static void main(String[] args) {
        try (JPAContext ctx = new JPAContext()) {
            IRepoClientesParticulares repoClientesParticulares = ctx.getRepoClientesParticulares();
            listClientesParticulares(repoClientesParticulares);

            IRepoAlarmes repoAlarmes = ctx.getRepoAlarmes();
            listAlarmes(repoAlarmes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static void listClientesParticulares(IRepoClientesParticulares repo) {
        List<ClienteParticular> clients = repo.find(
                "SELECT c from ClienteParticular c"
        );
        for (ClienteParticular client : clients) {
            System.out.println(client);
        }
    }

    private static void listAlarmes(IRepoAlarmes repo) {
        List<Alarme> alarms = repo.find(
                "SELECT c from Alarme c"
        );
        for (Alarme alarm : alarms) {
            System.out.println(alarm);
        }
    }
}
