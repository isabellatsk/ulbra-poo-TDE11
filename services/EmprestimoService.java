package biblioteca.services;

import biblioteca.models.Emprestimo;
import biblioteca.models.Material;
import biblioteca.models.Usuario;
import biblioteca.repositories.EmprestimoRepository;

import java.util.List;

// Regras de negócio de empréstimos e devoluções
public class EmprestimoService {
    private final EmprestimoRepository repositorio;
    private final MaterialService materialService;
    private final UsuarioService usuarioService;

    public EmprestimoService(EmprestimoRepository repositorio, MaterialService materialService,
                             UsuarioService usuarioService) {
        this.repositorio = repositorio;
        this.materialService = materialService;
        this.usuarioService = usuarioService;
    }

    public Emprestimo realizarEmprestimo(int codigoMaterial, int idUsuario) {
        // buscar() lança exceção se o material ou o usuário não estiverem cadastrados
        Material material = materialService.buscar(codigoMaterial);
        Usuario usuario = usuarioService.buscar(idUsuario);
        material.emprestar();
        Emprestimo emprestimo = new Emprestimo(usuario, material);
        repositorio.salvar(emprestimo);
        return emprestimo;
    }

    public Emprestimo realizarDevolucao(int codigoMaterial) {
        Material material = materialService.buscar(codigoMaterial);
        // devolver() lança exceção se o material não estiver emprestado
        material.devolver();
        return repositorio.remover(codigoMaterial);
    }

    public List<Emprestimo> listar() {
        return repositorio.listar();
    }
}
