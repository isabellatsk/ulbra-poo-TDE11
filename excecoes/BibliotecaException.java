package biblioteca.excecoes;

// Erro de regra de negócio, exibido ao usuário pelo menu
public class BibliotecaException extends RuntimeException {

    public BibliotecaException(String mensagem) {
        super(mensagem);
    }
}
