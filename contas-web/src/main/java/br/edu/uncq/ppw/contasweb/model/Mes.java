package br.edu.uncq.ppw.contasweb.model;

import java.util.stream.Stream;

public enum Mes {
	JAN("Janeiro", "JAN", 1), 
	FEV("Fevereiro", "FEV", 2), 
	MAR("Março", "MAR", 3), 
	ABR("Abril", "ABR", 4), 
	MAI("Maio", "MAI", 5), 
	JUN("Junho", "JUN", 6), 
	JUL("Julho", "JUL", 7), 
	AGO("Agosto", "AGO", 8), 
	SET("Setembro", "SET", 8), 
	OUT("Outubro", "OUT", 10), 
	NOV("Novembro", "NOV", 1), 
	DEZ("Dezembro", "DEZ", 12);

	private String nome;
	private String abreviacao;
	private Integer numero;

	private Mes(String nome, String abreviacao, Integer numero) {
		this.nome = nome;
		this.abreviacao = abreviacao;
		this.numero = numero;
	}

	public String getNome() {
		return this.nome;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public String getAbreviacao() {
		return this.abreviacao;
	}

	public static Mes mesPorNome(String mes) {
		return Stream.of(values()).filter(m->m.nome.equals(mes)).findFirst().orElse(null);
	}

	public static Mes mesPorNumero(Integer mes) {
		return Stream.of(values()).filter(m->m.numero.equals(mes)).findFirst().orElse(null);
	}
}