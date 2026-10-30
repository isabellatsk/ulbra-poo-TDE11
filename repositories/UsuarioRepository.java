package biblioteca.repositories;

import biblioteca.models.Usuario;

import java.util.LinkedHashMap;
import java.util.Map;

// Armazena os usuários cadastrados, indexados pelo identificador
public class UsuarioRepository {
    private final Map<Integer, Usuario> usuarios = new LinkedHashMap<>();

    public void salvar(Usuario usuario) {
        usuarios.put(usuario.getIdentificador(), usuario);
    }

    public Usuario buscar(int identificador) {
        return usuarios.get(identificador);
    }

    public boolean existe(int identificador) {
        return usuarios.containsKey(identificador);
    }
}
