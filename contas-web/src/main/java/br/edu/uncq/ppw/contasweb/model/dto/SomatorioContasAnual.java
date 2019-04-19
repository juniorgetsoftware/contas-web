package br.edu.uncq.ppw.contasweb.model.dto;

import java.math.BigDecimal;

import br.edu.uncq.ppw.contasweb.model.TipoConta;

public class SomatorioContasAnual {

	private TipoConta tipoConta;
	private BigDecimal jan;
	private BigDecimal fev;
	private BigDecimal mar;
	private BigDecimal abr;
	private BigDecimal mai;
	private BigDecimal jun;
	private BigDecimal jul;
	private BigDecimal ago;
	private BigDecimal set;
	private BigDecimal out;
	private BigDecimal nov;
	private BigDecimal dez;
	private Integer ano;

	public SomatorioContasAnual(TipoConta tipoConta, BigDecimal jan, BigDecimal fev, BigDecimal mar, BigDecimal abr,
			BigDecimal mai, BigDecimal jun, BigDecimal jul, BigDecimal ago, BigDecimal set, BigDecimal out,
			BigDecimal nov, BigDecimal dez, Integer ano) {

		this.tipoConta = tipoConta;
		this.jan = jan;
		this.fev = fev;
		this.mar = mar;
		this.abr = abr;
		this.mai = mai;
		this.jun = jun;
		this.jul = jul;
		this.ago = ago;
		this.set = set;
		this.out = out;
		this.nov = nov;
		this.dez = dez;
		this.ano = ano;

	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public BigDecimal getJan() {
		return jan;
	}

	public void setJan(BigDecimal jan) {
		this.jan = jan;
	}

	public BigDecimal getFev() {
		return fev;
	}

	public void setFev(BigDecimal fev) {
		this.fev = fev;
	}

	public BigDecimal getMar() {
		return mar;
	}

	public void setMar(BigDecimal mar) {
		this.mar = mar;
	}

	public BigDecimal getAbr() {
		return abr;
	}

	public void setAbr(BigDecimal abr) {
		this.abr = abr;
	}

	public BigDecimal getMai() {
		return mai;
	}

	public void setMai(BigDecimal mai) {
		this.mai = mai;
	}

	public BigDecimal getJun() {
		return jun;
	}

	public void setJun(BigDecimal jun) {
		this.jun = jun;
	}

	public BigDecimal getJul() {
		return jul;
	}

	public void setJul(BigDecimal jul) {
		this.jul = jul;
	}

	public BigDecimal getAgo() {
		return ago;
	}

	public void setAgo(BigDecimal ago) {
		this.ago = ago;
	}

	public BigDecimal getSet() {
		return set;
	}

	public void setSet(BigDecimal set) {
		this.set = set;
	}

	public BigDecimal getOut() {
		return out;
	}

	public void setOut(BigDecimal out) {
		this.out = out;
	}

	public BigDecimal getNov() {
		return nov;
	}

	public void setNov(BigDecimal nov) {
		this.nov = nov;
	}

	public BigDecimal getDez() {
		return dez;
	}

	public void setDez(BigDecimal dez) {
		this.dez = dez;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

}
