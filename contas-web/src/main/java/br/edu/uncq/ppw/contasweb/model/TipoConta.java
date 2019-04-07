package br.edu.uncq.ppw.contasweb.model;

public enum TipoConta {

	RECEITA("Receita"), DESPESA("Despesa");

	TipoConta(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}
}
