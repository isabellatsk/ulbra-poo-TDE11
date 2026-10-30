package biblioteca.services;

import biblioteca.excecoes.BibliotecaException;
import biblioteca.models.Material;
import biblioteca.repositories.MaterialRepository;

import java.util.List;

// Cadastro e consulta do acervo. Aceita qualquer subclasse de Material
public class MaterialService {
    private final MaterialRepository repositorio;

    public MaterialService(MaterialRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Material material) {
        if (repositorio.existe(material.getCodigo())) {
            throw new BibliotecaException("Já existe um material com o código " + material.getCodigo() + ".");
        }
        repositorio.salvar(material);
    }

    public Material buscar(int codigo) {
        Material material = repositorio.buscar(codigo);
        if (material == null) {
            throw new BibliotecaException("Material com código " + codigo + " não encontrado.");
        }
        return material;
    }

    public List<Material> listar() {
        return repositorio.listar();
    }
}
