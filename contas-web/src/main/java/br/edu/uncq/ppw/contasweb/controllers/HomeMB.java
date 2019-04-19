package br.edu.uncq.ppw.contasweb.controllers;

import static br.edu.uncq.ppw.contasweb.model.TipoConta.RECEITA;
import static java.util.Arrays.stream;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

import br.edu.uncq.ppw.contasweb.model.Mes;
import br.edu.uncq.ppw.contasweb.model.dto.SomatorioContasAnual;
import br.edu.uncq.ppw.contasweb.model.dto.SomatorioValorContaPorCategoria;
import br.edu.uncq.ppw.contasweb.service.HomeService;

@ManagedBean
@ViewScoped
public class HomeMB {

	private HomeService homeService = new HomeService();
	private Mes mes = Mes.mesPorNumero(LocalDate.now().getMonthValue());;
	private Integer ano = LocalDate.now().getYear();
	private List<SomatorioValorContaPorCategoria> totalReceitaPorCategoriaEMes;
	private List<SomatorioValorContaPorCategoria> totalDespesaPorCategoriaEMes;
	private List<SomatorioContasAnual> movimentacaoUltimos12Meses;
	private BigDecimal totalReceitasPorMes;
	private BigDecimal totalDespesasPorMes;

	private BigDecimal maiorReceitaDoMesNoAno;
	private BigDecimal menorReceitaDoMesNoAno;
	private BigDecimal maiorDespesaDoMesNoAno;
	private BigDecimal menorDespesaDoMesNoAno;

	private BarChartModel graficoMovimentacaoUltimos12Meses;

	@PostConstruct
	public void init() {
		totalReceitaPorCategoriaEMes = homeService.totalReceitaPorCategoriaEMes(mes, ano);
		totalDespesaPorCategoriaEMes = homeService.totalDespesaPorCategoriaEMes(mes, ano);

		totalReceitasPorMes = homeService.totalReceitaPorMes(mes, ano);
		totalDespesasPorMes = homeService.totalDespesaPorMes(mes, ano);

		maiorReceitaDoMesNoAno = homeService.maiorReceitaDoMesNoAno(mes, ano);
		menorReceitaDoMesNoAno = homeService.menorReceitaDoMesNoAno(mes, ano);
		maiorDespesaDoMesNoAno = homeService.maiorDespesaDoMesNoAno(mes, ano);
		menorDespesaDoMesNoAno = homeService.menorDespesaDoMesNoAno(mes, ano);

		movimentacaoUltimos12Meses = homeService.contasAnual(ano);

		criarGraficoMovimentacaoUltimos12Meses();
	}

