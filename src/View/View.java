package View;

import Controller.Controller;
import Model.ResultRow;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

import java.io.IOException;
import java.util.Map;

/**
 * Created by pacman29 on 04.03.17.
 */
public class View {
    @FXML
    private TableView table;
    @FXML
    private Spinner step;
    @FXML
    private Spinner points;
    @FXML
    private Spinner alpha;

    public View() {
        Controller.setView(this);
    }

    private static Stage stage;
    public static void setStage(Stage st) {
        stage = st;
    }

    private Controller cntr;
    public void setCntr(Controller cntr) {
        this.cntr = cntr;
    }

    private Double getDouble(Spinner<Double> tf) {
        Double val = tf.getValue();
        if(val == 0){
            MessageBox.show(stage,"Некорректные данные : "+ tf.getEditor().getText(),"Ошибка", MessageBox.OK);
        }
        return val;
    }

    private Integer getInteger(Spinner<Integer> tf) {
        Integer val =  tf.getValue();
        if(val == 0){
            MessageBox.show(stage,"Некорректные данные : "+ tf.getEditor().getText(),"Ошибка", MessageBox.OK);

        }
        return val;
    }


    @FXML
    private void OnPush(){

        Double step_value = getDouble(step);
        Integer points_value = getInteger(points);
        Double alpha_value = getDouble(alpha);

        if(step_value != 0 && points_value != 0 && alpha_value != 0) {
            this.cntr.calculate(step_value, points_value, alpha_value);
        } else {
            return;
        }


    }

    @SuppressWarnings("unchecked")
    public void InitView() throws IOException {

        SpinnerValueFactory svf_step = new SpinnerValueFactory.DoubleSpinnerValueFactory
                (0,Double.MAX_VALUE,0,0.05);
        this.step.setValueFactory(svf_step);

        SpinnerValueFactory svf_points = new SpinnerValueFactory.IntegerSpinnerValueFactory
                (1,Integer.MAX_VALUE,1, 1);
        this.points.setValueFactory(svf_points);

        SpinnerValueFactory svf_alpha = new SpinnerValueFactory.DoubleSpinnerValueFactory
                (0,1.0,0, 0.1);
        this.alpha.setValueFactory(svf_alpha);

        table.setEditable(false);
    }

    public void  InitTable(ObservableList data) throws IOException {
        table.setItems(data);
        ((TableColumn) table.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory<ResultRow, Double>("x"));
        ((TableColumn) table.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory<ResultRow, Double>("picar1"));
        ((TableColumn) table.getColumns().get(2)).setCellValueFactory(new PropertyValueFactory<ResultRow, Double>("picar2"));
        ((TableColumn) table.getColumns().get(3)).setCellValueFactory(new PropertyValueFactory<ResultRow, Double>("picar3"));
        ((TableColumn) table.getColumns().get(4)).setCellValueFactory(new PropertyValueFactory<ResultRow, Double>("picar4"));
        ((TableColumn) table.getColumns().get(5)).setCellValueFactory(new PropertyValueFactory<ResultRow, Double>("runge"));
        ((TableColumn) table.getColumns().get(6)).setCellValueFactory(new PropertyValueFactory<ResultRow, Double>("explicit"));
        ((TableColumn) table.getColumns().get(7)).setCellValueFactory(new PropertyValueFactory<ResultRow, Double>("implicit"));
    }
}
