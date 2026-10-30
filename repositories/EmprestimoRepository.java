package biblioteca.repositories;

import biblioteca.models.Emprestimo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Armazena os empréstimos em andamento, indexados pelo código do material
public class EmprestimoRepository {
    private final Map<Integer, Emprestimo> emprestimos = new LinkedHashMap<>();

    public void salvar(Emprestimo emprestimo) {
        emprestimos.put(emprestimo.getMaterial().getCodigo(), emprestimo);
    }

    public Emprestimo remover(int codigoMaterial) {
        return emprestimos.remove(codigoMaterial);
    }

    public List<Emprestimo> listar() {
        return new ArrayList<>(emprestimos.values());
    }
}
