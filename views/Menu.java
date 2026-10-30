package biblioteca.views;

import biblioteca.excecoes.BibliotecaException;
import biblioteca.models.ArtigoCientifico;
import biblioteca.models.Emprestimo;
import biblioteca.models.Estudante;
import biblioteca.models.Livro;
import biblioteca.models.Material;
import biblioteca.models.Pesquisador;
import biblioteca.models.Professor;
import biblioteca.models.Revista;
import biblioteca.models.Usuario;
import biblioteca.services.EmprestimoService;
import biblioteca.services.MaterialService;
import biblioteca.services.UsuarioService;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

// Camada de apresentação: lê dados do terminal e delega aos serviços
public class Menu {
    private final MaterialService materialService;
    private final UsuarioService usuarioService;
    private final EmprestimoService emprestimoService;
    private final Scanner scanner;

    public Menu(MaterialService materialService, UsuarioService usuarioService, EmprestimoService emprestimoService) {
        this.materialService = materialService;
        this.usuarioService = usuarioService;
        this.emprestimoService = emprestimoService;
        // Usa a codificação do terminal para que acentos digitados sejam lidos corretamente
        java.io.Console terminal = System.console();
        this.scanner = new Scanner(System.in, terminal != null ? terminal.charset() : Charset.defaultCharset());
    }

    public void executar() {
        int opcao;
        do {
            System.out.println("\nSistema de Biblioteca\n");
            System.out.println("1 - Cadastrar material");
            System.out.println("2 - Cadastrar usuário");
            System.out.println("3 - Listar materiais");
            System.out.println("4 - Realizar empréstimo");
            System.out.println("5 - Realizar devolução");
            System.out.println("6 - Consultar empréstimos");
            System.out.println("0 - Sair");
            opcao = lerInteiro("\nEscolha uma opção: ");

            // Erros de regra de negócio são exibidos sem encerrar a aplicação
            try {
                switch (opcao) {
                    case 1 -> cadastrarMaterial();
                    case 2 -> cadastrarUsuario();
                    case 3 -> listarMateriais();
                    case 4 -> realizarEmprestimo();
                    case 5 -> realizarDevolucao();
                    case 6 -> consultarEmprestimos();
                    case 0 -> System.out.println("Encerrando o sistema.");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (BibliotecaException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void cadastrarMaterial() {
        System.out.println("\nTipo: 1 - Livro | 2 - Revista | 3 - Artigo científico");
        int tipo = lerInteiro("Escolha o tipo: ");
        if (tipo < 1 || tipo > 3) {
            throw new BibliotecaException("Tipo de material inválido.");
        }
        int codigo = lerInteiro("Código: ");
        String titulo = lerTexto("Título: ");
        int ano = lerInteiro("Ano de publicação: ");

        Material material = switch (tipo) {
            case 1 -> new Livro(codigo, titulo, ano, lerTexto("Autor: "));
            case 2 -> new Revista(codigo, titulo, ano, lerInteiro("Edição: "));
            default -> new ArtigoCientifico(codigo, titulo, ano, lerTexto("Autores: "));
        };
        materialService.cadastrar(material);
        System.out.println(material.getTipo() + " cadastrado com sucesso!");
    }

    private void cadastrarUsuario() {
        System.out.println("Categoria: 1 - Estudante | 2 - Professor | 3 - Pesquisador");
        int categoria = lerInteiro("Escolha a categoria: ");
        if (categoria < 1 || categoria > 3) {
            throw new BibliotecaException("Categoria de usuário inválida.");
        }
        int identificador = lerInteiro("Identificador: ");
        String nome = lerTexto("Nome: ");

        Usuario usuario = switch (categoria) {
            case 1 -> new Estudante(identificador, nome, lerTexto("Curso: "));
            case 2 -> new Professor(identificador, nome, lerTexto("Departamento: "));
            default -> new Pesquisador(identificador, nome, lerTexto("Área de pesquisa: "));
        };
        usuarioService.cadastrar(usuario);
        System.out.println(usuario.getCategoria() + " cadastrado com sucesso!");
    }

    private void listarMateriais() {
        List<Material> materiais = materialService.listar();
        if (materiais.isEmpty()) {
            System.out.println("Nenhum material cadastrado.");
            return;
        }
        for (Material material : materiais) {
            String situacao = material.estaDisponivel() ? "Disponível" : "Emprestado";
            System.out.println("[" + material.getCodigo() + "] " + material.getTipo() + ": " + material.getTitulo()
                    + " (" + material.getAnoPublicacao() + ") - " + material.getDetalhes() + " - " + situacao);
        }
    }

    private void realizarEmprestimo() {
        int codigo = lerInteiro("Código do material: ");
        int identificador = lerInteiro("Identificador do usuário: ");
        Emprestimo emprestimo = emprestimoService.realizarEmprestimo(codigo, identificador);
        System.out.println("Empréstimo realizado com sucesso!");
        System.out.println("Material: " + emprestimo.getMaterial().getTitulo());
        System.out.println("Usuário: " + emprestimo.getUsuario().getNome());
        System.out.println("Categoria: " + emprestimo.getUsuario().getCategoria());
        System.out.println("Prazo de empréstimo: " + emprestimo.getPrazoDias() + " dias");
    }

    private void realizarDevolucao() {
        int codigo = lerInteiro("Código do material: ");
        Emprestimo emprestimo = emprestimoService.realizarDevolucao(codigo);
        System.out.println("Devolução realizada com sucesso!");
        System.out.println("Material: " + emprestimo.getMaterial().getTitulo());
        System.out.println("Usuário: " + emprestimo.getUsuario().getNome());
    }

    private void consultarEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoService.listar();
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum material emprestado.");
            return;
        }
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println("Material: " + emprestimo.getMaterial().getTitulo()
                    + " | Usuário: " + emprestimo.getUsuario().getNome()
                    + " (" + emprestimo.getUsuario().getCategoria() + ")"
                    + " | Prazo: " + emprestimo.getPrazoDias() + " dias");
        }
    }

    private String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Este campo é obrigatório.");
        }
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(lerTexto(mensagem));
            } catch (NumberFormatException e) {
                System.out.println("Informe um número inteiro.");
            }
        }
    }
}
