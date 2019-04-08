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
import br.edu.uncq.ppw.contasweb.service.ContaService;

@ManagedBean
@ViewScoped
public class ContaMB {

	private Long contaId;
	private Conta conta;
	private ContaService contaService;
	private FacesUtil facesUtil;
	private List<Conta> contas;

	@PostConstruct
	public void init() {
		contaService = new ContaService();
		facesUtil = new FacesUtil();
		contas = new ArrayList<Conta>();

		contaId = facesUtil.capturarParametroUrl("id", contaId);
		if (nonNull(contaId)) {
			conta = contaService.contaPorId(contaId);
		}
	}

	public void salvar() {
		contaService.salvarOuAtualizar(conta);
		String acao = isNull(conta.getId()) ? "cadastrada" : "atualizada";
		facesUtil.informacao("msg", "Conta " + acao + " com sucesso", "");
		conta = new Conta();
	}

	public void listar() {
		contas = contaService.contas();
	}

	public void deletar() {
		contaService.deletar(conta);
		facesUtil.informacao("msg", "Conta removida com sucesso", "Detalhes: " + conta.getTitulo());
		conta = new Conta();
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
