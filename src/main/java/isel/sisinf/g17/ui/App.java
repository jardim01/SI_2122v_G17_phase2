package isel.sisinf.g17.ui;

import isel.sisinf.g17.data.JPAContext;
import isel.sisinf.g17.data.repos.interfaces.*;
import isel.sisinf.g17.domain.Validation;
import isel.sisinf.g17.domain.*;
import jakarta.persistence.NoResultException;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.RollbackException;

import java.sql.Timestamp;
import java.util.Arrays;
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

    private final MenuOption[] menuOptions = {
            new MenuOption("Listar clientes particulares", new Action() {
                @Override
                public void run() {
                    listarClientesParticulares();
                }
            }),
            new MenuOption("Inserir cliente particular", new Action() {
                @Override
                public void run() {
                    inserirClienteParticular();
                }
            }),
            new MenuOption("Atualizar cliente particular", new Action() {
                @Override
                public void run() {
                    atualizarClienteParticular();
                }
            }),
            new MenuOption("Remover cliente particular", new Action() {
                @Override
                public void run() {
                    removerClienteParticular();
                }
            }),
            new MenuOption("Listar alarmes", new Action() {
                @Override
                public void run() {
                    listarAlarmes();
                }
            }),
            new MenuOption("Contar alarmes ano/veiculo", new Action() {
                @Override
                public void run() {
                    contarAlarmes();
                }
            }),
            new MenuOption("Listar registos n??o processados", new Action() {
                @Override
                public void run() {
                    listarRegistosNaoProcessados();
                }
            }),
            new MenuOption("Inserir registo", new Action() {
                @Override
                public void run() {
                    inserirRegisto();
                }
            }),
            new MenuOption("Processar registos", new Action() {
                @Override
                public void run() {
                    processarRegistos();
                }
            }),
            new MenuOption("Processar registos (Optimistic Locking)", new Action() {
                @Override
                public void run() {
                    processarRegistosOptimisticLocking();
                }
            }),
            new MenuOption("Listar ve??culos", new Action() {
                @Override
                public void run() {
                    listarVeiculos();
                }
            }),
            new MenuOption("Criar ve??culo", new Action() {
                @Override
                public void run() {
                    criarVeiculo();
                }
            }),
            new MenuOption("Criar ve??culo (sem procedimento)", new Action() {
                @Override
                public void run() {
                    criatVeiculoSemProcedimento();
                }
            }),
            new MenuOption("Apagar registos inv??lidos", new Action() {
                @Override
                public void run() {
                    apagarRegistosInvalidos();
                }
            }),
            new MenuOption("Desativar cliente", new Action() {
                @Override
                public void run() {
                    desativarCliente();
                }
            })
    };

    private MenuOption displayMenu() {
        System.out.println("################### Menu ###################");
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

    JPAContext ctx;
    IRepoClientesParticulares repoClientesParticulares;
    IRepoClientesInstitucionais repoClientesInstitucionais;
    IRepoAlarmes repoAlarmes;
    IRepoVeiculos repoVeiculos;
    IRepoFrotas repoFrotas;
    IRepoRegistosNaoProcessados repoRegistosNaoProcessados;

    public void run() {
        try (JPAContext ctx = new JPAContext()) {
            this.ctx = ctx;
            repoClientesParticulares = ctx.getRepoClientesParticulares();
            repoClientesInstitucionais = ctx.getRepoClientesInstitucionais();
            repoAlarmes = ctx.getRepoAlarmes();
            repoVeiculos = ctx.getRepoVeiculos();
            repoFrotas = ctx.getRepoFrotas();
            repoRegistosNaoProcessados = ctx.getRepoRegistosNaoProcessados();

            ConsoleUtils.clear();
            while (true) {
                MenuOption option = displayMenu();
                if (option == null) break;

                ConsoleUtils.clear();
                option.action.run();
                System.out.print("\nPressione ENTER para continuar...");
                new Scanner(System.in).nextLine();
                ConsoleUtils.clear();
            }
        } catch (Exception e) {
            System.err.println("\nERROR: " + e.getMessage());
        }
    }

    private ClienteParticular readClienteParticular() {
        int nif = ConsoleUtils.readNif("NIF: ");
        ClienteParticular c;
        while (true) {
            try {
                c = repoClientesParticulares.findByKey(nif);
                if (c.getRemovido()) throw new NoResultException();
                return c;
            } catch (NoResultException e) {
                System.out.println("\nERRO: N??o existe nenhum cliente particular com o NIF indicado");
            }
        }
    }

    private void listarClientesParticulares() {
        List<ClienteParticular> clients = repoClientesParticulares.find("SELECT c FROM ClienteParticular c");
        for (ClienteParticular client : clients) {
            if (client.getRemovido()) continue;
            System.out.println(client);
        }
    }

    private void inserirClienteParticular() {
        int nif = ConsoleUtils.readNif("NIF: ");
        String nome = ConsoleUtils.readNome("Nome: ");
        String morada = ConsoleUtils.readMorada("Morada: ");
        int telefone = ConsoleUtils.readTelefone("Telefone: ");
        int cc = ConsoleUtils.readCc("CC: ");

        try {
            ClienteParticular c = ctx.inserirClienteParticular(nif, nome, morada, telefone, cc);
            System.out.println("\nSucesso: " + c);
        } catch (PersistenceException e) {
            if (e.getMessage().contains("duplicate key value violates unique constraint \"clientes_pkey\"")) {
                System.out.println("\nERRO: J?? existe um cliente com o NIF indicado");
            } else {
                throw e;
            }
        }
    }

    private void atualizarClienteParticular() {
        ClienteParticular c = readClienteParticular();
        System.out.println("Dados atuais: " + c);

        String novoNome = ConsoleUtils.readNome("Nome: ");
        String novaMorada = ConsoleUtils.readMorada("Morada: ");
        int novoCc = ConsoleUtils.readCc("CC: ");

        ctx.beginTransaction();

        c.setNome(novoNome);
        c.setMorada(novaMorada);
        c.setCc(novoCc);

        boolean atualizarReferencia = ConsoleUtils.confirm("Deseja atualizar a refer??ncia? (y/n)\n> ");
        if (atualizarReferencia) {
            while (true) {
                int refNif = ConsoleUtils.readInt("NIF Referente: ");
                try {
                    ClienteParticular ref = repoClientesParticulares.findByKey(refNif);
                    c.setRefCliente(ref);
                    break;
                } catch (NoResultException e) {
                    System.out.println("\nERRO: N??o existe nenhum cliente particular com o NIF indicado");
                }
            }
        }

        ctx.commit();
        System.out.println("\nSucesso: " + c);
    }

    private void removerClienteParticular() {
        ClienteParticular c = readClienteParticular();

        boolean cancel = !ConsoleUtils.confirm(
                "Tem a certeza que deseja remover o cliente " + c.getNome() + "? (y/n)\n> "
        );
        if (cancel) return;

        ctx.beginTransaction();
        c.setRemovido(true);
        ctx.commit();

        System.out.println("\nCliente removido com sucesso");
    }

    private void listarAlarmes() {
        List<Alarme> alarms = repoAlarmes.find("SELECT c FROM Alarme c");
        for (Alarme alarm : alarms) {
            System.out.println(alarm);
        }
    }

    private void contarAlarmes() {
        int ano = ConsoleUtils.readInt("Ano: ");
        String matricula = ConsoleUtils.readLine("Matr??cula: ");
        if (!Validation.matriculaValida(matricula))
            matricula = null;

        long count = ctx.contarAlarmes(ano, matricula);
        if (matricula == null)
            System.out.println("\nTotal de alarmes para todos os ve??culos em " + ano + ": " + count);
        else
            System.out.println("\nTotal de alarmes do ve??culo " + matricula + " em " + ano + ": " + count);
    }

    private void listarRegistosNaoProcessados() {
        List<RegistoNaoProcessado> regs = repoRegistosNaoProcessados.find(
                "SELECT r FROM RegistoNaoProcessado r"
        );
        for (RegistoNaoProcessado reg : regs) {
            System.out.println(reg);
        }
    }

    private void inserirRegisto() {
        String matricula = ConsoleUtils.readMatricula("Matr??cula: ");
        Equipamento equipamento;
        try {
            Veiculo v = repoVeiculos.findByKey(matricula);
            equipamento = v.getEquipamento();
        } catch (NoResultException e) {
            System.out.println("\nERRO: N??o existe nenhum ve??culo com a matr??cula indicada");
            return;
        }
        double latitude = ConsoleUtils.readLatitude("Latitude: ");
        double longitude = ConsoleUtils.readLongitude("Longitude: ");

        RegistoNaoProcessado r = new RegistoNaoProcessado();
        r.setEquipamento(equipamento);
        r.setLatitude(latitude);
        r.setLongitude(longitude);
        r.setMarcaTemporal(new Timestamp(System.currentTimeMillis()));

        ctx.beginTransaction();
        repoRegistosNaoProcessados.add(r);
        ctx.commit();

        System.out.println("Sucesso!");
    }

    private void processarRegistos() {
        ctx.processarRegistos();
        System.out.println("Sucesso!");
    }

    private void processarRegistosOptimisticLocking() {
        try {
            ctx.processarRegistosOptimisticLocking();
            System.out.println("Sucesso!");
        } catch (RollbackException e) {
            if (e.getCause().getClass() != OptimisticLockException.class) throw e;
            System.out.println(
                    "N??o foi poss??vel processar os registos devido a altera????es concorrentes conflituantes"
            );
        }
    }

    private void listarVeiculos() {
        List<Veiculo> veiculos = repoVeiculos.find("SELECT v FROM Veiculo v");
        for (Veiculo veiculo : veiculos) {
            System.out.println(veiculo);
        }
    }

    private void criarVeiculo() {
        String matricula = ConsoleUtils.readMatricula("Matr??cula: ");
        String nomeCondutor = ConsoleUtils.readLine("Nome Condutor: ");
        int telCondutor = ConsoleUtils.readTelefone("Telefone Condutor: ");
        int nifCliente = ConsoleUtils.readNif("NIF Cliente: ");

        boolean associarZonaVerde = ConsoleUtils.confirm(
                "Deseja associar uma zona verde ao ve??culo? (y/n)\n> "
        );

        ZonaVerde zonaVerde = null;
        if (associarZonaVerde) {
            zonaVerde = new ZonaVerde();
            double latitude = ConsoleUtils.readLatitude("Latitude: ");
            double longitude = ConsoleUtils.readLongitude("Longitude: ");
            int raio = ConsoleUtils.readInt("Raio: ");
            zonaVerde.setLatitude(latitude);
            zonaVerde.setLongitude(longitude);
            zonaVerde.setRaio(raio);
            zonaVerde.setMatricula(matricula);
        }

        try {
            Veiculo v = ctx.criarVeiculo(matricula, nomeCondutor, telCondutor, nifCliente, zonaVerde);
            System.out.println("\nSucesso: " + v);
        } catch (PersistenceException e) {
            String msg = e.getMessage();
            if (msg.contains("null value in column \"id_frota\" of relation \"veiculos\""))
                System.out.println("\nERRO: N??o existe nenhum cliente com o NIF indicado");
            else if (msg.contains("duplicate key value violates unique constraint \"veiculos_pkey\""))
                System.out.println("\nERRO: J?? existe um ve??culo com a matr??cula indicada");
            else if (msg.contains("N??mero m??ximo de ve??culos alcan??ado"))
                System.out.println("\nERRO: N??mero m??ximo de ve??culos alcan??ado");
            else throw e;
        }
    }

    private void criatVeiculoSemProcedimento() {
        String matricula = ConsoleUtils.readMatricula("Matr??cula: ");

        try {
            repoVeiculos.findByKey(matricula);
            System.out.println("\nERRO: j?? existe um ve??culo com a matr??cula indicada");
            return;
        } catch (NoResultException e) {
            // great
        }

        String nomeCondutor = ConsoleUtils.readLine("Nome Condutor: ");
        int telCondutor = ConsoleUtils.readTelefone("Telefone Condutor: ");
        int nifCliente = ConsoleUtils.readNif("NIF Cliente: ");

        Veiculo v = new Veiculo();
        v.setMatricula(matricula);
        v.setNomeCondutorAtual(nomeCondutor);
        v.setTelefoneCondutorAtual(telCondutor);
        v.setEquipamento(new Equipamento(new Estado("Activo")));

        try {
            Frota frota = repoFrotas.findByNif(nifCliente);
            v.setFrota(frota);
        } catch (NoResultException e) {
            System.out.println("\nERRO: N??o existe nenhum cliente com o NIF indicado");
            return;
        }

        try {
            ctx.beginTransaction();
            repoVeiculos.add(v);
            ctx.commit();
        } catch (PersistenceException e) {
            if (e.getMessage().contains("duplicate key value violates unique constraint \"veiculos_pkey\""))
                System.out.println("\nERRO: J?? existe um ve??culo com a matr??cula indicada");
            else
                throw e;
        }

        System.out.println("\nSuccess: " + v);
    }

    private void apagarRegistosInvalidos() {
        ctx.apagarRegistosInvalidos();
        System.out.println("Sucesso!");
    }

    private void desativarCliente() {
        int nif = ConsoleUtils.readNif("NIF: ");
        try {
            ClienteParticular c = repoClientesParticulares.findByKey(nif);
            c.setRemovido(true);
            ctx.beginTransaction();
            ctx.commit();
            System.out.println("\nSucesso!");
            return;
        } catch (NoResultException e) {
            // ignore
        }

        try {
            ClienteInstitucional c = repoClientesInstitucionais.findByKey(nif);
            c.setRemovido(true);
            ctx.beginTransaction();
            ctx.commit();
            System.out.println("\nSucesso!");
            return;
        } catch (NoResultException e) {
            // ignore
        }

        System.out.println("\nERRO: N??o existe nenhum cliente com o NIF indicado");
    }
}
