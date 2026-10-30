package biblioteca.models;

public class Livro extends Material {
    private final String autor;

    public Livro(int codigo, String titulo, int anoPublicacao, String autor) {
        super(codigo, titulo, anoPublicacao);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String getTipo() {
        return "Livro";
    }

    @Override
    public String getDetalhes() {
        return "Autor: " + autor;
    }
}
