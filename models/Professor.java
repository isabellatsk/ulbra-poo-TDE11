package biblioteca.models;

public class Professor extends Usuario {
    private final String departamento;

    public Professor(int identificador, String nome, String departamento) {
        super(identificador, nome);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    @Override
    public String getCategoria() {
        return "Professor";
    }

    @Override
    public int calcularPrazoEmprestimo() {
        return 14;
    }
}
