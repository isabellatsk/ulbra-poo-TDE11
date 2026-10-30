package biblioteca.repositories;

import biblioteca.models.Material;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Armazena os materiais do acervo, indexados pelo código
public class MaterialRepository {
    private final Map<Integer, Material> materiais = new LinkedHashMap<>();

    public void salvar(Material material) {
        materiais.put(material.getCodigo(), material);
    }

    public Material buscar(int codigo) {
        return materiais.get(codigo);
    }

    public boolean existe(int codigo) {
        return materiais.containsKey(codigo);
    }

    public List<Material> listar() {
        return new ArrayList<>(materiais.values());
    }
}
