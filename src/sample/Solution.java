package sample;

/**
 * Created by pacman29 on 13.04.17.
 */
public class Solution {

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getT() {
        return t;
    }

    public void setT(Double t) {
        this.t = t;
    }

    private Double x, t;


    public Solution(Double x, Double t){
        this.x = x;
        this.t = t;
    }

    public Solution(){
        this.x = this.t = 0.;
    }
}
