package br.edu.uncq.ppw.contasweb.controllers;

import java.time.Month;
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
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.elements.Elements;
import org.primefaces.model.charts.optionconfig.elements.ElementsLine;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.radar.RadarChartDataSet;
import org.primefaces.model.charts.radar.RadarChartModel;
import org.primefaces.model.charts.radar.RadarChartOptions;

import br.edu.uncq.ppw.contasweb.model.dto.SomatorioValorContaPorCategoria;
import br.edu.uncq.ppw.contasweb.service.HomeService;

@ManagedBean
@ViewScoped
public class HomeMB {

	private HomeService homeService;

	private PieChartModel pieModel;
	private BarChartModel barModel;
	private LineChartModel lineModel;
	private RadarChartModel radarModel;
	
	private Month mes = Month.APRIL;

	private List<SomatorioValorContaPorCategoria> totalReceitaPorCategoriaEMes;

	private Iterable<SomatorioValorContaPorCategoria> totalDespesaPorCategoriaEMes;
	
	@PostConstruct
	public void init() {
		homeService = new HomeService();
		totalReceitaPorCategoriaEMes = homeService.totalReceitaPorCategoriaEMes(mes);
		totalDespesaPorCategoriaEMes = homeService.totalDespesaPorCategoriaEMes(mes);
		createPieModel();
		createLineModel();
		createBarModel();
		createRadarModel();
	}

	private void createPieModel() {

		pieModel = new PieChartModel();
		ChartData data = new ChartData();

		PieChartDataSet dataSet = new PieChartDataSet();
		List<Number> values = new ArrayList<>();
		totalReceitaPorCategoriaEMes.forEach(a->values.add(a.getValor()));
		dataSet.setData(values);

		List<String> bgColors = new ArrayList<>();
		bgColors.add("rgb(26, 88, 156)");
		bgColors.add("rgb(155, 89, 185)");
		bgColors.add("rgb(231, 76, 60)");
		bgColors.add("rgb(44, 62, 80)");
		bgColors.add("rgb(46, 204, 113)");
		bgColors.add("rgb(241, 196, 15)");
		bgColors.add("rgb(236, 240, 241)");
		bgColors.add("rgb(22, 160, 133)");
		bgColors.add("rgb(243, 158, 18)");
		bgColors.add("rgb(189, 195, 199)");
		bgColors.add("rgb(52, 152, 219)");
		bgColors.add("rgb(230, 126, 34)");
		bgColors.add("rgb(41, 128, 185)");
		bgColors.add("rgb(211, 84, 0)");
		bgColors.add("rgb(127, 140, 141)");
		
		dataSet.setBackgroundColor(bgColors);

		data.addChartDataSet(dataSet);
		List<String> labels = new ArrayList<>();
		totalReceitaPorCategoriaEMes.forEach(a->labels.add(a.getCategoria()));
		data.setLabels(labels);

		pieModel.setData(data);
	}

	public void createLineModel() {
		lineModel = new LineChartModel();
		ChartData data = new ChartData();

		LineChartDataSet dataSet = new LineChartDataSet();
		List<Number> values = new ArrayList<>();
		
		totalReceitaPorCategoriaEMes.forEach(a->values.add(a.getValor()));
		
		dataSet.setData(values);
		dataSet.setFill(false);
		dataSet.setLabel("Custo por categoria");
		dataSet.setBorderColor("rgb(75, 192, 192)");
		
		data.addChartDataSet(dataSet);

		List<String> labels = new ArrayList<>();
		totalReceitaPorCategoriaEMes.forEach(a->labels.add(a.getCategoria()));
		data.setLabels(labels);

		// Options
		LineChartOptions options = new LineChartOptions();
		Title title = new Title();
		title.setDisplay(true);
		title.setText("Line Chart");
		options.setTitle(title);

		lineModel.setOptions(options);
		lineModel.setData(data);
	}

