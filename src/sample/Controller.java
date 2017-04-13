package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class Controller {

    private Calculator calc;

    @FXML
    private void initialize(){
        this.calc = new Calculator();
        this.r_edit_change();
        this.l_edit_change();
        this.tenv_edit_change();
        this.f0_edit_change();
        this.S0_edit_change();
        this.m_edit_change();
        this.a0_edit_change();
        this.an_edit_change();

        x_column.setCellValueFactory(new PropertyValueFactory<Solution,Double>("x"));
        t_column.setCellValueFactory(new PropertyValueFactory<Solution,Double>("t"));
        chart.getStylesheets().add(Main.class.getResource("chart.css").toExternalForm());
        r_edit.textProperty().addListener((observable,oldValue,newValue)-> {
            Double tmp = Double.valueOf(newValue);
            this.calc.setR(tmp);
        });
        l_edit.textProperty().addListener((observable,oldValue,newValue)-> {
            Double tmp = Double.valueOf(newValue);
            this.calc.setL(tmp);
            this.clear_chart();
        });
        tenv_edit.textProperty().addListener((observable,oldValue,newValue)-> {
            Double tmp = Double.valueOf(newValue);
            this.calc.setTenv(tmp);
        });
        f0_edit.textProperty().addListener((observable,oldValue,newValue)-> {
            Double tmp = Double.valueOf(newValue);
            this.calc.setF0(tmp);
        });
        S0_edit.textProperty().addListener((observable,oldValue,newValue)-> {
            Double tmp = Double.valueOf(newValue);
            this.calc.setS0(tmp);
        });
        a0_edit.textProperty().addListener((observable,oldValue,newValue)-> {
            Double tmp = Double.valueOf(newValue);
            this.calc.setA0(tmp);
        });
        an_edit.textProperty().addListener((observable,oldValue,newValue)-> {
            Double tmp = Double.valueOf(newValue);
            this.calc.setaN(tmp);
        });

    }

    @FXML
    private TableView table;

    @FXML
    private TableColumn<Solution,Double> x_column, t_column;

    @FXML
    private TextField r_edit,l_edit, tenv_edit, f0_edit, S0_edit, m_edit, a0_edit, an_edit, node_edit;

    @FXML
    private LineChart chart;

    @FXML
    private void clear_chart(){
        chart.getData().clear();
    }

    private ObservableList<Solution> result;
    @FXML
    private void calculate(){


        Integer N = Integer.valueOf(node_edit.getText());

        result = FXCollections.observableList(this.calc.calculate(N));

        table.setItems(result);

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        ObservableList<XYChart.Data<String,Double>> datas = FXCollections.observableArrayList();

        for(int i = 0; i< result.size(); ++i){
            datas.add(new XYChart.Data(result.get(i).getX().toString(),result.get(i).getT()));
        }

        series.setData(datas);

        //chart.getData().clear();

        chart.getData().add(series);
    }

    private void r_edit_change(){
        Double tmp = Double.valueOf(r_edit.getText());
        this.calc.setR(tmp);
    }

    private void l_edit_change(){
        Double tmp = Double.valueOf(l_edit.getText());
        this.calc.setL(tmp);
    }

    private void tenv_edit_change(){
        Double tmp = Double.valueOf(tenv_edit.getText());
        this.calc.setTenv(tmp);
    }

    private void f0_edit_change(){
        Double tmp = Double.valueOf(f0_edit.getText());
        this.calc.setF0(tmp);
    }

    private void S0_edit_change(){
        Double tmp = Double.valueOf(S0_edit.getText());
        this.calc.setS0(tmp);
    }

    private void m_edit_change(){
        Double tmp = Double.valueOf(m_edit.getText());
        this.calc.setM(tmp);
    }

    private void a0_edit_change(){
        Double tmp = Double.valueOf(a0_edit.getText());
        this.calc.setA0(tmp);
    }

    private void an_edit_change(){
        Double tmp = Double.valueOf(an_edit.getText());
        this.calc.setaN(tmp);
    }
}
