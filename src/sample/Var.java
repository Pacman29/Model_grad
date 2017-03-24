package sample;

/**
 * Created by pacman29 on 24.03.17.
 */
public class Var {
    private double Rk; // Ом
    private double I0; // А
    private double Lk; // Гн
    private double Ck; // Ф
    private double Uc0; // В
    private double R; // см
    private double P0; // атм
    private double Tstart; // К
    private double Tw; // К
    private double le; // см

    public Var() {
    }

    public Var(double rk, double i0, double lk, double ck, double uc0, double r, double p0, double tstart, double tw, double le) {
        Rk = rk;
        I0 = i0;
        Lk = lk;
        Ck = ck;
        Uc0 = uc0;
        R = r;
        P0 = p0;
        Tstart = tstart;
        Tw = tw;
        this.le = le;
    }

    public double getRk() {
        return Rk;
    }

    public void setRk(double rk) {
        Rk = rk;
    }

    public double getI0() {
        return I0;
    }

    public void setI0(double i0) {
        I0 = i0;
    }

    public double getLk() {
        return Lk;
    }

    public void setLk(double lk) {
        Lk = lk;
    }

    public double getCk() {
        return Ck;
    }

    public void setCk(double ck) {
        Ck = ck;
    }

    public double getUc0() {
        return Uc0;
    }

    public void setUc0(double uc0) {
        Uc0 = uc0;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public double getP0() {
        return P0;
    }

    public void setP0(double p0) {
        P0 = p0;
    }

    public double getTstart() {
        return Tstart;
    }

    public void setTstart(double tstart) {
        Tstart = tstart;
    }

    public double getTw() {
        return Tw;
    }

    public void setTw(double tw) {
        Tw = tw;
    }

    public double getLe() {
        return le;
    }

    public void setLe(double le) {
        this.le = le;
    }
}
