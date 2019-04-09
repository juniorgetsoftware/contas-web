package br.edu.uncq.ppw.contasweb.controllers;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.uncq.ppw.contasweb.infra.jsf.FacesUtil;
import br.edu.uncq.ppw.contasweb.model.Categoria;
import br.edu.uncq.ppw.contasweb.service.CategoriaService;

@ManagedBean
@ViewScoped
public class CategoriaMB {

	private Long categoriaId;
	private Categoria categoria;
	private CategoriaService categoriaService;
	private List<Categoria> categorias;

	@PostConstruct
	public void init() {
		categoriaService = new CategoriaService();
		categorias = new ArrayList<Categoria>();
		categoriaId = FacesUtil.current().capturarParametroUrl("id", categoriaId);
		if (nonNull(categoriaId)) {
			categoria = categoriaService.categoriaPorId(categoriaId);
		}
	}

	public String 	salvar() {
		categoriaService.salvarOuAtualizar(categoria);
		boolean isNotEdicao = isNull(categoria.getId());
		String acao = isNotEdicao ? "cadastrada" : "atualizada";
		boolean manterMensagemAposRedirect = isNotEdicao;
		FacesUtil.current().informacao("msg", "Categoria " + acao + " com sucesso", "", manterMensagemAposRedirect);
		categoria = new Categoria();
		return isNotEdicao ? "" : "/categoria/listar.xhtml?faces-redirect=true";
	}

	public void listar() {
		categorias = categoriaService.categorias();
	}

	public void deletar(Categoria categoria) {
		categoriaService.deletar(categoria);
		FacesUtil.current().informacao("msg", "Categoria removida com sucesso", "Detalhes: " + categoria.getNome(),
				false);
	}

	public Categoria getCategoria() {
		if (isNull(categoria)) {
			categoria = new Categoria();
		}
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
}
