package biblioteca.models;

public class Revista extends Material {
    private final int edicao;

    public Revista(int codigo, String titulo, int anoPublicacao, int edicao) {
        super(codigo, titulo, anoPublicacao);
        this.edicao = edicao;
    }

    public int getEdicao() {
        return edicao;
    }

    @Override
    public String getTipo() {
        return "Revista";
    }

    @Override
    public String getDetalhes() {
        return "Edição: " + edicao;
    }
}
