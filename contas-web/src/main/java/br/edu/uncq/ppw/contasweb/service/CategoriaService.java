package br.edu.uncq.ppw.contasweb.service;

import java.util.List;

import br.edu.uncq.ppw.contasweb.model.Categoria;
import br.edu.uncq.ppw.contasweb.repository.CategoriaRepository;

public class CategoriaService {

	private CategoriaRepository categoriaRepository;

	public CategoriaService() {
		this.categoriaRepository = new CategoriaRepository();
	}

	public Categoria salvarOuAtualizar(Categoria categoria) {
		return this.categoriaRepository.salvarOuAtualizar(categoria);
	}

	public Categoria categoriaPorId(Long id) {
		return this.categoriaRepository.categoriaPorId(id);
	}

	public void deletar(Categoria categoria) {
		this.categoriaRepository.deletar(categoria);
	}

	public List<Categoria> categorias() {
		return this.categoriaRepository.categorias();
	}

}
