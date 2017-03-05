package Model;

public class Runge {
    public static double Calculate(Func f, double alpha,double prevX, double prevY, double x) {
        double step = Math.abs(x-prevX);

        return  prevY + step * ((1 - alpha) * f.calculate(prevX, prevY) + alpha *
                f.calculate(prevX + step / (2 * alpha), prevY + step / (2 * alpha) * f.calculate(prevX, prevY)));

    }
}
