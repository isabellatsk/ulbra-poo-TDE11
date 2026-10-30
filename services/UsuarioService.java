package biblioteca.services;

import biblioteca.excecoes.BibliotecaException;
import biblioteca.models.Usuario;
import biblioteca.repositories.UsuarioRepository;

// Cadastro e consulta de usuários. Aceita qualquer subclasse de Usuario
public class UsuarioService {
    private final UsuarioRepository repositorio;

    public UsuarioService(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Usuario usuario) {
        if (repositorio.existe(usuario.getIdentificador())) {
            throw new BibliotecaException("Já existe um usuário com o identificador " + usuario.getIdentificador() + ".");
        }
        repositorio.salvar(usuario);
    }

    public Usuario buscar(int identificador) {
        Usuario usuario = repositorio.buscar(identificador);
        if (usuario == null) {
            throw new BibliotecaException("Usuário com identificador " + identificador + " não encontrado.");
        }
        return usuario;
    }
}
