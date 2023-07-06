import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class TESTE {
    public static class Contato {
        private String nome;
        private String telefone;
        private String email;

        public Contato(String nome, String telefone, String email) {
            this.nome = nome;
            this.telefone = telefone;
            this.email = email;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static void inserirContato(Contato[][] matriz, int linhas, int colunas) {
        Scanner scanner = new Scanner(System.in);
        int linha;

        mostrarContatos(matriz, linhas, colunas);
        do {
            System.out.println("Informe onde você deseja inserir o contato com valores de 1 a " + linhas);
            linha = scanner.nextInt();
        } while (linha < 1 || linha > linhas);

        System.out.println("Insira o nome do contato: ");
        String nome = scanner.next();
        System.out.println("Insira o telefone do contato: ");
        String telefone = scanner.next();
        System.out.println("Insira o email do contato: ");
        String email = scanner.next();

        matriz[linha - 1][0] = new Contato(nome, telefone, email);
        System.out.println("Contato adicionado.");
    }

    public static void mostrarContatos(Contato[][] matriz, int linhas, int colunas) {
        System.out.println("Contatos:");
        boolean listaVazia = true;

        Contato[] contatos = new Contato[linhas];
        int index = 0;

        for (int i = 0; i < linhas; i++) {
            Contato contato = matriz[i][0];
            if (contato != null) {
                contatos[index] = contato;
                index++;
                listaVazia = false;
            }
        }

        if (!listaVazia) {
            Arrays.sort(contatos, Comparator.comparing(Contato::getNome));

            for (Contato contato : contatos) {
                System.out.println("Nome: " + contato.getNome());
                System.out.println("Telefone: " + contato.getTelefone());
                System.out.println("Email: " + contato.getEmail());
                System.out.println();
            }
        } else {
            System.out.println("A lista de contatos está vazia.");
        }
    }

    public static void removerContato(Contato[][] matriz, int linhas, String nomeContato) {
        boolean encontrado = false;
        for (int i = 0; i < linhas; i++) {
            Contato contato = matriz[i][0];
            if (contato != null && contato.getNome().equals(nomeContato)) {
                encontrado = true;
                matriz[i][0] = null;
            }
        }
        if (encontrado) {
            System.out.println("Contato removido.");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    public static void editarContato(Contato[][] matriz, int linhas) {
        Scanner scanner = new Scanner(System.in);
        mostrarContatos(matriz, linhas, 1);

        System.out.println("Informe o número do contato que deseja editar:");
        int numeroContato;
        while (true) {
            try {
                numeroContato = Integer.parseInt(scanner.next());
                if (numeroContato >= 1 && numeroContato <= linhas) {
                    break;
                } else {
                    System.out.println("Número de contato inválido. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Número de contato inválido. Tente novamente.");
            }
        }

        Contato contato = matriz[numeroContato - 1][0];
        if (contato != null) {
            System.out.println("Contato selecionado: ");
            System.out.println("Nome: " + contato.getNome());
            System.out.println("Telefone: " + contato.getTelefone());
            System.out.println("Email: " + contato.getEmail());
            System.out.println();

            System.out.println("Insira o novo nome do contato: ");
            String nome = scanner.next();
            System.out.println("Insira o novo telefone do contato: ");
            String telefone = scanner.next();
            System.out.println("Insira o novo email do contato: ");
            String email = scanner.next();

            contato.setNome(nome);
            contato.setTelefone(telefone);
            contato.setEmail(email);

            System.out.println("Contato editado.");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    public static void buscarContato(Contato[][] matriz, int linhas) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o nome do contato que deseja procurar:");
        String nomeContato = scanner.next();

        boolean encontrado = false;
        for (int i = 0; i < linhas; i++) {
            Contato contato = matriz[i][0];
            if (contato != null && contato.getNome().equalsIgnoreCase(nomeContato)) {
                System.out.println("Nome: " + contato.getNome());
                System.out.println("Telefone: " + contato.getTelefone());
                System.out.println("Email: " + contato.getEmail());
                System.out.println();
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Contato não encontrado.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int linhas, colunas;

        System.out.println("Informe o número de contatos que deseja armazenar: ");
        linhas = scanner.nextInt();
        colunas = 1;

        Contato[][] matriz = new Contato[linhas][colunas];

        int opcao;
        do {
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Inserir contato");
            System.out.println("2 - Mostrar contatos");
            System.out.println("3 - Remover contato");
            System.out.println("4 - Editar contato");
            System.out.println("5 - Buscar contato");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    inserirContato(matriz, linhas, colunas);
                    break;
                case 2:
                    mostrarContatos(matriz, linhas, colunas);
                    break;
                case 3:
                    System.out.println("Informe o nome do contato que deseja remover:");
                    String nomeRemover = scanner.next();
                    removerContato(matriz, linhas, nomeRemover);
                    break;
                case 4:
                    editarContato(matriz, linhas);
                    break;
                case 5:
                    buscarContato(matriz, linhas);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
}
