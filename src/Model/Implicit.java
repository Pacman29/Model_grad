package Model;

/**
 * Created by pacman29 on 05.03.17.
 */

import static java.lang.Math.sqrt;

public class Implicit {
    public static double Calculate(Func f,double prevX, double prevY, double x){
        double step = Math.abs(x - prevX);
        return (1 - sqrt(1 - 4 * step * (prevY + step*x*x))) / 2 / step;
    }
}
