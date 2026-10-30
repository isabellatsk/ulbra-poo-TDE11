package biblioteca.models;

public class Estudante extends Usuario {
    private final String curso;

    public Estudante(int identificador, String nome, String curso) {
        super(identificador, nome);
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    @Override
    public String getCategoria() {
        return "Estudante";
    }

    @Override
    public int calcularPrazoEmprestimo() {
        return 7;
    }
}
