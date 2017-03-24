package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    private Calc calculator;
    private InputTable data;

    @FXML
    private void initialize(){
        calculator = new Calc();
        data = new InputTable("/home/pacman29/Model_grad/n.txt",
                "/home/pacman29/Model_grad/t.txt",
                "/home/pacman29/Model_grad/sigma.txt");
        Var var = new Var();

        var.setCk(Double.valueOf(input_ck.getText()));
        var.setRk(Double.valueOf(input_rk.getText()));
        var.setLk(Double.valueOf(input_lk.getText()));
        var.setUc0(Double.valueOf(input_uc0.getText()));
        var.setI0(Double.valueOf(input_i0.getText()));
        var.setR(Double.valueOf(input_r.getText()));
        var.setP0(Double.valueOf(input_p0.getText()));
        var.setTstart(Double.valueOf(input_t0.getText()));
        var.setTw(Double.valueOf(input_tw.getText()));
        var.setLe(Double.valueOf(input_l.getText()));

        calculator.setVar(var);


        col_t.setCellValueFactory(new PropertyValueFactory<Solution,Double>("t"));
        col_i.setCellValueFactory(new PropertyValueFactory<Solution,Double>("I"));
        col_uc.setCellValueFactory(new PropertyValueFactory<Solution,Double>("Uc"));
        col_ucp.setCellValueFactory(new PropertyValueFactory<Solution,Double>("U"));
        col_r.setCellValueFactory(new PropertyValueFactory<Solution,Double>("R"));
    }

    @FXML
    private TableView table;

    @FXML
    private TableColumn<Solution, Double> col_t, col_i, col_uc, col_ucp, col_r;

    @FXML
    private LineChart chart_1, chart_2;

    @FXML
    private TextField input_step, input_nodes, input_rk, input_lk,
            input_ck, input_uc0, input_i0, input_r,
            input_p0, input_t0, input_tw, input_l;

    @FXML
    private RadioButton rbtn_rk, rbtn_tr;

    @FXML
    private ToggleGroup Method;

    @FXML
    private Button btn_calc;


    private ObservableList<Solution> result;
    @FXML
    private void Calculate(){

        this.new_params();

        Double step = Double.valueOf(input_step.getText());
        Integer nodes = Integer.valueOf(input_nodes.getText());



        if (rbtn_rk.isSelected()){
            result = FXCollections.observableList(calculator.runge(step,nodes,data));
        } else {
            result = FXCollections.observableList(calculator.Trapeze(step,nodes,data));
        }

        table.setItems(result);

        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();
        XYChart.Series<String, Double> series3 = new XYChart.Series<>();
        XYChart.Series<String, Double> series4 = new XYChart.Series<>();
        ObservableList<XYChart.Data<String,Double>> datas1 = FXCollections.observableArrayList();
        ObservableList<XYChart.Data<String,Double>> datas2 = FXCollections.observableArrayList();
        ObservableList<XYChart.Data<String,Double>> datas3 = FXCollections.observableArrayList();
        ObservableList<XYChart.Data<String,Double>> datas4 = FXCollections.observableArrayList();

        for(int i = 0; i< result.size(); ++i){
            datas1.add(new XYChart.Data(result.get(i).getT().toString(),result.get(i).getU()));
            datas2.add(new XYChart.Data(result.get(i).getT().toString(),result.get(i).getI()));
            datas3.add(new XYChart.Data(result.get(i).getT().toString(),result.get(i).getUc()));
            datas4.add(new XYChart.Data(result.get(i).getT().toString(),result.get(i).getR()));
        }

        series1.setData(datas1);
        series2.setData(datas2);
        series3.setData(datas3);
        series4.setData(datas4);

        chart_1.getData().clear();
        chart_2.getData().clear();

        chart_1.getData().add(series1);
        chart_1.getData().add(series2);
        chart_1.getData().add(series3);
        chart_2.getData().add(series4);
    }

    @FXML
    private void new_params(){
        Var var = new Var();

        var.setCk(Double.valueOf(input_ck.getText()));
        var.setRk(Double.valueOf(input_rk.getText()));
        var.setLk(Double.valueOf(input_lk.getText()));
        var.setUc0(Double.valueOf(input_uc0.getText()));
        var.setI0(Double.valueOf(input_i0.getText()));
        var.setR(Double.valueOf(input_r.getText()));
        var.setP0(Double.valueOf(input_p0.getText()));
        var.setTstart(Double.valueOf(input_t0.getText()));
        var.setTw(Double.valueOf(input_tw.getText()));
        var.setLe(Double.valueOf(input_l.getText()));

        calculator.setVar(var);
    }
}
