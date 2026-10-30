package biblioteca.models;

// Novo tipo de material: basta herdar de Material, sem alterar o restante do sistema
public class ArtigoCientifico extends Material {
    private final String autores;

    public ArtigoCientifico(int codigo, String titulo, int anoPublicacao, String autores) {
        super(codigo, titulo, anoPublicacao);
        this.autores = autores;
    }

    public String getAutores() {
        return autores;
    }

    @Override
    public String getTipo() {
        return "Artigo científico";
    }

    @Override
    public String getDetalhes() {
        return "Autores: " + autores;
    }
}
