package biblioteca.models;

// Nova categoria: o serviço de empréstimos não precisou ser alterado
public class Pesquisador extends Usuario {
    private final String areaPesquisa;

    public Pesquisador(int identificador, String nome, String areaPesquisa) {
        super(identificador, nome);
        this.areaPesquisa = areaPesquisa;
    }

    public String getAreaPesquisa() {
        return areaPesquisa;
    }

    @Override
    public String getCategoria() {
        return "Pesquisador";
    }

    @Override
    public int calcularPrazoEmprestimo() {
        return 21;
    }
}
