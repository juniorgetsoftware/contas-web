package br.edu.uncq.ppw.contasweb.infra.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;

public class FacesUtil {

	private FacesContext context;
	private ExternalContext external;
	private HttpServletRequest request;
	private PrimeFaces primefaces;

	public FacesUtil() {
		context = FacesContext.getCurrentInstance();
		external = context.getExternalContext();
		request = (HttpServletRequest) external.getRequest();
		primefaces = PrimeFaces.current();
	}

	public void mensagemBase(Severity severity, String sumario, String detalhe, String componenteId,
			boolean manterMensagemAposRedirect) {
		FacesMessage message = new FacesMessage(severity, sumario, detalhe);
		context.addMessage(componenteId, message);
		atualizarComponente(componenteId);
		external.getFlash().setKeepMessages(manterMensagemAposRedirect);
	}

	public void informacao(String componenteId, String sumario, String detalhe, boolean manterMensagemAposRedirect) {
		mensagemBase(FacesMessage.SEVERITY_INFO, sumario, detalhe, componenteId, manterMensagemAposRedirect);
	}

	public void erro(String componenteId, String sumario, String detalhe, boolean manterMensagemAposRedirect) {
		mensagemBase(FacesMessage.SEVERITY_ERROR, sumario, detalhe, componenteId, manterMensagemAposRedirect);
	}

	public void fatal(String componenteId, String sumario, String detalhe, boolean manterMensagemAposRedirect) {
		mensagemBase(FacesMessage.SEVERITY_FATAL, sumario, detalhe, componenteId, manterMensagemAposRedirect);
	}

	public void atencao(String componenteId, String sumario, String detalhe, boolean manterMensagemAposRedirect) {
		mensagemBase(FacesMessage.SEVERITY_WARN, sumario, detalhe, componenteId, manterMensagemAposRedirect);
	}

	public void atualizarComponente(String... componentes) {
		primefaces.ajax().update(componentes);
	}

	public Long capturarParametroUrl(String nomeParametro, Long id) {
		String valorParametro = request.getParameter(nomeParametro);
		if (!String.valueOf(valorParametro).matches("\\d+"))
			return null;
		return Long.parseLong(valorParametro);
	}

	public boolean isPostBack() {
		return context.isPostback();
	}

	public boolean isNotPostBack() {
		return !isPostBack();
	}
}
