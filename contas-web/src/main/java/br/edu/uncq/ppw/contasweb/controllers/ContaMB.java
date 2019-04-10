package br.edu.uncq.ppw.contasweb.controllers;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.uncq.ppw.contasweb.infra.jsf.FacesUtil;
import br.edu.uncq.ppw.contasweb.model.Conta;
import br.edu.uncq.ppw.contasweb.model.TipoConta;
import br.edu.uncq.ppw.contasweb.service.ContaService;

@ManagedBean
@ViewScoped
public class ContaMB {

	private Long contaId;
	private Conta conta;
	private ContaService contaService;
	private List<Conta> contas;

	@PostConstruct
	public void init() {
		contaService = new ContaService();
		contas = new ArrayList<>();

		contaId = FacesUtil.current().capturarParametroUrl("id", contaId);
		if (nonNull(contaId)) {
			conta = contaService.contaPorId(contaId);
		}
	}

	public String salvar() {
		boolean isNotEdicao = isNull(conta.getId());
		try {
			contaService.salvarOuAtualizar(conta);
			String acao = isNotEdicao ? "cadastrada" : "atualizada";
			boolean manterMensagemAposRedirect = isNotEdicao;
			FacesUtil.current().informacao("msg", "Conta " + acao + " com sucesso", "", manterMensagemAposRedirect);
			conta = new Conta();
		} catch (Exception e) {
			FacesUtil.current().erro("msg", "Erro inesperado!", "Detalhes: " + e.getMessage(), true);
		}
		return isNotEdicao ? "" : "/conta/listar.xhtml?faces-redirect=true";
	}

	public void listar() {
		contas = contaService.contas();
	}

	public void deletar(Conta conta) {
		try {
			contaService.deletar(conta);
			FacesUtil.current().informacao("msg", "Conta removida com sucesso", "Detalhes: " + conta.getTitulo(),
					false);
		} catch (Exception e) {
			FacesUtil.current().erro("msg", "Erro inesperado!", "Detalhes: " + e.getMessage(), true);
		}
	}

	public TipoConta[] tiposConta() {
		return TipoConta.values();
	}

	public Conta getConta() {
		if (isNull(conta)) {
			conta = new Conta();
		}
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
}
