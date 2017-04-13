package sample;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by pacman29 on 13.04.17.
 */
public class Calculator {
    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getKa() {
        return ka;
    }

    public void setKa(Double ka) {
        this.ka = ka;
    }

    public Double getKb() {
        return kb;
    }

    public void setKb(Double kb) {
        this.kb = kb;
    }

    public Double getR() {
        return R;
    }

    public void setR(Double r) {
        R = r;
    }

    public Double getS0() {
        return s0;
    }

    public void setS0(Double s0) {
        this.s0 = s0;
    }

    public Double getM() {
        return m;
    }

    public void setM(Double m) {
        this.m = m;
    }

    public Double getA0() {
        return a0;
    }

    public void setA0(Double a0) {
        this.a0 = a0;
    }

    public Double getaN() {
        return aN;
    }

    public void setaN(Double aN) {
        this.aN = aN;
    }

    public Double getL() {
        return l;
    }

    public void setL(Double l) {
        this.l = l;
    }

    public Double getTenv() {
        return Tenv;
    }

    public void setTenv(Double tenv) {
        Tenv = tenv;
    }

    public Double getF0() {
        return F0;
    }

    public void setF0(Double f0) {
        F0 = f0;
    }

    private Double a,b,ka,kb,
    R, s0, m, a0, aN, l, Tenv, F0;

    private class tridiagonal{
        public Double getK0() {
            return K0;
        }

        public void setK0(Double k0) {
            K0 = k0;
        }

        public Double getM0() {
            return M0;
        }

        public void setM0(Double m0) {
            M0 = m0;
        }

        public Double getP0() {
            return P0;
        }

        public void setP0(Double p0) {
            P0 = p0;
        }

        public Double getKN() {
            return KN;
        }

        public void setKN(Double KN) {
            this.KN = KN;
        }

        public Double getMN() {
            return MN;
        }

        public void setMN(Double MN) {
            this.MN = MN;
        }

        public Double getPN() {
            return PN;
        }

        public void setPN(Double PN) {
            this.PN = PN;
        }

        public Double[] getA() {
            return A;
        }

        public void setA(Double[] a) {
            A = a;
        }

        public Double[] getB() {
            return B;
        }

        public void setB(Double[] b) {
            B = b;
        }

        public Double[] getC() {
            return C;
        }

        public void setC(Double[] c) {
            C = c;
        }

        public Double[] getF() {
            return F;
        }

        public void setF(Double[] f) {
            F = f;
        }

        private Double K0, M0, P0,
                       KN, MN, PN;

        private Double[] A,B,C,F;

        public tridiagonal(int N){
            this.A = new Double[N];
            this.B = new Double[N];
            this.C = new Double[N];
            this.F = new Double[N];

        }

        public void solve(ArrayList<Solution> sol){
            int n = sol.size()-1;
            Double[] ksi = new Double[n+1],
                           eta = new Double[n+1];

            ksi[1] = -this.M0 / this.K0;
            eta[1] = this.P0 / this.K0;

            for(int i = 2; i<=n; ++i){
                ksi[i] = this.C[i-1]/ (this.B[i-1] - this.A[i-1] * ksi[i-1]);
                eta[i] = (this.F[i-1] + this.A[i-1]*eta[i-1]) / (this.B[i-1] - this.A[i-1] * ksi[i-1]);
            }

            sol.get(n).setT((this.PN - this.MN * eta[n]) / (this.KN + this.MN * ksi[n]));

            for(int i = n-1; i>= 0; i--){
                sol.get(i).setT(ksi[i+1]*sol.get(i+1).getT() + eta[i+1]);
            }
        }
    }

    private tridiagonal tridiag;

    public Calculator(){
    }

    public ArrayList<Solution> calculate(int N){
        this.tridiag = new tridiagonal(N);
        ArrayList<Solution> results = new ArrayList<Solution>();
        this.b = this.aN * this.l / (this.aN - this.a0);
        this.a = - this.a0 * this.b;
        for(int i = 0; i<=N; ++i){
            results.add(new Solution(0.,500.));
        }

        Double Told = 500.;
        Double h = this.l / (N - 1);



        do {
            Told = results.get(0).getT();
            Double hi12 = hi(results.get(0).getT(),results.get(1).getT());
            Double p12 = (pf(0., R) + pf(h, R)) / 2.;
            this.tridiag.setK0(hi12 + h * h * p12 / 8. + h * h * pf(0.,R) / 4.);
            this.tridiag.setM0(h*h*p12 / 8. - hi12);
            this.tridiag.setP0(h * F0 + h * h * (3. * f(0., Tenv, R) + f(h, Tenv, R)) / 8.);


            this.tridiag.setKN(- (hi(results.get(N-1).getT(), results.get(N).getT()) + alpha(l) * h) / h
                    - h * (5.*pf(l, R) + pf(l-h, R)) / 16.);
            this.tridiag.setMN(hi(results.get(N-1).getT(), results.get(N).getT()) / h
                    - h * (pf(l, R) + pf(l-h, R)) / 16.);
            this.tridiag.setPN( - alpha(l) * Tenv - h * (3.*f(l, Tenv, R) + f(l-h,Tenv, R)) / 8.);

            Double x = h;
            for(int i = 1; i < N; i++){
                results.get(i).setX(x);
                this.tridiag.getA()[i] = hi(results.get(i-1).getT(),results.get(i).getT())/h;
                this.tridiag.getC()[i] = hi(results.get(i).getT(),results.get(i+1).getT())/h;
                this.tridiag.getB()[i] = this.tridiag.getA()[i] + this.tridiag.getC()[i]+pf(x,R)*h;
                this.tridiag.getF()[i] = f(x,Tenv,R)*h;
                x+= h;
            }
            this.tridiag.solve(results);
        } while (Math.abs(results.get(0).getT() - Told) / results.get(0).getT() > 1e-5);
        results.remove(results.size()-1);
        return results;
    }

    Double k(Double t){
        return this.s0 * Math.pow(t/300., this.m );
    }

    Double alpha(Double x){
        return this.a / (x - this.b);
    }

    Double hi(Double t1, Double t2)
    {
        return 2. * k(t1) * k(t2) / (k(t1) + k(t2));
    }

    Double f(Double x, Double t, Double r)
    {
        return 2. * alpha(x) * t / r;
    }

    Double pf(Double x, Double r)
    {
        return 2. * alpha(x) / r;
    }
}