	public void createBarModel() {
		barModel = new BarChartModel();
		ChartData data = new ChartData();

		BarChartDataSet barDataSet = new BarChartDataSet();
		barDataSet.setLabel("My First Dataset");

		List<Number> values = new ArrayList<>();
		totalReceitaPorCategoriaEMes.forEach(a->values.add(a.getValor()));
		barDataSet.setData(values);

		List<String> bgColor = new ArrayList<>();
		bgColor.add("rgba(255, 99, 132, 0.2)");
		bgColor.add("rgba(255, 159, 64, 0.2)");
		bgColor.add("rgba(255, 205, 86, 0.2)");
		bgColor.add("rgba(75, 192, 192, 0.2)");
		bgColor.add("rgba(54, 162, 235, 0.2)");
		bgColor.add("rgba(153, 102, 255, 0.2)");
		bgColor.add("rgba(201, 203, 207, 0.2)");
		barDataSet.setBackgroundColor(bgColor);

		List<String> borderColor = new ArrayList<>();
		borderColor.add("rgb(255, 99, 132)");
		borderColor.add("rgb(255, 159, 64)");
		borderColor.add("rgb(255, 205, 86)");
		borderColor.add("rgb(75, 192, 192)");
		borderColor.add("rgb(54, 162, 235)");
		borderColor.add("rgb(153, 102, 255)");
		borderColor.add("rgb(201, 203, 207)");
		barDataSet.setBorderColor(borderColor);
		barDataSet.setBorderWidth(1);

		data.addChartDataSet(barDataSet);

		List<String> labels = new ArrayList<>();
		totalReceitaPorCategoriaEMes.forEach(a->labels.add(a.getCategoria()));
		data.setLabels(labels);
		barModel.setData(data);

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
		title.setText("Bar Chart");
		options.setTitle(title);

		Legend legend = new Legend();
		legend.setDisplay(true);
		legend.setPosition("top");
		LegendLabel legendLabels = new LegendLabel();
		legendLabels.setFontStyle("bold");
		legendLabels.setFontColor("#2980B9");
		legendLabels.setFontSize(24);
		legend.setLabels(legendLabels);
		options.setLegend(legend);

		barModel.setOptions(options);
	}

	public void createRadarModel() {
		radarModel = new RadarChartModel();
		ChartData data = new ChartData();

		RadarChartDataSet radarDataSet = new RadarChartDataSet();
		radarDataSet.setLabel("Reeceitas");
		radarDataSet.setFill(true);
		radarDataSet.setBackgroundColor("rgba(255, 99, 132, 0.2)");
		radarDataSet.setBorderColor("rgb(255, 99, 132)");
		radarDataSet.setPointBackgroundColor("rgb(255, 99, 132)");
		radarDataSet.setPointBorderColor("#fff");
		radarDataSet.setPointHoverBackgroundColor("#fff");
		radarDataSet.setPointHoverBorderColor("rgb(255, 99, 132)");
		List<Number> dataVal = new ArrayList<>();
		totalReceitaPorCategoriaEMes.forEach(a->dataVal.add(a.getValor()));
		radarDataSet.setData(dataVal);

		RadarChartDataSet radarDataSet2 = new RadarChartDataSet();
		radarDataSet2.setLabel("Despesas");
		radarDataSet2.setFill(true);
		radarDataSet2.setBackgroundColor("rgba(54, 162, 235, 0.2)");
		radarDataSet2.setBorderColor("rgb(54, 162, 235)");
		radarDataSet2.setPointBackgroundColor("rgb(54, 162, 235)");
		radarDataSet2.setPointBorderColor("#fff");
		radarDataSet2.setPointHoverBackgroundColor("#fff");
		radarDataSet2.setPointHoverBorderColor("rgb(54, 162, 235)");
		List<Number> dataVal2 = new ArrayList<>();
		totalDespesaPorCategoriaEMes.forEach(a->dataVal2.add(a.getValor()));
		radarDataSet2.setData(dataVal2);

		data.addChartDataSet(radarDataSet);
		data.addChartDataSet(radarDataSet2);

		List<String> labels = new ArrayList<>();
		totalReceitaPorCategoriaEMes.forEach(a->labels.add(a.getCategoria()));
		data.setLabels(labels);

		/* Options */
		RadarChartOptions options = new RadarChartOptions();
		Elements elements = new Elements();
		ElementsLine elementsLine = new ElementsLine();
		elementsLine.setTension(0);
		elementsLine.setBorderWidth(3);
		elements.setLine(elementsLine);
		options.setElements(elements);

		radarModel.setOptions(options);
		radarModel.setData(data);
	}

	

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public RadarChartModel getRadarModel() {
		return radarModel;
	}

	public void setRadarModel(RadarChartModel radarModel) {
		this.radarModel = radarModel;
	}

}
