package sample;

import java.util.ArrayList;

/**
 * Created by pacman29 on 19.05.17.
 */
public class Graph {
    public Graph() {
        this.solutionArray = new ArrayList<>();
    }

    public ArrayList<Solution> getSolutionArray() {
        return solutionArray;
    }

    public Graph copy(){
        Graph tmp = new Graph();
        for(Solution sol : this.solutionArray){
            tmp.getSolutionArray().add(sol.copy());
        }
        return tmp;
    }

    public void setSolutionArray(ArrayList<Solution> solutionArray) {
        this.solutionArray = solutionArray;
    }

    public Graph(ArrayList<Solution> solutionArray) {
        this.solutionArray = solutionArray;
    }

    public void initXs (double x_start, double step, int N){
        for (int i = 0; i<N; i++){
            Solution tmp = new Solution();
            tmp.setX(x_start);
            solutionArray.add(tmp);
            x_start += step;
        }
    }

    public ArrayList<Solution> solutionArray;
}
