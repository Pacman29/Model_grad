package sample;

import java.util.ArrayList;

/**
 * Created by pacman29 on 19.05.17.
 */
public class ParsTable {
    private ArrayList<ArrayList<Double>> table;

    public ParsTable(ArrayList<ArrayList<Double>> table) {
        this.table = table;
    }

    public ParsTable() {
    }

    private ArrayList<Double> getarrayT(){
        return table.get(0);
    }

    private ArrayList<Double> getarrayC(){
        return table.get(1);
    }

    private ArrayList<Double> getarrayK(){
        return table.get(2);
    }


    public ArrayList<ArrayList<Double>> getTable() {
        return table;
    }

    public void setTable(ArrayList<ArrayList<Double>> table) {
        this.table = table;
    }

    public double get_C(double t){
        int ind = this.getIndex(t);
        return (this.getarrayC().get(ind-1) + (this.getarrayC().get(ind) - this.getarrayC().get(ind-1))
        * (t - this.getarrayT().get(ind - 1)) / (this.getarrayT().get(ind) - this.getarrayT().get(ind - 1)));
    }

    public double get_K(double t){
        int ind = this.getIndex(t);
        return (this.getarrayK().get(ind-1) + (this.getarrayK().get(ind) - this.getarrayK().get(ind-1))
                * (t - this.getarrayT().get(ind - 1)) / (this.getarrayT().get(ind) - this.getarrayT().get(ind - 1)));
    }

    private int getIndex(double t){
        int ind = 0;
        if(t <= this.getarrayT().get(0)){
            ind = 1;
        } else if (t > this.getarrayT().get(this.getarrayT().size()-1)) {
            ind = this.getarrayT().size()-1;
        } else {
            for(;this.getarrayT().get(ind) < t; ind++);
        }
        return ind;
    }
}
