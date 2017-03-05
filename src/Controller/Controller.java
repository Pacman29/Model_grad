package Controller;

import Model.Model;
import View.View;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

public class Controller {
    public static Model model;
    public static View view;
    public Controller() throws IOException {
        view.InitView();
        view.InitTable(model.getTable());
        view.setCntr(this);
    }

    public static void setView(View vw){
        view = vw;
    }

    public static void setModel(Model mdl){
        model = mdl;
    }

    public void calculate(double step,int points, double alpha){
        model.setAlpha(alpha);
        model.Calculate(step,points);
    }
}
