package biblioteca.models;

import biblioteca.excecoes.BibliotecaException;
import biblioteca.interfaces.Emprestavel;

// Classe base abstrata de todo item do acervo
public abstract class Material implements Emprestavel {
    private final int codigo;
    private String titulo;
    private final int anoPublicacao;
    private boolean disponivel = true;

    protected Material(int codigo, String titulo, int anoPublicacao) {
        this.codigo = codigo;
        setTitulo(titulo);
        this.anoPublicacao = anoPublicacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public final void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new BibliotecaException("O título não pode ficar vazio.");
        }
        this.titulo = titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    // A disponibilidade só muda por emprestar() e devolver()
    @Override
    public boolean estaDisponivel() {
        return disponivel;
    }

    @Override
    public void emprestar() {
        if (!disponivel) {
            throw new BibliotecaException("O material '" + titulo + "' já está emprestado.");
        }
        disponivel = false;
    }

    @Override
    public void devolver() {
        if (disponivel) {
            throw new BibliotecaException("O material '" + titulo + "' não está emprestado.");
        }
        disponivel = true;
    }

    public abstract String getTipo();
    public abstract String getDetalhes();
}
