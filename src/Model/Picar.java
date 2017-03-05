package Model;

/**
 * Created by pacman29 on 04.03.17.
 */
public class Picar{
    private double[] pic = new double[4];

    public Picar() {
    }


    private double Calculate1(double x) {

        return x*x*x / 3.0;
        }

    private double Calculate2(double x) {

        return this.pic[0] * (1.0 + x*this.pic[0]/7.0);
        }

    private double Calculate3(double x) {

        return this.pic[1] + Math.pow(this.pic[0],3.0)*(2*x*x/77.0 +
                this.pic[0]*this.pic[0]/245.0);
    }


    private double Calculate4(double x) {
        return this.pic[2] + Math.pow(this.pic[0], 4.0) *
                (this.pic[1]*(4/385.0 + 2*x*this.pic[0]/5635)
                        + this.pic[0]*this.pic[0]*(8*x/107065.0
                                + this.pic[0]*(12*x*x/136367.0
                                    + this.pic[0]*this.pic[0]*(4/769735.0
                                        + x*this.pic[0]/1860755.0))));
    }


    public void Calculate(double x){
        pic[0] = Calculate1(x);
        pic[1] = Calculate2(x);
        pic[2] = Calculate3(x);
        pic[3] = Calculate4(x);
    }

    double getResult(int i){
        return pic[i];
    }

}
