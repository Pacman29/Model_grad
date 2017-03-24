package sample;

/**
 * Created by pacman29 on 24.03.17.
 */
public class Solution {
    public Solution(Double t, Double i, Double u, Double uc, Double r) {
        this.t = t;
        I = i;
        U = u;
        Uc = uc;
        R = r;
    }

    public Solution() {
    }

    public Double getT() {
        return t;
    }

    public void setT(Double t) {
        this.t = t;
    }

    public Double getI() {
        return I;
    }

    public void setI(Double i) {
        I = i;
    }

    public Double getU() {
        return U;
    }

    public void setU(Double u) {
        U = u;
    }

    public Double getUc() {
        return Uc;
    }

    public void setUc(Double uc) {
        Uc = uc;
    }

    public Double getR() {
        return R;
    }

    public void setR(Double r) {
        R = r;
    }

    private Double t;
    private Double I;
    private Double U;
    private Double Uc;
    private Double R;
}
