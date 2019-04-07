package br.edu.uncq.ppw.contasweb.infra.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

public class FacesUtil {

	public void mensagemBase(Severity severity, String sumario, String detalhe, String componenteId) {
		FacesMessage message = new FacesMessage(severity, sumario, detalhe);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(componenteId, message);
	}

	public void informacao(String componenteId, String sumario, String detalhe) {
		mensagemBase(FacesMessage.SEVERITY_INFO, sumario, detalhe, componenteId);
	}

	public void erro(String componenteId, String sumario, String detalhe) {
		mensagemBase(FacesMessage.SEVERITY_ERROR, sumario, detalhe, componenteId);
	}

	public void fatal(String componenteId, String sumario, String detalhe) {
		mensagemBase(FacesMessage.SEVERITY_FATAL, sumario, detalhe, componenteId);
	}

	public void atencao(String componenteId, String sumario, String detalhe) {
		mensagemBase(FacesMessage.SEVERITY_WARN, sumario, detalhe, componenteId);
	}

	public void atualizarComponente(String... componentes) {
		PrimeFaces.current().ajax().update(componentes);
	}
}
