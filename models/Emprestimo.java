package biblioteca.models;

// Composição: um empréstimo é formado por um usuário e um material
public class Emprestimo {
    private final Usuario usuario;
    private final Material material;
    private final int prazoDias;

    public Emprestimo(Usuario usuario, Material material) {
        this.usuario = usuario;
        this.material = material;
        // Polimorfismo: cada categoria de usuário responde com o seu prazo
        this.prazoDias = usuario.calcularPrazoEmprestimo();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Material getMaterial() {
        return material;
    }

    public int getPrazoDias() {
        return prazoDias;
    }
}
