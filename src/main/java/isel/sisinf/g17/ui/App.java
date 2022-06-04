package isel.sisinf.g17.ui;

import isel.sisinf.g17.data.JPAContext;
import isel.sisinf.g17.data.repos.interfaces.IRepoAlarmes;
import isel.sisinf.g17.data.repos.interfaces.IRepoClientesParticulares;
import isel.sisinf.g17.data.repos.interfaces.IRepoVeiculos;
import isel.sisinf.g17.domain.Alarme;
import isel.sisinf.g17.domain.ClienteParticular;
import isel.sisinf.g17.domain.Veiculo;

import java.util.List;
import java.util.Scanner;

public class App {

    private static class MenuOption {
        String description;
        Action action;

        public MenuOption(String description, Action action) {
            this.description = description;
            this.action = action;
        }
    }

    private interface Action {
        void run();
    }

    private final MenuOption[] menuOptions = new MenuOption[]{
            new MenuOption("Listar clientes particulares", new Action() {
                @Override
                public void run() {
                    listClientesParticulares();
                }
            }),
            new MenuOption("Inserir cliente particular", new Action() {
                @Override
                public void run() {
                    System.out.println("TODO");
                }
            }),
            new MenuOption("Atualizar cliente particular", new Action() {
                @Override
                public void run() {
                    System.out.println("TODO");
                }
            }),
            new MenuOption("Remover cliente particular", new Action() {
                @Override
                public void run() {
                    System.out.println("TODO");
                }
            }),
            new MenuOption("Contar alarmes ano/veiculo", new Action() {
                @Override
                public void run() {
                    System.out.println("TODO");
                }
            }),
            new MenuOption("Processar registos", new Action() {
                @Override
                public void run() {
                    System.out.println("TODO");
                }
            }),
            new MenuOption("Listar veículos", new Action() {
                @Override
                public void run() {
                    listVeiculos();
                }
            }),
            new MenuOption("Criar veículo", new Action() {
                @Override
                public void run() {
                    System.out.println("TODO");
                }
            }),
            new MenuOption("Criar veículo (sem procedimento)", new Action() {
                @Override
                public void run() {
                    System.out.println("TODO");
                }
            }),
            new MenuOption("Listar alarmes", new Action() {
                @Override
                public void run() {
                    listAlarmes();
                }
            }),
            new MenuOption("Inserir alarme", new Action() {
                @Override
                public void run() {
                    System.out.println("TODO");
                }
            }),
            new MenuOption("Apagar registos inválidos", new Action() {
                @Override
                public void run() {
                    System.out.println("TODO");
                }
            }),
            new MenuOption("Desativar cliente", new Action() {
                @Override
                public void run() {
                    System.out.println("TODO");
                }
            })
    };

    private MenuOption displayMenu() {
        System.out.println("############### Menu ###############");
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.printf("%d. %s\n", i + 1, menuOptions[i].description);
        }
        System.out.printf("%d. Sair\n", menuOptions.length + 1);
        System.out.print("\n> ");
        Scanner s = new Scanner(System.in);
        int idx = s.nextInt() - 1;

        if (idx < 0 || idx >= menuOptions.length) return null;
        return menuOptions[idx];
    }

    IRepoClientesParticulares repoClientesParticulares;
    IRepoAlarmes repoAlarmes;
    IRepoVeiculos repoVeiculos;

    public void run() {
        try (JPAContext ctx = new JPAContext()) {
            repoClientesParticulares = ctx.getRepoClientesParticulares();
            repoAlarmes = ctx.getRepoAlarmes();
            repoVeiculos = ctx.getRepoVeiculos();

            clearScreen();
            while (true) {
                MenuOption option = displayMenu();
                if (option == null) break;

                clearScreen();
                option.action.run();
                System.out.print("\nPress ENTER to continue...");
                new Scanner(System.in).nextLine();
                clearScreen();
            }
        } catch (Exception e) {
            System.err.println("\nERROR: " + e.getMessage());
        }
    }

    private static void clearScreen() {
        // 80 lines is probably enough
        for (int i = 0; i < 80; i++)
            System.out.println();
    }

    private void listClientesParticulares() {
        List<ClienteParticular> clients = repoClientesParticulares.find("SELECT c from ClienteParticular c");
        for (ClienteParticular client : clients) {
            if (client.getRemovido()) continue;
            System.out.println(client);
        }
    }

    private void listAlarmes() {
        List<Alarme> alarms = repoAlarmes.find("SELECT c from Alarme c");
        for (Alarme alarm : alarms) {
            System.out.println(alarm);
        }
    }

    private void listVeiculos() {
        List<Veiculo> veiculos = repoVeiculos.find("SELECT v from Veiculo v");
        for (Veiculo veiculo : veiculos) {
            System.out.println(veiculo);
        }
    }
}
