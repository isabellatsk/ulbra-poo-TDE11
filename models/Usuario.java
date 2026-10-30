package biblioteca.models;

import biblioteca.excecoes.BibliotecaException;

// Classe base abstrata: cada categoria define o próprio prazo de empréstimo
public abstract class Usuario {
    private final int identificador;
    private String nome;

    protected Usuario(int identificador, String nome) {
        this.identificador = identificador;
        setNome(nome);
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public final void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new BibliotecaException("O nome não pode ficar vazio.");
        }
        this.nome = nome;
    }

    public abstract String getCategoria();
    public abstract int calcularPrazoEmprestimo();
}
