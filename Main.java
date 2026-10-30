package biblioteca;

import biblioteca.repositories.EmprestimoRepository;
import biblioteca.repositories.MaterialRepository;
import biblioteca.repositories.UsuarioRepository;
import biblioteca.services.EmprestimoService;
import biblioteca.services.MaterialService;
import biblioteca.services.UsuarioService;
import biblioteca.views.Menu;

public class Main {

    public static void main(String[] args) {
        // Montagem das camadas: cada uma recebe as dependências de que precisa
        MaterialService materialService = new MaterialService(new MaterialRepository());
        UsuarioService usuarioService = new UsuarioService(new UsuarioRepository());
        EmprestimoService emprestimoService =
                new EmprestimoService(new EmprestimoRepository(), materialService, usuarioService);

        new Menu(materialService, usuarioService, emprestimoService).executar();
    }
}
