package br.edu.uncq.ppw.contasweb.model;

import static java.util.Objects.isNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "conta")
@Entity(name = "conta")
public class Conta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6552362757263787037L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "titulo", nullable = false)
	private String titulo;

	@Column(name = "valor", nullable = false, precision = 10, scale = 2)
	private BigDecimal valor;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_conta", nullable = false)
	private TipoConta tipoConta;

	@ManyToOne()
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@Column(name = "is_quitada", nullable = false)
	private boolean isQuitada;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento", nullable = false)
	private Date dataVencimento;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_pagamento")
	private Date dataPagamento;

	/*
	 * 
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public boolean isQuitada() {
		return isQuitada;
	}

	public void setQuitada(boolean isQuitada) {
		this.isQuitada = isQuitada;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public boolean isNova() {
		return isNull(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
