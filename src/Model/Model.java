package Model;

import Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pacman29 on 04.03.17.
 */
public class Model {
    private Func func;
    private double a;

    public ObservableList<ResultRow> getTable() {
        return table;
    }

    private ObservableList<ResultRow> table = FXCollections.observableArrayList();

    public Model() {
        Controller.setModel(this);
        this.func = (x,u) -> (x*x + u*u);
    }

    public void setAlpha(double a){
        this.a = a;
    }


    public void Calculate(double step, int points){
        table.clear();
        Picar pic = new Picar();
        for (int i = 0; i< points; ++i){
            ResultRow row = new ResultRow();
            double x = i*step;
            row.setX(x);

            pic.Calculate(x);
            row.setPicar1( pic.getResult(0));
            row.setPicar2( pic.getResult(1));
            row.setPicar3( pic.getResult(2));
            row.setPicar4( pic.getResult(3));

            if(i == 0){
                row.setExplicit(0);
                row.setImplicit(0);
                row.setRunge(0);
            }
            else {
                ResultRow tmp = table.get(i-1);
                double prevX = tmp.getX();

                row.setRunge(Runge.Calculate(this.func, this.a,
                        prevX,
                        tmp.getRunge(),
                        x));

                row.setExplicit(Explicit.Calculate(this.func,prevX,tmp.getExplicit(),x));
                row.setImplicit(Implicit.Calculate(this.func,prevX,tmp.getImplicit(),x));
            }
            table.add(row);
        }
    }
}
