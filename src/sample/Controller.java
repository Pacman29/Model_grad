package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.util.ArrayList;

public class Controller {

    private Calculator calc;
    private InputTable inputTable;

    @FXML
    private void initialize(){
        this.calc = new Calculator();
        this.inputTable = new InputTable("data.txt");
        this.calc.getTable().setTable(inputTable.getFileTable());
        this.r_edit_change();
        this.l_edit_change();
        this.tenv_edit_change();
        this.f0_edit_change();
        this.S0_edit_change();
        this.m_edit_change();
        this.a0_edit_change();
        this.an_edit_change();
        this.N_izo_edit_change();
        this.tau_edit_change();

        //chart.getStylesheets().add(Main.class.getResource("chart.css").toExternalForm());
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
        N_izo_edit.textProperty().addListener((observable,oldValue,newValue)-> {
            Integer tmp = Integer.valueOf(newValue);
            this.calc.setNizo(tmp);
        });
        tau_edit.textProperty().addListener((observable,oldValue,newValue)-> {
            Double tmp = Double.valueOf(newValue);
            this.calc.setTau(tmp);
        });

    }

    @FXML
    private TextField r_edit,l_edit, tenv_edit, f0_edit, S0_edit, m_edit, a0_edit, an_edit, node_edit,N_izo_edit,tau_edit;

    @FXML
    private LineChart chart;

    @FXML
    private void clear_chart(){
        chart.getData().clear();
    }

    private ArrayList<Graph> result;
    @FXML
    private void calculate(){


        Integer N = Integer.valueOf(node_edit.getText());

        //result = FXCollections.observableList(this.calc.calculate(N));

        result = this.calc.calculate(N);

        int gnum = this.calc.getNizo();
        chart.getData().clear();
        for (int i = 0; i<gnum; ++i){
            Graph graph = result.get((result.size()-1) * i / (gnum -1));
            ObservableList<Solution> tmp = FXCollections.observableList(graph.getSolutionArray());
            XYChart.Series<String, String> series = new XYChart.Series<>();
            ObservableList<XYChart.Data<String,String>> datas = FXCollections.observableArrayList();
            int j = -1;
            for(Solution point: tmp){
                j++;
                if(j % 20 == 0) {
                    datas.add(new XYChart.Data(((Double) (Math.rint(10e+6 * point.getX()) / 10e+6)).toString(),
                            ((Double) (Math.rint(10e+2 * point.getT()) / 10e+2)).toString()));
                } else{
                    continue;
                }
            }
            series.setData(datas);
            chart.getData().add(series);
        }
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

    private void N_izo_edit_change(){
        Integer tmp = Integer.valueOf(N_izo_edit.getText());
        this.calc.setNizo(tmp);
    }

    private void tau_edit_change(){
        Double tmp = Double.valueOf(tau_edit.getText());
        this.calc.setTau(tmp);
    }
}