	public void criarGraficoMovimentacaoUltimos12Meses() {

		graficoMovimentacaoUltimos12Meses = new BarChartModel();

		ChartData data = new ChartData();

		BarChartDataSet receitas = new BarChartDataSet();
		receitas.setLabel("Receitas");
		receitas.setBackgroundColor("#319731");
		receitas.setBorderColor("#019b3f");
		receitas.setBorderWidth(1);

		BarChartDataSet despesas = new BarChartDataSet();
		despesas.setLabel("Despesas");
		despesas.setBackgroundColor("#db4534");
		despesas.setBorderColor("#bc2929");
		despesas.setBorderWidth(1);

		List<Number> values = new ArrayList<>();
		List<Number> values2 = new ArrayList<>();
		List<String> labels = new ArrayList<>();

		stream(Mes.values()).forEach(m -> labels.add(m.getNome()));

		movimentacaoUltimos12Meses.forEach(c -> System.out.println(c.getTipoConta().getDescricao()));
		movimentacaoUltimos12Meses.forEach(c -> System.out.println(c.getJan()));
		movimentacaoUltimos12Meses.forEach(c -> System.out.println(c.getAbr()));
		
		movimentacaoUltimos12Meses.forEach(c -> {

			if (RECEITA.equals(c.getTipoConta())) {
				values.add(c.getJan());
				values.add(c.getFev());
				values.add(c.getMar());
				values.add(c.getAbr());
				values.add(c.getMai());
				values.add(c.getJun());
				values.add(c.getJul());
				values.add(c.getAgo());
				values.add(c.getSet());
				values.add(c.getOut());
				values.add(c.getNov());
				values.add(c.getDez());
			} else {
				values2.add(c.getJan());
				values2.add(c.getFev());
				values2.add(c.getMar());
				values2.add(c.getAbr());
				values2.add(c.getMai());
				values2.add(c.getJun());
				values2.add(c.getJul());
				values2.add(c.getAgo());
				values2.add(c.getSet());
				values2.add(c.getOut());
				values2.add(c.getNov());
				values2.add(c.getDez());
			}
		});

		receitas.setData(values);
		despesas.setData(values2);

		data.addChartDataSet(receitas);
		data.addChartDataSet(despesas);

		data.setLabels(labels);

		graficoMovimentacaoUltimos12Meses.setData(data);

		// Options
		BarChartOptions options = new BarChartOptions();
		CartesianScales cScales = new CartesianScales();
		CartesianLinearAxes linearAxes = new CartesianLinearAxes();
		CartesianLinearTicks ticks = new CartesianLinearTicks();
		ticks.setBeginAtZero(true);
		linearAxes.setTicks(ticks);
		cScales.addYAxesData(linearAxes);
		options.setScales(cScales);

		Title title = new Title();
		title.setDisplay(true);
		title.setText("Receitas e Despesas de " + ano);
		options.setTitle(title);

		graficoMovimentacaoUltimos12Meses.setOptions(options);
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public Mes[] mese() {
		return Mes.values();
	}

	public Integer[] anos() {
		Integer anoAterior = ano - 1;
		Integer anoAtual = ano;
		Integer proximoAno = ano + 1;
		return new Integer[] { anoAterior, anoAtual, proximoAno };
	}

	public List<SomatorioContasAnual> contasAnual(Integer ano) {
		return this.homeService.contasAnual(ano);
	}

	public List<SomatorioValorContaPorCategoria> getTotalReceitaPorCategoriaEMes() {
		return totalReceitaPorCategoriaEMes;
	}

	public void setTotalReceitaPorCategoriaEMes(List<SomatorioValorContaPorCategoria> totalReceitaPorCategoriaEMes) {
		this.totalReceitaPorCategoriaEMes = totalReceitaPorCategoriaEMes;
	}

	public List<SomatorioValorContaPorCategoria> getTotalDespesaPorCategoriaEMes() {
		return totalDespesaPorCategoriaEMes;
	}

	public void setTotalDespesaPorCategoriaEMes(List<SomatorioValorContaPorCategoria> totalDespesaPorCategoriaEMes) {
		this.totalDespesaPorCategoriaEMes = totalDespesaPorCategoriaEMes;
	}

	public BigDecimal getTotalReceitasPorMes() {
		return totalReceitasPorMes;
	}

	public void setTotalReceitasPorMes(BigDecimal totalReceitasPorMes) {
		this.totalReceitasPorMes = totalReceitasPorMes;
	}

	public BigDecimal getTotalDespesasPorMes() {
		return totalDespesasPorMes;
	}

	public void setTotalDespesasPorMes(BigDecimal totalDespesasPorMes) {
		this.totalDespesasPorMes = totalDespesasPorMes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public BigDecimal getMaiorReceitaDoMesNoAno() {
		return maiorReceitaDoMesNoAno;
	}

	public void setMaiorReceitaDoMesNoAno(BigDecimal maiorReceitaDoMesNoAno) {
		this.maiorReceitaDoMesNoAno = maiorReceitaDoMesNoAno;
	}

	public BigDecimal getMenorReceitaDoMesNoAno() {
		return menorReceitaDoMesNoAno;
	}

	public void setMenorReceitaDoMesNoAno(BigDecimal menorReceitaDoMesNoAno) {
		this.menorReceitaDoMesNoAno = menorReceitaDoMesNoAno;
	}

	public BigDecimal getMaiorDespesaDoMesNoAno() {
		return maiorDespesaDoMesNoAno;
	}

	public void setMaiorDespesaDoMesNoAno(BigDecimal maiorDespesaDoMesNoAno) {
		this.maiorDespesaDoMesNoAno = maiorDespesaDoMesNoAno;
	}

	public BigDecimal getMenorDespesaDoMesNoAno() {
		return menorDespesaDoMesNoAno;
	}

	public void setMenorDespesaDoMesNoAno(BigDecimal menorDespesaDoMesNoAno) {
		this.menorDespesaDoMesNoAno = menorDespesaDoMesNoAno;
	}

	public List<SomatorioContasAnual> getMovimentacaoUltimos12Meses() {
		return movimentacaoUltimos12Meses;
	}

	public void setMovimentacaoUltimos12Meses(List<SomatorioContasAnual> movimentacaoUltimos12Meses) {
		this.movimentacaoUltimos12Meses = movimentacaoUltimos12Meses;
	}

	public BarChartModel getGraficoMovimentacaoUltimos12Meses() {
		return graficoMovimentacaoUltimos12Meses;
	}

	public void setGraficoMovimentacaoUltimos12Meses(BarChartModel graficoMovimentacaoUltimos12Meses) {
		this.graficoMovimentacaoUltimos12Meses = graficoMovimentacaoUltimos12Meses;
	}

}
