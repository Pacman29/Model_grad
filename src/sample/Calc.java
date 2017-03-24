package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman29 on 24.03.17.
 */
public class Calc {
    public Calc() {
    }

    public Var getVar() {
        return var;
    }

    public void setVar(Var var) {
        this.var = var;
    }

    private Var var;

    public Double get_params(InputTable data, Double I){
        I = Math.abs(I);
        Integer count = 41;
        List<Double> temps = new ArrayList<Double>();
        Double t = 0.,m = 0.;
        t = data.get_t_t(t,m,I);
        m = data.get_t_m(t,m,I);
        Double rad = 0.;
        Double h = var.getR() / (count - 1);
        for(Integer i = 0; i< count; ++i){
            temps.add((var.getTw() - t) * Math.pow(rad/var.getR(),m) + t);
            rad += h;
        }
        Double p = this.find_pressure(data, temps);
        return this.find_resistance(data,temps,p);
    }

    private Double find_pressure(InputTable data, List<Double> temps){
        Double l = 1.;
        Double h = 1.;

        while (this.n_func(data,temps,l) * n_func(data,temps,l+h) > 0){
            l+=h;
        }

        Double r = l+h;
        Double p = (l+r)/2.;
        while (Math.abs(r-l)/p > 1e-4){
            if (this.n_func(data,temps,l) * this.n_func(data,temps,p) < 0){
                r = p;
            } else {
                l = p;
            }
            p = (l+r) / 2.;
        }

        return p;
    }

    private Double n_func(InputTable data,  List<Double> temps, Double p){
        Double r = 0.;
        Double h = var.getR() / (temps.size() - 1);
        Double sum = 0.;
        Double n;
        for (Integer i = 1; i < temps.size(); i++)
        {
            r += h;
            n = data.get_n(temps.get(i), p);
            if (i % 2 == 0)
                sum += 2. * n * r;
            else
                sum += 4. * n * r;
        }
        n = data.get_n(temps.get(temps.size() - 1), p);
        sum += n * var.getR();
        sum = sum * h / 3.;
        sum = sum * 2 / var.getR() / var.getR();
        Double pkt = var.getP0() * 7242 / var.getTstart();
        return sum - pkt;
    }

    private Double find_resistance(InputTable data,  List<Double> temps, Double p){
        Double r = 0.;
        Double h = var.getR() / (temps.size() - 1);
        Double sum = 0.;
        Double sgm = 0.;
        for (int i = 1; i < temps.size(); i++)
        {
            r += h;
            sgm = data.get_sigma(temps.get(i), p);
            if (i % 2 == 0)
                sum += 2. * sgm * r;
            else
                sum += 4. * sgm * r;
        }
        sgm = data.get_sigma(temps.get(temps.size() - 1), p);
        sum += sgm * var.getR();
        sum = sum * h / 3;
        return var.getLe() / sum / 2 / Math.PI;
    }

    private Double fi(Double I, Double Uc, Double R){
        return (Uc - (var.getRk() + R) * I) / var.getLk();
    }

    private Double fu (Double I){
        return - I /var.getCk();
    }

    public ArrayList<Solution> runge(Double step,Integer nodes,InputTable data){
        ArrayList<Solution> sln = new ArrayList<Solution>();
        Double I = var.getI0();
        Double Uc = var.getUc0();

        Double R = this.get_params(data,I);
        Double U = I * R;
        Double t = 0.;

        Double[] k = new Double[4];
        Double[] m = new Double[4];

        for (int i = 0; i < nodes; i++)
        {
            t += step;

            R = get_params(data, I);
            k[0] = fi(I, Uc, R);
            m[0] = fu(I);

            double I1 = I + step * k[0] / 2;
            double U1 = Uc + step * m[0] / 2;
            R = get_params(data, I1);
            k[1] = fi(I1, U1, R);
            m[1] = fu(I1);

            I1 = I + step * k[1] / 2;
            U1 = Uc + step * m[1] / 2;
            R = get_params(data, I1);
            k[2] = fi(I1, U1, R);
            m[2] = fu(I1);

            I1 = I + step * k[2];
            U1 = Uc + step * m[2];
            R = get_params(data, I1);
            k[3] = fi(I1, U1, R);
            m[3] = fu(I1);

            I = I + step / 6 * (k[0] + 2*k[1] + 2*k[2] + k[3]);
            Uc = Uc + step / 6 * (m[0] + 2*m[1] + 2*m[2] + m[3]);
            R = get_params(data, I);
            U = I * R;
            sln.add(new Solution(t,I,U,Uc,R));
        }
        return sln;
    }

    public ArrayList<Solution> Trapeze(Double step,Integer nodes,InputTable data){
        ArrayList<Solution> sln = new ArrayList<Solution>();
        Double Ck = var.getCk();
        Double Rk = var.getRk();
        Double Lk = var.getLk();

        Double In = var.getI0();
        Double Ucn = var.getUc0();

        Double Rn = get_params(data, In);

        Double t = 0.;

        Double U = In * Rn;

        Double I, Uc, R, In1;

        for (int i = 0; i < nodes; i++)
        {
            t += step;
            Rn = get_params(data, In);
            I = (-2*Ck*Rn*In*step + 4*Ck*In*Lk - 2*Ck*In*Rk*step + 4*Ck*Ucn*step - In*step*step)/
                    (4*Ck*Lk + 2*Ck*Rk*step + 2*Ck*Rn*step + step*step);
            do
            {
                In1 = I;
                R = get_params(data, In1);
                I = (-2*Ck*Rn*In*step + 4*Ck*In*Lk - 2*Ck*In*Rk*step + 4*Ck*Ucn*step - In*step*step)/
                        (4*Ck*Lk + 2*Ck*Rk*step + 2*Ck*R*step + step*step);
            } while (Math.abs(In1 - I) / I > 1e-5);
            Uc = Ucn - step * (In + I) / 2 / Ck;

            R = get_params(data, I);
            U = I * R;

            sln.add(new Solution(t,I,U,Uc,R));

            In = I;
            Ucn = Uc;


        }

        return sln;
    }
}

